import { useState } from "react";

function AddGameForm() {
  const [formData, setFormData] = useState({
    title: "",
    platform: "",
    releaseYear: "",
    genre: "",
    publisher: "",
    thumbnail: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Form Submitted: ", formData);
    // You can handle form submission here
  };

  return (
    <div className="flex justify-center items-center min-h-screen bg-gray-100">
      <div className="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
        <h2 className="text-2xl font-semibold text-center mb-6">Add New Game</h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label htmlFor="title" className="block text-sm font-medium text-gray-700">
              Game Title
            </label>
            <input
              type="text"
              id="title"
              name="title"
              value={formData.title}
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
              value={formData.platform}
              onChange={handleChange}
              className="w-full p-2 mt-1 border border-gray-300 rounded-md"
              placeholder="Enter platform"
              required
            />
          </div>

          <div className="mb-4">
            <label htmlFor="releaseYear" className="block text-sm font-medium text-gray-700">
              Release Year
            </label>
            <input
              type="number"
              id="releaseYear"
              name="releaseYear"
              value={formData.releaseYear}
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
              value={formData.genre}
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
              value={formData.publisher}
              onChange={handleChange}
              className="w-full p-2 mt-1 border border-gray-300 rounded-md"
              placeholder="Enter publisher"
              required
            />
          </div>

          <div className="mb-4">
            <label htmlFor="thumbnailUrl" className="block text-sm font-medium text-gray-700">
              Thumbnail URL
            </label>
            <input
              type="url"
              id="thumbnailUrl"
              name="thumbnailUrl"
              value={formData.thumbnailUrl}
              onChange={handleChange}
              className="w-full p-2 mt-1 border border-gray-300 rounded-md"
              placeholder="Enter thumbnail URL"
            />
          </div>

          <div className="flex justify-center">
            <button
              type="submit"
              className="bg-blue-500 text-white px-6 py-2 rounded-md hover:bg-blue-600 transition duration-300"
            >
              Add Game
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default AddGameForm;
