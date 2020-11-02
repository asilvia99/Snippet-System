import React, {useEffect, useState} from "react";
import './Editor.css'
import AceEditor from "react-ace";
import axios from 'axios'

import "ace-builds/src-noconflict/mode-java";
import "ace-builds/src-noconflict/mode-javascript";
import "ace-builds/src-noconflict/mode-python";

import "ace-builds/src-noconflict/theme-github";
import "ace-builds/src-noconflict/theme-monokai";


/**
 * for ref: https://github.com/securingsincity/react-ace/blob/master/docs/FAQ.md
 */
function Editor({language, text, canEdit, setCanComment, mark, setSelect, snippetId}) {
    const markers = [
        {
            startRow: 0,
            startCol: 0,
            endRow: 4,
            endCol: 14,
            className: 'marker',
            type: 'background'
        },
        {
            startRow: 8,
            startCol: 0,
            endRow: 8,
            endCol: 19,
            className: 'marker',
            type: 'background'
        }
    ];

    const annotations = [
        {
            row: 1, // must be 0 based
            column: 4, // must be 0 based
            text: "[comment 1] this is the comment", // text to show in tooltip
            type: "info"
        },
        {
            row: 1, // must be 0 based
            column: 4, // must be 0 based
            text: "[comment 2] this is a comment2", // text to show in tooltip
            type: "info"
        }
    ];
    const [lang, setLang] = useState('')
    const [code, setCode] = useState('')
    const [isEditable, setIsEditable] = useState(true)
    // const [select, setSelect] = useState({})
    const [sid, setSid] = useState("")

    useEffect(() => {
        setLang(language)
    }, [language]);

    useEffect(() => {
        setCode(text)
    }, [text])

    useEffect(() => {
        setIsEditable(canEdit)
    }, [canEdit])

    useEffect(() => {
        setSid(snippetId)
    }, [snippetId])

    const updateText = async (text) => {
        try {
            const headers = {
                'Content-Type': 'application/json'
            };
            console.log(text)
            console.log(`https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/snippet/${sid}/update/text`)
            const r = await axios.post(`https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/snippet/${sid}/update/text`,
                {text: text},
                {headers})
            console.log(r)
        } catch (e){
            console.log('=========================')
            console.log(e)
        }
    }

    return (
        <div className="editor">
            <AceEditor
                placeholder="Type your code here..."
                mode={lang}
                showPrintMargin={false}
                theme="monokai"
                onChange={c => {
                    console.log(isEditable)
                    if (isEditable) {
                        setCode(c)
                        updateText(c)
                    }
                }}
                onSelectionChange={selection => {
                    if ((selection.anchor.row !== selection.cursor.row) || (selection.anchor.column !== selection.cursor.column)) {
                        setCanComment(true)
                    } else {
                        setCanComment(false)
                    }

                    let startRow, endRow, startCol, endCol = 0

                    if (selection.anchor.row === selection.cursor.row ) {
                        if (selection.anchor.column > selection.cursor.column) {
                            startRow = selection.cursor.row
                            startCol = selection.cursor.column
                            endRow = selection.anchor.row
                            endCol = selection.anchor.column
                        } else if (selection.anchor.column < selection.cursor.column) {
                            startRow = selection.anchor.row
                            startCol = selection.anchor.column
                            endRow = selection.cursor.row
                            endCol = selection.cursor.column
                        }
                    } else if (selection.anchor.row > selection.cursor.row) {
                        startRow = selection.cursor.row
                        startCol = selection.cursor.column
                        endRow = selection.anchor.row
                        endCol = selection.anchor.column
                    } else {
                        startRow = selection.anchor.row
                        startCol = selection.anchor.column
                        endRow = selection.cursor.row
                        endCol = selection.cursor.column
                    }

                    setSelect({startRow: startRow, startCol: startCol, endRow: endRow, endCol: endCol})
                }}
                name="code-editor-area"
                editorProps={{$blockScrolling: true}}
                value={code}
                markers={mark}
                // annotations={annotations}
                readOnly={!isEditable}
                height='100%'
                width='100%'
                highlightActiveLine={false}
            />
            {/*<p>{JSON.stringify(select)}</p>*/}
        </div>
    );
}

export default Editor;