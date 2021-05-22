import React from "react";
import Card from "./Card";

function TrainDeck() {
  return (
    <div className="train-deck">
      <Card type="black"/>
      <Card type="orange"/>
      <Card type="black"/>
      <Card type="blue"/>
      <Card type="purple"/>
      <Card type="train_ticket_back"/>
    </div>
  );
}

export default TrainDeck;
