import React, {Component, useState, useEffect} from 'react';
import logo from './logo.svg';


function App () {
    const [message, setMessage] = useState("");

    useEffect(() => {
        fetch('/api')
            .then(response => response.json())
            .then(message => {
                setMessage(message[0]);
            });
    },
    [])


    return (
        <div className="App">
            <h1>{message}</h1>
        </div>
    )
}

export default App;
