import React from "react";
import Card from "./Card";
import { useSelector } from "react-redux";
import { getOnFieldCards } from "../../../../redux/selectors";

function TrainDeck() {

  const isTable = true;
  const isDeck = true;

  const onFieldCards = useSelector(getOnFieldCards);

  return (
    <div className="train-deck">
      {onFieldCards && onFieldCards.map((card, index) => (
            <Card
                type={card.type}
                key={index}
                clicked={index}
                isTable={isTable}
            />
        ))}
      <Card type="train_ticket_back" isDeck={isDeck}/>
    </div>
  );
}

export default TrainDeck;
