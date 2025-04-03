import React, { useState } from 'react';
import SearchBar from './SearchBar';

function Search() {
  const data = ['apple', 'banana', 'cherry', 'date', 'grape']; // Example data
  const [filteredData, setFilteredData] = useState(data);

  const handleSearch = (searchTerm) => {
    const results = data.filter(item =>
      item.toLowerCase().includes(searchTerm.toLowerCase())
    );
    setFilteredData(results);
  };

  return (
    <div>
      <SearchBar onSearch={handleSearch} />
      <ul>
        {filteredData.map((item) => (
          <li key={item}>{item}</li>
        ))}
      </ul>
    </div>
  );
}

export default Search;