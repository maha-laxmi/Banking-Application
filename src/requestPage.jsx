import React from "react";
import { useState } from "react";
import Navbar from "./dashboard/Avatar/navbar";
import axios from "axios";
import SideMenu from "./dashboard/SideMenu";
import { useSelector, useDispatch } from "react-redux";
import { updateRequest} from "./switchAccountSlice";
const RequestPage = () => {
  const dispatch = useDispatch();
  const [username, setUserName] = useState("");
  const [amount, setAmount] = useState(0);
  const [listOfUserRequest, setListOfUserRequest] = useState([]);
  const [listOfRequestUser,setListOfRequestUser]=useState([]);
  const { selectedAccount, balance, userName } = useSelector((state) => state.reducer);
  const handleUserName = (e) => {
    setUserName(e.target.value);
  };
  const handleAmount = (e) => {
    setAmount(e.target.value);
  };
  const current = new Date();
  const dates = `${current.getFullYear()}-${
    current.getMonth()+ 1
  }-${current.getDate()}`;
 // console.log(dates);
  const payload = {
    requestAccountNumber: selectedAccount,
    requestUser: userName,
    toUser: username,
    amount: parseFloat(amount),
    date: dates,
    
  };
  const handleRequest = (e) => {
    e.preventDefault();
    axios.post("http://localhost:8080/account/request", payload).then(
      (response) => {
        //console.log(response);
        document.querySelector("#response").innerHTML = response.data.msg;
        let cclass = "alert";
        let sclass = "";

        if (response.status === 200) {
          sclass = "alert-success";
          console.log("c");
        } else {
          sclass = "alert-danger";
          console.log("d");
        }
        document.querySelector("#response").classList.add(cclass);
        document.querySelector("#response").classList.add(sclass);
        setTimeout(() => {
          document.querySelector("#response").innerHTML = "";
          document.querySelector("#response").classList.remove(cclass);
          document.querySelector("#response").classList.remove(sclass);
        }, 5000);
      },
      (error) => {
        console.log("b" + error);
        document.querySelector("#response").innerHTML = error.response.data.msg;
        document.querySelector("#response").classList.add("alert");
        document.querySelector("#response").classList.add("alert-danger");
        setTimeout(() => {
          document.querySelector("#response").innerHTML = "";
          document.querySelector("#response").classList.remove("alert");
          document.querySelector("#response").classList.remove("alert-danger");
        }, 5000);
      }
    );
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
            <div class="accordion " id="accordionFlushExample">
              <div class="accordion-item">
                <h2 class="accordion-header" id="flush-headingOne">
                  <button
                    class="accordion-button collapsed"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#flush-collapseOne"
                    aria-expanded="false"
                    aria-controls="flush-collapseOne"
                  >
                    Generate Request
                  </button>
                </h2>
                <div
                  id="flush-collapseOne"
                  class="accordion-collapse collapse"
                  aria-labelledby="flush-headingOne"
                  data-bs-parent="#accordionFlushExample"
                >
                  <div class="accordion-body">
                    {" "}
                    <div role="alert" id="response"></div>
                    <div className="col-sm-6 p-3 mb-8  bg-light rounded-3">
                      <form>
                        <div className="row mb-3">
                          <label
                            for="inputEmail3"
                            className="col-sm-3 col-form-label font-weight-bold"
                          >
                            UserName:
                          </label>
                          <div className="col-sm-9">
                            <input
                              type="text"
                              placeholder="username"
                              className="form-control"
                              id="userName"
                              value={username}
                              onChange={handleUserName}
                            ></input>
                          </div>
                        </div>
                        <div className="row mb-3">
                          <label
                            for="inputEmail3"
                            className="col-sm-3 col-form-label font-weight-bold"
                          >
                            Amount:
                          </label>
                          <div className="col-sm-9">
                            <input
                              type="number"
                              placeholder="amount"
                              className="form-control"
                              value={amount}
                              id="amount"
                              onChange={handleAmount}
                            ></input>
                          </div>
                        </div>
                        <button
                          type="button"
                          className="btn btn-success"
                          onClick={handleRequest}
                        >
                          Request
                        </button>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="accordion-item">
              <h2 class="accordion-header" id="flush-headingTwo">
                <button
                  class="accordion-button collapsed"
                  type="button"
                  data-bs-toggle="collapse"
                  data-bs-target="#flush-collapseTwo"
                  aria-expanded="false"
                  aria-controls="flush-collapseTwo"
                  onClick={() => {
                    axios
                      .get(`http://localhost:8080/getRequest/${userName}`)
                      .then(
                        (response) => {
                          //console.log(response);
                          dispatch(updateRequest(response.data));
                          setListOfUserRequest(response.data);
                        },
                        (error) => {
                          console.log(error);
                        }
                      );
                  }}
                >
                  View Request
                </button>
              </h2>
              <div
                id="flush-collapseTwo"
                class="accordion-collapse collapse"
                aria-labelledby="flush-headingTwo"
                data-bs-parent="#accordionFlushExample"
              >
                <div class="accordion-body">
                  <div class="row row-cols-1 row-cols-md-3 g-4 ">
                         {listOfUserRequest.map((item, index) => {
                      return (
                        <div class="col">
                           <div class="card h-100">
                            <div class="card-body" key={index}>
                              <div id={index}></div>

                              <h4 class="card-title">{item.requestUser}</h4>
                              <h6 className="card-title">{item.amount}</h6>
                              <p class="card-text">{item.description}</p>
                              <button
                                type="button"
                                className="btn btn-success "
                                onClick={() =>
                                  axios
                                    .put(
                                      "http://localhost:8080/acceptUserRequest",
                                      {
                                        toUserAccountNumber: selectedAccount,
                                        requestId: parseInt(item.requestId),
                                      }
                                    )
                                    .then(
                                      (response) => {
                                        console.log(response);
                                        if (response.status === 202) {
                                          axios
                                            .get(
                                              `http://localhost:8080/getRequest/${userName}`
                                            )
                                            .then(
                                              (response) => {
                                                console.log(response);
                                                setListOfUserRequest(
                                                  response.data
                                                );
                                              },
                                              (error) => {
                                                console.log(error);
                                              }
                                            );
                                          alert(response.data.msg);
                                        } else {
                                          alert(response.data.msg);
                                        }
                                      },
                                      (error) => {
                                        console.log(error);
                                        alert(error.response.data.message);
                                      }
                                    )
                                }
                              >
                                <i class="bi bi-check-lg"></i>
                              </button>
                              <button
                                type="button"
                                className="btn btn-danger ms-3"
                                onClick={() =>
                                  axios
                                    .post(
                                      "http://localhost:8080/rejectRequest",
                                      {
                                        requestId: parseInt(item.requestId),
                                      }
                                    )
                                    .then(
                                      (response) => {
                                        console.log(response);
                                        axios
                                          .get(
                                            `http://localhost:8080/getRequest/${userName}`
                                          )
                                          .then(
                                            (response) => {
                                              //console.log(response);
                                              setListOfUserRequest(
                                                response.data
                                              );
                                            },
                                            (error) => {
                                              //console.log(error);
                                            }
                                          );
                                      },
                                      (error) => {
                                        console.log(error);
                                      }
                                    )
                                }
                              >
                                <i class="bi bi-x"></i>
                              </button>
                            </div>
                          </div>
                          
                          
                         
                          
                        </div>
                      );
                    })}
                     
                  </div>
                </div>
              </div>
            </div>
            <div class="accordion-item">
              <h2 class="accordion-header" id="headingThree">
                <button
                  class="accordion-button collapsed"
                  type="button"
                  data-bs-toggle="collapse"
                  data-bs-target="#collapseThree"
                  aria-expanded="false"
                  aria-controls="collapseThree"
                  onClick={() => {
                    axios
                      .get(`http://localhost:8080/getRequestUser/${userName}`)
                      .then(
                        (response) => {
                          //console.log(response);
                          setListOfRequestUser(response.data);
                        },
                        (error) => {
                          //console.log(error);
                        }
                      );
                  }}
                >
                  Transaction Details
                </button>
              </h2>
              <div
                id="collapseThree"
                class="accordion-collapse collapse"
                aria-labelledby="headingThree"
                data-bs-parent="#accordionExample"
              >
                <div class="accordion-body">
                  <div className="row">
                    <div className="col-sm-12">
                      <table className="table table-striped">
                        <thead>
                          <tr>
                            <th scope="col">Date</th>
                            <th scope="col">Request To</th>
                            <th scope="col">Request Amount</th>
                            <th scope="col">Status</th>
                          </tr>
                        </thead>

                        <tbody>
                        {listOfRequestUser.map((val, key) => {
                         return (
                        <tr key={key} scope="row">
                          <td>{val.date}</td>
                          <td>{val.toUser}</td>
                          <td>{val.amount}</td>
                          <td>
                            {val.status}
                          </td>
                        </tr>
                      );
                    })}
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        {/* <Footer></Footer> */}
      </div>
    </>
  );
};
export default RequestPage;
