import React, { useState } from "react";
import SideMenu from "./SideMenu";
import Navbar from "./Avatar/navbar";
import axios from "axios";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
const Transferamount = () => {
  const sel = [
    {
      account: "Self account",
      val: 1,
    },
    {
      account: "To account",
      val: 2,
    },
  ];
  const opt = [
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
  const [selectedvalue, setSelectedValue] = useState(1);
  const [amount, setAmount] = useState(0);
  let { selectedAccount, balance } = useSelector((state) => state.reducer);
  const [toAccount, setToAccount] = useState(0);
  const [remark,setRemark]=useState("");
  const [success, setSuccess] = useState(false);
  const [failure, setFailure] = useState(false);
  const [msg, setMsg] = useState("");
  const [title, setTitle] = useState("");
  const [conform, setConform] = useState(false);
  const [successMessage, setSucessMessage] = useState("");
  const handleChanges = (e) => {
    setSelectedValue(parseInt(e.target.value));
  };
  const handleAmount = (e) => {
    setAmount(e.target.value);
  };
  const handleRemark = (e) => {
    setRemark(e.target.value);
  };
  const handleAccountType = (e) => {};
  const handleToAccount = (e) => {
    setToAccount(e.target.value);
  };
  
  const current = new Date();
  const date = `${current.getDate()}-${
    current.getMonth() + 1
  }-${current.getFullYear()}`;
  const dates = `${current.getFullYear()}-${
    current.getMonth() + 1
  }-${current.getDate()}`;
  const payload = {
    date: dates,
    amount: amount,
    fromAccount: selectedAccount,
    toAccount: parseInt(toAccount),
    remark:remark
  };
  const depositPayload = {
    depositAmount: amount,
    date: date,
    remark:remark
  };
  const handleChange = (e) => {
    handleSubmit(e);
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    if (selectedvalue === 2) {
      axios.put("http://localhost:8080/account/transferAmount", payload).then(
        (response) => {
          setSuccess(false);
          document.querySelector(".modal-title").innerHTML="Confirm"
          document.querySelector(".modal-body").innerHTML="Are you sure?";
          setSucessMessage(response.data.msg);
          setSuccess(true);
        },
        (error) => {
          //console.log(error);
          document.querySelector(".modal-title").innerHTML="Failure"
          document.querySelector(".modal-body").innerHTML=error.response.data.msg;
          setFailure(true);

        }
      );
    } else {
      axios
        .put(
          "http://localhost:8080/account/deposit/" + selectedAccount,
          depositPayload
        )
        .then(
          (response) => {
           
          document.querySelector(".modal-title").innerHTML="Confirm"
          document.querySelector(".modal-body").innerHTML="Are you sure?";
          setSucessMessage(response.data.msg);
          setSuccess(true);
          },
          (error) => {
           console.log(error);
            document.querySelector(".modal-title").innerHTML="Failure"
            document.querySelector(".modal-body").innerHTML=error.response.data.msg;
          //document.querySelector(".modal-body").innerHTML=error.response.data.message;
          setFailure(true);
          }
        );
    }
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
            <div className="col py-3 d-flex align-items-center justify-content-center">
              <div className="col-sm-6 p-3 mb-8 bg-light rounded-3">
                <form>
                  <div className="row mb-3">
                    <label
                      for="inputEmail3"
                      className="col-sm-3 col-form-label font-weight-bold"
                    >
                      Deposit
                    </label>
                    <div className="col-sm-9">
                      <select
                        className="form-select"
                        onChange={handleChanges}
                        value={selectedvalue}
                        id="deposit"
                      >
                        {sel.map((data) => (
                          <option key={data.val} value={data.val}>
                            {data.account}
                          </option>
                        ))}
                      </select>
                    </div>
                  </div>
                  {selectedvalue === 2 && (
                    <>
                      <div className="row mb-3">
                        <label
                          for="accountType"
                          className="col-sm-3 col-form-label font-weight-bold"
                        >
                          Account Type:
                        </label>
                        <div className="col-sm-9">
                          <select
                            className="form-select"
                            onChange={handleAccountType}
                            id="accountType"
                          >
                            {opt.map((data) => (
                              <option key={data.value} value={data.value}>
                                {data.name}
                              </option>
                            ))}
                          </select>
                        </div>
                      </div>
                      <div className="row mb-3">
                        <label
                          for="accountNumber"
                          className="col-sm-3 col-form-label font-weight-bold"
                        >
                          Account Number
                        </label>
                        <div className="col-sm-9">
                          <input
                            type="number"
                            id="accountNumber"
                            className="form-control"
                            onChange={handleToAccount}
                          ></input>
                        </div>
                      </div>
                    </>
                  )}
                  <div className="row mb-3">
                    <label
                      for="amount"
                      className="col-sm-3 col-form-label font-weight-bold"
                    >
                      Amount:
                    </label>
                    <div className="col-sm-9">
                      <input
                        type="number"
                        placeholder="amount"
                        className="form-control"
                        id="amount"
                        onChange={handleAmount}
                      ></input>
                    </div>
                  </div>
                  <div
                    className="submit-btn"
                    
                  >
                    <div className="row mb-3">
                    <label
                      for="remark"
                      className="col-sm-3 col-form-label font-weight-bold"
                    >
                      Remark:
                    </label>
                    <div className="col-sm-9">
                      <input
                        type="text"
                        placeholder="remark"
                        className="form-control"
                        id="remark"
                        onChange={handleRemark}
                      ></input>
                    </div>
                  </div>
                  <div
                    className="submit-btn"
                    
                  ></div>
                    <button type="button" className="btn btn-success" data-bs-toggle="modal"
                    data-bs-target="#staticBackdrop" 
                    onClick={handleChange}
                    >
                      Confirm
                    </button>
                  </div>
                </form>
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
                    {success && !failure &&
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
                    }
                    {conform && !failure&& (
                      <button
                        type="button"
                        className="btn btn-success"
                        onClick={() => {
                          nav("/dashboard");
                          setConform(false);
                          setSuccess(false);
                        }}
                        data-bs-dismiss="modal"
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
      </div>
    </>
  );
};

export default Transferamount;
