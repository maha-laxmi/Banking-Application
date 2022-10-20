import React from "react";
import SideMenu from "../SideMenu";
import Navbar from "../Avatar/navbar";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { updateAccounts } from "../../switchAccountSlice";
import axios from "axios";
const AddAccount = () => {
  const option = [
    {
      name: "Saving",
      value: 1,
    },
    {
      name: "Current",
      value: 2,
    },
  ];
  const nav = useNavigate();

  const [bal, setBalance] = useState(" ");
  const [accountType, setAccounttype] = useState(option[0].name);
  const [accountNumber, setAccountNumber] = useState("");
  const [success, setSuccess] = useState(false);
  const [failure, setFailure] = useState(false);
  const [msg, setMsg] = useState("");
  const [title, setTitle] = useState("");
  const [conform, setConform] = useState(false);
  const [successMessage, setSucessMessage] = useState("");
  const dispatch = useDispatch();
  const { loggedUserId, selectedAccount, balance } = useSelector(
    (state) => state.reducer
  );
  const handleAccountType = (e) => {
    setAccounttype(e.target.value);
  };

  const handleBalance = (e) => {
    setBalance(e.target.value);
  };
  const handleAccountNumber = (e) => {
    if (e.target.value !== accountNumber.match(accountNumber)) {
      setAccountNumber(e.target.value);
      document.getElementById("accountNumber").style.borderColor = "green";
    } else {
      document.getElementById("accountNumber").style.borderColor = "red";
    }
  };

  const payload = {
    accountType: accountType,
    accountNumber:parseFloat(accountNumber), 
    initialAmount: parseInt(bal),
    loginId: loggedUserId,
  };
  const loginIdPayload = {
    loginDetailId: parseInt(loggedUserId),
  };
  const handleChange = (e) => {
    submitHandle(e);
  };

  const submitHandle = (e) => {
    e.preventDefault();
    console.log(payload);
    axios.post("http://localhost:8080/account/add", payload).then(
      (response) => {
        var suc = response.data.msg;
        setSuccess(false);
        if (response.status === 200) {
          axios
            .post("http://localhost:8080/listofaccount", {
              loginDetailId: parseInt(loggedUserId),
            })
            .then(
              (response) => {
                document.querySelector(".modal-title").innerHTML = "Confirm";
                document.querySelector(".modal-body").innerHTML =
                  "Are you sure? ";
                setSucessMessage(suc);
                setSuccess(true);

                if (response.length === 0) {
                  nav("/dashboard/account");
                } else {
                  dispatch(updateAccounts(response.data));
                }
              },
              (error) => {
                console.log(error);
                document.querySelector(".modal-title").innerHTML = "Failure";
                document.querySelector(".modal-body").innerHTML =
                  error.response.data.message;
                setFailure(true);
              }
            );
        }
      },
      (error) => {
        console.log(error);
        document.querySelector(".modal-title").innerHTML = "Failure";
        document.querySelector(".modal-body").innerHTML =
          error.response.data.message;
         
        setFailure(true);
      }
    );
  };
  const handleContent = (e) => {
    let val=`<p>${successMessage}</p>`;
    document.querySelector(".modal-body").innerHTML = val;
    document.querySelector(".modal-title").innerHTML = "Success";
  };
  return (
    <>
      <Navbar />
      <div className="container-fluid">
        <div className="row flex-nowrap">
          <SideMenu />
          <div className="col py-3 ">
            <div className="row">
              <div className="col-sm-6">
                <div className="card bg-c-blue order-card">
                  <div className="card-block">
                    <h6 className="m-b-20">Account Number</h6>
                    <h4 className="text-center">
                      <i className="bi bi-person-plus-fill"> </i>
                      <span>{selectedAccount}</span>
                    </h4>
                  </div>
                </div>
              </div>

              <div className="col-sm-6">
                <div className="card bg-c-green order-card">
                  <div className="card-block">
                    <h6 className="m-b-20">Balance</h6>
                    <h4 className="text-center">
                      <i className="bi bi-cash"> </i>
                      <span>{balance}</span>
                    </h4>
                  </div>
                </div>
              </div>
            </div>
            <div className="row d-flex align-items-center justify-content-center">
              <div className="col-sm-6 p-3 mb-8 bg-light rounded-3 ">
                <form>
                  <div className="row mb-3">
                    <label
                      for="inputEmail3"
                      className="col-sm-3 col-form-label font-weight-bold"
                    >
                      Account Number
                    </label>
                    <div className="col-sm-9">
                      <input
                        type="text"
                        placeholder="accountNo"
                        className="form-control"
                        id="accountNumber"
                        name="accountNumber"
                        value={accountNumber}
                        onChange={handleAccountNumber}
                      />
                    </div>
                  </div>
                  <div className="row mb-3">
                    <label
                      for="inputPassword3"
                      className="col-sm-3 col-form-label font-weight-bold"
                    >
                      Account Type
                    </label>
                    <div className="col-sm-9">
                      <select
                        className="form-select"
                        id="accountType"
                        name="accountType"
                        value={accountType}
                        onChange={handleAccountType}
                      >
                        {option.map((data) => (
                          <option key={data.value} value={data.name}>
                            {data.name}
                          </option>
                        ))}
                      </select>
                    </div>
                  </div>
                  <div className="row mb-3">
                    <label
                      for="balance"
                      className="col-sm-3 col-form-label font-weight-bold"
                    >
                      Initial Amount:
                    </label>
                    <div className="col-sm-9">
                      <input
                        type="number"
                        placeholder="amount"
                        className="form-control"
                        id="bal"
                        value={bal}
                        onChange={handleBalance}
                      ></input>
                    </div>
                  </div>
                  <button
                    type="button"
                    className="btn btn-success"
                    data-bs-toggle="modal"
                    data-bs-target="#staticBackdrop"
                    onClick={handleChange}
                  >
                    Confirm
                  </button>
                </form>
              </div>
            </div>
          </div>
          <div
            className="modal fade"
            id="staticBackdrop"
            data-bs-backdrop="static"
            data-bs-keyboard="false"
            tabindex="-1"
            aria-labelledby="staticBackdropLabel"
            aria-hidden="true"
          >
            <div className="modal-dialog modal-dialog-centered">
              <div className="modal-content">
                <div className="modal-header">
                  <h5 className="modal-title" id="staticBackdropLabel">
                    {title}
                  </h5>
                  <button
                    type="button"
                    className="btn-close"
                    data-bs-dismiss="modal"
                    aria-label="Close"
                  ></button>
                </div>
                <div className="modal-body">{msg}</div>
                <div className="modal-footer">
                  {success && !failure && (
                    <>
                      <button
                        type="button"
                        className="btn btn-secondary"
                        onClick={() => {
                          handleContent();
                          setConform(true);
                          setSuccess(false);
                        }}
                      >
                        Yes
                      </button>
                      <button
                        type="button"
                        className="btn btn-primary"
                        data-bs-dismiss="modal"
                      >
                        No
                      </button>
                    </>
                  )}
                  {conform && !failure && (
                    <button
                      type="button"
                      className="btn btn-success"
                      onClick={() => {
                        nav("/dashboard");
                        setConform(false);
                        setSuccess(false);
                      }}
                      data-bs-toggle="modal"
                    >
                      OK
                    </button>
                  )}
                  {failure && (
                    <button
                      type="button"
                      className="btn btn-danger"
                      onClick={() => {
                        setFailure(false);
                        setSuccess(false);
                      }}
                      data-bs-dismiss="modal"
                    >
                      OK
                    </button>
                  )}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default AddAccount;
