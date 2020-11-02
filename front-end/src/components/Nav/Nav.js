import React from "react";
import './Nav.css';
import {Link} from 'react-router-dom'

function Nav() {

    const navStyle = {
        color: 'white'
    }

    const logoNavStyle = {
        color: 'white',
        textDecoration: 'none',
        display: 'flex',
        alignItems: 'center',
        fontSize: '2.2em'
    }

    return (
        <nav>
            <Link style={logoNavStyle} to='/'>
                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-file-earmark-code-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M2 2a2 2 0 0 1 2-2h5.293A1 1 0 0 1 10 .293L13.707 4a1 1 0 0 1 .293.707V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2zm7.5 1.5v-2l3 3h-2a1 1 0 0 1-1-1zM6.646 7.646a.5.5 0 1 1 .708.708L5.707 10l1.647 1.646a.5.5 0 0 1-.708.708l-2-2a.5.5 0 0 1 0-.708l2-2zm2.708 0a.5.5 0 1 0-.708.708L10.293 10l-1.647 1.646a.5.5 0 0 0 .708.708l2-2a.5.5 0 0 0 0-.708l-2-2z"/>
                </svg>
                <h3>Snippet System</h3>
            </Link>
            <ul className="nav-links">
                <li>
                    <a href="https://github.com/kcbimonte/CS509-SnippetSystem" target="_blank" rel="noopener noreferrer">
                        <img className="center-footer" src={require("./github-64.png")} alt="GitHub" />
                    </a>
                </li>
            </ul>
        </nav>
    );
}

export default Nav;