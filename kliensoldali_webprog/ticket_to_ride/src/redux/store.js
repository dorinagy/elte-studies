import { applyMiddleware, combineReducers, createStore } from "redux";
import { composeWithDevTools } from "redux-devtools-extension";
import { createLogger } from "redux-logger";
import { 
    playerCountReducer, 
    gameStateReducer,
    playerReducer,
    //cardsReducer,
    gameDataReducer
} from "./reducer";

const reducers = combineReducers({
    playerCount: playerCountReducer,
    gameState: gameStateReducer,
    gameData: gameDataReducer,
    players: playerReducer,
    //cards: cardsReducer,
});

const logger = createLogger({
    collapsed: true,
});

export const configureStore = () => {
    return createStore(
        reducers,
        composeWithDevTools(applyMiddleware(logger))
    );
};
