import React from "react";
import GameLogCard from "./GameLogCard";

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

const GameLogList = () => {
  return (
    <div className="p-6 flex flex-wrap gap-4 justify-center">
      {gameLogs.map((log, index) => (
        <GameLogCard key={index} {...log} />
      ))}
    </div>
  );
};

export default GameLogList;
