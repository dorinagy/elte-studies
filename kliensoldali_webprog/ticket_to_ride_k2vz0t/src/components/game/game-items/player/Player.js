
function Player(properties) {

  let color = '#FFC13E';
  if (properties.isSelected) {
    color = '#FF8D3E'
  }

  const style = { backgroundColor: color }

  return (
    <div 
      className="player" 
      style={style}
    >
    <div className="player-name">{properties.name}</div>
    <div className="player-info">
      <div className="column">
        <div className="player-points">points: {properties.points}</div>
        <div className="player-wagons">wagons: {properties.wagons}</div>
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
