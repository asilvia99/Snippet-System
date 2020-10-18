import React from "react";
import './Nav.css';
import {Link} from 'react-router-dom'

function Nav() {

    const navStyle ={
        color: 'white'
    }

    const logoNavStyle = {
        color: 'white',
        textDecoration: 'none'
    }

    return (
        <nav>
            <Link style = {logoNavStyle} to='/'>
                <h3>Logo</h3>
            </Link>
            <ul className="nav-links">
                <Link style={navStyle} to='/about'>
                    <li>About</li>
                </Link>
                <Link style={navStyle} to='/shop'>
                    <li>Shop</li>
                </Link>

            </ul>
        </nav>
    );
}

export default Nav;