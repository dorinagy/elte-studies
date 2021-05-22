import { useEffect, useState } from "react";
import { useDispatch, useSelector, useStore } from "react-redux";
import { changeToPlayer1, changeToPlayer2 } from "../../../../redux/actions";

function Player(properties) {

  const store = useStore();
  const dispatch = useDispatch();

  useEffect(() => {
    console.log(store.getState());
  }, [store]);
  
  const players = useSelector((state) => state.players);

  const clickOnPlayer1 = () => {
    dispatch(changeToPlayer1({players}));
  }
  const clickOnPlayer2 = () => {
      dispatch(changeToPlayer2({players}));
  }

  let color = '#FFC13E';
  if (properties.isSelected) {
    color = '#FF8D3E'
  }

  const style = { backgroundColor: color }

  return (
    <div 
      className="player" 
      style={style}
      onClick={properties.index == 0 ? clickOnPlayer1 : clickOnPlayer2}
    >
    <div className="player-name">{properties.name}</div>
    <div className="player-info">
      <div className="column">
        <div className="player-points">points: {properties.points}</div>
        <div className="player-vagons">vagons: {properties.vagons}</div>
      </div>
      <div className="column">
        <div className="player-cards">cards: {properties.cards}</div>
        <div className="player-destinations">destination: {properties.destination}</div>
      </div>
      <div className="column">
        <div className="player-round">round: {properties.round}</div>
      </div>
    </div>
    </div>
 )
}

export default Player
