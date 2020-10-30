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

    const snippetObj = {
        language: 'javascript',
        text:
            `function onLoad(editor) {
    console.log("i've loaded");
}

function onLoad(editor) {
    console.log("i've loaded");
}

function onLoad(editor) {
    console.log("i've loaded");
}`,
        canEdit: true,
    }

    const [isCreator, setIsCreator] = useState(false)

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
        const j = JSON.parse(s.response)
        console.log(s.response);
        console.log(j)
        console.log(j.text)
        setSnippet(j)
    }

    return (
        <div>
            {/*<h2>{props.match.params.id}</h2>*/}
            <main>
                <section>
                    <div className="info-container">
                        <Info id={props.match.params.id} isCreator={isCreator}/>
                    </div>
                    <div className="editor-container">
                        <Editor snippetId={snippet.id} language={snippet.codingLanguage} text={snippet.text} canEdit={snippetObj.canEdit}/>
                    </div>
                </section>
                <section>
                    <div className="control-container">
                        <Controls isCreator={isCreator}/>
                    </div>
                    <div className="comments-container">
                        <Comments/>
                    </div>
                </section>
            </main>

        </div>
    );
}

export default Snippet;