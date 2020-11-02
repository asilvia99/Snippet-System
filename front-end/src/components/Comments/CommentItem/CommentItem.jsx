import React from "react";
import './CommentItem.css'

function CommentItem({comment, setMark}) {
    const onMouseEnter = () => {
        setMark([{
            startRow: comment.startRow,
            startCol: comment.startCol,
            endRow: comment.endRow,
            endCol: comment.endCol,
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
                <span>{`StartRow: ${comment.startRow}, StartCol: ${comment.startCol}, EndRow: ${comment.endRow}, EndCol: ${comment.endCol}`}</span>
            </div>
        </li>
    );
}

export default CommentItem;