import React from "react";
import GameLogCard from "./GameLogCard";
import { Link } from "react-router-dom";



const gameLogs = [
  {
    gameName: "Elden Ring",
    status: "In Progress",
    notes: "Exploring the Lands Between and fighting epic bosses!",
    logDate: "March 24, 2025",
  },
  {
    gameName: "God of War",
    status: "Completed",
    notes: "The story was phenomenal! Absolutely loved the father-son dynamic.",
    logDate: "March 10, 2025",
  },
];

// where the fetch call should go to get the user's logs

const GameLogList = () => {
  return (
    <div >
     
    <div className="p-6 flex flex-wrap gap-4 justify-center">
      {gameLogs.map((log, index) => (
        <GameLogCard key={index} {...log} />
      ))}
    </div>
    <div className="spacer-64"></div>
    <div className="d-flex justify-content-center">
    <Link to="/games" className="yellow-bg text-black semi-bold py-3 px-4 rounded text-sm">Add to Log</Link></div>
    </div>
  );
};

export default GameLogList;
