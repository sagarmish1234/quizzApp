import "./App.css";
import { Routes, Route } from "react-router-dom";
import Login from "./pages/login/Login";
import Register from "./pages/register/Register";
import Questions from "./pages/questions/Questions";
import Stagging from "./pages/stagging/Stagging";
import { createContext, useState } from "react";
import GameOver from "./pages/gameOver/GameOver";

export const QuestionsFound = createContext(null);
export const CorrectAnswered = createContext(0);

function App() {
  const [questionsFound, setQuestionsFound] = useState([]);
  const [correctAnswered, setCorrectAnswered] = useState(0);
  return (
    <QuestionsFound.Provider value={[questionsFound, setQuestionsFound]}>
      <CorrectAnswered.Provider value={[correctAnswered, setCorrectAnswered]}>
        <div>
          <Routes>
            <Route path="/" element={<Login></Login>}></Route>
            <Route path="/register" element={<Register></Register>}></Route>
            <Route path="/stag" element={<Stagging></Stagging>}></Route>
            <Route path="/play" element={<Questions></Questions>}></Route>
            <Route path="/game-over" element={<GameOver></GameOver>}></Route>
          </Routes>
        </div>
      </CorrectAnswered.Provider>
    </QuestionsFound.Provider>
  );
}

export default App;
