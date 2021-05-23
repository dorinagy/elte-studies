import { useSelector } from "react-redux";
import { getCurrentPlayer} from "../../../../redux/selectors";

import "./ActPlayer.css";

function ActPlayer() {

  const player = useSelector(getCurrentPlayer);

  return (
   <div className="act-player">
      <h1>Aktuális játékos:</h1>
      <div><strong>Név: {player.name}</strong></div>
      <br/>
      <div className="game-destinations">
        <div className="dest-title"><strong>Rövid úticélok: </strong></div>
        {player.shortDestCards.map((card, index) => (
          <div key={index}>{card.fromCity} - {card.toCity}</div>
        ))}
        <br/>
        <div className="dest-title"><strong>Hosszú úticélok: </strong></div>
        {player.longDestCards.map((card, index) => (
          <div key={index}>{card.fromCity} - {card.toCity}</div>
        ))}
      </div>
      <br/>
      <div><strong>Kártyák: </strong></div>
      <div>Rövid úticél kártyák: {player.shortDestCards.length}</div>
      <div>Hosszú úticél kártyák: {player.longDestCards.length}</div>
      <div>Vasúti kártyák: {player.wagonCards.length}</div>
      <br/>
      <div><strong>Utolsó 2 lépés: </strong></div>
      <div>{player.moves[player.moves.length-1]}</div>
      <div>{player.moves[player.moves.length-2]}</div>
    </div>
  );
}

export default ActPlayer;
