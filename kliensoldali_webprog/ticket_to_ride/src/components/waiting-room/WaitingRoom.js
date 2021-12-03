import React, { useState } from "react";
import { initGame } from "../../redux/actions";
import { useDispatch, useSelector } from "react-redux";
import { Redirect, useHistory } from "react-router-dom";
import { getClientPlayer, getMaxPlayers, getPlayers, getRoomCode } from "../../redux/selectors";

import socket from "../../socket/socket";

import "./WaitingRoom.css";

function Wait() {

  const players = useSelector(getPlayers);

  const playersCount = Object.values(players).filter(player => player.name).length;

  let maxPlayers = useSelector(getMaxPlayers);

  //const clientPlayer = useSelector(getClientPlayer);

  const history = useHistory();

  const code = useSelector(getRoomCode);

  if (!maxPlayers) maxPlayers = 2;

  const dispatch = useDispatch();

  const enterGame = () => {

    alert("A játék automatikusan elindul ha minden játékos csatlakozott!");

    return;

    /* if (players && players[0].name !== clientPlayer?.name) {
      
      return;
    }

    dispatch(initGame(players));

    socket.emit("close-room", code, (ack) => {
      if (ack.status === "error") {
        alert(ack.message);
      }
    });

    history.push("/game"); */
  };

  if (playersCount === maxPlayers) {
    history.push("/game");
  }

  socket.on("room-is-full", (res) => {
    socket.emit("get-state", code, (stateAck) => {
      if (stateAck.status === "ok") {
        const state = JSON.parse(stateAck.state);
        if (state.gameStatus !== 'IN_GAME') {
          dispatch(initGame(players));
        }
        history.push("/game");
      }
    });
  });
  
  const [goBack, setGoBack] = useState(false);

  const ReturnHome = event => {
    event.preventDefault();
    setGoBack(true);
  };

    if (goBack) {
      return (<Redirect push to={{ pathname: "/" }} />);
    }

    return (
      <div className="waiting-room">
        <h1>KÓD: {code}</h1><br/>
        <div>Waiting for other players to connect.</div>
        <div>{playersCount} / {maxPlayers} players have joined.</div>

        <div className="btn-container">
          {/* @todo: remove */}
          <button className="custom-btn" onClick={enterGame}>
            Tovább a játékra
          </button>

          <button className="custom-btn" onClick={ReturnHome}>
            Vissza
          </button>
        </div>
    </div>
    );
}

export default Wait;
