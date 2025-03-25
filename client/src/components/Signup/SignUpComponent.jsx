import { useState } from "react"
import { useNavigate } from "react-router-dom"
import SignInForm from "./SignIn.jsx"
import SignUpForm from "./SignUp.jsx"


const SignUpComponent=({loggedInUser,setLoggedInUser})=>{
  const navigate = useNavigate();
  const [type, setType] = useState("signIn");
  const handleOnClick = (text) => {
    if (text !== type) {
      setType(text);
      return;
    }
  };
  const containerClass =
    "container " + (type === "signUp" ? "right-panel-active" : ""); 

  return (
    <div className="gradient main">
      <div className="spacer-64"></div>
      <div className="spacer-64"></div>
      <div className="spacer-64"></div>
      <div className={containerClass} id="container">
        <SignUpForm />
        <SignInForm />
        <div className="overlay-container">
          <div className="overlay-signup">
            <div className="overlay-panel overlay-left">
              <h1 className="bold">Welcome Back!</h1>
              <p>
                To keep connected with us please login with your personal info
              </p>
              <button
                className="button ghost"
                id="signIn"
                onClick={() => handleOnClick("signIn")}
              >
                Sign In
              </button>
            </div>
            <div className="overlay-panel overlay-right">
              <h1 className="bold">Document Your Quest!</h1>
              <p>Register today and start logging your digital adventures! </p>
              <button
                className="button ghost "
                id="signUp"
                onClick={() => handleOnClick("signUp")}
              >
                Sign Up
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>

  )
}

export default SignUpComponent