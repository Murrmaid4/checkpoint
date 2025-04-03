import React from "react";
import NavBar from "../NavBar";
import Footer from "../Footer";
import GameGrid from "./GameGrid";
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";

function Games({ loggedInUser, setLoggedInUser }) {
  // fetch call to get all games
  useEffect(() => {
    fetch("http://localhost:8080/api/games").then((res) =>
      res.json().then((fetchedGames) => {
        setHasFinishedFetching(true);
        setGames(fetchedGames);
      })
    );
  }, []);

  const [games, setGames] = useState([]);
  const [hasFinishedFetching, setHasFinishedFetching] = useState(false);

  if (games.length === 0) {
    if (hasFinishedFetching) {
      return <div>There are no games to show</div>;
    } else {
      return null;
      // this could be a loading screen or a spinnner placeholder instead
    }
  }

  return (
    <>
      <NavBar loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser} />
      <main className="main-container">
        <div className="spacer-64"></div>
        <div className="spacer-64"></div>
        <div className="d-flex justify-content-center ">
          <div className=" text-white max-w-7xl ">
            <GameGrid games={games} />
          </div>
        </div>
        <div className="spacer-64"></div>
        {/* <div className='d-flex justify-content-center '>
    <div className='section-sm'>
    {loggedInUser ? (
  <Link to="/addGames" className='yellow-bg text-black semi-bold py-3 px-4 rounded text-sm'>Add Game</Link>
) : null}
    </div>
    </div> */}
    <Footer />
      </main>
    </>
  );
}

export default Games;
