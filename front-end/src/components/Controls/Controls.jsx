import React, {useEffect, useState} from "react";
import './Controls.css'
import {useHistory} from "react-router-dom";
import ViewSnippetForm from "./ViewSnippetForm";
import InfoForm from "../Info/InfoForm";

function Controls({isCreator}) {
    const history = useHistory();
    const id = 'controls123';

    const location = {
        pathname: "/snippet/"+id,
        state: {isCreator: true}
    }

    function newSnippet() {
        history.push(location);
    }

    function deleteSnippet() {
        history.push("/snippet/");
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