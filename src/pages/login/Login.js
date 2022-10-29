import React, { useState } from "react";
import {Link,useNavigate} from "react-router-dom"
import "./login.css"
import axios from "axios";
import baseUrl from "../../config/BaseUrl";
import { FadeLoader } from "react-spinners";
function Login() {
  const [formDetails, setFormDetails] = useState({
    usernameOrEmail: "",
    password: "",
  });
  const [loading,setLoading] = useState(false);


  const handleChange = (e)=>{
    const {name,value} = e.target
    setFormDetails((prev)=>{
        return {...prev,[name]:value}
    });
  }
  const navigate = useNavigate()

  const handleSubmit = async (e)=>{
    e.preventDefault();
    setLoading(true)
    try{
      const response = await axios.post(`${baseUrl}/auth/login`,formDetails);
      window.sessionStorage.setItem("token",response.data.accessToken)
      alert("User Signed Successfully")
      navigate("/stag")
    }catch(e){
       alert(e.response.data.message)
    }
    finally{
      setLoading(false)
    }
  }



  return (
    <div className="loginPageContainer">
      <h1 className="loginPageTitle">Login</h1>
      <form className="loginPageForm" onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Username or Email"
          value={formDetails.usernameOrEmail}
          name="usernameOrEmail"
          onChange={handleChange}
          className="loginPageInput"
          required
        />
        <input
          type="password"
          placeholder="Password"
          value={formDetails.password}
          name="password"
          onChange={handleChange}
          className="loginPageInput"
          required
        />
        <FadeLoader color ={"blue"} loading={loading} cssOverride={{
          marginTop:"20px"
        }}/>
        {!loading && <button type="submit" className="loginPageSubmitButton">Login</button>}
      </form>
      <p className="loginPageFooter">Don't have an account? <Link to="/register">Register</Link></p>
    </div>
  );
}

export default Login;
