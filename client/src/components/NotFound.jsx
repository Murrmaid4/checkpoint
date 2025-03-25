import React from 'react'
import Image from '../assets/notfound.gif'
import NavBar from './NavBar'

function NotFound() {
  return (
    <>
    <NavBar/>
    <div>
    
    <main className='main gradient'>
    <div className='spacer-64'></div>
    <div  className="d-flex justify-content-center align-items-center flex-column  vh-full">
      <div className='spacer-64'></div>
      <h1 className='yellow semi-bold'>
        404 - Not Found
      </h1>
      <div className='spacer-32'></div>
      <img src={Image} alt="Not Found" className='responsive' />
      <div className='spacer-64'></div>
      <h2>

        But you found something even better... 
      </h2>

      

    </div>
    </main>
    </div>
    </>
  )
}

export default NotFound