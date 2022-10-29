import React, { useContext, useEffect, useState } from "react";
import "./questions.css";
import { QuestionsFound, CorrectAnswered } from "../../App";
import { useNavigate } from "react-router-dom";
import baseUrl from "../../config/BaseUrl";
import axios from "axios";
function Questions() {
  const [questionsFound] = useContext(QuestionsFound);
  const [counter, setCounter] = useState(0);
  const [timer, setTimer] = useState(10);
  const [isDisabled, setIsDisabled] = useState(false);
  const [setCorrectAnswered] = useContext(CorrectAnswered);
  const navigate = useNavigate();
  // useEffect(() => {
  //   window.history.pushState(null, document.title, window.location.href);
  //   window.addEventListener("popstate", function (event) {
  //     window.history.pushState(null, document.title, window.location.href);
  //   });
  // }, []);
  useEffect(() => {
    navigate(questionsFound.length!==0 ? "/play" : "/stag");
  }, []);
  
  useEffect(() => {
    let timer1 = setInterval(() => setTimer((prev) => prev - 1), 1000);
    if (questionsFound.length!==0 && counter === questionsFound.length) navigate("/game-over");
    return () => {
      clearInterval(timer1);
    };
  }, [counter]);
  var currentQuestion;
  if(questionsFound){
    currentQuestion = questionsFound[counter];
  }
  

  const handleAnswer = async (e) => {
    setIsDisabled(true);
    try {
      const response = await axios.post(
        `${baseUrl}/answer/give`,
        {
          questionId: currentQuestion.id,
          optionId: e.target.id,
        },
        {
          headers: {
            Authorization: `Bearer ${window.sessionStorage.getItem("token")}`,
          },
        }
      );
      if (response.data.correct) {
        setCorrectAnswered((prev) => prev + 1);
      }
    } catch (e) {
      console.log(e.response.data.message);
      navigate("/stag");
    } finally {
      setCounter((prev) => prev + 1);
      setTimer(10);
      setIsDisabled(false);
    }
  };

  useEffect(() => {
    
    if (timer === -1) {
      setCounter((prev) => prev + 1);
      setTimer(10);
      setIsDisabled(false);
    }
  }, [timer])
  

  return (
    <div className="questionPageContainer">
      <div className="questionPageHeader">
        <div className="questionPageTitle">
          Answer the question by selecting from the following options
        </div>
        <div className="questionPageTimer">Time Left: {timer}</div>
      </div>
      <div className="questionPageQuestionBody">{currentQuestion && currentQuestion.body}</div>
      <div className="questionPageQuestionOptions" onChange={handleAnswer}>
        {currentQuestion && currentQuestion.options.map((option, index) => {
          return (
            <div key={index}>
              <span
                style={{
                  fontSize: "1.5rem",
                  fontWeight: "600",
                }}
              >
                {index + 1}
              </span>
              &nbsp;&nbsp;&nbsp;&nbsp;
              <input
                type="button"
                name={"option" + option.id}
                id={option.id}
                value={option.body}
                key={index}
                disabled={isDisabled}
                className="questionPageQuestionOption"
                onClick={handleAnswer}
              />
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default Questions;
