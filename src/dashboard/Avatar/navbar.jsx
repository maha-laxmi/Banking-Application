import React, { useState } from "react";
import { useEffect } from "react";
import axios from "axios";
import { useSelector, useDispatch } from "react-redux";
import { updateSelectAccount, updateBalances } from "../../switchAccountSlice";
const Navbar = () => {
  const [click, setclick] = useState(false);
  const [dropdown, setDropdown] = useState(false);

  let { selectedAccount, listOfAccount, userName } = useSelector(
    (state) => state.reducer
  );
  let dispatch = useDispatch();

  useEffect(() => {
    checkAccount();
  }, []);
  useEffect(() => {
    axios.get(`http://localhost:8080/balance/${selectedAccount}`).then(
      (response) => {
        //console.log(response);
        dispatch(updateBalances(response.data.balance));
      },
      (error) => {
        console.log(error);
      }
    );
  },[selectedAccount]);
  const checkAccount = () => {
    //console.log(listOfAccount.length);
    if (listOfAccount.length > 0 && selectedAccount === 0) {
      dispatch(updateSelectAccount(listOfAccount[0].accountNumber));
    }
  };
  const onMouseEnter = () => {
    if (window.innerWidth < 960) {
      setDropdown(false);
    } else {
      setDropdown(true);
    }
  };

  const onMouseLeave = () => {
    if (window.innerWidth < 960) {
      setDropdown(false);
    } else {
      setDropdown(false);
    }
  };
  let select;
  return (
    <>
      <nav className="navbar navbar-fixed-top navbar-dark bg-dark ">
        <div className="container-fluid">
          <div className="header-mini">
            <h4 className="header-mini">
              FAIRY BANK
              <img
                src={require("./bank-icon.png")}
                className="image-header"
                alt="iconimages"
              />{" "}
            </h4>
          </div>
          <h5 className="text-white">{userName}</h5>
          <ul className="navbar-nav">
            <li
              className="nav-item dropdown"
              onMouseLeave={onMouseLeave}
            >
              <a
                href="#"
                id="dropdown-switch"
                className="nav-link dropdown-toggle"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                role="button"
              >
                Switch account
              </a>
              <ul className="dropdown-menu" aria-labelledby="dropdown-switch">
                {listOfAccount.map((item, index) => {
                  // console.log(item);
                  return (
                    <li
                      key={index}
                      onClick={() => {
                        dispatch(updateSelectAccount(item.accountNumber));
                      }}
                    >
                      <a href="#" className="dropdown-item">
                        {item.accountNumber}
                      </a>
                    </li>
                  );
                })}
              </ul>
            </li>
          </ul>
        </div>
      </nav>
    </>
  );
};

export default Navbar;
