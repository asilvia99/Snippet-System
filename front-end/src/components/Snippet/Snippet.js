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

    const snippetObj = {
        language: 'javascript',
        text:' ',
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
        console.log(props)

        // if url has no id then redirect to home
        if (props.match.params.id == undefined) {
            history.push("/");
        }

        fetchItems(props.match.params.id)


        // check if the user is creator
        if (props.location.state && props.location.state.isCreator) {
            setIsCreator(true)
        } else {
            setIsCreator(false)
        }

    }, [props]);

    const fetchItems = async (sid) => {
        const data = await fetch('https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/snippet/'+sid)
        const s = await data.json();
        console.log(s.response)
        console.log(s.response.id)
        const j = JSON.parse(s.response)
        console.log(s.response.text);
        console.log(j)
        console.log(j.text)
        setSnippet(j)
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
            {/*<h2>{props.match.params.id}</h2>*/}
            <main>
                <section className="section-a">
                    <div className="editor-container">
                        <Editor snippetId={snippet.id}
                                language={snippet.codingLanguage}
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
                        <Info id={props.match.params.id } isCreator={isCreator} information = {snippet.info} language={snippet.codingLanguage}/>
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

        </div>
    );
}

export default Snippet;