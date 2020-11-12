import React, {useEffect, useState} from "react";
import './Snippet.css'
import Editor from "../Editor/Editor";
import Comments from "../Comments/Comments";
import Info from "../Info/Info";
import Controls from "../Controls/Controls";
import {useHistory} from "react-router-dom";
import axios from "axios";

function Snippet(props) {
    const history = useHistory();

    const [snippet, setSnippet] = useState({});
    const [comments, setComments] = useState([]);
    const [isCreator, setIsCreator] = useState(false)
    const [canComment, setCanComment] = useState(false)
    const [mark, setMark] = useState([])
    const [select, setSelect] = useState({})
    const [isAuthenticated, setIsAuthenticated] = useState(false)
    const [viewerPassword, setViewerPassword] = useState('')


    let snippetObj = {
        // language: null,
        text:' ',
        codingLang:'Text',
        canEdit: true,
        comments: [
            {
                id: '625de137-1cb5-11eb-83b2-a117ee6b83ac',
                text: 'This is a comment 1',
                startRow: 1,
                startCol: 2,
                endRow: 3,
                endCol: 4,
            },
            {
                id: '625de137-1cb5-11eb-83b2-a117ee6b8333',
                text: 'This is a comment 2',
                startRow: 4,
                startCol: 2,
                endRow: 6,
                endCol: 4,
            },
        ],
    }

    useEffect(() => {
        // if url has no id then redirect to home
        if (props.match.params.id === undefined) {
            history.push("/");
        }

        // check if the user is creator
        if (props.location.state && props.location.state.isCreator) {
            setIsCreator(true)
            setIsAuthenticated(true)
        } else {
            setIsCreator(false)
        }

        fetchSnippet(props.match.params.id)
         fetchComments(props.match.params.id)

    }, [props, history]);

    const fetchSnippet = async (sid) => {
        console.log('https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/snippet/'+sid)
        const data = await fetch('https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/snippet/'+sid)
        const s = await data.json();
        const status = JSON.parse(s.httpCode)
        if (status == 200){
            const j = JSON.parse(s.response)
            console.log(j)
            setSnippet(j)
            console.log(j.password)
            // check if the snippet is password protected
            if (j.password == '' || !j.password) {
                console.log("we're authenticated", j.password)
                setIsAuthenticated(true)
            } else {
                console.log("we're not authenticated", j.password)
                setIsAuthenticated(false)
            }
            if (props.location.state && props.location.state.isCreator) {
                setIsAuthenticated(true)
            }
        } else {
            console.log("S couldn't be found:", s)
            history.push("/");
        }

    }

    const fetchComments = async (sid) => {
        console.log('https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/comments/'+sid)
        const data = await fetch('https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/comments/'+sid)
        const s = await data.json();
        const j = JSON.parse(s.response)
        console.log(j)
        setComments(j)
    }

    const addComment = async comment => {
        console.log("add comment")
        let c = {}
        c.text = comment
        c.start = `${String(select.startRow)}:${String(select.startCol)}`
        c.end = `${String(select.endRow)}:${String(select.endCol)}`

        try {
            const headers = {
                'Content-Type': 'application/json'
            };
            const r = await axios.post(`https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/snippet/comment/${props.match.params.id}`,
                {text: c.text, start: c.start, end: c.end},
                {headers})
            console.log(r)
        } catch (e){
            console.log(e)
        }

        await fetchComments(props.match.params.id)

        setSelect({})
    }

    const deleteComment = async cID => {
        console.log('Delete comment:', cID)
        try {
            const headers = {
                'Content-Type': 'application/json'
            };
            const r = await axios.post(`https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/comment/${cID}/delete`,
                {},
                {headers})
            console.log(r)
        } catch (e){
            console.log(e)
        }

        await fetchComments(props.match.params.id)
        setSelect({})
    }


    return (
        <div>
            { isAuthenticated &&
            <main>
                <section className="section-a">
                    <div className="editor-container">
                        <Editor snippetId={snippet.id}
                                language={snippet.codingLang}
                                text={snippet.text}
                                canEdit={snippetObj.canEdit}
                                setCanComment={setCanComment}
                                mark={mark}
                                setSelect={setSelect}
                        />
                    </div>
                </section>
                <section className="section-b">
                    <div className="info-container">
                        <Info id={props.match.params.id } isCreator={isCreator} information = {snippet.info} language={snippet.codingLang}/>
                    </div>
                    <div className="control-container">
                        <Controls id={props.match.params.id } isCreator={isCreator}/>
                    </div>
                    <div className="comments-container">
                        <Comments comments={comments}
                                  canComment={canComment}
                                  setMark={setMark}
                                  addComment={addComment}
                                  deleteComment={deleteComment}
                        />
                    </div>
                </section>
            </main>
            }
            {!isAuthenticated &&
            <form onSubmit={(event) => {
                event.preventDefault();
                console.log(snippet.password)
                if(viewerPassword == snippet.password){
                    setIsAuthenticated(true)
                }
                else{
                    setViewerPassword('')
                }
            }}>
                <input type="password" value={viewerPassword}
                       onChange={(e)=>setViewerPassword(e.target.value)} placeholder="Enter the password"/>
                <input type="submit" value="Submit password"/>
            </form>
            }
        </div>
    );
}

export default Snippet;