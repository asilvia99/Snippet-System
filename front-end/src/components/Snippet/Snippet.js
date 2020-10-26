import React, {useEffect} from "react";
import './Snippet.css'
import Editor from "../Editor/Editor";
import Comments from "../Comments/Comments";
import Info from "../Info/Info";
import Controls from "../Controls/Controls";

function Snippet(props) {

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

    useEffect(() => {
        console.log(props)
    }, [props]);

    return (
        <div>
            <h2>{props.match.params.id}</h2>
            <main>
                <section>
                    <div className="info-container">
                        <Info/>
                    </div>
                    <div className="editor-container">
                        <Editor language={snippetObj.language} text={snippetObj.text} canEdit={snippetObj.canEdit}/>
                    </div>
                </section>
                <section>
                    <div className="control-container">
                        <Controls/>
                    </div>
                    <div className="comments-container">
                        <Comments />
                    </div>
                </section>
            </main>

        </div>
    );
}

export default Snippet;