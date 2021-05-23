import { ticketToRideData } from "../res/ticket-to-ride-data";

import { initialGameState, initalPlayers } from "./status";

export const playerCountReducer = (state = 0, action) => {
    const { type, payload } = action;

    if (type === "MODIFY_PLAYER_COUNT") {
        return state + (payload);
    }

    return state;
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

    if (type === "START_GAME") {

        state.state = 'START_GAME';
        state.players = payload.players;
        // set current player
        state.currentPlayer = state.players['player1'];
        state.code = Math.random().toString(36).substring(7); // generate room code

    } else if (type === "INIT_GAME") {
        state.state = 'INIT_GAME';

        // if more than 3 locomotives - redraw deck
        do {
            state.wagonCards = generateWagonCards();
            state.onFieldWagonCards = [];
            if (state.wagonCards) {
                for (let i = 0; i < 5; i++) {
                    // train cards on table (4)
                    state.onFieldWagonCards?.push(state.wagonCards[i]);
                }
            }
        } while (countWagons(state.onFieldWagonCards) >= 3);

        state.wagonCards = state.wagonCards?.slice(5, state.wagonCards.length);

        // generate destination cards
        state.shortDestinationCards = generateShortDestinationCards();
        state.longDestinationCards = generateLongDestinationCards();

        state.players = payload.players
        // set current player
        state.currentPlayer = state.players[0];

        // add wagon cards to players (4)
        state.players.forEach((player) => {
            player.wagonCards = [];
            if (state.wagonCards) {
                for (let i = 0; i < 4; i++) {
                    player.wagonCards.push(JSON.parse(JSON.stringify(state.wagonCards[i])));
                }    
            } 
            state.wagonCards = state.wagonCards?.slice(4, state.wagonCards.length);
            player.shortDestCards = [];
            if (state.shortDestinationCards) { // 4 short destination card for each player
                for (let i = 0; i < 5; i++) {
                    player.shortDestCards.push(state.shortDestinationCards[i]);
                }
            }
            state.shortDestinationCards = state.shortDestinationCards?.slice(5, state.shortDestinationCards.length);
            player.longDestCards = [];
            if (state.longDestinationCards) { // 1 long destination card for each player
                player.longDestCards.push(state.longDestinationCards[0]);
            }

            state.longDestinationCards = state.longDestinationCards?.slice(1, state.longDestinationCards.length);
            player.wagons = 45; // initial wagons/player : 45
            player.points = 0;
        });

        state.players[0].isSelected = true;

        state.status = 'IN_GAME';
        return state;

    }  else if (type === 'DRAW_FROM_DECK') {
        /* 
            DRAW CARD FROM DECK
        */
        console.log('player status', state.currentPlayer)
        if (state.currentPlayer?.status === 'BEGIN') {

            state.currentPlayer?.wagonCards?.push(state.wagonCards[0]);

            state.players = state.players.map((player) => {
                if (player.index === state.currentPlayer?.index && state.wagonCards) {
                    console.log(`${player.name} ${state.wagonCards[0].type} vasútikocsi kártyát húzott a pakliból`)
                    player.moves.push(`${player.name} ${state.wagonCards[0].type} vasútikocsi kártyát húzott a pakliból`);
                    player.wagonCards?.push(state.wagonCards[0]);
                    state.wagonCards = state.wagonCards.slice(1, state.wagonCards.length);
                }
                return player;
            });

            state.currentPlayer.status = 'DRAW';

        } else if (state.currentPlayer?.status === 'DRAW') {
            console.log('DRAW')

            state.currentPlayer?.wagonCards?.push(state.wagonCards[0]);

            state.players = state.players.map((player, i) => {
                if (i === state.currentPlayer?.index && state.wagonCards) {
                    player.wagonCards?.push(state.wagonCards[0]);
                    console.log(`${player.name} ${state.wagonCards[0].type} vasútikocsi kártyát húzott a pakliból`)
                    player.moves.push(`${player.name} ${state.wagonCards[0].type} vasútikocsi kártyát húzott a pakliból`);
                    state.wagonCards = state.wagonCards.slice(1, state.wagonCards.length);
                }
                return player;
            });

            let index = state.currentPlayer?.index == 0 ? 1 : 0;

            state.currentPlayer.status = 'END';
            
            state.players[state.currentPlayer.index].isSelected = false;
            state.players[index].isSelected = true;
            state.players[index].round++;

            state.currentPlayer = Object.assign({}, state.players[index]);
            state.currentPlayer.status = 'BEGIN';

            console.log('Next round, player:', state.currentPlayer.name);
        }

        state = JSON.parse(JSON.stringify(state));
        return state;

    } else if (type === 'DRAW_CARD') {
        /* 
            VASÚTI KÁRTYA HÚZÁSA AZ ASZTALRÓL
        */
        console.log('player status', state.currentPlayer) // aktuális játékos
        const { number: idx } = payload; // kártya száma
        if (state.currentPlayer?.status === 'BEGIN') { // ELSŐ KÖRÖS HÚZÁS  

            // ha van a pakliban kártya
            if (state.onFieldWagonCards && state.wagonCards) {

                console.log('REFRESH PLAYER CARDS');
                state.currentPlayer?.wagonCards?.push(state.onFieldWagonCards[idx]);

                // kicseréljük a húzott kártyát
                const cards = Object.assign([], state.onFieldWagonCards);
                cards[idx] = state.wagonCards[0];
                state.onFieldWagonCards = Object.assign([], cards);

                state.wagonCards = state.wagonCards.slice(1, state.wagonCards.length);

                state.players.forEach((player, i) => {
                    if (i === state.currentPlayer?.index) {
                        player.wagonCards = state.currentPlayer?.wagonCards;
                        console.log(`${player.name} ${state.onFieldWagonCards[idx].type} vasútikocsi kártyát húzott az asztalról`)
                        player.moves.push(`${player.name} ${state.onFieldWagonCards[idx].type} vasútikocsi kártyát húzott az asztalról`);
                    }
                });
            }

            if (
                state.currentPlayer.wagonCards &&
                state.currentPlayer.wagonCards[state.currentPlayer.wagonCards.length - 1].type === "locomotive" // mozdony húzása után nem húzhat több kártyát
            ) {
                // ha nem léphet többet - a másik játékos jön
                const next = state.currentPlayer?.index == 0 ? 1 : 0;

                state.currentPlayer.status = 'END';

                state.players[state.currentPlayer.index].isSelected = false;
                state.players[next].isSelected = true;
                state.players[next].round++;

                state.currentPlayer = Object.assign({}, state.players[next]);
                state.currentPlayer.status = 'BEGIN';

                console.log('Next round, player:', state.currentPlayer);
            } else {
                // második körös húzás
                state.currentPlayer.status = 'DRAW';
            }

        } else if (state.currentPlayer?.status === 'DRAW') {
            console.log('Player status: DRAW'); // második körös húzás

            if (state.onFieldWagonCards && state.wagonCards) {

                console.log('REFRESH PLAYER CARDS')
                state.currentPlayer?.wagonCards?.push(state.onFieldWagonCards[idx]);

                state.onFieldWagonCards[idx] = state.wagonCards[0];
                state.wagonCards = state.wagonCards.slice(1, state.wagonCards.length);
                
                state.players.forEach((player, i) => {
                    if (i === state.currentPlayer?.index) {
                        player.wagonCards = state.currentPlayer?.wagonCards;
                        console.log(`${player.name} ${state.onFieldWagonCards[idx].type} vasútikocsi kártyát húzott az asztalról`)
                        player.moves.push(`${player.name} ${state.onFieldWagonCards[idx].type} vasútikocsi kártyát húzott az asztalról`);
                    }
                });
            }

            let next = state.currentPlayer?.index == 0 ? 1 : 0;

            state.currentPlayer.status = 'END';
        
            state.players[state.currentPlayer.index].isSelected = false;
            state.players[next].isSelected = true;
            state.players[next].round++;

            state.currentPlayer = Object.assign({}, state.players[next]);
            state.currentPlayer.status = 'BEGIN';

            console.log('Next round, player:', state.currentPlayer);
            
        }
        state = JSON.parse(JSON.stringify(state));
        return state;
    }    

    return state;
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
        deck.push({ type: "locomotive" });
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

function countWagons(deck) {
    return deck.filter((card) => card.type === "locomotive").length;
}