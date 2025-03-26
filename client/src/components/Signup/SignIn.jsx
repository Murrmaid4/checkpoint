import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { jwtDecode } from "jwt-decode"

const SignInForm = ({ setLoggedInUser }) => {
  const navigate = useNavigate()

  const [user, setUser] = useState({
      username: "",
      password: ""
  })

  const [errors, setErrors] = useState([])

  const handleSubmit = (event) => {
      event.preventDefault()

      fetch("http://localhost:8080/api/user/login", {
          method: "POST",
          headers: {
              "Content-Type": "application/json",
          },
          body: JSON.stringify(user)
      })
      .then(response => {
          if (response.status >= 200 && response.status < 300) {
              response.json().then(fetchedUser => {
                  const user = jwtDecode(fetchedUser.jwt)
                  user.jwt = fetchedUser.jwt
                  setLoggedInUser(user)
                  localStorage.setItem("loggedInUser", JSON.stringify(user))
                  navigate("/myLogs")
              })
          } else {
              response.json().then(fetchedErrors => setErrors(fetchedErrors))
          }
      })

  }

  const handleChange = (event) => {
      setUser({ ...user, [event.target.name]: event.target.value })
  }

return (
  
<div className="form-container sign-in-container">

  <form onSubmit={handleSubmit} className="form">
  <div >
            {errors.length > 0 && <ul id="errors">
                {errors.map(error => <li key={error}>{error}</li>)}
            </ul>}
    <h1 className="bold">Sign in</h1>
    <div className="spacer-32"></div>
    {/* <div className="social-container">
      <a href="#" className="social">
        <i className="fab fa-facebook-f" />
      </a>
      <a href="#" className="social">
        <i className="fab fa-google-plus-g" />
      </a>
      <a href="#" className="social">
        <i className="fab fa-linkedin-in" />
      </a>
    </div> */}

    <input
    
      type="username"
      placeholder="Username"
      name="username"
      value={setUser.username}
      onChange={handleChange}
      className="reg-input"
    />
    <div className="spacer-16"></div>
    <input
      type="password"
      name="password"
      placeholder="Password"
      value={setUser.password}
      onChange={handleChange}
      className="reg-input"
    />
    <div className="spacer-32"></div>
    {/* <a href="#">Forgot your password?</a> */}
    <button className="button">Sign In</button>
    </div>
  </form>
</div>

);
}

export default SignInForm;