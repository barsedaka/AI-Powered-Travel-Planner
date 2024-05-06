import React, { useContext, useState } from 'react'
import { loginAPICall, saveLoggedInUser, saveLoggedInUserRole, storeToken } from '../services/AuthService';
import { Link, Navigate } from 'react-router-dom';
import { UserContext } from '../UserContext';

export default function LoginPage() {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [redirect, setRedirect] = useState(false);
    const { setUser, setRole } = useContext(UserContext);

    async function handleLoginForm(e) {
        e.preventDefault();

        await loginAPICall(username, password).then((response) => {
            const token = 'Bearer ' + response.data.accessToken;
            const role = response.data.role;
            storeToken(token);

            //saveLoggedInUserRole(role);
            saveLoggedInUser(username);
            saveLoggedInUserRole(role);
            setUser(username);
            setRole(role);
            alert('Login successful');
            setRedirect(true);

            //window.location.reload(false);
        }).catch(error => {
            alert(error.response.data.message);
        });
    }

    if (redirect) {
        return <Navigate to={'/'} />
    }

    return (
        <div className="mt-4 grow flex items-center justify-around">
            <div className="mb-64">
                <h1 className="text-4xl text-center mb-4">Login</h1>
                <form className="max-w-md mx-auto">
                    <input
                        type='text'
                        name='username'
                        className='form-control'
                        placeholder='Enter username'
                        value={username}
                        onChange={ (e) => setUsername(e.target.value) }
                    />
                    
                    <input type="password"
                            placeholder="password"
                            value={password}
                            onChange={ev => setPassword(ev.target.value)} />

                    <button className="primary" onClick={ (e) => handleLoginForm(e) }>Login</button>
                    <div className="text-center py-2 text-gray-500">
                        Don't have an account yet? <Link className="underline text-black" to={'/register'}>Register now</Link>
                    </div>
                </form>
            </div>
    </div>
    )
}