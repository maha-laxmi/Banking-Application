import React, { useEffect, useState } from "react";
import MenuItem from "./Menuitem";
import { useSelector } from "react-redux";
const menuItem = [
  {
    name:"Dashboard",
    to:"/dashboard",
    iconClassName:"fs-4 bi-speedometer2"
  },
  {
    name: "Account Operation",
    to: "/dashboard/account",
    iconClassName: "bi bi-person-square",
  },
  {
    name: "Transfer amount",
    to: "/dashboard/transferamount",
    iconClassName: "bi bi-arrow-left-right",
  },
  {
    name:"Request",
    to:"/requestAmount",
    iconClassName:"bi bi-arrow-up-right-square-fill",
  },
  { name: "Logout", to: "/signin",iconClassName: "bi bi-box-arrow-right" },
];

const SideMenu = () => {
  const [inactive, setInactive] = useState(true);
  const arrowHandle = () => {
    setInactive(!inactive);
  };
  useEffect(() => {
    if (inactive) {
      document.querySelectorAll("#sidebarCollapse").forEach((el) => {
        el.classList.remove("active");
      });
    }
  }, [inactive]);
  const { listOfRequest } = useSelector(
    (state) => state.reducer
  );
  let length=listOfRequest.length;
  return (

<div className="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
          <div className="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">

          <ul className="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start"
              id="menu">
            
            {menuItem.map((menuItem, index) => {
            return (<MenuItem
              key={index}
              name={menuItem.name}
              to={menuItem.to}
              iconClassName={menuItem.iconClassName}  
              length={length}            
            />)
            }
              
          )}
          </ul>

</div>
</div>
  );
};

export default SideMenu;
