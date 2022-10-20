import React from "react";
import { useNavigate } from "react-router-dom";
const LandingPageNav = () => {
    const navigate = useNavigate();
  const registerHandle = () => {
    navigate("/register");
  };
  const signInHandle = () => {
    navigate("/signin");
  };
  return (
    <div>
      <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container">
          <a class="navbar-brand me-2" href="https://mdbgo.com/">
            <img
              src={require("./bank-icon.png")}
              height="30"
              alt="MDB Logo"
              loading="lazy"
              style={{marginTop: "-1px"}}
              className="rounded"
            />
            FAIRY BANK
          </a>
          <button
            class="navbar-toggler"
            type="button"
            data-mdb-toggle="collapse"
            data-mdb-target="#navbarButtonsExample"
            aria-controls="navbarButtonsExample"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <i class="fas fa-bars"></i>
          </button>
          <div class="collapse navbar-collapse" id="navbarButtonsExample">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              {/* <li class="nav-item">
                <a class="nav-link" href="/">
                  Home
                </a>
              </li> */}
            </ul>

            <div class="d-flex align-items-center">
              <button type="button" class="btn btn-primary me-2 btn-lg" onClick={signInHandle}>
                Login
              </button>
            </div>
          </div>
        </div>
      </nav>
    </div>
  );
};
export default LandingPageNav;
