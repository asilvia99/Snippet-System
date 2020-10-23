import React, {useEffect, useState} from "react";
import './Editor.css'
import AceEditor from "react-ace";

import "ace-builds/src-noconflict/mode-java";
import "ace-builds/src-noconflict/mode-javascript";
import "ace-builds/src-noconflict/mode-python";

import "ace-builds/src-noconflict/theme-github";
import "ace-builds/src-noconflict/theme-monokai";


/**
 * for ref: https://github.com/securingsincity/react-ace/blob/master/docs/FAQ.md
 */
function Editor({language, text, canEdit}) {
    const markers = [
        {   startRow: 0,
            startCol: 0,
            endRow: 4,
            endCol: 14,
            className: 'marker',
            type: 'background'
        },
        {   startRow: 8,
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
    const [select, setSelect] = useState({})

    useEffect(() => {
        setLang(language)
    }, [language]);

    useEffect(() => {
        setCode(text)
    }, [text])

    useEffect(() => {
        setIsEditable(canEdit)
    }, [canEdit])

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
                    }
                }}
                onSelectionChange={selection => {
                    setSelect({anchorRow: selection.anchor.row, anchorColumn: selection.anchor.column, cursorRow: selection.cursor.row, cursorColumn: selection.cursor.column, })
                }}
                name="code-editor-area"
                editorProps={{ $blockScrolling: true }}
                value={code}
                markers={markers}
                annotations={annotations}
                readOnly={!isEditable}
                height='90%'
                width='100%'
                highlightActiveLine={false}
            />
            <p>{JSON.stringify(select)}</p>
        </div>
    );
}

export default Editor;