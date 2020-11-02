import React from "react";
import './Controls.css'
import {useHistory} from "react-router-dom";
import ViewSnippetForm from "./ViewSnippetForm";
import axios from "axios";

function Controls({isCreator}) {
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
            console.log(e)
        }
        history.push(location);
    }

    function deleteSnippet() {
        history.push("/");
    }
    function enablePassword(){
        alert("Viewers will need this password to view: atklgbhgluf")
    }

    return (
        <div className="controls">
            <div className="snippetControls">
                <button className="btnNewSnippet" onClick={newSnippet}>New Snippet</button>
                <ViewSnippetForm history={useHistory()}/>
                {isCreator &&
                <button className="btnDeleteSnippet" onClick={deleteSnippet}>Delete Snippet</button>
                }
                {isCreator &&
                <button className = "btnPassword"onClick={enablePassword}>Password Protect</button>
                    //need to update backend
                }
            </div>
        </div>
    );
}

export default Controls;