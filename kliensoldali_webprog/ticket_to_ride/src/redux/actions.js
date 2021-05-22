export const modifyPlayerCount = (number) => ({
    type: 'MODIFY_PLAYER_COUNT',
    payload: number,
});

export const startGame = ({state}) => ({
    type: 'START_GAME',
    payload: {state},
});

export const initGame = ({players}) => ({
    type: 'INIT_GAME',
    payload: {players}
});

export const changeToPlayer1 = ({players}) => ({
    type: 'CHANGE_TO_PLAYER1',
    payload: {players: players}
});

export const changeToPlayer2 = ({players}) => ({
    type: 'CHANGE_TO_PLAYER2',
    payload: {players: players}
});

export const getCardFromDeckToPlayer = ({currentPlayer, cardsOnTable, storage, backlog}) => ({
    type: 'GET_CARD_FROM_DECK_TO_PLAYER',
    payload: {currentPlayer, cardsOnTable, storage, backlog}
});

export const getCardFromTableToPlayer = ({currentPlayer, players, cardsOnTable, storage, chosedCard, backlog}) => ({
    type: 'GET_CARD_FROM_TABLE_TO_PLAYER',
    payload: {currentPlayer, players, cardsOnTable, storage, chosedCard, backlog}
});

export const tooManyLocomotives = ({cardsOnTable, storage}) => ({
    type: 'TOO_MANY_LOCOMOTIVES',
    payload: {cardsOnTable, storage}
});

export const destinationMouseEnter = ({dest}) => ({
    type: 'DESTINATION_MOUSE_ENTER',
    payload: {dest}
});

export const destinationMouseLeave = () => ({
    type: 'DESTINATION_MOUSE_LEAVE',
    payload: {}
});
