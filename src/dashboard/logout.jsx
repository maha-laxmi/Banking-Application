import React from 'react'
import Header from '../Header/header'
const logout = () => {
  return (
    <div>
      <Header ></Header>
      <div className='welcome-main'>
          <a href='/signIn' className='btn btn-warning'>LOGIN</a>
          <a href='/' className='btn btn-dark' style={{marginLeft:"30px"}}>HOME</a>
      </div>
      <img src={require("./icons8-approval.gif")} className="tick-main"/>
      <h6 className='logout-cont'>You have been logged out</h6>
      <p className='logout-thank'>Thank You</p>
    </div>
  )
}

export default logout