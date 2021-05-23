import { ticketToRideData } from "../res/ticket-to-ride-data";

export const status = {
    MAIN_PAGE: "Main page",
    WAITING_FOR_PLAYERS: "Waiting for players",
    IN_GAME: "In game",
};

export const initPlayer = {
    name: '',
    points: 0,
    wagons: 45,
    cards: [],
    destinations: 0,
    longDestCards: [],
    shortDestCards: [],
    wagonCards: [],
    round: 0,
    drawCount: 0,
    isSelected: false,
    index: 0,
    status: 'BEGIN',
    moves: []
}

export const initalPlayers = [
    Object.assign({}, initPlayer, {index:0}),
    Object.assign({}, initPlayer, {index:1, name: 'Játékos2'})
];

export const initialGameState = {
    state: "INITAL", 
    players: Object.assign([], initalPlayers), 
    cards: [], 
    cardsOnTable: [],
    backlog: [],
    gamedata: ticketToRideData,
    ldPair: [],
    maxPlayers: 2,
    currentPlayer: ''
}
