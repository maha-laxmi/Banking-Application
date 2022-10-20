import React from "react";
import "../App.css";
import { useNavigate } from "react-router-dom";
import Header from "../Header/header";
const Homepage = () => {
  const navigate = useNavigate();
  const registerHandle = () => {
    navigate("/register");
  };
  const signInHandle = () => {
    navigate("/signin");
  };
  return (
    <div>
      <Header></Header>
      <img src={require("./ph.png")} className="img" />
      <h1>WELCOME TO FAIRY BANK!!</h1>
      <p className="para">CROSS-PLATFORM SECURE BANKING APPLICATION</p>
      <hr className="hori" />
      <p className="description">
        Fairy bank have fast transaction,easy to deposit and secure banking .
      </p>
      <button className="register_button" onClick={registerHandle}>
        {" "}
        REGISTER
      </button>
      <button className="login_button" onClick={signInHandle}>
        SIGN IN
      </button>
    </div>
  );
};

export default Homepage;
