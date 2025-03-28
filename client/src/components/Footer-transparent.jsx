import React from 'react'
import { Link } from 'react-router'

function FooterTransparent() {
  return (
<div className='footer bg-transparent fixed bottom-0 w-full text-center p-4'>
    <div className=' footer-container'>
    <Link to="/" className="semi-bold text-white">
        <span className='yellow'>Checkpoint</span> © {new Date().getFullYear()}
      </Link>
      
      </div>
    </div>
  )
}

export default FooterTransparent