import React, {useEffect, useState} from "react";
import './Controls.css'
import {useHistory} from "react-router-dom";
import ViewSnippetForm from "./ViewSnippetForm";
import InfoForm from "../Info/InfoForm";

function newSnippet() {
    alert('Create a new snippet');
}

function deleteSnippet() {
    alert('Delete this snippet');
}

function Controls() {
    return (
        <div className="controls">
            <div className= "snippetControls">
                <button className="btnNewSnippet" onClick={newSnippet}>New Snippet</button>
                <ViewSnippetForm/>
                <button className="btnDeleteSnippet" onClick={newSnippet}>Delete Snippet</button>
            </div>
        </div>
    );
}

export default Controls;