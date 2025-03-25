import React from 'react'
import NavBar from '../NavBar'

import GameGrid from './GameGrid';

function Games() {
// write the fetch here to get the games
  const games = [
    {
      "id": 1,
      "title": "Super Mario Bros.",
      "platform": "NES",
      "release_year": 1985,
      "genre": "Platformer",
      "publisher": "Nintendo",
      "thumbnail": "https://upload.wikimedia.org/wikipedia/en/0/03/Super_Mario_Bros._box.png"
    },
    {
      "id": 2,
      "title": "The Legend of Zelda",
      "platform": "NES",
      "release_year": 1986,
      "genre": "Action-Adventure",
      "publisher": "Nintendo",
      "thumbnail": "https://upload.wikimedia.org/wikipedia/en/4/41/Legend_of_zelda_cover_%28with_cartridge%29_gold.png"
    },
    {
      "id": 3,
      "title": "Metroid",
      "platform": "NES",
      "release_year": 1986,
      "genre": "Action-Adventure",
      "publisher": "Nintendo",
      "thumbnail": "https://i.ebayimg.com/images/g/UUEAAOSw1Xlkjmjc/s-l1200.jpg"
    },
  ];
  


  return (
    <>
     <NavBar />
    <main className='main gradient '>
    <div className='spacer-64'></div>
    <div className='spacer-64'></div>
   
    
    <div className='spacer-64'></div>
    <div className='d-flex justify-content-center '>
    <div className=" text-white max-w-xl">

<GameGrid games={games} />
</div>
</div>
    <div className='spacer-64'></div>
    <div className='d-flex justify-content-center '>
    <div className='section-sm'>

    </div>
    </div>
    </main>
    </>
  )
}

export default Games