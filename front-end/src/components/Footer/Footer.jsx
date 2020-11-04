import React from "react";
import "./Footer.css";

/**
 * Footer for our website. Shows authors and github link
 *
 * @component
 * @example
 * return (
 *   <Footer />
 * )
 */
const Footer = () => {
    return (
        <footer>
            <div><strong><code>Snippet System</code></strong>&#160;&#160;&#160;was created as a final project for CS 509 - Design of Software Systems</div>
            <div><p>By Khalid Alnuaim, Kevin Bimonte, Raysa Rivera-Bergollo, and Allison Silvia</p></div>
        </footer>
    );
}

export default Footer;