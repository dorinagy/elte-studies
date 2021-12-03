import React, { useState } from "react";
import { Redirect } from "react-router-dom";
import "./Rules.css";

function Rules() {
  const [goBack, setGoBack] = useState(false);

  const ReturnHome = event => {
    event.preventDefault();
    setGoBack(true);
  };

  if (goBack) {
    return (
      <Redirect push to={{ pathname: "/" }} />
    );
  }

  return (
    <div className="rules">
      <h1>Játék Szabályzat</h1>

      <h2>Játékosok száma</h2>
      <p>A játékot 1-5 játékos játszhatja.</p>
            
      <h2>Játék célja</h2>
        <p>Célunk vasútvonalak építésével minél több pontot szerezni.</p>
      
      <h2>Játék menete</h2>
        <p>
          Pontot a megépített vasútvonalak hossza után, illetve a játék közben húzott célok
          (menetjegy-kártyák) teljesítésével lehet kapni, illetve a játék
          végén plusz pont jár a leghosszabb összefüggő vasútvonalért is.
          A nem teljesített célok pontjai levonásra kerülnek. A játék
          elején minden játékos kap 1 hosszú célt, és 3 rövid célt. A 3
          rövid célból legalább 1-et meg kell tartani. A célok mellett
          kapunk még 4 vasútkocsi-kártyát is. Az asztalon elő van készítve
          5 felfedett vasútkocsi kártya, a vasútkocsi-kártyák talonja,
          valamint a célok talonja. Minden játékosnak emellett van 45 vagonja is.
        </p>
        <p>
          Egy játékos a körében a következő három lehetőség közül választ:
        </p>
        <ol>
          <li>
            <b>Vasútkocsi-kártyát húz:</b> ezt megteheti a felfedett
            kártyák közül, ilyenkor húzás után azonnal pótolni kell a
            lapot, vagy a talonból is húzhat. A mozdony két kártyát ér,
            így azt másodkként nem lehet húzni a felfedettek közül
            (talonból akár 2 is húzható). Ha a felfedett lapok között 3
            mozdony van, akkor az 5 lap megy a dobópakliba, és 5 újat
            kell osztani.
          </li>
          <li>
            <b>Útvonalat épít:</b> ekkor az útvonal színének megfelelő
            mennyiségű lapot kell kijátszania a kezéből. Szürke utak
            bármilyen, de egyféle színből megépíthetők. A mozdonyt
            ábrázoló utakhoz legalább annyi mozdonyt kell kijátszani,
            ahányat az út ábrázol. A mozdony egyébként joker, bármilyen
            vonatkocsi-kártyát helyettesíthet. A vagonokat fel kell
            helyezni a táblára. Az épített út pontértéke azonnal
            feltüntetésre kerül. A dupla sínpárokat az alap feladatból
            kihagyjuk, azaz minden várost csak egy sínpár köt össze.
            Plusz pontért lehet a dupla sínpárokat figyelembe venni:
            ahol dupla sínpár van két város között, oda ugyanaz a
            játékos nem építhet kétszer. Továbbá 1-3 játékos esetén
            pedig csak az egyik sínpár építhető meg, de ezt a szabályt
            is plusz pontért lehet alkalmazni.
          </li>
          <li>
            <b>Új célkártyákat húz:</b> 3 új célkártya húzható, ebből
            legalább 1-et (legfeljebb 3-at) meg kell tartani. Ezt úgy
            egyszerűsítjük, hogy ezt a fázist kihagyjuk, és a játék
            elején mindenki kap 5 célkártyát, amiket teljesíteni kell.
          </li>
        </ol>

        <h3>Vasútkocsi-kártyák:</h3>
        <ul>
          <li>
            lila, fehér, kék, sárga, narancs, fekete, piros, zöld: mindegyikből 12db
          </li>
          <li>
              mozdony: 14db
          </li>
        </ul>
        <p>
          A játék akkor ér véget, ha valamelyik játékos raktárában a
          vagonok száma 2 vagy kevesebb lesz. Ekkor az összes játékosnak
          van még egy utolsó köre, beleértve azt is, akinek először
          lefogyott ennyire. Ezután a pontok kiszámítása következik:
        </p>
        <ul>
          <li>(az utak hosszát menet közben számoljuk;)</li>
          <li>a teljesített célok pontértéke hozzáadásra kerül;</li>
          <li>a nem teljesített célok értéke levonásra kerül;</li>
          <li>a leghosszabb összefüggő út tulajdonosa +10 pontot kap.</li>
        </ul>
        <div className="button-container">
          <button className="custom-btn" onClick={ReturnHome}>
            Vissza
          </button>
        </div>
    </div>
  );
}

export default Rules;
