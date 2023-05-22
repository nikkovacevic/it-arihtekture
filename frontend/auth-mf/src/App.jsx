import { useEffect, useState } from 'react';
import { FaUserCircle } from 'react-icons/fa'
import './App.css'
import { TextField } from '@mui/material';
import axios from 'axios';

function App() {

    const [isLoggedIn, setIsLoggedIn] = useState(false);

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')

    const handleLogin = async () => {

        let loginDto = {
            email: username,
            password: password
        }

        const response = await axios.post('http://localhost:8085/api/users/authenticate', loginDto)

        console.log(response)


        if (!response.data) {
            setUsername('')
            setPassword('')
        }

        setIsLoggedIn(response.data)
        setUsername('')
        setPassword('')
    }

    const handleLogout = () => {
       setIsLoggedIn(false)
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
                    <div className="navbar-right">
                        <div className="inputs">
                            { isLoggedIn ? (<></>) : (
                                <>
                                    <div>
                                        <TextField
                                            className="text"
                                            size="small"
                                            value={username}
                                            onChange={(e) => {
                                                setUsername(e.target.value)
                                            }}
                                        />
                                    </div>
                                    <div>
                                        <TextField
                                            className="text"
                                            size="small"
                                            type="password"
                                            value={password}
                                            onChange={(e) => {
                                                setPassword(e.target.value)
                                            }}
                                        />
                                    </div>
                                </>

                            )}

                        </div>
                        <p className="navbar-odjava" onClick={handleLogin}>
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
