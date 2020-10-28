import React, {useEffect} from "react";
import './Snippet.css'
import Editor from "../Editor/Editor";
import Comments from "../Comments/Comments";
import Info from "../Info/Info";
import Controls from "../Controls/Controls";
import { useHistory } from "react-router-dom";

function Snippet(props) {
    const history = useHistory();

    const snippetObj = {
        language: 'python',
        text: 'let text = "hello"\nlet text = "hello2"',
        canEdit: true,
    }

    useEffect(() => {
        console.log(props)
        if (props.match.params.id == undefined) {
                history.push("/");
                console.log("need to redirect")
        }
    }, [props]);

    return (
        <div>
            {/*<h2>{props.match.params.id}</h2>*/}
            <main>
                <section>
                    <div className="info-container">
                        <Info id = {props.match.params.id} />
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