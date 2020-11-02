import React from 'react';

import './App.css';
import Nav from "./components/Nav/Nav";
import Home from "./components/Home/Home";
import Snippet from "./components/Snippet/Snippet";

import {
    BrowserRouter as Router,
    Switch,
    Route
} from 'react-router-dom';
import Footer from "./components/Footer/Footer";

function App() {
    return (
        <Router>
            <div className="page-container">
                <Nav/>
                <div className="content-wrap">
                    <Switch>
                        <Route path="/" exact component={Home}/>
                        <Route path="/snippet/:id" component={Snippet}/>
                        <Route path="/snippet" exact component={Snippet}/>
                    </Switch>
                </div>
                <Footer/>
            </div>
        </Router>
    );
}

export default App;
