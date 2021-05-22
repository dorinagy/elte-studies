import "./App.css";
import React from "react";
import Home from "./components/home/Home";
import Rules from "./components/rules/Rules";
import Game from "./components/game/Game";
import WaitingRoom from "./components/waiting-room/WaitingRoom";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

function App() {
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
