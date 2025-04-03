import React from "react";
import NavBar from "../NavBar";
import GameLogList from "./GameLogList";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import FooterTransparent from "../Footer-transparent";
import LogTable from "./LogTable";

function UserLogs({ loggedInUser, setLoggedInUser }) {
  useEffect(() => {
    fetch("http://localhost:8080/api/log/myLogs", {
      method: "GET",
      headers: {
        Authorization: loggedInUser.jwt,
      },
    }).then((res) =>
      res.json().then((fetchedLogs) => {
        setHasFinishedFetching(true);
        setLogs(fetchedLogs);
      })
    );
  }, []);

  const [logs, setLogs] = useState([]);
  const [hasFinishedFetching, setHasFinishedFetching] = useState(false);

  const handleDeleteLog = (deletedLogId) => {
    setLogs((prevLogs) => prevLogs.filter((log) => log.id !== deletedLogId));
  };
  
  // if (logs.length === 0) {
  //     if (hasFinishedFetching) {
  //         return (
  //             <div>There are no logs to show</div>
  //         )
  //     } else {
  //         return (
  //             null
  //             // this could be a loading screen or a spinnner placeholder instead

  //         )
  //     }
  // }
  return (
    <>
      <NavBar loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser} />
      <main className=" main-container">
        <div className="spacer-64"></div>
        <div className="spacer-64"></div>
        <div className="spacer-16"></div>
        <div className="d-flex justify-content-center">
          <h2>{loggedInUser.username}'s Lists</h2>
          <div className="spacer-64"></div>
          
        </div>
        <div className='spacer-24'></div>  
        <div className="d-flex justify-content-center ">
          <div className="w-full max-w-6xl">
            {hasFinishedFetching ? (
              logs.length > 0 ? (
                <GameLogList logs={logs} onDelete={handleDeleteLog}/>
              ) : (
                <div className="text-center">There are no logs to show</div>
              )
            ) : (
              <div className="text-center">Loading logs...</div> // Optional loading indicator
            )}

            <div className="spacer-24"></div>

          
            <div className="d-flex justify-content-center">
              <Link
                to="/games"
                className="yellow-bg text-black semi-bold py-3 px-4 rounded text-sm"
              >
                Add to Log
              </Link>

            </div>
          </div>
        </div>
      </main>
      <FooterTransparent />
    </>
  );
}

export default UserLogs;
