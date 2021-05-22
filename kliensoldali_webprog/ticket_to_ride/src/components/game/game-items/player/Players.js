import { useSelector } from "react-redux";
import { getPlayers } from "../../../../redux/selectors";
import Player from "./Player";

import "./Players.css";

function Players() {

  const players = useSelector(getPlayers);

  return (
    <div className="game-players">
      <div className="title">Játékosok</div>
      {players.map((player, index) => ( 
        <Player 
          name={player.name} 
          points={player.points}  
          vagons={player.vagons} 
          cards={player.cards} 
          destination={player.destinations} 
          round={player.round} 
          key={index}
          index={index}
          isSelected={player.isSelected}
        />
      ))}
    </div>
  );
}

export default Players;
