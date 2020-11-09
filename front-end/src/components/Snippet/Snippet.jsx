import React, {useEffect, useState} from "react";
import './Snippet.css'
import Editor from "../Editor/Editor";
import Comments from "../Comments/Comments";
import Info from "../Info/Info";
import Controls from "../Controls/Controls";
import {useHistory} from "react-router-dom";

function Snippet(props) {
    const history = useHistory();

    const [snippet, setSnippet] = useState({});
    const [isCreator, setIsCreator] = useState(false)
    const [canComment, setCanComment] = useState(false)
    const [mark, setMark] = useState([])
    const [select, setSelect] = useState({})
    const [isAuthenticated, setIsAuthenticated] = useState(false)
    const [viewerPassword, setViewerPassword] = useState('')

    const snippetObj = {
        // language: null,
        text:' ',
        codingLang:'Text',
        canEdit: true,
        comments: [
            // {
            //     text: 'This is a comment 1',
            //     startRow: 1,
            //     startCol: 2,
            //     endRow: 3,
            //     endCol: 4,
            // },
            // {
            //     text: 'This is a comment 2',
            //     startRow: 4,
            //     startCol: 2,
            //     endRow: 6,
            //     endCol: 4,
            // },
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

    }, [props, history]);

    const fetchSnippet = async (sid) => {
        const data = await fetch('https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/snippet/'+sid)
        const s = await data.json();
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

    }

    const addComment = comment => {
        let c = {}
        c.text = comment
        c.startRow = select.startRow
        c.startCol = select.startCol
        c.endRow = select.endRow
        c.endCol = select.endCol
        snippetObj.comments.push(c)
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
                        <Controls isCreator={isCreator}/>
                    </div>
                    <div className="comments-container">
                        <Comments comments={snippetObj.comments}
                                  canComment={canComment}
                                  setMark={setMark}
                                  addComment={addComment}
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