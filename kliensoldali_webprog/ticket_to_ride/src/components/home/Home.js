import "./Home.css";
import logo from "../../res/logo.png";
import Room from "./Room";
import React, { useState } from "react";
import { Redirect } from "react-router-dom";

function Home() {
  const [submitted, setSubmitted] = useState(false);
  
  const redirectToRules = e => {
    e.preventDefault();
    setSubmitted(true);
  };

  if (submitted) {
    return (
      <Redirect push to={{ pathname: "/rules" }}/>
    );
  }

  return (
    <div className="home">
      <img src={logo} alt={"logo"} className="logo"/>
      <Room/>
      <button
        onClick={redirectToRules}
        className="custom-btn large"
      >
        Játék Szabályok
      </button>
    </div>
  );
}

export default Home;
