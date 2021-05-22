import { getSource } from "../../../../ImageLoader";

function Card(properties) {
  return (
    <div className="train-ticket">
      <img src={getSource(properties.type)} alt="train ticket" />
    </div>
  );
}

export default Card;
