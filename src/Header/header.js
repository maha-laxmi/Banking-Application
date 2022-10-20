import React from "react";

const header = () => {
  return (
    <div>
      <h2 className="header">
        <img src={require("./bank-icon.png")} className="image" alt="iconimages" />{" "}
        FAIRY BANK
      </h2>
    </div>
  );
};

export default header;
