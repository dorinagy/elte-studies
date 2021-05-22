import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import { startGame, modifyPlayerCount } from "../../redux/actions";
import { countPlayers } from "../../redux/selectors";

const Room = () => {

  const history = useHistory();

  const connectRoom = () => {
    dispatch(startGame("START_GAME"));
    history.push("/waiting-room")
  };

  let name = "";
  const playerNumber = useSelector(countPlayers);

  const setName = (value) => {
    name = value;
  }

  const dispatch = useDispatch();

  const addPlayer = () => {
    if (playerNumber < 5) {
      dispatch(modifyPlayerCount(1));
    }
  };
  const removePlayer = () => {
    if (playerNumber > 1) {
      dispatch(modifyPlayerCount(-1));
    }
  };

  const handleCreate = () => {
    dispatch(startGame("START_GAME"));
    history.push("/waiting-room");
  };

  return (
    <div className="room">
      <div className="room-container">
      <form className="room_create" onSubmit={handleCreate}>
        <div className="room-description">Új szoba létrehozása</div>
        <div>Játékosok száma:</div>
        <div className="players">
          <div className="room-players-count">{playerNumber}</div>
          <button
            className="custom-btn add-players room-item"
            onClick={addPlayer}
          >+</button>
          <button
              className="custom-btn remove-players room-item"
              onClick={removePlayer}
            >-</button>
        </div>
        <div className="input">
          <label className="label">Név: </label>
          <input
            id="name"
            type="text"
            required
            maxLength={20}
            className="custom-input room-item"
            placeholder="Játékos1"
            onChange={(e) => { setName(e.target.value); }}
          />
        </div>
        <button type="submit" className="custom-btn room-item">
          Szoba Létrehozása
        </button>
      </form>
      </div>

      <div className="room-container">
      <form className="room_connect" onSubmit={connectRoom}>
        <div className="room-description">Csatlakozás a szobához</div>
        <div className="room-connect">
          <label className="player-label">
            Név: 
          </label>
          <input
            id="name"
            type="text"
            required
            maxLength={20}
            placeholder="Játékos2"
            className="custom-input room-item"
          />
        </div>
        <div className="room-id-container">
          <label className="room-label">
            Azonosító: 
          </label>
          <input
            id="room-id"
            type="text"
            maxLength={20}
            className="custom-input room-item"
          />
        </div>
        <button
          type="submit"
          className="custom-btn room-item"
        >
        Csatlakozás
      </button>
    </form>
    </div>
    
  </div>
  );
};

export default Room;
