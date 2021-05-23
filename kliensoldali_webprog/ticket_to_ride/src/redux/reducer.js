import { ticketToRideData } from "../res/ticket-to-ride-data";

import { initialGameState, initalPlayers, initPlayer } from "./status";

export const playerCountReducer = (newState = 0, action) => {
    const { type, payload } = action;

    if (type === "MODIFY_PLAYER_COUNT") {
        return newState + (payload);
    }

    return newState;
};

export const gameDataReducer = (state = ticketToRideData, action) => { 

    return state;
};

export const playerReducer = (state = initalPlayers, action) => { 

    return state;
};

export const gameStateReducer = (
    state = initialGameState,
    action
) => {
    const { type, payload } = action;

    let newState = Object.assign({}, state);

    if (type === "START_GAME") {

        newState.state = payload.state;

        const actPlayer = Object.assign(initPlayer, {name: 'Játékos1'});
        //newState.players.push(owner);
        newState.currentPlayer = actPlayer;
        //newState.gameStatus = 'WAITING_FOR_PLAYERS';
       // newState.maxPlayers = 2;
        newState.code = Math.random().toString(36).substring(7);

        return newState;
    } else if (type === "INIT_GAME") {
        newState.wagonCards = generateWagonCards();
        newState.shortDestinationCards = generateShortDestinationCards();
        newState.longDestinationCards = generateLongDestinationCards();

        console.log(newState);

        newState.players.forEach((element) => {
            element.wagonCards = [];
            if (newState.wagonCards) {
                for (let i = 0; i < 4; i++) {
                    element.wagonCards.push(JSON.parse(JSON.stringify(newState.wagonCards[i])));
                }    
            } 
            newState.wagonCards = newState.wagonCards?.slice(4, newState.wagonCards.length);
            element.shortDestCards = [];
            if (newState.shortDestinationCards) {
                for (let i = 0; i < 5; i++) {
                    element.shortDestCards.push(newState.shortDestinationCards[i]);
                }
            }
            newState.shortDestinationCards = newState.shortDestinationCards?.slice(5, newState.shortDestinationCards.length);
            element.longDestCards = [];
            if (newState.longDestinationCards) {
                element.longDestCards.push(newState.longDestinationCards[0]);
            }

            newState.longDestinationCards = newState.longDestinationCards?.slice(1, newState.longDestinationCards.length);
            element.wagons = 45;
            element.points = 0;
        });

        newState.players[0].isSelected = true;

        //newState.gameStatus = 'IN_GAME';
        return newState;
    }  else if (action.type === "CHANGE_TO_PLAYER1") {
        console.log('CHANGE_TO_PLAYER1',action)
        newState.players = action.payload.players;

        newState.players[0].isSelected = true;
        newState.players[1].isSelected = false;

        return newState;

    } else if (action.type === "CHANGE_TO_PLAYER2") {
        
        console.log('CHANGE_TO_PLAYER2',action)
        newState.players = action.payload.players;

        newState.players[0].isSelected = false;
        newState.players[1].isSelected = true;

        return newState;

    } else if (action.type === "GET_CARD_FROM_DECK_TO_PLAYER") {
        newState = action.payload;
        newState.currentPlayer.cards.push(newState.storage.pop()); 
        
        return newState;

    } else if (action.type === "GET_CARD_FROM_TABLE_TO_PLAYER") {
        newState = action.payload;
        console.log(newState.backlog);

        for (const card in newState.cardsOnTable) {
            if (newState.cardsOnTable[card].image.props.alt === newState.chosedCard) {
                newState.newState = newState.players[0].isSelected 
                    ? `${newState.players[0].name} köre`
                    : `${newState.players[1].name} köre`;

                if (newState.currentPlayer.drawCount < 2) {
                    if (newState.chosedCard === "Mozdony" && newState.currentPlayer.drawCount === 0) {
                        newState.currentPlayer.cards.push(newState.cardsOnTable[card]);
                        newState.cardsOnTable[card] = newState.storage.pop();
                        newState.backlog.push(newState.currentPlayer.name + " húzott egy mozdonyt");

                        newState.currentPlayer.round++;
                        newState.currentPlayer.drawCount = 0;

                        if (newState.players[0].isSelected) {
                            newState.players[0].isSelected = false;
                            newState.players[1].isSelected = true;
                            newState.newState = `${newState.players[1].name} köre`;
                        } else {
                            newState.players[0].isSelected = true;
                            newState.players[1].isSelected = false;
                            newState.newState = `${newState.players[0].name} köre`;
                        }
                        break;
                    } else if(newState.chosedCard === "Mozdony" && newState.currentPlayer.drawCount !== 0) { 
                        alert("Már húztál egy lapot! Nem húzhatsz mozdonyt!");
                        break;
                    } else {
                        newState.currentPlayer.cards.push(newState.cardsOnTable[card]);
                        newState.cardsOnTable[card] = newState.storage.pop();
                        newState.backlog.push(newState.currentPlayer.name + " húzott egy vasútkocsi-kártyát");

                        newState.currentPlayer.drawCount++;
                        if (newState.currentPlayer.drawCount === 2) { // Váltás
                            newState.currentPlayer.round++;
                            newState.currentPlayer.drawCount = 0;
                            if (newState.players[0].isSelected) {
                                newState.players[0].isSelected = false;
                                newState.players[1].isSelected = true;
                                newState.newState = `${newState.players[1].name} köre`;
                            } else {
                                newState.players[0].isSelected = true;
                                newState.players[1].isSelected = false;
                                newState.newState = `${newState.players[0].name} köre`;
                            }
                        }
                        break;
                    }
                }
            }
        }
        return newState;

    } else if (action.type === "TOO_MANY_LOCOMOTIVES") {
        newState.storage = action.payload.storage;
        newState.cardsOnTable = action.payload.cardsOnTable;
        newState.backlog.push("Kártyacsere történt, mivel 3 mozdony volt az asztalon");

        for (let i=0; i<5; i++) {
            newState.storage.push(newState.cardsOnTable.pop());
        }
        for (let i = newState.storage.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            const temp = newState.storage[i];
            newState.storage[i] = newState.storage[j];
            newState.storage[j] = temp;
        }
        for(let i=0; i<5; i++) {
            newState.cardsOnTable.push(newState.storage.pop());
        }

        return newState;
    } else if (action.type === "DESTINATION_MOUSE_ENTER") {
        newState.ld = action.payload.dest;
        newState.ldPair = [];
        newState.ldPair.push(newState.ld?.from);
        newState.ldPair.push(newState.ld?.to);
        return newState;
    } else if (action.type === "DESTINATION_MOUSE_LEAVE") {
        newState.ldPair = [];
        return newState;
    }

    return newState;
};

function generateWagonCards() {
    let deck = [];
    for (let i = 0; i < 8; i++) {
        for (let j = 0; j < 12; j++) {
            if (i === 0) deck.push({ type: "purple"});
            else if (i === 1) deck.push({ type: "white" });
            else if (i === 2) deck.push({ type: "blue" });
            else if (i === 3) deck.push({ type: "yellow" });
            else if (i === 4) deck.push({ type: "orange" });
            else if (i === 5) deck.push({ type: "black" });
            else if (i === 6) deck.push({ type: "red" });
            else if (i === 7) deck.push({ type: "green" });
        }
    }
    for (let i = 0; i < 14; i++) {
        deck.push({ coltypeor: "colorful" });
    }
    return deck
        .map((a) => ({ sort: Math.random(), value: a }))
        .sort((a, b) => a.sort - b.sort)
        .map((a) => a.value);
}

function generateShortDestinationCards() {
    let deck = [];

    for (const [key, value] of Object.entries(ticketToRideData.destinations)) {
      deck.push(value);
    }
    return deck
        .map((a) => ({ sort: Math.random(), value: a }))
        .sort((a, b) => a.sort - b.sort)
        .map((a) => a.value);
}

function generateLongDestinationCards() {
    let deck = [];

    for (const [key, value] of Object.entries(ticketToRideData.longDestinations)) {
      deck.push(value);
    }

    return deck
      .map((a) => ({ sort: Math.random(), value: a }))
      .sort((a, b) => a.sort - b.sort)
      .map((a) => a.value);
}
