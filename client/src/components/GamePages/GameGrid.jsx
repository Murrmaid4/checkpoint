import React, {useState} from "react";
import { Link } from "react-router-dom";



const GameGrid = ({ games }) => {
  const gamesPerPage = 10; // Adjust as needed
  const [currentPage, setCurrentPage] = useState(1);

  // Calculate total pages
  const totalPages = Math.ceil(games.length / gamesPerPage);

  // Get current games
  const startIndex = (currentPage - 1) * gamesPerPage;
  const currentGames = games.slice(startIndex, startIndex + gamesPerPage);

  return (
    <div className="p-6">
         {/* Grid Layout */}
         <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-4">
        {currentGames.map((game) => (
          <div key={game.id} className="game-card">
            
            <img src={game.thumbnail} alt={game.title} className="game-image" />
          
          {/* Overlay for hover effect */}
          <div className="game-overlay">
            <h3 className="game-title">{game.title}</h3>

            {/* Add to Log Button */}
               <Link to={`/addLog/${game.id}`} className="add-to-log">
            <svg 
    xmlns="http://www.w3.org/2000/svg" 
    viewBox="0 0 24 24" 
    width="24" 
    height="24" 
    fill="white" 
    className="plus-icon">
    <circle cx="12" cy="12" r="10" stroke="white" strokeWidth="2" fill="black"/>
    <line x1="12" y1="7" x2="12" y2="17" stroke="white" strokeWidth="2"/>
    <line x1="7" y1="12" x2="17" y2="12" stroke="white" strokeWidth="2"/>
  </svg>
              <span className="tooltip">Add to Log</span>
            </Link>
          </div>
        </div>
      ))}
      </div>
<div className="spacer-32"></div>
        {/* Pagination Controls */}
        {totalPages > 1 && (
        <div className="flex justify-center mt-6 space-x-2">
          <button
            className="px-4 py-2 text-white bg-gray-700 rounded disabled:opacity-50"
            onClick={() => setCurrentPage(currentPage - 1)}
            disabled={currentPage === 1}
          >
            Previous
          </button>
          <span className="text-lg font-semibold px-4 py-2">{currentPage} / {totalPages}</span>
          <button
            className="px-4 py-2 text-white bg-gray-700 rounded disabled:opacity-50"
            onClick={() => setCurrentPage(currentPage + 1)}
            disabled={currentPage === totalPages}
          >
            Next
          </button>
        </div>
      )}
    </div>
  );
};

export default GameGrid;
