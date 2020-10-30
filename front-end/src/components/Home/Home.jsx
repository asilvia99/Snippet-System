import React from "react";
import {useHistory} from "react-router-dom";


function Home() {
    const history = useHistory();
    const id = 'ddb7ed69-f520-4f5a-8fe9-08271484b3c7';
    const location = {
        pathname: "/snippet/" + id,
        state: {isCreator: true}
    }

    function newSnippet() {
        history.push(location);
    }

    return (
        <div>
            <h1>Home Page</h1>
            <button className="btnNewSnippet" onClick={newSnippet}>Create a New Snippet</button>
        </div>
    );
}

export default Home;