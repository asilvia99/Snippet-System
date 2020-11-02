import React from "react";
import {useHistory} from "react-router-dom";
import axios from 'axios'

function Home() {
    const history = useHistory();
    const location = {
        pathname: "",
        state: {isCreator: true}
    }

    async function newSnippet() {
        // call backend to create snippet
        try {
            const headers = { 'Content-Type': 'application/json' };
            const r = await axios.post(`https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/snippet/`,
                {info: '', text: ''},
                {headers})
            console.log(r.data.response)
            location.pathname = "/snippet/" + r.data.response

        } catch (e){
            console.log('=========================')
            console.log(e)
        }
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