import React, {useState} from "react";
import "./SnippetList.css"
import SnippetItem from "./SnippetItem/SnippetItem";

function SnippetList({snippets, deleteSnippet}) {

    return (
        <div className="snippets">
            <table >
                <thead> <tr> <th> Snippet Id </th> <th> Snippet Info </th> <th> Coding Lang </th> <th> Last Modified Date</th> <th> Creation Date </th> <th></th></tr></thead>
                    <tbody>
                    {snippets.map(s =>
                        <SnippetItem key={s.ID} snippet={s} deleteSnippet={deleteSnippet}/>
                        )}
                    </tbody>

            </table>
            {/*<ul>*/}
            {/*    {snippets.map(s =>*/}
            {/*        <SnippetItem key={s.ID} snippet={s} deleteSnippet={deleteSnippet}/>*/}
            {/*    )}*/}
            {/*</ul>*/}
        </div>
    );
}

export default SnippetList;