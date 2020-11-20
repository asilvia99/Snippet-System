import React from 'react';

import './App.css';
import Nav from "./components/Nav/Nav";
import Home from "./components/Home/Home";
import Snippet from "./components/Snippet/Snippet";
import Admin from "./components/Admin/Admin";

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
                        <Route path="/index.html" exact component={Home}/>
                        <Route path="/" exact component={Home}/>
                        <Route path="/snippet/:id" component={Snippet}/>
                        <Route path="/snippet" exact component={Snippet}/>
                        <Route path="/admin" exact component={Admin}/>

                    </Switch>
                </div>
                <Footer/>
            </div>
        </Router>
    );
}

export default App;
