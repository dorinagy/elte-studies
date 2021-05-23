export const modifyPlayerCount = (number) => ({
    type: 'MODIFY_PLAYER_COUNT',
    payload: number,
});

export const startGame = ({players}) => ({
    type: 'START_GAME',
    payload: {players},
});

export const initGame = ({players}) => ({
    type: 'INIT_GAME',
    payload: {players}
});

export const drawFromDeck = ({player}) => {
    return {
        type: 'DRAW_FROM_DECK',
        payload: { player },
    };
};

export const drawCard = ({player, number}) => {
    return {
        type: 'DRAW_CARD',
        payload: { player, number },
    };
};
