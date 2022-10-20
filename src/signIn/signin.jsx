import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { useDispatch } from "react-redux";
import {
  updateAccounts,
  loggedUserId,
  updateUserName,
} from "../switchAccountSlice";
const Signin = () => {
  const dispatch = useDispatch();
  const [userName, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loginId, setLoginId] = useState("");

  const handleUsername = (e) => {
    setUsername(e.target.value);
    if (e.target.value !== "")
      document.getElementById("username").style.borderColor = "green";
    else document.getElementById("username").style.borderColor = "red";
  };

  const handlePassword = (e) => {
    setPassword(e.target.value);
    if (e.target.value === "")
      document.getElementById("password").style.borderColor = "red";
    else document.getElementById("password").style.borderColor = "green";
  };
  const navigate = useNavigate();
  const payload = {
    userName: userName,
    password: password,
  };
  const signHandle = (e) => {
    if (userName !== " " && password !== " ") {
      e.preventDefault();
      axios.post("http://localhost:8080/customer/signin", payload).then(
        (response) => {
          // console.log(payload.userName);
          dispatch(updateUserName(payload.userName));
          console.log("signIn" + response);
          setLoginId(response.data.id);
          let alert = "alert";
          let alertMsg = "";
          dispatch(loggedUserId(response.data.id));
          if (response.status === 202) {
            axios
              .post("http://localhost:8080/listofaccount", {
                loginDetailId: parseInt(response.data.id),
              })
              .then(
                (response) => {
                  console.log(response);
                  dispatch(updateAccounts(response.data));
                  if (response.length === 0) {
                    navigate("/dashboard/account");
                  } else {
                    navigate("/dashboard");
                  }
                },
                (error) => {
                  console.log(error);
                }
              );
            if (response.status === 200) {
              alert = "alert-success";
              console.log("c");
            } else {
              alert = "alert-danger";
              console.log("d");
            }
            document.querySelector("#response").classList.add(alertMsg);
            document.querySelector("#response").classList.add(alert);
            setTimeout(() => {
              document.querySelector("#response").innerHTML = "";
              document.querySelector("#response").classList.remove(alertMsg);
              document.querySelector("#response").classList.remove(alert);
            }, 5000);
          }
        },
        (error) => {
          console.log(error);
          document.querySelector("#response").innerHTML =
            error.response.data.message;
          document.querySelector("#response").classList.add("alert");
          document.querySelector("#response").classList.add("alert-danger");
          setTimeout(()=>{
            document.querySelector("#response").innerHTML ="";
            document.querySelector("#response").classList.remove("alert");
            document.querySelector("#response").classList.remove("alert-danger");
          },5000);
          if (error.status !== 202) {
            navigate("/signin");
          }
        }
      );
    }
  };
  return (
    <section className="vh-100">
      <div className="container py-5 h-100">
        <div className="row d-flex justify-content-center align-items-center h-100">
          <div className="col col-xl-10">
            <div className="card" style={{ borderRadius: "1rem" }}>
              <div className="row g-0">
                <div className="col-md-6 col-lg-5 d-none d-md-block">
                  {/* <img
                    src={require("./photo.jpg")}
                    alt="login form"
                    className="img-fluid"
                    height="200"
                    style={{ borderRadius: "1rem 0 0 1rem" }}
                  /> */}
                </div>

                <div className="col-md-6 col-lg-7 d-flex align-items-center">
                  <div className="card-body p-4 p-lg-5 text-black">
                    <div id="response" role="alert"></div>
                    <form>
                      <div className="d-flex align-items-center mb-3 pb-1">
                        {/* <img
                          src={require("./bank-icon.png")}
                          className="image"
                          alt="iconimages"
                          height="40"
                        />{" "} */}
                        <span className="h1 fw-bold mb-0">FAIRY BANK</span>
                      </div>

                      <h4
                        className="fw-normal mb-3 pb-3"
                        style={{ letterSpacing: "1px" }}
                      >
                        Sign into your account
                      </h4>

                      <div className="form-outline mb-4">
                        <input
                          type="text"
                          placeholder="Username"
                          name="username"
                          value={userName}
                          id="username"
                          onChange={handleUsername}
                          className="form-control form-control-lg"
                        />
                      </div>

                      <div className="form-outline mb-4">
                        <input
                          type="password"
                          placeholder="Password"
                          name="password"
                          value={password}
                          id="password"
                          onChange={handlePassword}
                          className="form-control form-control-lg"
                        />
                      </div>

                      <div className="border border-light p-3 mb-4">
                        <button
                          className="btn btn-dark btn-lg btn-block"
                          type="button"
                          onClick={signHandle}
                        >
                          Login
                        </button>
                      </div>
                      <p>
                        Don't have an account?{" "}
                        <a href="/register" className="link-info">
                          Register here{" "}
                        </a>
                      </p>
                      <a href="/" className="link-info">
                        Home
                      </a>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        
      </div>
    </section>
  );
};

export default Signin;
