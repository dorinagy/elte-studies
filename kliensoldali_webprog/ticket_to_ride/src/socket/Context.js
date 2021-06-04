import io from "socket.io-client";
import { isFunction } from "lodash";
import { createContext } from "react";
import { useDispatch } from "react-redux";
import { syncState } from "./redux/actions";
import { withRouter } from "react-router-dom";
import React, { useEffect, useState } from "react";
import handleJoinRoomThunk from "./redux/thunks/socket/handleJoinRoomThunk";
import handleInitGameThunk from "./redux/thunks/socket/handleInitGameThunk";
import handlePlayerLeftThunk from "./redux/thunks/socket/handlePlayerLeftThunk";

export const SocketContext = createContext(null);

export const MESSAGES = {
  STATE_CHANGED: "state-changed",
  ROOM_IS_FULL: "room-is-full",
  PLAYER_LEFT: "player-left",
  SYNC_STATE: "sync-state",
  LEAVE_ROOM: "leave-room",
  CREATE_ROOM: "create-room",
};

export const socket = io("http://webprogramozas.inf.elte.hu:3031");
export let roomId = null;

export const SocketContextProvider = withRouter(({ children, history }) => {
  const dispatch = useDispatch();
  const [roomId, setRoomId] = useState(null);
  const [isGameStarted, setIsGameStarted] = useState(false);
  const [playerIndex, setPlayerIndex] = useState(null);

  const registerListeners = (socket) => {
    socket.on(MESSAGES.STATE_CHANGED, ({ roomId: _, state }) => {
      dispatch(syncState(state));
    });

    // props: roomId, player, state
    socket.on(MESSAGES.ROOM_IS_FULL, ({ player }) => {
      setIsGameStarted(true);
      setTimeout(() => history.push("/"), 3000); // TODO: game starting in 3 secs alert..
      setPlayerIndex(player - 1);
    });

    socket.on(MESSAGES.PLAYER_LEFT, ({ roomId, socketId }) => {
      dispatch(handlePlayerLeftThunk(socket, roomId, socketId));
    });
  };

  useEffect(() => {
    registerListeners(socket);
  }, []);

  const leaveRoom = () => socket.emit(MESSAGES.LEAVE_ROOM, roomId);

  const createRoom = (roomSize, name, cb) => {
    socket.emit(MESSAGES.CREATE_ROOM, roomSize, ({ status, roomId }) => {
      if (status !== "ok") return;
      setRoomId(roomId);
      roomId = roomId;
      dispatch(handleInitGameThunk(name, socket, roomId));
      if (isFunction(cb)) cb();
    });
  };

  const joinRoom = (roomId, name, cb) => {
    socket.emit("join-room", roomId, ({ status, state }) => {
      if (status !== "ok") return;
      setRoomId(roomId);
      roomId = roomId;
      const parsedState = JSON.parse(state);
      dispatch(syncState(parsedState));
      dispatch(handleJoinRoomThunk(socket, name, roomId, cb));
    });
  };

  return (
    <SocketContext.Provider
      value={{
        createRoom,
        joinRoom,
        isInRoom: roomId !== null,
        roomId,
        leaveRoom,
        isGameStarted,
        playerIndex,
      }}
    >
      {children}
    </SocketContext.Provider>
  );
});
