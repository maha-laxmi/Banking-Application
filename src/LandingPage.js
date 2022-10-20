import React from "react";
import LandingPageNav from "./landingpage-nav";
import { useNavigate } from "react-router-dom";
const LandingPage = () => {
  const navigate = useNavigate();
  const registerHandle = () => {
    navigate("/register");
  };
  return (
    <div className="landpage">
      <LandingPageNav />
      <div className="section">
        <div className="content">
          <div className="content-main">
            <h2>WELCOME TO FAIRY BANK!!</h2>
            <p>CROSS-PLATFORM SECURE BANKING APPLICATION</p>
            <hr className="hori" />
            <p>
              Fairy bank have fast transaction,easy to deposit and secure
              banking .
            </p>
            <div>
              <button
                class="btn btn-success  me-2 btn-lg"
                onClick={registerHandle}
              >
                REGISTER
              </button>
            </div>
          </div>
          <img
            src={require("./ph.png")}
            class="shadow p-3 mb-5 bg-#BBBD80 rounded"
            style={{
              maxWidth: "22rem",
              height: "26rem",
              borderRadius: "3px",
              paddingTop: "8rem",
              float: "right",
            }}
          />
        </div>
      </div>
    </div>
  );
};
export default LandingPage;
