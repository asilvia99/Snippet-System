import React from "react";
import './CommentItem.css'
import axios from "axios";

function CommentItem({comment, setMark, deleteComment}) {
    const onMouseEnter = () => {
        setMark([{
            startRow: comment.start.split(':')[0],
            startCol: comment.start.split(':')[1],
            endRow: comment.end.split(':')[0],
            endCol: comment.end.split(':')[1],
            className: 'marker',
            type: 'background'
        }])
    }

    const onMouseLeave = () => {
        setMark([])
    }

    return (
        <li className="comment" onMouseEnter={onMouseEnter} onMouseLeave={onMouseLeave}>
            <div>
                <p>{comment.text}</p>
                <span>{`StartRow: ${comment.start.split(':')[0]}, StartCol: ${comment.start.split(':')[1]}, EndRow: ${comment.end.split(':')[0]}, EndCol: ${comment.end.split(':')[1]}`}</span>
                <br/>
                <button onClick={() => {deleteComment(comment.ID); setMark([])} }>Delete</button>
            </div>
        </li>
    );
}

export default CommentItem;