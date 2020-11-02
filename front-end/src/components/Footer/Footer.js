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
            <div><strong>This website was created as a final project for CS 509 - Design of Software Systems</strong></div>
            <div><p>By Khalid Alnuaim, Kevin Bimonte, Raysa Rivera-Bergolla, and Allison Silvia</p></div>
        </footer>
    );
}

export default Footer;