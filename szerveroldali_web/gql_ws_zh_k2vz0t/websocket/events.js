const faker = require("faker");
const Joi = require("joi");
const { v4: uuidv4 } = require('uuid');
const validate = require("./validate");

// Socket.IO emit cheat sheet: https://socket.io/docs/v4/emit-cheatsheet/
// Joi dokumentáció, példák: https://joi.dev/api/?v=17.5.0#general-usage

let db = {
    rooms: {
        room1: {
            admin: "socket1",
            members: ["socket1", "socket2", "socket3"],
            muted: ["socket2"],
            messages: [
                { timestamp: 123456789, client: "socket1", message: "sziasztok" },
                { timestamp: 123456789, client: "socket3", message: "hali" },
            ],
        },
        "Másik szoba": {
            admin: "socket2",
            members: ["socket2"],
            muted: [],
            messages: [{ timestamp: 123456789, client: "socket2", message: "foreveralone" }],
        },
    },
};

module.exports = {
    db,
    events: (io) => {
        io.on("connection", (socket) => {
            socket.on("test", (data, ack) => {
                socket.emit("test-client", "uzenet a szerverrol");
                ack({
                    status: "ok",
                });
            });

            socket.on(
                "number",
                validate(
                    Joi.object({
                        number: Joi.number().integer().required(),
                    }),
                    ({ number }, ack) => {
                        ack({
                            number,
                            status: "ok",
                        });
                    }
                )
            );

            socket.on("list-rooms", (_, ack) => ack({ status: "ok", rooms: Object.keys(db.rooms) }));
            socket.on(
                "create-room",
                validate(
                    Joi.object({
                        room: Joi.string().trim().min(1).required(),
                    }),
                    ({ room }, ack) => {
                        const uuid = uuidv4();

                        if (db.rooms[room]) throw new Error("This name is already taken!");

                        db.rooms[room] = {
                            uuid,
                            socketId: socket.id,
                        };
                        ack({ status: "ok" });
                    }
                )
            );

            socket.on(
                "join-room",
                validate(
                    Joi.object({
                        uuid: Joi.string().guid({
                            version: ["uuidv4"],
                        }),
                    }),
                    ({ uuid }, ack) => {
                        let room = null;
                        for (const [name, details] of Object.entries(db.rooms)) {
                            if (details.uuid === uuid) {
                                room = { name, ...details };
                                break;
                            } 
                        }

                        if (!room) throw new Error("No such room in our system!");

                        ack({ status: "ok", uuid });
                    }
                )
            );

            socket.on(
                "mute-client",
                validate(
                    Joi.object({
                        room: Joi.string().trim().min(1).required(),
                        clientId: Joi.string().guid({
                            version: ["uuidv4"],
                        }),
                        reason: Joi.string().trim(),
                    }),
                    ({ room, clientId, reason }, ack) => {
                        
                        if (!db.rooms[room])  throw new Error("No such room in our system!");
                        

                        ack({ status: "ok", });
                    }
                )
            );

            socket.on(
                "send-message",
                validate(
                    Joi.object({
                        room: Joi.string().trim().min(1).required(),
                        message: Joi.string().trim().min(1).required()
                    }),
                    ({ room, message }, ack) => {

                        if(!db.rooms[room]) throw new Error("No such room in our system!");

                        io.to(db.rooms[room].socketId).emit("solution-sent", {
                            room,
                            client: db.rooms[room].socketId,
                            message
                        });
                        ack({
                            status: "ok",
                        });
                    }
                )
            );
        });
    },
};
