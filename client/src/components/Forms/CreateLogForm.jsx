import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";



const initialData = {
    game_Id: "",
    user_Id: "",
    status: "",
    notes: "",
  }

function gameLogForm( {loggedInUser} ) {
  const [log, setLog] = useState(initialData);
  const [errors, setErrors] = useState([]);
  const { gameId, logId } = useParams(); // Get game ID from URL
  const navigate = useNavigate(); // For redirecting

  function handleChange(evt) {
    setLog((previous) => {
      const next = { ...previous };
      next[evt.target.name] = evt.target.value;
      return next;
    });
  }
  // Fetch log data when editing
  useEffect(() => {
    console.log("ID:", logId);
    if (logId) {
      fetch(`http://localhost:8080/api/log/${logId}`)
        .then((res) => res.json())
        .then((data) => setLog(data))
        .catch((err) => console.error("Error fetching log:", err));
    } else {
      setLog(initialData);
      
    }
  }, [logId]);

  // TODO: Modify this function to support update as well as add/create.
  function handleSubmit(evt) {
    evt.preventDefault();

    log.game_Id = gameId;
    const config = {
      method: logId ? "PUT" : "POST",
      headers: { "Content-Type": "application/json", Authorization: loggedInUser.jwt },
      body: JSON.stringify(log),
    };

    fetch(
      logId
        ? `http://localhost:8080/api/log/${logId}`
        : "http://localhost:8080/api/log",
      config
      
    )
      .then((res) => {
        if (res.ok) {
          console.log(res);
          navigate("/myLogs"); // Redirect to agent list after saving
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
    navigate("/games");
  }

  return (
    <>
    <div className="main-container">
        <div className="flex justify-center items-center min-vh-100  flex-column">
      <h1 className="yellow bold ">
        {logId ? `Edit Log - ${logId}` : "Add to Log"}
      </h1>

      {errors && errors.length > 0 && <div className="alert alert-danger">
                <ul className="mb-0">
                    {errors.map(err => <li key={err}>{err}</li>)}
                </ul>
            </div>}
      <div className="w-full max-w-xl">
      <form onSubmit={handleSubmit} className="form2 ">
        <div className="text-left">
          <div className="mb-3">
            <label className="form-label mb-4" htmlFor="notes">
             Add your Game notes!
            </label>
            <textarea
              id="notes"
              name="notes"
              placeholder="Enter your notes here..."
              type="text"
              className="form-control"
              required
              onChange={handleChange}
              value={log.notes}
            />
          </div>
          <div className="mb-5">
          <label className="mb-2" htmlFor="status">Game Status: </label>
                    <select name="status" className="form-control " id="status" value={log.status} onChange={handleChange}>
                        <option value="">Pick a Status...</option>
                        <option value="PLAYING">Playing Currently</option>
                        <option value="ON_HOLD">On Hold</option>
                        <option value="COMPLETED">Completed</option>
                        <option value="DROPPED">Dropped</option>
                        <option value="WANT_TO_PLAY">Want to Play</option>
                    </select>
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
      </div>
      </div>
      </div>
    </>
  );
}

export default gameLogForm;
