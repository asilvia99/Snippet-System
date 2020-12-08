import React from "react";
import './SnippetItem.css'
import axios from "axios";

function SnippetItem({snippet, deleteSnippet}) {

    return (

        <tr><td>{snippet.ID}</td><td>{snippet.info}</td><td>{snippet.commentCount}</td><td>{new Date(snippet.created.seconds * 1000).toLocaleString()}</td><td>{new Date(snippet.lastModified.seconds * 1000).toLocaleString()}</td><td><button onClick={() => {deleteSnippet(snippet.ID)} }>Delete</button></td></tr>


    // <li className="snippet" >
    //         <div>
    //
    //             <p>{snippet.ID}</p>
    //             <span>{`SnippetInfo: ${snippet.info}, SnippetLang: ${snippet.codingLang}, LastModified: ${snippet.lastModified}, CreationDate: ${snippet.lastModified}`}</span>
    //             <button onClick={() => {deleteSnippet(snippet.ID)} }>Delete</button>
    //         </div>
    //     </li>
    );
}

export default SnippetItem;