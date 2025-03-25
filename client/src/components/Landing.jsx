import React from 'react'
import { motion } from "framer-motion";
import { FaGamepad, FaStar, FaListUl, FaPlay } from "react-icons/fa";
import videoBg from "../assets/bg-video.mp4";
import logo from "../assets/logo.png";
import { Link } from 'react-router';
import Footer from './Footer';

function Landing() {
  return (
    <>

    <div className="container-fluid text-white min-vh-100 d-flex flex-column align-items-center py-5 main">
        <div className='overlay'></div>
      <video src={videoBg} autoPlay loop muted className="video-bg" />
      <img src={logo} alt="" width="150" />
      <motion.h1 
        className="display-4 fw-bold text-center mb-4"
        initial={{ opacity: 0, y: -20 }}
        animate={{ opacity: 1, y: 0 }}
      >
        Welcome to <span className='yellow'>Checkpoint.</span>  
      </motion.h1>
    
      <motion.p 
        className="lead text-center mb-4 w-75"
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        transition={{ delay: 0.3 }}
      >
        Organize your gaming journey! Keep track of games you're playing, create favorite lists, and leave reviews.
      </motion.p>
      
      <motion.div 
        className="row row-cols-1 row-cols-md-2 g-4 w-75"
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        transition={{ delay: 0.5 }}
      >
        <FeatureCard icon={<FaGamepad />} title="Track Your Games" description="Log your gaming progress and keep track of completed games." url="/log" />
        <FeatureCard icon={<FaStar />} title="Favorite List" description="Save and organize your favorite games." url="/lists"/>
        <FeatureCard icon={<FaListUl />} title="Wishlist" description="Keep a list of games you want to play in the future." url="/lists" />
        <FeatureCard icon={<FaPlay />} title="Reviews" description="Share your thoughts on the games you've played." url="/games"/>
        
      </motion.div>
      
      <motion.div 
        className="mt-4"
        initial={{ opacity: 0, scale: 0.8 }}
        animate={{ opacity: 1, scale: 1 }}
        transition={{ delay: 0.7 }}
      >
        <div className='spacer-32'></div>
        <Link className="yellow-bg text-black semi-bold py-3 px-4 rounded text-sm" to="/signup">Get Started</Link>
        {/* this will go to sign up page */}
      </motion.div>
    
    </div>
    
    </>
  );
}

function FeatureCard({ icon, title, description, url }) {
  return (
    <Link className="link" to={url}>
    <div className="col  ">
      <div className="card bg-dark  h-100">
        <div className="card-body d-flex align-items-center">
          <div className="fs-2 text-primary me-3">{icon}</div>
          <div>
            <h5 className="yellow ">{title}</h5>
            <p className="card-text">{description}</p>
          </div>
        </div>
      </div>
    </div>
    </Link>
   
  );
}




export default Landing