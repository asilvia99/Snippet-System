import React, {useEffect, useState} from "react";
import './Controls.css'

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
                <form className = "viewSnippet">
                    <label htmlFor="snippetId">Enter Snippet Id to view:</label>
                    <input type="text" id="snippetId" name="snippetId"></input>
                    <input type="submit" value="View Snippet"></input>
                </form>
                <button className="btnDeleteSnippet" onClick={newSnippet}>Delete Snippet</button>
            </div>
        </div>
    );
}

export default Controls;