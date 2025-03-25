import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { jwtDecode } from "jwt-decode"

const SignUpForm = ({ setLoggedInUser }) => {

    const navigate = useNavigate()

    const [user, setUser] = useState({
        email: "",
        username: "",
        password: "",
        first_name: "",
        last_name: ""
    })

    const [errors, setErrors] = useState([])

    const handleSubmit = (event) => {
        event.preventDefault()

        fetch("http://localhost:8080/api/user", {
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
                    navigate("/games")
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
         
<div className="form-container sign-up-container">

<form onSubmit={handleSubmit} className="form">
<div >
          {errors.length > 0 && <ul id="errors">
              {errors.map(error => <li key={error}>{error}</li>)}
          </ul>}
          <h1 className="bold">Create Account</h1>
          <div className="spacer-16"></div>
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
<div >
        <input
          type="text"
          name="first_name"
          value={setUser.first_name}
          onChange={handleChange}
          placeholder="First Name"
className="small-input"
        />
         <input
          type="text"
          name="last_name"
          value={setUser.last_name}
          onChange={handleChange}
          placeholder="Last Name"
          
        />
        </div>
        <div>
        <input
        type="email"
        name="email"
        value={setUser.email}
        onChange={handleChange}
        placeholder="Email"
        className="small-input"
      />
    
        <input
          type="text"
          name="username"
          value={setUser.username}
          onChange={handleChange}
          placeholder="Username"
        />
        </div>
        <input
          type="password"
          name="password"
          value={setUser.password}
          onChange={handleChange}
          placeholder="Password"
          className="small-input"
        />
        <div className="spacer-16"></div>
        <button className="button">Sign Up</button>
  </div>
</form>
</div>

    );
}

export default SignUpForm