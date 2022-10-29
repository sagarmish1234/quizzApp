import React, { useState } from "react";
import "./register.css";
import { Link, useNavigate } from "react-router-dom";
import { FadeLoader } from "react-spinners";
import axios from "axios";
import baseUrl from "../../config/BaseUrl";

function Register() {
  const [formDetails, setFormDetails] = useState({
    name: "",
    username: "",
    email: "",
    password: "",
    confirmPassword: "",
  });
  const navigate = useNavigate()
  const [loading, setLoading] = useState(false);
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormDetails((prev) => {
      return { ...prev, [name]: value };
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (formDetails.password !== formDetails.confirmPassword) {
      alert("Passwords do not match");
      return;
    }
    setLoading(true);
    try {
      const response = await axios.post(
        `${baseUrl}/auth/register`,
        formDetails
      );
      alert(response.data.message);
        navigate("/")
    } catch (e) {
      alert(e.response.data.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <div className="loginPageContainer">
        <h1 className="loginPageTitle">Register</h1>
        <form className="loginPageForm" onSubmit={handleSubmit}>
          <input
            type="text"
            placeholder="Full Name"
            value={formDetails.name}
            name="name"
            onChange={handleChange}
            className="loginPageInput"
            required
          />
          <input
            type="text"
            placeholder="Username"
            value={formDetails.username}
            name="username"
            onChange={handleChange}
            className="loginPageInput"
            required
          />
          <input
            type="email"
            placeholder="Email"
            value={formDetails.email}
            name="email"
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
          <input
            type="password"
            placeholder="Confirm password"
            value={formDetails.confirmPassword}
            name="confirmPassword"
            onChange={handleChange}
            className="loginPageInput"
            required
          />
          <FadeLoader
            color={"blue"}
            loading={loading}
            cssOverride={{
              marginTop: "20px",
            }}
          />
          {!loading && (
            <button type="submit" className="loginPageSubmitButton">
              Login
            </button>
          )}
        </form>
        <p className="loginPageFooter">
          Already have an account? <Link to="/">Sign in</Link>
        </p>
      </div>
    </div>
  );
}

export default Register;
