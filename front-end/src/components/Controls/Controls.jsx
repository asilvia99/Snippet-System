import React, {useEffect, useState} from "react";
import './Controls.css'
import {useHistory} from "react-router-dom";
import ViewSnippetForm from "./ViewSnippetForm";
import InfoForm from "../Info/InfoForm";

function Controls() {
    const history = useHistory();
    const id = 'controls123';
    function newSnippet() {
        history.push("/snippet/"+ id);
    }
    function deleteSnippet() {
        history.push("/snippet/");
    }
    return (
        <div className="controls">
            <div className= "snippetControls">
                <button className="btnNewSnippet" onClick={newSnippet}>New Snippet</button>
                <ViewSnippetForm history = {useHistory()}/>
                <button className="btnDeleteSnippet" onClick={deleteSnippet}>Delete Snippet</button>
            </div>
        </div>
    );
}

export default Controls;