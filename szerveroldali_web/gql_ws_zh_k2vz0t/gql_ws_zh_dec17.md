# Szerveroldali webprogramozás - GraphQL, Websocket zárthelyi

_2021. december 17._

Tartalom:
- [Szerveroldali webprogramozás - GraphQL, Websocket zárthelyi](#szerveroldali-webprogramozás---graphql-websocket-zárthelyi)
  - [Tudnivalók](#tudnivalók)
  - [Hasznos linkek](#hasznos-linkek)
  - [Kezdőcsomag](#kezdőcsomag)
  - [GraphQL (15 pont)](#graphql-15-pont)
    - [Modellek](#modellek)
    - [Típusdefiníció](#típusdefiníció)
    - [1. feladat: `recipes` (2 pont)](#1-feladat-recipes-2-pont)
    - [2. feladat: `updateIngredient` (2 pont)](#2-feladat-updateingredient-2-pont)
    - [3. feladat: `ingredient` (1 pont)](#3-feladat-ingredient-1-pont)
    - [4. feladat: `smallestStorage` (2 pont)](#4-feladat-smalleststorage-2-pont)
    - [5. feladat: `storeIngredients` (2 pont)](#5-feladat-storeingredients-2-pont)
    - [6. feladat: `changeApplianceName` (3 pont)](#6-feladat-changeappliancename-3-pont)
    - [7. feladat:  `statistics` (3 pont)](#7-feladat--statistics-3-pont)
  - [Websocket/Socket.io (15 pont)](#websocketsocketio-15-pont)
    - [1. feladat: `list-rooms` (1 pont)](#1-feladat-list-rooms-1-pont)
    - [2. feladat: `create-room` (2 pont)](#2-feladat-create-room-2-pont)
    - [3. feladat: `join-room` (1 pont)](#3-feladat-join-room-1-pont)
    - [4. feladat: `mute-client` (5 pont)](#4-feladat-mute-client-5-pont)
    - [5. feladat: `send-message` (6 pont)](#5-feladat-send-message-6-pont)

## Tudnivalók

- Kommunikáció
  - **A Teams csoport Általános csatornáján a zárthelyi egész ideje alatt lesz egy meeting! Erősen ajánlott, hogy ehhez csatlakozzatok, hiszen elsősorban ebben a meetingben válaszolunk a felmerülő kérdésekre, valamint az esetleges időközben felmerülő információkat is itt osztjuk meg veletek!**
  - Ha a zárthelyi közben valamilyen problémád, kérdésed adódik, akkor keresd az oktatókat a meetingben vagy privát üzenetben (Teams chaten).
- Időkeret
  - **A zárthelyi megoldására 2 óra áll rendelkezésre: *16:15-18:15*.**
  - Oszd be az idődet! Ha egy feladat nem megy, akkor inkább ugord át (legfeljebb később visszatérsz rá), és foglalkozz a többivel, hogy ne veszíts pontot olyan feladatból, amit meg tudnál csinálni!
- Beadás
  - **A beadásra további *15* perc áll rendelkezésre: *18:15-18:30*. Ez a +15 perc *ténylegesen* a beadásra van! *18:30* után a Canvas lezár, és további beadásra nincs lehetőség!**
  - Ha előbb végzel, természetesen 18:30-ig bármikor beadhatod a feladatot.
  - A feladatokat `node_modules` mappa nélkül kell becsomagolni egy .zip fájlba, amit a Canvas rendszerbe kell feltölteni!
  - **A dolgozat megfelelő és hiánytalan beadása a hallgató felelőssége.** Mivel a dolgozat végén külön 15 perces időkeretet adunk a feladat megfelelő, nyugodt körülmények közötti beadására, ebből kifolyólag ilyen ügyekben nem tudunk utólagos reklamációknak helyt adni. Tehát ha valaki a zárthelyi után jelzi, hogy egy vagy több fájlt nem adott be, akkor azt sajnos nem tudjuk elfogadni.
- Értékelés
  - A legutoljára beadott megoldás lesz értékelve.
  - **A zárthelyin legalább a pontok 40%-át, vagyis legalább 12 pontot kell elérni**, ez alatt a zárthelyi sikertelen. A GraphQL-re vagy Websocket-re **nincs** külön-külön minimumpont, összesen kell elérni legalább a 12 pontot, ami akár úgy is összetevődhet, hogy a GraphQL feladat 12 pontos, a Websocket pedig 0 pontos.
  - Vannak részpontok.
  - A pótzárthelyin nem lehet rontani a zárthelyi eredményéhez képest, csak javítani.
  - **Érvényes nyilatkozat (megfelelően kitöltött statement.txt) hiányában a kapott értékelés érvénytelen, vagyis 0 pont.**
  - Az elrontott, elfelejtett nyilatkozat utólag pótolható: Canvasen kommentben kell odaírni a feladathoz.
- Egyéb
  - A feladatokat Node.js környezetben, JavaScript nyelven kell megoldani, a tantárgy keretein belül tanult technológiák használatával!
  - Ajánlott a Node.js LTS verziójának a használata. Ha a gépedre már telepítve van a Node.js és telepített példány legalább 2 főverzióval le van maradva az aktuálisan letölthető legfrissebbhez képest, akkor érdemes lehet frissíteni.

## Hasznos linkek

- Dokumentációk
  - Sequelize:
    - [Sequelize dokumentáció](https://sequelize.org/master/)
    - [Model querying basics](https://sequelize.org/master/manual/model-querying-basics.html)
    - [Sequelize asszociációk](https://github.com/szerveroldali/2021-22-1/blob/main/SequelizeAssociations.md) (tantárgyi leírás)
  - GraphQL:
    - [GraphQL dokumentáció](https://graphql.org/learn/)
    - [GraphQL scalars](https://www.graphql-scalars.dev/docs) (a kezdőcsomag tartalmazza)
  - Socket.IO:
    - [Socket.IO dokumentáció](https://socket.io/docs)
    - [Socket.IO szobák működése](https://socket.io/docs/v4/rooms/)
    - [Socket.IO emit cheatsheet](https://socket.io/docs/v4/emit-cheatsheet/)
- Eszközök:
  - [Postman](https://www.postman.com/)
  - [Firecamp Chrome kiegészítő](https://chrome.google.com/webstore/detail/firecamp-a-campsite-for-d/eajaahbjpnhghjcdaclbkeamlkepinbl)
  - [DB Browser for SQLite](https://sqlitebrowser.org/)
- Gyakorlati anyagok:
  - [Tavalyi ZH mintamegoldása](https://github.com/szerveroldali/2021-22-1/tree/main/graphql_websocket_minta)
  - [GraphQL gyakorlati anyag](https://github.com/szerveroldali/2021-22-1/tree/main/esti_3_4_csut_19_30/restapi/graphql)
  - [Socket.IO gyakorlati anyag](https://github.com/szerveroldali/2021-22-1/tree/main/esti_3_4_csut_19_30/websocket)

## Kezdőcsomag
Segítségképpen készítettünk egy kezdőcsomagot a zárthelyihez. Csak telepíteni kell a csomagokat, és kezdheted is a fejlesztést.

- A kezdőcsomag elérhető itt:
  - https://github.com/szerveroldali/graphql_websocket_kezdocsomag
- Automatikus tesztelő (`gql:` és `ws:` prefix): `npm run gql:test <FELADATOK SZÁMAI>`
  - Pl. 1. és 2. feladat tesztelése: `npm run gql:test 1 2`
  - Minden feladat tesztelése: `npm run gql:test`
- Zippelő: `npm run zip`

## GraphQL (15 pont)

A feladatot a kezdőcsomagon belül a `graphql/graphql/typedefs.gql`, `graphql/graphql/resolvers.js` fájlokba kell kidolgozni.

A kezdőcsomag két grafikus felületet is biztosít a GraphQL-hez, ezek az alábbi linkeken érhetők el:
  - [localhost:4000/graphql](http://localhost:4000/graphql): GraphiQL
  - [localhost:4000/playground](http://localhost:4000/playground): GraphQL Playground (ajánlott)

### Modellek

Az alábbi modelleket készen kapod, ami tartalmazza a következőket: migration, model, seeder. **Tehát nem neked kell őket megírni, neked csak annyi a feladatod, hogy a készen kapott fájlokat bemásolod és inicializálod az adatbázist (`npm run db`), hogy használni tudd!**  Az adatokat lokális SQLite adatbázisban kell tárolni.

Segítségképppen a modellek felépítése az alábbi módon néz ki:

- Modellek:
  - `Storage`: tárolók
    - `name`: string, unique (tehát egyedi, vagyis ugyanaz a név nem szerepelhet kétszer)
    - `capacity`: number
  - `Appliance`: konyhai berendezések
    - `name`: string
  - `Ingredient`: hozzávalók
    - `name`: string
    - `amount`: number
    - `StorageId` (összekapcsolásból jön)
  - `Recipe`: receptek
    - `name`: string, unique (tehát egyedi, vagyis ugyanaz a név nem szerepelhet egyszerre több beszállítónál)
    - `isVegetarian`: boolean
    - `doneCount`: number _(vagyis hányszor készítették már el)_
- Relációk
  - `Recipe` N - N `Ingredient`
  - `Appliance` 1 - N `Recipe`
  - `Storage` 1 - N `Ingredient`

### Típusdefiníció

Adott az alábbi GraphQL típusdefiníció. Másold be a kezdőcsomagba, ezt követően pedig implementálod a szükséges műveleteket. A műveletek implementálása során a típusdefiníciót értelemszerűen ki kell egészíteni, hiszen az alábbi csak egy kezdeti állapot.

```graphql
# Ezt majd feladatról feladatra haladva ki kell egészíteni

type Recipe {
  id: ID!
  name: String
  isVegetarian: Boolean
  doneCount: Int
  appliance: Appliance
  ingredients: [Ingredient]
  createdAt: DateTime!
  updatedAt: DateTime!
}

type Ingredient {
  id: ID!
  name: String
  amount: Int
  isInBigStorage: Boolean
  createdAt: DateTime!
  updatedAt: DateTime!
}

type Appliance {
  id: ID!
  name: String
  createdAt: DateTime!
  updatedAt: DateTime!
}

type Storage {
  id: ID!
  name: String
  capacity: Int
  createdAt: DateTime!
  updatedAt: DateTime!
}
```

### 1. feladat: `recipes` (2 pont)
- Összes recept lekérése, a hozzá tartozó berendezéssel és hozzávalókkal együtt
  - Kérés:
    ```graphql
    query {
      recipes {
        id,
        name,
        isVegetarian,
        doneCount,
        createdAt,
        updatedAt,
        appliance {
          id,
          name
        },
        ingredients {
          id,
          name,
          amount
        }

      }
    }
    ```
  - Válasz: a recepthez kapcsolt berendezést és hozzávalókat listázni kell a példán látható módon
    ```json
    {
    "data": {
      "recipes": [
        {
          "id": "1",
          "name": "molestiae",
          "isVegetarian": false,
          "doneCount": 40,
          "createdAt": "2021-12-17T...",
          "updatedAt": "2021-12-17T...",
          "appliance": {
            "id": "5",
            "name": "expedita"
          },
          "ingredients": [
            {
              "id": "13",
              "name": "corrupti",
              "amount": 42
            }
          ]
        },
        {
          "id": "2",
          "name": "omnis",
          "isVegetarian": false,
          "doneCount": 67,
          "createdAt": "2021-12-17T...",
          "updatedAt": "2021-12-17T...",
          "appliance": {
            "id": "3",
            "name": "ea"
          },
          "ingredients": []
        },
        ...
    }
    ```

### 2. feladat: `updateIngredient` (2 pont)
- `updateIngredient(ingredientId, input)`
- Egy hozzávaló módosítása. Itt az input azokra a kulcs-érték párosokra utal, amit módosítani szeretnénk.
    - Kérés
      ```graphql
      mutation {
        updateIngredient(ingredientId: 1, input: { name: "UJ_NEV" }) {
          id
          name
          updatedAt
        }
      }
      ```
    - Válasz
      ```json
      {
        "data": {
          "updateIngredient": {
            "id": "1",
            "name": "UJ_NEV",
            "updatedAt": "2021-12-17T..."
          }
        }
      }
      ```

### 3. feladat: `ingredient` (1 pont)
- `ingredient(id)`
- Egy hozzávaló lekérdezése (erre még nem jár pont). Implementáld a `isInBigStorage` mezőt, ami visszaadja, hogy a hozzá tartozó tároló kapacitása nagyobb-e **20**-nál.
    - Kérés:
      ```graphql
      query {
        ingredient(id: 1) {
          id,
          name,
          isInBigStorage
        }
      }
      ```
    - Válasz:
      ```json
        {
          "data": {
            "ingredient": {
              "id": "1",
              "name": "UJ_NEV",
              "isInBigStorage": true
            }
          }
        }
      ```

### 4. feladat: `smallestStorage` (2 pont)
- Visszaadja az adatbázisban tárolt legkisebb kapacitású tárolót és az összes olyan hozzávalót, amivel ebben található (tehát amikkel relációban van).
    - Kérés:
        ```graphql
          query {
            smallestStorage {
              id,
              name,
              capacity,
              ingredients {
                id,
                name,
                amount
              }
            }
          }
        ```
    - Válasz:
      ```json
        {
          "data": {
            "smallestStorage": {
              "id": "9",
              "name": "quod",
              "capacity": 2,
              "ingredients": [
                {
                  "id": "8",
                  "name": "est",
                  "amount": 54
                },
                {
                  "id": "9",
                  "name": "omnis",
                  "amount": 80
                }
              ]
            }
          }
        }
      ```

### 5. feladat: `storeIngredients` (2 pont)
- `storeIngredients(storageId, ingredients)`
- Egy megadott tárolóhoz rendel hozzá plusz hozzávalókat. Fontos, hogy az `ingredients` mezőben a hozzávalók mezői vannak (`name`, `amount`) és nem cserélik le a tárolóban lévőeket, hanem kiegészítik azokat (tehát csak hozzáadódnak a tárolóhoz).
    - Kérés:
      ```graphql
        mutation {
          storeIngredients(storageId: 4, ingredients: [
              { name: "ingredient1" },
              { name: "ingredient2", amount: 3 }
            ]
          ) {
            id,
            name,
            amount
          }
        }
      ```
    - Válasz: A tárolóban lévő hozzávalók.
      ```json
      {
        "data": {
          "storeIngredients": [
            {
              "id": "8",
              "name": "occaecati",
              "amount": 44
            },
            {
              "id": "24",
              "name": "ingredient1",
              "amount": null
            },
            {
              "id": "25",
              "name": "ingredient2",
              "amount": 3
            }
          ]
        }
      }
      ```
### 6. feladat: `changeApplianceName` (3 pont)
- `changeApplianceName(applianceId, newName)`
- Az `applianceId` mezőben megadott id-jú berendezés nevét lecseréli a `newName` mező értékére, **DE** csak akkor, ha a berendezéshez a receptek kevesebb mint 30%-a tartozik.
  Tehát:
    - ha `BERENDEZÉSHEZ_TARTOZÓ_RECEPTEK_SZÁMA < ÖSSZES_RECEPT_30_SZÁZALÉKA`, akkor megváltozik a neve
    - ha `BERENDEZÉSHEZ_TARTOZÓ_RECEPTEK_SZÁMA >= ÖSSZES_RECEPT_30_SZÁZALÉKA`, akkor nem történik semmi

    - Kérés
      ```graphql
        mutation {
          changeApplianceName(applianceId: 1, newName: "MEGVALTOZOTT") {
            id,
            name
          }
        }
      ```

    - Válasz
      ```json
      {
        "data": {
          "changeApplianceName": {
            "id": "1",
            "name": "MEGVALTOZOTT"
          }
        }
      }
      ```

### 7. feladat:  `statistics` (3 pont)
- A receptekről készít statisztikát.
    - Kérés:
      ```graphql
        query {
          statistics {
            popularVegetarianRecipeCount
            mostPopularRecipeName
            leastPopularRecipeName
            averageDoneCount
          }
        }
      ```
    - Válasz: le kell kérni a receptekhez aktuálisan tartozó statisztikákat. A statisztika a következő mezőkből áll:
      - `popularVegetarianRecipeCount`: hány olyan vegetáriánus recept van (`isVegetarian`) amit több mint 10 ember csinált meg (`doneCount`)
      - `mostPopularRecipeName`: annak a receptnek a neve (`name`), aminek a legmagasabb a `doneCount`-ja. Ha több, doneCount-ú recept van, akkor a legkisebb id-jút kell visszaadni
      - `leastPopularRecipeName`: annak a receptnek a neve (`name`), aminek a legalacsonyabb a `doneCount`-ja. Ha több, doneCount-ú recept van, akkor a legkisebb id-jút kell visszaadni
      - `averageDoneCount`: az összes recept `doneCount` mezőjéből vett átlag (lefelé kerekítve, egész számra)
      Az alább példa szerint kell visszaadni a statisztikát:
    - Megjegyzés: Ehhez már új típust is létre kell hozni
      ```json
      {
        "data": {
          "statistics": {
            "popularVegetarianRecipeCount": 8,
            "mostPopularRecipeName": "ut",
            "leastPopularRecipeName": "ipsum",
            "averageDoneCount": 60
          }
        }
      }
      ```
## Websocket/Socket.io (15 pont)

A feladatod egy chat elkészítése. A klienseknek legyen lehetősége belépni chatszobákba, ahol üzenetet tudnak küldeni a többi tagnak. Egy kliens saját szobát is csinálhat, ahová más kliensek csatlakozhatnak. Ha valaki létrehoz egy szobát, automatikusan a saját szobája adminjává válik, és jogában áll lenémítani tagokat, így ők onnantól kezdve nem írhatnak a többieknek abban a szobában.

Az adatokat a szerver memóriájában kell tárolni, az alábbi minta szerint:
```js
let db = {
    rooms: {
        room1: {
            admin: "socket1",
            members: ["socket1", "socket2", "socket3"],
            muted: ["socket2"],
            messages: [
                { timestamp: 123456789, client: "socket1", message: "sziasztok" },
                { timestamp: 123456789, client: "socket3", message: "hali" },
            ],
        },
        "Másik szoba": {
            admin: "socket2",
            members: ["socket2"],
            muted: [],
            messages: [{ timestamp: 123456789, client: "socket2", message: "foreveralone" }],
        },
    },
};
```

**Ezekre figyelj, hogy a tesztelő működjön:**
- *Az adatokat a `db`-be tárold és onnan is olvasd ki.*
- *A fenti példa elnevezéseit és felépítését kövesd. Pl: a `muted` maradjon `muted`, NE legyen pl. `mutedMembers`!*
- *A végpontoknak két paramétere legyen, ahogy gyakorlatokon tanultuk: `data` és `ack`, ahol a `data` egy objektum, ami tartalmazza az összes bemenő adatot.*
- *Pontosan kövesd a feladatokban megadott hibaüzeneteket.*

A feladatot a kezdőcsomagon belül a `websocket/events.js` fájlba kell kidolgozni.

### 1. feladat: `list-rooms` (1 pont)
  - Elérhető chat szobák listázása
  - Paraméterek: -
  - Válasz (acknowledgement):
    - Jó esetben: `{ status: 'ok', rooms: ['room1', 'room2', ...] }`
    - Hiba esetén: `{ status: 'error', message: '<hibaüzenet>'}`

### 2. feladat: `create-room` (2 pont)
  - Szoba létrehozása. Ilyenkor aki létrehozza a szobát, automatikusan csatlakozik hozzá, illetve az adminjává is válik. A kliens csatlakozását és admin jogát az adatbázisban le kell tárolni, továbbá SocketIO szinten is hozzá kell őt adni a szobához.
  - Paraméterek
    - `room`: a létrehozandó szoba neve
  - Válasz (acknowledgement):
    - Jó esetben: `{ status: 'ok' }`
    - Hiba esetén:
      - Hiányzó, rossz paraméter: `{ status: 'error', message: '<hibaüzenet>'}`
      - A megadott névvel már létezik szoba: `{ status: 'error', message: 'This name is already taken!'}`

### 3. feladat: `join-room` (1 pont)
  - Csatlakozás egy chatszobához. A kliens csatlakozását az adatbázisban is le kell tárolni, továbbá SocketIO szinten is hozzá kell őt adni a szobához.
  - Paraméterek
    - `room`: a szoba neve, amihez csatlakozni szeretnénk
  - Válasz (acknowledgement):
    - Jó esetben: `{ status: 'ok' }`
    - Hiba esetén:
      - Hiányzó, rossz paraméter: `{ status: 'error', message: '<hibaüzenet>'}`
      - Nem létező szoba: `{ status: 'error', message: 'No such room in our system!'}`
      - A kliens már a szoba tagja: `{ status: 'error', message: 'You are already subscribed to this room!'}`

### 4. feladat: `mute-client` (5 pont)
  - Az adminnak legyen lehetősége lenémítani egy klienst, aki az ő szobájában van.
  - Paraméterek
    - `room`: a szoba neve, amelyben némítani szeretnénk
    - `clientId`: a némítandó kliens SocketIO azonosítója
    - `reason`: a némítás oka (opcionális)
  - Válasz (acknowledgement):
    - Jó esetben:
      - `{ status: 'ok' }`
      - Továbbá a lenémított kliensnek el kell küldeni a `muted` üzenetet, a következő adatokkal (minta):
        - `{ room: 'room1', admin: 'socket1', reason: 'káromkodtál' }`
        - Ha nem volt `reason` megadva, az értéke legyen `"Nincs indok"`
    - Hiba esetén:
      - Hiányzó, rossz paraméter: `{ status: 'error', message: '<hibaüzenet>'}`
      - Nem létező szoba: `{ status: 'error', message: 'No such room in our system!'}`
      - A kliens nem a szoba tagja: `{ status: 'error', message: 'You are not subscribed to this room!'}`
      - A kliens nem admin a szobában: `{ status: 'error', message: 'You have no power here, Gandalf the Grey!'}`
      - A kliens saját magát akarja némítani: `{ status: 'error', message: 'You can't mute yourself!'}`
      - A célpont nem tagja a szobának: `{ status: 'error', message: 'The target is not a member of the room!'}`
      - A célpont már némítva van: `{ status: 'error', message: 'The target is already muted!'}`

### 5. feladat: `send-message` (6 pont)
  - Üzenet küldése a chat szoba tagjainak, ha nem vagyunk némítva.
  - Paraméterek
    - `room`: a szoba neve, amelyben üzenni szeretnénk
    - `message`: üzenet tartalma
  - Válasz (acknowledgement):
    - Jó esetben:
      - `{ status: 'ok' }`
      - Továbbá a szobában lévő összes kliensnél (de csak náluk) `message-received` eseményt kell kiváltani, a következő adatokkal (minta):
        - `{ room: 'room1', timestamp: 123456789, client: 'socket1', message: 'sziasztok' }`
    - Hiba esetén:
      - Hiányzó, rossz paraméter: `{ status: 'error', message: '<hibaüzenet>'}`
      - Nem létező szoba: `{ status: 'error', message: 'No such room in our system!'}`
      - A kliens nem a szoba tagja: `{ status: 'error', message: 'You are not subscribed to this room!'}`
      - A kliens némítva van: `{ status: 'error', message: 'You are muted!'}`

