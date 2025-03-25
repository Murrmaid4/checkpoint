import React from 'react'
import NavBar from '../NavBar'
import GameLogList from './GameLogList';


function UserLogs() {
    return (
        <>
         <NavBar />
        <main className='main gradient '>
        <div className='spacer-64'></div>
        <div className='spacer-64'></div>
        <div className='content-nav'><h2>UserLogs</h2></div>
        <div className='line'></div>
        <div className='spacer-64'></div>
        <div className='spacer-64'></div>
        <div className='d-flex justify-content-center '>
        <div className='section-sm'>
        <GameLogList />
        </div>
        </div>
        </main>
        </>
        );
}

export default UserLogs