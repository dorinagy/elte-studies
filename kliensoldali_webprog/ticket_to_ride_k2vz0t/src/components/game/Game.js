import { Redirect } from "react-router-dom";
import React, { useState, useEffect } from "react";

import ActPlayer from "./game-items/player/ActPlayer";
import Players from "./game-items/player/Players";
import Cards from "./game-items/deck/Cards";
import Deck from "./game-items/deck/Deck";
import Map from "./game-items/map/Map";

import "./Game.css";
import "./game-items/player/Players.css";

function Game() {

  useEffect(() => {
    window.onbeforeunload = function () {
        console.log("If you refresh the page game data will be lost.");
        return true;
    };

    return () => {
        window.onbeforeunload = null;
    };
  }, []);

  const [goBack, setGoBack] = useState(false);

  const ReturnHome = event => {
    event.preventDefault();
    setGoBack(true);
  };

  if (goBack) {
    return (
      <Redirect push to={{ pathname: "/" }} />
    );
  }

  return (
    <div className="game">
      <div className="players-area">
        <Players/>      {/* játékosok */}
        <ActPlayer/>    {/* aktuális játékos */}
      </div>
      <div className="game-area">
        <Map/>          {/* térkép */}
        <Cards/>        {/* játékos kártyái */}
      </div>
      <div className="deck-area">
        <Deck/>         {/* vasútikocsi kártyák + úticél kártyák */}
        <button className="custom-btn" onClick={ReturnHome}>
            Kilépés
        </button>
      </div>
    </div>
  );
}

export default Game;
