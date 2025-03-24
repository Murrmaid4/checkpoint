import { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import NavBar from "./components/NavBar";
import NotFound from "./components/NotFound";
import Landing from "./components/Landing";
import AllGames from "./components/AllGames";



function App() {
  const [loggedInUser, setLoggedInUser] = useState(null);
  const [hasFinishedCheckingLocalStorage, setHasFinishedCheckingLocalStorage] =
    useState(false);

  useEffect(() => {
    if (localStorage.getItem("loggedInUser") !== undefined) {
      setLoggedInUser(JSON.parse(localStorage.getItem("loggedInUser")));
    }
    setHasFinishedCheckingLocalStorage(true);
  }, []);

  if (!hasFinishedCheckingLocalStorage) {
    return null;
  }

  return (
    <main >
      
      <Router>
     
          {/* <NavBar
            loggedInUser={loggedInUser}
            setLoggedInUser={setLoggedInUser}
          /> */}
          <Routes>
          <Route path="/" element={<Landing />} />
          <Route path="/games" element={<AllGames />} />
          <Route path="*" element={<NotFound />}/>
          </Routes>
        
      </Router>
    </main>
  );
}

export default App;
