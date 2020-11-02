import React, {useState} from "react";
import './Comments.css'
import CommentItem from "./CommentItem/CommentItem";

function Comments({comments, canComment, setMark, addComment}) {

    const [comment, setComment] = useState("")

    return (
        <div className="comments">
            <textarea disabled={!canComment} value={comment} onChange={event => setComment(event.target.value)}></textarea>
            <button disabled={!canComment}>Add Comment</button>
            <ul>
                {comments.map(c =>
                    <CommentItem comment={c} setMark={setMark}/>
                )}
            </ul>
        </div>
    );
}

export default Comments;