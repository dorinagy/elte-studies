import { useSelector } from "react-redux";
import { useEffect, useRef, useState } from "react";
import map from "../../../../res/ticket-to-ride-europe-map.jpg";

import "./Map.css";

const MAX_WIDTH = 900;
const MAX_HEIGHT = 600;

const Map = (props) => {
  
  const cities = useSelector((state) => state.gameData.cities);
  const ldPair = useSelector((state) => state.gameState?.ldPair);

  const canvas = useRef(null);
  const [image, setImage] = useState(null);

  useEffect(() => {
    const tableimg = new Image();
    tableimg.src = map;
    tableimg.onload = () => setImage(tableimg);
}, []);

  useEffect(() => {
      if (image && canvas) {
          const ctx = canvas.current.getContext("2d");
          ctx.drawImage(image, 0, 0);
      }
      if (ldPair) {
          let from;
          let to;
          
          for (const city in cities) {
              if(city === ldPair[0]) {
                  from = cities[city];
              }
              if (city === ldPair[1]) {
                  to = cities[city];
              }
          }
          
          const fromX = (from?.x * MAX_WIDTH)/100;
          const fromY = (from?.y * MAX_HEIGHT)/100;
          const toX = (to?.x * MAX_WIDTH)/100;
          const toY = (to?.y * MAX_HEIGHT)/100;

          const ctxFrom = canvas.current.getContext("2d");
          ctxFrom.beginPath();
          ctxFrom.arc(fromX,fromY,15,0, 2 * Math.PI);
          ctxFrom.fillStyle = "red";
          ctxFrom.fill();
          ctxFrom.stroke();
          const ctxTo = canvas.current.getContext("2d");
          ctxTo.beginPath();
          ctxTo.arc(toX,toY,15,0, 2 * Math.PI);
          ctxTo.fillStyle = "blue";
          ctxTo.fill();
          ctxTo.stroke();

      }
  }, [image,canvas,ldPair,cities]);


  const clickHandler = (e) => {
      const x = Math.round((e.nativeEvent.offsetX / MAX_WIDTH)*100);
      const y = Math.round((e.nativeEvent.offsetY / MAX_HEIGHT)*100);

      for (const city in cities) {
          const cityX = (Math.round(cities[city].x));
          const cityY = (Math.round(cities[city].y))
          if ((cityX === x || (cityX >= x-3 && cityX <= x+3)) && (cityY === y || (cityY >= y-3 && cityY <= y+3))) {
              console.log("TALÁLAT " + cities[city].city);
              break;
          }
      }
  }
  return (
    <div className="game-map">
        <canvas ref={canvas} className="map-img" width={MAX_WIDTH} height={MAX_HEIGHT} onClick={clickHandler}/>
    </div>
  );
};

export default Map;
