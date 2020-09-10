import React from 'react';
import './App.css';
import People from './components/people/People'

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <p>
          SL Coding Assessment
        </p>
      </header>
      <div className="App-body">
        <People/>
      </div>
    </div>
  );
}

export default App;
