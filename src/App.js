import "./App.css";
import React from "react";
import SignIn from "./signIn/signin";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Register from "./register/register";
import DashBoard from "./dashboard/dashBoard";
import AddAccount from "./dashboard/add/AddAccount";
import Transferamount from "./dashboard/transferamount";
import Logout from "./dashboard/logout";
import LandingPage from "./LandingPage";
import RequestPage from "./requestPage";
function App() {
  return (
    <BrowserRouter>
        <Routes>
          <Route path="/" exact element={<LandingPage/>} />
          <Route path="/register" exact element={<Register />} />
          <Route path="/signin" exact element={<SignIn/>}/>
          <Route path="/dashboard" exact element={<DashBoard/>}/>
          <Route path="/dashboard/account" exact element={<AddAccount/>}/>
          <Route path="/dashboard/transferamount" exact element={<Transferamount />}/>
          <Route path="/dashboard/logout" exact element={<Logout/>}/>
          <Route path="/requestAmount" exact element={<RequestPage/>}/>
        </Routes>
    </BrowserRouter>
  );
}

export default App;
