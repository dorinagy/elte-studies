import { ticketToRideData } from "../res/ticket-to-ride-data";

export const status = {
    MAIN_PAGE: "Main page",
    WAITING_FOR_PLAYERS: "Waiting for players",
    IN_GAME: "In game",
};

export const initPlayer = {
    name: '',
    points: 0,
    vagons: 0,
    cards: [],
    destinations: 0,
    lomgDestCards: [],
    shortDestCards: [],
    wagonCards: [],
    round: 0,
    drawCount: 0,
    isSelected: false
}

export const initalPlayers = {
    player1: {
        name: "Player1",
        points: 0,
        vagons: 0,
        cards: [],
        destinations: 0,
        lomgDestCards: [],
        shortDestCards: [],
        wagonCards: [],
        round: 0,
        drawCount: 0,
        isSelected: false
    },
    player2: {
        name: "Player2",
        points: 0,
        vagons: 0,
        cards: [],
        destinations: 0,
        lomgDestCards: [],
        shortDestCards: [],
        wagonCards: [],
        round: 0,
        drawCount: 0,
        isSelected: false
    }
};

export const initialGameState = {
    state: "INITAL", 
    players: {
            player1: {
                name: "Player1",
                points: 0,
                vagons: 0,
                cards: [],
                destinations: 0,
                lomgDestCards: [],
                shortDestCards: [],
                wagonCards: [],
                round: 0,
                drawCount: 0,
                isSelected: false
            },
            player2: {
                name: "Player2",
                points: 0,
                vagons: 0,
                cards: [],
                destinations: 0,
                lomgDestCards: [],
                shortDestCards: [],
                wagonCards: [],
                round: 0,
                drawCount: 0,
                isSelected: false
            }
        }, 
    cards: [], 
    cardsOnTable: [],
    backlog: [],
    gamedata: ticketToRideData,
    ldPair: [],
    maxPlayers: 2
}
