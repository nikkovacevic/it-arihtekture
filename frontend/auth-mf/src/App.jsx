import { useEffect, useState } from 'react';
import { FaUserCircle } from 'react-icons/fa'
import './App.css'

function App() {

    const [isLoggedIn, setIsLoggedIn] = useState(
        !!localStorage.getItem('token')
    );

    useEffect(() => {
        // Update the state whenever the token is updated in localStorage
        window.addEventListener('storage', handleStorageChange);

        return () => {
            // Clean up the event listener when the component unmounts
            window.removeEventListener('storage', handleStorageChange);
        };
    }, []);

    const handleStorageChange = (event) => {
        if (event.key === 'token') {
            setIsLoggedIn(!!event.newValue);
        }
    };

    const handleLogin = () => {
        //open modal / show fields
        //call endpoint
        //set true to localstorage
    }

    const handleLogout = () => {
        //clear false from localstorage
    }

  return (
    <>
        <div className="navbar">
            <div className="navbar-left">
                IT-arhitekture
            </div>

            {
                isLoggedIn ? (
                    <div className="navbar-right" onClick={handleLogout}>
                        <p className="navbar-odjava">
                            Logout
                        </p>
                        <FaUserCircle/>
                    </div>
                ) : (
                    <div className="navbar-right" onClick={handleLogin}>
                        <p className="navbar-odjava">
                            Login
                        </p>
                        <FaUserCircle/>
                    </div>
                )
            }



        </div>

    </>
  )
}

export default App
