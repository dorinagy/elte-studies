export const modifyPlayerCount = (number) => ({
    type: 'MODIFY_PLAYER_COUNT',
    payload: number,
});

export const modifyPlayerName = (name) => ({
    type: 'MODIFY_PLAYER_NAME',
    payload: name,
});

export const startGame = ({ players }) => ({
    type: 'START_GAME',
    payload: { players },
});

export const initGame = ({ players }) => ({
    type: 'INIT_GAME',
    payload: { players }
});

export const drawFromDeck = ({ player }) => {
    return {
        type: 'DRAW_FROM_DECK',
        payload: { player },
    };
};

export const createRoom = (init) => {
    const player = { name: init.name, id: 1, isOwner: true };
    return {
        type: 'NEW_GAME',
        payload: { player, num: init.roomsize, code: init.roomcode },
    };
};

export const drawCard = ({ player, number }) => {
    return {
        type: 'DRAW_CARD',
        payload: { player, number },
    };
};

export const updateGameState = (state) => {
    return {
        type: 'UPDATE_GAME_STATE',
        payload: state,
    };
};

export const joinRoom = ({ players, maxPlayers, code, name }) => {
    return {
        type: 'JOIN_ROOM',
        payload: { players, maxPlayers, code, name },
    };
};
