import React, { useContext, useEffect } from "react";
import "./gameOver.css";
import { CorrectAnswered, QuestionsFound } from "../../App";
import { useNavigate } from "react-router-dom";
function GameOver() {
  const [correctAnswered, setCorrectAnswered] = useContext(CorrectAnswered);
  const [questionsFound] = useContext(QuestionsFound);
  const navigate = useNavigate();

  useEffect(() => {
    if (questionsFound.length === 0) navigate("/stag");
  }, []);

  return (
    <div className="gameScoreContainer">
      <div className="gameScoreTitle">GameOver!!!</div>
      <div className="gameScoreHeader">
        Your Score is{" "}
        <span>{(correctAnswered * 100) / questionsFound.length}%</span>
      </div>
      <button
        className="gameRetry"
        onClick={() => {
          setCorrectAnswered(0);
          navigate("/stag");
        }}
      >
        Re-try
      </button>
    </div>
  );
}

export default GameOver;
