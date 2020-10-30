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

    return (
        <div className="controls">
            <div className="snippetControls">
                <button className="btnNewSnippet" onClick={newSnippet}>New Snippet</button>
                <ViewSnippetForm history={useHistory()}/>
                {isCreator &&
                <button className="btnDeleteSnippet" onClick={deleteSnippet}>Delete Snippet</button>
                }
            </div>
        </div>
    );
}

export default Controls;