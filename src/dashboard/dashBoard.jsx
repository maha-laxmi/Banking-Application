import React from "react";
import SideMenu from "./SideMenu";
import Navbar from "./Avatar/navbar";
import { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { updateBalances, updateRequest } from "../switchAccountSlice";
import axios from "axios";
const DashBoard = () => {
  const { selectedAccount ,userName,balance} = useSelector((state) => state.reducer);
  const dispatch = useDispatch();
  const [data, setData] = useState([]);

  useEffect(() => {
    axios
      .get(
        `http://localhost:8080/account/transactionhistory/${selectedAccount}`
      )
      .then(
        (response) => {
         // console.log(response);
          setData(response.data);
         
        },
        (error) => {
          console.log(error);
        }
      );
  }, [selectedAccount]);
  useEffect(()=>{
    axios
    .get(`http://localhost:8080/getRequest/${userName}`)
    .then(
      (response) => {
        dispatch(updateRequest(response.data));
      },
      (error) => {
        console.log(error);
      }
    );
  },[]);
  return (
    <>
      <Navbar/>
      <div className="container-fluid">
        <div className="row flex-nowrap">
          <SideMenu />
          <div className="col py-3">
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

            <div className="row">
              <div className="col-sm-12">
                <table className="table table-striped">
                  <thead>
                    <tr>
                      <th scope="col">Date</th>
                      <th scope="col">Transaction Id</th>
                      <th scope="col">Credit </th>
                      <th scope="col">Debit</th>
                      <th scope="col">Balance</th>
                      <th scope="col">Description & Remark</th>
                    </tr>
                  </thead>

                  <tbody>
                    {data.map((val, key) => {
                      return (
                        <tr key={key} scope="row">
                          <td>{val.date}</td>
                          <td>{val.transactionId}</td>
                          <td>{val.credit}</td>
                          <td>{val.debit}</td>
                          <td>{val.balance}</td>
                          <td>
                           {val.description}
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
    </>
  );
};

export default DashBoard;
