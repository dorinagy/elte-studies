import React from "react";
import DestinationDeck from "./DestinationDeck";
import TrainDeck from "./TrainDeck";
import "./Deck.css";

function Deck() {
  return (
    <div className="game-deck">
      <DestinationDeck/>
      <TrainDeck/>
    </div>
  );
}

export default Deck;
