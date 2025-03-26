import React from "react";



const GameGrid = ({ games }) => {
  return (
    <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-4 p-6">
      {games.map((game) => (
        <div key={game.id} className="relative group cursor-pointer">
          {/* Game Image */}
          
          <img
            src={game.thumbnail}
            alt={game.title}
            className="w-full h-full object-cover rounded-lg transition-transform duration-300 group-hover:scale-105"
          />

          {/* Hover Effect */}
          <div className=" group-hover:opacity-100 absolute inset-0 bg-black/70 opacity-0 transition-opacity duration-300 flex items-center justify-center rounded-lg ">
          <h3 className="text-white text-lg font-bold z-100">{game.title}</h3>
          </div>
        </div>
      ))}
    </div>
  );
};

export default GameGrid;
