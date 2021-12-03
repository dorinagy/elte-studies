import "./App.css";
import React from "react";
import socket from "./socket/socket";
import Home from "./components/home/Home";
import Rules from "./components/rules/Rules";
import Game from "./components/game/Game";
import { useDispatch } from "react-redux";
import { updateGameState } from "./redux/actions";
import WaitingRoom from "./components/waiting-room/WaitingRoom";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

function App() {

  const dispatch = useDispatch();

  socket.on("state-changed", (msg) => {
      const sync = msg?.state;
      dispatch(updateGameState(sync));
  });

  return (
    <Router>
      <Switch>
        <Route exact path="/" component={Home}/>
        <Route exact path="/game" component={Game}/>
        <Route exact path="/rules" component={Rules}/>
        <Route exact path="/waiting-room" component={WaitingRoom}/>
      </Switch>
    </Router>
  );
}

export default App;
