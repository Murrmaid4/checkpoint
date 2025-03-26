import React from 'react'
import NavBar from '../NavBar'
import GameLogList from './GameLogList';
import { useEffect, useState } from 'react';


function UserLogs({loggedInUser, setLoggedInUser}) {
    // useEffect(() => {
	// 	fetch("http://localhost:8080/api/log/myLogs")
	// 	.then(res => res.json()
	// 	.then(fetchedLogs => {
    //         setHasFinishedFetching(true)
    //         setLogs(fetchedLogs)
    //     }))
	// }, [])
    
    // const [logs, setLogs] = useState([])
    // const [hasFinishedFetching, setHasFinishedFetching] = useState(false)

    // if (logs.length === 0) {
    //     if (hasFinishedFetching) {
    //         return (
    //             <div>There are no logs to show</div>
    //         )
    //     } else {
    //         return (
    //             null
    //             // this could be a loading screen or a spinnner placeholder instead
                
    //         )
    //     }
    // }
    return (
        <>
         <NavBar loggedInUser={loggedInUser}
            setLoggedInUser={setLoggedInUser} />
        <main className='main gradient '>
        <div className='spacer-64'></div>
        <div className='spacer-64'></div>

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