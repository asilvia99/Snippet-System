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
        <div className="main-footer">
            <div className="container">
                <div className="text">
                    <strong>This website was created as a final project for CS 509 - Design of Software Systems</strong>
                    <p>
                        By Khalid Alnuaim, Kevin Bimonte, Raysa Rivera-Bergolla, and Allison Silvia
                    </p>
                    <figure className="image is-64x64">
                        <a
                            href="https://github.com/kcbimonte/CS509-SnippetSystem"
                            target="_blank"
                            rel="noopener noreferrer"
                        >
                            <img
                                className="center-footer"
                                src={require("./github-64.png")}
                                alt=""
                            />
                        </a>
                    </figure>
                </div>
            </div>
        </div>

    );
}

export default Footer;