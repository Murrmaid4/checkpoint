import React from "react";
import GameLogCard from "./GameLogCard";
import { Link } from "react-router-dom";



// where the fetch call should go to get the user's logs

const GameLogList = ({logs, onDelete}) => {
 
 
  return (
    <div >
     
    <div className="">
      {logs.map((log, index) => (
        <GameLogCard key={index} {...log} onDelete={onDelete} />
      ))}
    </div>
    <div className="spacer-32"></div>
   
    </div>
  );
};

export default GameLogList;
