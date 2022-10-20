import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../App.css";
import Header from "../Header/header";

const Register = () => {
  const [firstName, setfirstName] = useState("");
  const [lastname, setLastName] = useState("");
  const [fathername, setFathername] = useState("");
  const [mothername, setMothername] = useState("");
  const [date, setDate] = useState("");
  const [telephone, setTelephone] = useState("");
  const [aadharCard, setaadharCard] = useState("");
  const [option, setOption] = useState("");
  const [district, setDistrict] = useState("");
  const [userName, setUsername] = useState("");
  const [password, setpassword] = useState("");
  const [states, setStates] = useState([]);
  const [msg, setMsg] = useState("");
  const [title, setTitle] = useState("");
  const [failure, setFailure] = useState(false);
  const [success, setSuccess] = useState(false);
  const [conform,setConform]=useState(false);
  const [successMessage, setSucessMessage] = useState("");
  const ValidatePwd=()=>{
    var error=document.getElementById("Error");
    var pword=document.getElementById("password").value;
    var regexpwd="/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}";
    if(!regexpwd.match(pword)){
      error.innerHTML=" ";
      return true;
    }
    else{
      error.innerHTML="Example:Test@123";
      return false;
    }
   
    
  };
  function ValidateDOB() {
    var lblError = document.getElementById("lblError");
    var dateString = document.getElementById("date").value;
    var regex=/^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
    if (regex.test(dateString)) {
        var parts = dateString.split("-");
        var dtDOB = new Date(parts[2] + "-" + parts[1] + "-" + parts[0]);
        var dtCurrent = new Date();
        lblError.innerHTML = "Eligibility 18 years ONLY."
        if (dtCurrent.getFullYear() - dtDOB.getFullYear() < 18) {
            return false;
        }
  
        if (dtCurrent.getFullYear() - dtDOB.getFullYear() == 18) {
            if (dtCurrent.getMonth() < dtDOB.getMonth()) {
                return false;
            }
            if (dtCurrent.getMonth() == dtDOB.getMonth()) {
                if (dtCurrent.getDate() < dtDOB.getDate()) {
                    return false;
                }
            }
        }
        lblError.innerHTML = "";
        return true;
    } else {
        lblError.innerHTML = "Enter date in yyyy-mm-dd format ONLY."
        return false;
    }
  }
  const areAllFieldsFilled =
    firstName !== "" &&
    lastname !== "" &&
    aadharCard !== "" &&
    userName !== "" &&
    password !== "";
  const navigate = useNavigate();
  useEffect(() => {
    let data = [
      {
        name: "Andhra Pradesh",
        value: 1,
      },
      {
        name: "Assam",
        value: 2,
      },
      {
        name: "Bihar",
        value: 3,
      },
      {
        name: "Chandigarh",
        value: 4,
      },
      {
        name: "Chhattisgarh",
        value: 5,
      },
      {
        name: "Dadar and Nagar Haveli",
        value: 6,
      },
      {
        name: "Daman and Diu",
        value: 7,
      },
      {
        name: "Delhi",
        value: 8,
      },
      {
        name: "Lakshadweep",
        value: 9,
      },
      {
        name: "Puducherry",
        value: 10,
      },
      {
        name: "Goa",
        value: 11,
      },
      {
        name: "Gujarat",
        value: 12,
      },
      {
        name: "Haryana",
        value: 13,
      },
      {
        name: "Himachal Pradesh",
        value: 14,
      },
      {
        name: "Jammu and Kashmir",
        value: 15,
      },
      {
        name: "Jharkhand",
        value: 16,
      },
      {
        name: "Karnataka",
        value: 17,
      },
      {
        name: "Kerala",
        value: 18,
      },
      {
        name: "Madhya Pradesh",
        value: 19,
      },
      {
        name: "Maharashtra",
        value: 20,
      },
      {
        name: "Manipur",
        value: 21,
      },
      {
        name: "Meghalaya",
        value: 22,
      },
      { name: "Mizoram", value: 23 },
      { name: "Nagaland", value: 24 },
      { name: "Odisha", value: 25 },
      { name: "Punjab", value: 26 },
      { name: "Rajasthan", value: 27 },
      { name: "Sikkim", value: 28 },
      { name: "Tamil Nadu", value: 29 },
      { name: "Telangana", value: 30 },
      { name: "Tripura", value: 31 },
      { name: "Uttar Pradesh", value: 32 },
      { name: "Uttarakhand", value: 33 },
      { name: "West Bengal", value: 34 },
    ];
    setStates(data);
  }, []);
  const payload = {
    firstName: firstName,
    lastName: lastname,
    fatherName: fathername,
    motherName: mothername,
    dob: date,
    mobileNo: telephone,
    aadharCard: aadharCard,
    state: option,
    district: district,
    userName: userName,
    password: password,
  };
  const userNamepayLoad = {
    userName: userName,
  };
  const handleUserName = (e) => {
    setUsername(e.target.value);
  };
  const handleChange = (e) => {
    registerHandle(e);
  };

  const registerHandle = (e) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/customer/checkUserName", userNamepayLoad)
      .then(
        (response) => {
        
          if (response.status === 200) {
            axios.post("http://localhost:8080/customer/register", payload).then(
              (response) => {
                // console.log("1",response);
                if (response.status == 201) {
                 
                    document.querySelector(".modal-title").innerHTML = "Confirm";
                    document.querySelector(".modal-body").innerHTML =
                      "Are you willing to register?";
                      setSucessMessage(response.data.msg);
                    setSuccess(true);
                } else {
                  alert("Invalid data entered");
                  navigate("/register");
                }
              },
              (error) => {
                // console.log("3",error);
                document.querySelector(".modal-title").innerHTML = "Validation Error";

                var err = error.response.data;
                // console.log("2 ",err);
                let ele = "<ul >";
                err.map((errorMesage, key) => {
                  ele =
                    ele +
                    `<li key=${key} style='list-style-type:square'>${errorMesage.message}</li> `;
                });
                ele = ele + "</ul>";
                document.querySelector(".modal-body").innerHTML = ele;
                setFailure(true);
              }
            );
          } else {
            document.getElementById("userName").style.borderColor = "red";
            document.querySelector(".modal-title").innerHTML = "Failure";
            document.querySelector(".modal-body").innerHTML =
              "UserName already exist";
            setFailure(true);
            console.log("UserName already exist");
          }
        },
        (error) => {
          console.log("b", error);
          console.log("c", error.response);
          document.querySelector(".modal-title").innerHTML = "Failure";
          document.querySelector(".modal-body").innerHTML =
            error.response.data.msg;
          setFailure(true);
        }
      );
    return false;
  };
  const handleFirstName = (e) => {
    setfirstName(e.target.value);

    if (e.target.value !== "" && e.target.value.length < 225) {
      document.getElementById("firstName").style.borderColor = "green";
    } else {
      document.getElementById("firstName").style.borderColor = "red";
    }
  };
  const handleLastName = (e) => {
    setLastName(e.target.value);
    if (e.target.value === "")
      document.getElementById("LastName").style.borderColor = "red";
    else document.getElementById("LastName").style.borderColor = "green";
  };
  const handleFatherName = (e) => {
    setFathername(e.target.value);
    if (e.target.value === "")
      document.getElementById("FatherName").style.borderColor = "red";
    else document.getElementById("FatherName").style.borderColor = "green";
  };
  const handleMotherName = (e) => {
    setMothername(e.target.value);
    if (e.target.value === "")
      document.getElementById("MotherName").style.borderColor = "red";
    else document.getElementById("MotherName").style.borderColor = "green";
  };
  const handleDate = (e) => {
    setDate(e.target.value);
  };
  const handleTelephone = (e) => {
    setTelephone(e.target.value);
    if (e.target.value !== "" && e.target.value.length === 10)
      document.getElementById("telephone").style.borderColor = "green";
    else document.getElementById("telephone").style.borderColor = "red";
  };
  const checkaadharCard = {
    aadharCard: aadharCard,
  };
  const handleaadharCard = (e) => {
    setaadharCard(e.target.value);
    if (e.target.value !== "" && e.target.value.length === 12) {
      document.getElementById("aadharCard").style.borderColor = "green";
    } else {
      document.getElementById("aadharCard").style.borderColor = "red";
    }
  };
  const handleDistrict = (e) => {
    setDistrict(e.target.value);
  };
  const handleOption = (e) => {
    setOption(e.target.value);
  };
  const handlePassword = (e) => {
    setpassword(e.target.value);
    if (e.target.value === "")
      document.getElementById("password").style.borderColor = "red";
    else document.getElementById("password").style.borderColor = "green";
  };
  const handleContent = (e) => {
    let val=`<p>${successMessage}</p>`;
    document.querySelector(".modal-body").innerHTML = val;
    document.querySelector(".modal-title").innerHTML = "Success";
  };
  return (
    <div className="reg">
      <Header></Header>
      <div className="register">
        <form method="post" className="row g-3" name="registerform">
          <div>
            <h3 className="card-title">Create account</h3>
          </div>
          <div className="col-sm-4">
            <input
              type="text"
              placeholder="firstName"
              id="firstName"
              name="firstName"
              value={firstName}
              className="form-control"
              onChange={handleFirstName}
            ></input>
          </div>
          <div className="col-sm-4">
            <input
              type="text"
              placeholder="lastName"
              name="lastname"
              value={lastname}
              className="form-control"
              onChange={handleLastName}
              id="LastName"
            ></input>
          </div>
          <div className="col-sm-4">
            <input
              type="text"
              placeholder="fatherName"
              name="fathername"
              value={fathername}
              id="FatherName"
              className="form-control"
              onChange={handleFatherName}
            ></input>
          </div>
          <div className="col-sm-4">
            <input
              type="text"
              placeholder="motherName"
              name="mothername"
              value={mothername}
              id="MotherName"
              className="form-control"
              onChange={handleMotherName}
            ></input>
          </div>
          <div className="col-sm-4">
            <input
              type="text"
              name="date"
              value={date}
              placeholder="dob"
              id="date"
              className="form-control"
              onChange={handleDate}
              onBlur={ValidateDOB}
            ></input>
            <span class="error" id="lblError"></span>
          </div>
          <div className="col-sm-4">
            <input
              type="number"
              placeholder="telephone"
              name="telephone"
              value={telephone}
              id="telephone"
              className="form-control"
              onChange={handleTelephone}
            ></input>
          </div>
          <div className="col-sm-4">
            <input
              type="number"
              placeholder="aadharCard number"
              name="aadharcardnumber"
              id="aadharCard"
              value={aadharCard}
              className="form-control"
              maxLength="12"
              onChange={handleaadharCard}
            ></input>
          </div>
          <div className="col-sm-4">
            <select
              name="state"
              id="state"
              value={option}
              className="form-select"
              onChange={handleOption}
            >
              {states.map((data) => (
                <option key={data.value} value={data.name}>
                  {data.name}
                </option>
              ))}
            </select>
          </div>
          <div className="col-sm-4">
            <input
              type="text"
              placeholder="district"
              name="district"
              className="form-control"
              value={district}
              onChange={handleDistrict}
            ></input>
          </div>
          <div className="col-sm-4">
            <input
              type="text"
              placeholder="username"
              id="userName"
              name="username"
              className="form-control"
              value={userName}
              onChange={handleUserName}
            ></input>
          </div>
          <div className="col-sm-4">
            <input
              type="password"
              placeholder="password"
              name="password"
              className="form-control"
              value={password}
              id="password"
              onChange={handlePassword}
              onBlur={ValidatePwd}
            ></input>
             <span class="error" id="Error"></span>
          </div>
          <div className="col-md-12 text-center">
            <button
              type="button"
              className="btn btn-primary"
              onClick={handleChange}

              data-bs-toggle="modal"
              data-bs-target="#staticBackdrop"
              // disabled={!areAllFieldsFilled}
            >
              SUBMIT
            </button>
          </div>
        </form>
      </div>
      <div
        className="modal fade"
        id="staticBackdrop"
        data-bs-backdrop="static"
        data-bs-keyboard="false"
        tabIndex="-1"
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
              {success && !failure && (
                <>
                  <button
                    type="button"
                    className="btn btn-secondary"
                     onClick={()=>{
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
              )}
              {conform && !failure&& (
                <button
                  type="button"
                  className="btn btn-success"
                  onClick={() =>{
                    navigate("/");
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
    
  );
};



export default Register;
