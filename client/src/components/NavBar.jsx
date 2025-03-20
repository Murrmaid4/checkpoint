import React from 'react';
import { Link, NavLink } from 'react-router-dom';
import logo from '../assets/logo-full-white.png';

const NavBar = ({ loggedInUser, setLoggedInUser }) => {
    return (
        <header >
				<nav className='navbar navbar-dark navbar-expand'>
					<div className=' d-flex justify-content-between container'>
                    <div className='d-flex align-items-center'>
                    
                        <NavLink className='navbar-brand' to='/'>
                        
                            <div >
                                <img src={logo} alt='Checkpoint' width='225'  className='logo' />
                            </div>
						</NavLink>
                        
                        </div>
						{/* <ul className='navbar-nav'>
							<li className='nav-item'>
								<NavLink className={(arg) => {
									if (arg.isActive) {
										return 'nav-link custom-active'
									} else {
										return 'nav-link'
									}
								}} to='/'>
									Home
								</NavLink>
							</li>
							<li className='nav-item'>
								<NavLink className={(arg) => {
									if (arg.isActive) {
										return 'nav-link custom-active'
									} else {
										return 'nav-link'
									}
								}} to='/'>
									Your Log
								</NavLink>
							</li>
							<li className='nav-item'>
								<NavLink className={(arg) => {
									if (arg.isActive) {
										return 'nav-link custom-active'
									} else {
										return 'nav-link'
									}
								}} to='/'>
									Contact
								</NavLink>
							</li>
							<li className='nav-item'>
								<NavLink className={(arg) => {
									if (arg.isActive) {
										return 'nav-link custom-active'
									} else {
										return 'nav-link'
									}
								}} to="/list">View All Panels</NavLink>
							</li>
							{ loggedInUser === null ? 
								<>
									<li className='nav-item'>
										<NavLink className={(arg) => {
											if (arg.isActive) {
												return 'nav-link custom-active'
											} else {
												return 'nav-link'
											}
										}} to="/signup">Register</NavLink>
									</li>
									<li className='nav-item'>
										<NavLink className={(arg) => {
											if (arg.isActive) {
												return 'nav-link custom-active'
											} else {
												return 'nav-link'
											}
										}} to="/login">Log In</NavLink>
									</li>
								</> : <>
									<li className='nav-item'>
										<button className='nav-link' onClick={() => {
											setLoggedInUser(null)
											localStorage.clear("loggedInUser")	
										}}>Log Out</button>
									</li>
									<li className='nav-item'>
										<NavLink className={(arg) => {
											if (arg.isActive) {
												return 'nav-link custom-active'
											} else {
												return 'nav-link'
											}
										}} to="/myPanels">View My Panels</NavLink>
									</li>
									<li className='nav-item'>
										<NavLink className={(arg) => {
											if (arg.isActive) {
												return 'nav-link custom-active'
											} else {
												return 'nav-link'
											}
										}} to="/add">Add a Panel</NavLink>
									</li>
								</>
							}
						</ul> */}
					</div>
				</nav>
			</header>
    )
}

export default NavBar