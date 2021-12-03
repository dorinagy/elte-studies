export const countPlayers = ({ playerCount}) => playerCount;

export const getPlayers = ({ gameState }) => gameState.players;
    
export const getWinner = ({ gameStateReducer }) => gameStateReducer.winner;

export const getGameStatus = ({ gameState}) => gameState.gameStatus;

export const getMaxPlayers = ({ gameState }) => gameState.maxPlayers;

export const getRoomCode = ({ gameState }) => gameState.code;

export const getWagonDeck = ({ gameState }) => gameState.wagonCards;

export const getShortDeck = ({ gameState }) => gameState.shortDestinationCards;

export const getLongDeck = ({ gameState }) => gameState.longDestinationCards;

export const getCurrentPlayer = ({ gameState }) => gameState.currentPlayer;

export const getClientPlayer= ({ gameState, playerName }) => Object.values(gameState.players).find((element) => element.name === playerName);

//export const getUserWagons = ({ gameState }) => gameState.currentPlayer?.wagonCards;

export const getUserWagons = ({ gameState, playerName }) => Object.values(gameState.players).find((element) => element.name === playerName)?.wagonCards;

export const getOnFieldCards = ({ gameState }) => gameState.onFieldWagonCards;
