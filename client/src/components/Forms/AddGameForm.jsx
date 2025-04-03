import { useState } from "react";
import { useNavigate } from "react-router-dom";


function AddGameForm() {
  
//  year select input functionality to mess with later vvv 

    // const currentYear = new Date().getFullYear();
    // const years = Array.from({ length: 101 }, (_, i) => currentYear - i); // Generates years from current year to 100 years ago
    // const [selectedYear, setSelectedYear] = useState('');
  
    // const handleYearChange = (event) => {
    //   setSelectedYear(event.target.value);
    // };
    const navigate = useNavigate()

    const [errors, setErrors] = useState([])
  const [newGame, setNewGame] = useState({
    title: "",
    platform: "",
    release_year: "",
    genre: "",
    publisher: "",
    thumbnail: "",
  });

  const handleChange = (event) => {
    setNewGame({ ...newGame, [event.target.name]: event.target.value })
}

const handleCancel = () => {
  navigate("/games")
}

const handleSubmit = (event) => {
  event.preventDefault()

  fetch("http://localhost:8080/api/games", {
      method: "POST",
      headers: {
          "Content-Type": "application/json",
      },
      body: JSON.stringify(newGame)
  })
  .then(response => {
      if (response.status >= 200 && response.status < 300) {
          navigate("/games")
      } else {
          response.json().then(fetchedErrors => setErrors(fetchedErrors))
      }
  })

}

  return (
    <>
   
    <div className=" gradient">
    <div className="flex justify-center items-center min-h-screen ">
    <div className="row">
            {errors.length > 0 && <ul id="errors">
                {errors.map(error => <li key={error}>{error}</li>)}
            </ul>} </div>
      <div className=" w-full max-w-xl">
        <h2 className="text-lg bold text-center yellow">Add New Game</h2>
        <div className="spacer-24"></div>
        <form onSubmit={handleSubmit} >
          <div className="mb-4">
            <label htmlFor="title" className="block text-sm font-medium text-gray-700">
              Game Title
            </label>
            <input
              type="text"
              id="title"
              name="title"
              value={newGame.title}
              onChange={handleChange}
              className="w-full p-2 mt-1 border border-gray-300 rounded-md"
              placeholder="Enter game title"
              required
            />
          </div>

          <div className="mb-4">
            <label htmlFor="platform" className="block text-sm font-medium text-gray-700">
              Platform
            </label>
            <input
              type="text"
              id="platform"
              name="platform"
              value={newGame.platform}
              onChange={handleChange}
              className="w-full p-2 mt-1 border border-gray-300 rounded-md"
              placeholder="Enter platform"
              required
            />
          </div>

          <div className="mb-4">
            <label htmlFor="release_year" className="block text-sm font-medium text-gray-700">
              Release Year
            </label>
            <input
              type="number"
              id="release_year"
              name="release_year"
              value={newGame.release_year}
              onChange={handleChange}
              className="w-full p-2 mt-1 border border-gray-300 rounded-md"
              placeholder="Enter release year"
              required
            />
          </div>

          <div className="mb-4">
            <label htmlFor="genre" className="block text-sm font-medium text-gray-700">
              Genre
            </label>
            <input
              type="text"
              id="genre"
              name="genre"
              value={newGame.genre}
              onChange={handleChange}
              className="w-full p-2 mt-1 border border-gray-300 rounded-md"
              placeholder="Enter genre"
              required
            />
          </div>

          <div className="mb-4">
            <label htmlFor="publisher" className="block text-sm font-medium text-gray-700">
              Publisher
            </label>
            <input
              type="text"
              id="publisher"
              name="publisher"
              value={newGame.publisher}
              onChange={handleChange}
              className="w-full p-2 mt-1 border border-gray-300 rounded-md"
              placeholder="Enter publisher"
              required
            />
          </div>

          <div className="mb-4">
            <label htmlFor="thumbnail" className="block text-sm font-medium text-gray-700">
              Thumbnail URL
            </label>
            <input
              type="url"
              id="thumbnail"
              name="thumbnail"
              value={newGame.thumbnail}
              onChange={handleChange}
              className="w-full p-2 mt-1 border border-gray-300 rounded-md"
              placeholder="Enter thumbnail URL"
            />
          </div>

          <div className="flex justify-center">
            <button
              type="submit"
              className="yellow-bg text-black semi-bold py-2 px-4 rounded text-sm"
            >
              Submit
            </button>
            <button
              onClick={handleCancel}
              type="button"
              className="transparent yellow semi-bold py-2 px-4 rounded text-sm mx-3 "
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

export default AddGameForm;
