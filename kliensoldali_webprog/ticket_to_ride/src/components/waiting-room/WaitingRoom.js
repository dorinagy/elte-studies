import React from "react";
import { Redirect } from "react-router-dom";

import { getMaxPlayers, countPlayers } from "../../redux/selectors";

import { SocketContext } from "../../socket/Context";

import "./WaitingRoom.css";

function WaitingRoom() {

  const { roomId, isInRoom, leaveRoom } = useContext(SocketContext);

  const playersCount = useSelector(countPlayers);
  let maxPlayers = useSelector(getMaxPlayers);

  if (!isInRoom) return <Redirect to="/" />;

  if (!maxPlayers) maxPlayers = 2;

  return (
    <div className="waiting-room">
      <h1>KÓD: {roomId}</h1><br/>
      <div>Waiting for other players to connect.</div>
      <div>{playersCount} / {maxPlayers} players have joined.</div>
      <div className="btn-container">
        <button className="custom-btn" onClick={leaveRoom}>
          Vissza
        </button>
      </div>
    </div>
  );
}

export default WaitingRoom;
