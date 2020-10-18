import React from 'react';

import './App.css';
import Nav from "./components/Nav/Nav";
import Home from "./components/Home/Home";
import About from "./components/About/About";
import Shop from "./components/Shop/Shop";
import ItemDetail from "./components/ItemDetail/ItemDetail";

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
                        <Route path="/about" exact component={About}/>
                        <Route path="/shop" exact component={Shop}/>
                        <Route path="/shop/:id" component={ItemDetail}/>
                    </Switch>
                </div>
                <Footer/>
            </div>
        </Router>
    );
}

export default App;
