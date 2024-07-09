import {Link} from "react-router-dom";
import React, { useState } from 'react'
import { registerAPICall } from "../services/AuthService";

export default function RegisterPage() {

    const [name, setName] = useState('');
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    function registerUser(e) {
        e.preventDefault();

        const register = {name, username, email, password};

        registerAPICall(register).then((response) => {
            console.log(response.data);
            alert('Registration successful. Now you can log in');
        }).catch(error => {
            alert(error.response.data.message);
        });
    }
    
    return (
        <div className="mt-4 grow flex items-center justify-around">
            <div className="mb-64">
                <h1 className="text-4xl text-center mb-4">Register</h1>
                <form className="max-w-md mx-auto">
                <input 
                    type="text"
                    placeholder='Enter name'
                    value={name}
                    onChange={ev => setName(ev.target.value)} />
                <input 
                    type="email"
                    placeholder="your@email.com"
                    value={email}
                    onChange={ev => setEmail(ev.target.value)} />
                <input type="password"
                        placeholder="password"
                        value={password}
                        onChange={ev => setPassword(ev.target.value)} />
                <button className="primary" onClick={ (e) => registerUser(e) }>Register</button>
                <div className="text-center py-2 text-gray-500">
                    Already a member? <Link className="underline text-black" to={'/login'}>Login</Link>
                </div>
                </form>
            </div>
        </div>
    )
}

