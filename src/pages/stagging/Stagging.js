import React, { useContext, useState } from "react";
import "./stagging.css";
import { FadeLoader } from "react-spinners";
import baseUrl from "../../config/BaseUrl";
import axios from "axios";
import { QuestionsFound, CorrectAnswered } from "../../App";
import { useLocation, useNavigate } from "react-router-dom";
function Stagging() {
  const [questionsFound, setQuestionsFound] = useContext(QuestionsFound);
  const [loading, setLoading] = useState(false);
  const [correctAnswered,setCorrectAnswered] = useContext(CorrectAnswered)
const navigate = useNavigate();
  const handleStart = async () => {
    setLoading(true);
    try {
      const response = await axios.get(`${baseUrl}/question/getAll`, {
        headers: {
          Authorization: `Bearer ${window.sessionStorage.getItem("token")}`,
        },
      });
      console.log(response.data);
      setQuestionsFound(response.data);
      setCorrectAnswered(0);
      navigate("/play")
    } catch (e) {
      alert(e.response.data.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="staggingPageContainer">
      <div className="staggingPageTitle">Let's start the quizz</div>
      <FadeLoader loading={loading} color={"blue"}></FadeLoader>
      {!loading && (
        <button className="staggingPageButton" onClick={handleStart}>
          Start
        </button>
      )}
    </div>
  );
}

export default Stagging;
