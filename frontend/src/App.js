import React, {Component, useState, useEffect} from 'react';
import logo from './logo.svg';


function App () {
    const [message, setMessage] = useState("");

    useEffect(() => {
        fetch('/api/hello')
            .then(response => response.text())
            .then(message => {
                setMessage(message);
            });
    },[])
    return (
        <div className="App">
            <h1>{message}</h1>
        </div>
    )
}

export default App;
