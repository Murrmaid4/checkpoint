import { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import NavBar from "./components/NavBar";
import NotFound from "./components/NotFound";
import Landing from "./components/Landing";
import AllGames from "./components/GamePages/AllGames";
import Footer from "./components/Footer";
import SignUpComponent from "./components/Signup/SignUpComponent";
import UserLogs from "./components/LogsPages/UserLogs";
import AddGameForm from "./components/Forms/AddGameForm";



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
    <main className="main">
      
      <Router>
     
          {/* <NavBar
            loggedInUser={loggedInUser}
            setLoggedInUser={setLoggedInUser}
          /> */}
          <Routes>
          <Route path="/" element={<Landing />} />
          
          <Route path="/myLogs" element={ loggedInUser === null ?
							<Navigate to="/signup" /> : <UserLogs loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser} />} />
         
          
          <Route path="/addGames" element={<AddGameForm/>} />
          <Route path="/games" element={<AllGames loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser}/>} />
         
          <Route path="/lists" element={<AllGames />} />
          <Route path="/signup" element={<SignUpComponent   loggedInUser={loggedInUser}
            setLoggedInUser={setLoggedInUser}/>} />

          <Route path="*" element={<NotFound  />}/>
          </Routes>
          <Footer />
      </Router>
    

    </main>
  );
}

export default App;
