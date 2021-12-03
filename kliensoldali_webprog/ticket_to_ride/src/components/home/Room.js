import { useState } from "react";
import socket from "../../socket/socket";
import { useHistory } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { countPlayers, getPlayers, getRoomCode } from "../../redux/selectors";
import { startGame, modifyPlayerCount, joinRoom, createRoom, modifyPlayerName } from "../../redux/actions";

const Room = () => {

  const [name, setName] = useState("");
  const [roomId, setRoomId] = useState("");

  const history = useHistory();

  const players = useSelector(getPlayers);
  const playerNumber = useSelector(countPlayers);

  const dispatch = useDispatch();

  const connectRoom = (e) => {
    
    e.preventDefault();

    socket.emit("join-room", roomId, (ack) => {

      if (ack.status === "ok") {
          socket.emit("get-state", roomId, (stateAck) => {
            console.log('roomid',roomId)
            if (stateAck.status === "ok") {
              const state = JSON.parse(stateAck.state); 
              const syncRoomInfo = {
                players: state.players,
                maxPlayers: state.maxPlayers,
                code: state.code,
                name,
              };

              dispatch(joinRoom(syncRoomInfo));
              dispatch(modifyPlayerName(name));
              dispatch(startGame({players}));

              history.push("/waiting-room");
            }
          });
      } else {
        alert(ack.message + " " + roomId);
      }
    })
  };

  const setPlayerName = (name, index) => {
    setName(name);
    players[index].name = name;
  }

  const addPlayer = () => {
    if (playerNumber < 5) {
      dispatch(modifyPlayerCount(1));
    }
  };
  const removePlayer = () => {
    if (playerNumber > 1) {
      dispatch(modifyPlayerCount(-1));
    }
  };

  const handleCreate = (e) => {
    e.preventDefault();
        socket.emit("create-room", playerNumber , (ack) => {
          if (ack.status === "ok") {
            dispatch(modifyPlayerName(name));
            dispatch(createRoom({ name, roomsize: playerNumber, roomcode: ack.roomId }));
            dispatch(startGame({players}));

            history.push("/waiting-room");
          } else {
            alert("error creating room");
          }
      });
  };

  return (
    <div className="room">
      <div className="room-container">
      <form className="room_create" onSubmit={handleCreate}>
        <div className="room-description">Új szoba létrehozása</div>
        <div>Játékosok száma:</div>
        <div className="players">
          <div className="room-players-count">{playerNumber}</div>
          <button
            className="custom-btn add-players room-item"
            onClick={addPlayer}
          >+</button>
          <button
              className="custom-btn remove-players room-item"
              onClick={removePlayer}
            >-</button>
        </div>
        <div className="input"  key={`player-input`}>
            <label className="label" key={`player-input`}>Név: </label>
            <input
              id={`name`}
              type="text"
              required
              maxLength={20}
              className="custom-input room-item"
              placeholder={`Játékos1`}
              onChange={(e) => { setPlayerName(e.target.value, 0); }}
            />
        </div>
       {/*  <div>
          <label className="label" key={`room-input`}>Szobakód: </label>
          <input
            type="text"
            maxLength={7}
            value={roomId}
            onChange={(e) => {setRoomId(e.target.value);}}
            className="custom-input room-item"
          />
        </div> */}
        <button type="submit" className="custom-btn room-item">
          Szoba Létrehozása
        </button>
      </form>
      </div>

      <div className="room-container">
      <form className="room_connect" onSubmit={connectRoom}>
        <div className="room-description">Csatlakozás a szobához</div>
        <div className="room-connect">
          <label className="player-label">
            Név: 
          </label>
          <input
            id="name"
            type="text"
            required
            maxLength={20}
            placeholder="Játékos2"
            className="custom-input room-item"
          />
        </div>
        <div className="room-id-container">
          <label className="room-label">
            Azonosító: 
          </label>
          <input
            id="room-id"
            type="text"
            maxLength={20}
            onChange={(e) => {setRoomId(e.target.value);}}
            className="custom-input room-item"
          />
        </div>
        <button
          type="submit"
          className="custom-btn room-item"
        >
        Csatlakozás
      </button>
    </form>
    </div>
    
  </div>
  );
};

export default Room;
