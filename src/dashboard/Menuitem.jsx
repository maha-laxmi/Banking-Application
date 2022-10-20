import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
const MenuItem = (props) => {
  const { listOfAccount } = useSelector((state) => state.reducer);
  const { name, subMenus, iconClassName, to, onClick,length } = props;
  const navigate = useNavigate();
  const MenuHandler = () => {
    if(listOfAccount.length>=1 || to=="/dashboard/logout"){
        navigate(to);
    }
    else{
      navigate("/dashboard/account");
    }

  };
  return (
    <li>
      <a data-bs-toggle="collapse" className="nav-link px-0 align-middle">
        <i className={iconClassName}></i>
        <span onClick={MenuHandler} className="ms-1 d-none d-sm-inline">
        {name} {'\u00A0'}
          {(name === "Request" && length>0) &&<span class="badge bg-danger">{length}</span> }
        </span>
      </a>
    </li>
  );
};

export default MenuItem;
