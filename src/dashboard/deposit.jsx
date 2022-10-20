import React, { useEffect, useState } from "react";
import Header from "../Header/header";
const Deposit = () => {
  const [option, setOption] = useState([]);
  const [select, setSelect] = useState([]);
  useEffect(() => {
    let opt = [
      {
        name: "Saving",
        value: 1,
      },
      {
        name: "Current",
        value: 2,
      },
    ];
    setOption(opt);
  }, []);
  useEffect(() => {
    let sel = [
      {
        account: "Self account",
        val: 1,
      }
    ];
    setSelect(sel);
  }, []);
  return (
    <div className="deposit">
      <Header></Header>
      <form className="deposit-cont">
        <h3 className="dep-header">Make Deposit</h3>
        {/* <p className='deposit-main'>Account Number:</p>
        <input type="number"></input> */}
        <p className="deposit-main">Select account type:</p>
        <select className="deposit">
          {option.map((data) => (
            <option key={data.value} value={data.value}>
              {data.name}
            </option>
          ))}
        </select>
        <p className="deposit-main">Deposit</p>
        <select className="deposit">
          {select.map((data) => (
            <option key={data.val} value={data.val}>
              {data.account}
            </option>
          ))}
        </select>
        <p className="deposit-main">Enter an amount to deposit:</p>
        <input type="number" placeholder="amount" className="deposit"></input>
        <div className="submit-btn">
          <button type="submit">Confirm</button>
        </div>
      </form>
    </div>
  );
};

export default Deposit;
