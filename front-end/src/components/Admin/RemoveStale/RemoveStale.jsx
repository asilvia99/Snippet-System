import React, {useState} from "react";
import "./RemoveStale.css"

function RemoveStale({removeStaleSnippets}) {
    const [staleDays, setStaleDays] = useState("")


    return (
        <div>
            <input type="text" value={staleDays} onChange={event => setStaleDays(event.target.value)} placeholder="Enter the number of days old to remove"/>
            <button onClick={() => {removeStaleSnippets(staleDays)} }> Remove </button>
        </div>
    );
}

export default RemoveStale;