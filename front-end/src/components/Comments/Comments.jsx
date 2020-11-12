import React, {useState} from "react";
import './Comments.css'
import CommentItem from "./CommentItem/CommentItem";

function Comments({comments, canComment, setMark, addComment, deleteComment}) {

    const [comment, setComment] = useState("")

    return (
        <div className="comments">
            <textarea disabled={!canComment} value={comment} onChange={event => setComment(event.target.value)}></textarea>
            <button disabled={!canComment} onClick={() => {addComment(comment); setComment('')}}>Add Comment</button>
            <ul>
                {comments.map(c =>
                    <CommentItem key={c.ID} comment={c} setMark={setMark} deleteComment={deleteComment}/>
                )}
            </ul>
        </div>
    );
}

export default Comments;