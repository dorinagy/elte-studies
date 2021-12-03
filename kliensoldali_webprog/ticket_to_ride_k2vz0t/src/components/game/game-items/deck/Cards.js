import { useSelector } from "react-redux";
import { getUserWagons } from "../../../../redux/selectors";
import Card from "./Card";

import "./Deck.css";

function Cards() {

  const cards = useSelector(getUserWagons);

  return (
    <div className="cards">
      {cards ? cards.map((card, i) =>
        <Card type={cards[i].type} key={i}/>
      ) : ''}
    </div>
  );
}

export default Cards;
