import React, { useState } from "react";
import { Redirect } from "react-router-dom";

import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import { initGame } from "../../redux/actions";
import { getMaxPlayers, getPlayers, countPlayers, getRoomCode } from "../../redux/selectors";

import "./WaitingRoom.css";

function Wait() {

  const players = useSelector(getPlayers);
  const playersCount = useSelector(countPlayers);
  let maxPlayers = useSelector(getMaxPlayers);
  const history = useHistory();

  const code = useSelector(getRoomCode);

  if (!maxPlayers) maxPlayers = 2;

  const dispatch = useDispatch();

  const enterGame = () => {
      dispatch(initGame({players}));
      history.push("/game");
  };
  
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
