import { getSource } from "../../../../ImageLoader";
import { useDispatch, useSelector } from "react-redux";
import { drawCard, drawFromDeck } from "../../../../redux/actions";
import { getCurrentPlayer } from "../../../../redux/selectors";


function Card(properties) {

  const index = properties.clicked;

  const isDeck = properties.isDeck;
  const isTable = properties.isTable;

  const currentPlayer = useSelector(getCurrentPlayer);

  const cards = useSelector(getCurrentPlayer)?.wagonCards?.length;

  const dispatch = useDispatch();

  const onClick = () => {
    if (cards && cards < 9) {
      if (isDeck) {
        console.log('draw card from deck');
        dispatch(drawFromDeck({player: currentPlayer }));
      } else if (isTable) {
        console.log('draw card');
        dispatch(drawCard({player: currentPlayer, number: index}));
      }
  }
  }

  const getImageScource = (name) => {
    if (typeof name === undefined || !name) name =  'white';
    return getSource(name);
  }

  return (
    <div className="train-ticket" onClick={onClick}>
      <img src={getImageScource(properties.type)} alt={`${properties.type} train ticket`} />
    </div>
  );
}

export default Card;
