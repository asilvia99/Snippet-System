import React from "react";
import { useHistory } from "react-router-dom";



function Home() {
    const history = useHistory();
    const id = '123';
    function newSnippet() {
        history.push("/snippet/"+ id);
    }
    return (
        <div>
            <h1>Home Page</h1>
            <button className="btnNewSnippet" onClick={newSnippet}>Create a New Snippet</button>
        </div>
    );
}

export default Home;