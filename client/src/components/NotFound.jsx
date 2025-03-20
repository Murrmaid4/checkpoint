import React from 'react'
import Image from '../assets/notfound.gif'

function NotFound() {
  return (
    <div  className="d-flex justify-content-center align-items-center flex-column">
      <h1>
        404 - Not Found
      </h1>
      <img src={Image} alt="Not Found" />
      <h2>

        But you found something even better... 
      </h2>

      

    </div>
  )
}

export default NotFound