import React from 'react'
import { Link } from 'react-router'

function Footer() {
  return (
<div className='footer bg-transparent  w-full text-center p-4'>
    <div className=' footer-container'>
    <Link to="/" className="semi-bold text-white">
        <span className='yellow'>Checkpoint</span> © {new Date().getFullYear()}
      </Link>
      
      </div>
    </div>
  )
}

export default Footer