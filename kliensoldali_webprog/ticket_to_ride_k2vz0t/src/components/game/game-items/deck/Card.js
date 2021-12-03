import { getSource } from "../../../../ImageLoader";
import { useDispatch, useSelector } from "react-redux";
import { drawCard, drawFromDeck } from "../../../../redux/actions";
import { getCurrentPlayer, getClientPlayer } from "../../../../redux/selectors";


function Card(properties) {

  const index = properties.clicked;

  const isDeck = properties.isDeck;
  const isTable = properties.isTable;

  const currentPlayer = useSelector(getCurrentPlayer);
  const clientPlayer = useSelector(getClientPlayer);

  const cards = useSelector(getClientPlayer)?.wagonCards?.length;

  const dispatch = useDispatch();

  const onClick = () => {
    if (cards && cards < 9 && currentPlayer.name === clientPlayer.name) {
      if (isDeck) {
        console.log('draw card from deck');
        dispatch(drawFromDeck({player: clientPlayer }));
      } else if (isTable) {
        console.log('draw card');
        dispatch(drawCard({player: clientPlayer, number: index}));
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
