import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";

// TODO: Modify this component to support update/edit.
// An update URL should have an agent id.
// Use that id to fetch a single agent and populate it in the form.

const initialData = {
    game_Id: "",
    middleName: "",
    lastName: "",
    dob: "",
    heightInInches: "",
  }

function AgentForm( ) {
  const [agent, setAgent] = useState(initialData);
  const [errors, setErrors] = useState([]);
  const { id } = useParams(); // Get agent ID from URL
  const navigate = useNavigate(); // For redirecting

  function handleChange(evt) {
    setAgent((previous) => {
      const next = { ...previous };
      next[evt.target.name] = evt.target.value;
      return next;
    });
  }
  // Fetch agent data when editing
  useEffect(() => {
    console.log("ID:", id);
    if (id) {
      fetch(`http://localhost:8080/api/agent/${id}`)
        .then((res) => res.json())
        .then((data) => setAgent(data))
        .catch((err) => console.error("Error fetching agent:", err));
    } else {
      setAgent(initialData);
    }
  }, [id]);
  // TODO: Modify this function to support update as well as add/create.
  function handleSubmit(evt) {
    evt.preventDefault();

    const config = {
      method: id ? "PUT" : "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(agent),
    };

    fetch(
      id
        ? `http://localhost:8080/api/agent/${id}`
        : "http://localhost:8080/api/agent",
      config
    )
      .then((res) => {
        if (res.ok) {
          console.log(res);
          navigate("/agents"); // Redirect to agent list after saving
        } else {
          return res.json();
        }
      })
      .then((errs) => {
        if (errs) {
          return Promise.reject(errs);
        }
      })
      .catch((errs) => {
        if (errs.length ) {
           
            console.log(errs);
          setErrors(errs);
        } else {
          setErrors([errs]);
        }
      });
  }

  function handleCancel() {
    navigate("/agents");
  }

  return (
    <>
      <h1 className="display-6">
        {id ? `Edit Agent - ${agent.firstName}` : "Add an Agent"}
      </h1>
      {errors && errors.length > 0 && <div className="alert alert-danger">
                <ul className="mb-0">
                    {errors.map(err => <li key={err}>{err}</li>)}
                </ul>
            </div>}
      <form onSubmit={handleSubmit}>
        <div className="row mb-3">
          <div className="col">
            <label className="form-label" htmlFor="firstName">
              First Name
            </label>
            <input
              id="firstName"
              name="firstName"
              type="text"
              className="form-control"
              required
              onChange={handleChange}
              value={agent.firstName}
            />
          </div>
          <div className="col">
            <label className="form-label" htmlFor="middleName">
              Middle Name
            </label>
            <input
              id="middleName"
              name="middleName"
              type="text"
              className="form-control"
              onChange={handleChange}
              value={agent.middleName}
            />
          </div>
        </div>
        <div className="mb-3">
          <label className="form-label" htmlFor="lastName">
            Last Name
          </label>
          <input
            id="lastName"
            name="lastName"
            type="text"
            className="form-control"
            required
            onChange={handleChange}
            value={agent.lastName}
          />
        </div>
        <div className="row mb-3">
          <div className="col">
            <label className="form-label" htmlFor="dob">
              DOB
            </label>
            <input
              id="dob"
              name="dob"
              type="date"
              className="form-control"
              required
              onChange={handleChange}
              value={agent.dob}
            />
          </div>
          <div className="col">
            <label className="form-label" htmlFor="heightInInches">
              Height (inches)
            </label>
            <input
              id="heightInInches"
              name="heightInInches"
              type="number"
              className="form-control"
              required
              min="36"
              max="96"
              onChange={handleChange}
              value={agent.heightInInches}
            />
          </div>
        </div>
        <div className="mb-3">
          <button onClick={handleSubmit} type="submit" className="btn btn-primary me-2">
            Save
          </button>
          {/* TODO: Change this button to a React Router Link. */}
          <button
            type="button"
            className="btn btn-warning"
            onClick={handleCancel}
          >
            Cancel
          </button>
        </div>
      </form>
    </>
  );
}

export default AgentForm;
