import React, {useEffect, useState} from "react";
import './Editor.css'
import CodeEditor from 'react-simple-code-editor';
import { highlight, languages } from 'prismjs/components/prism-core';
import 'prismjs/components/prism-clike';
import 'prismjs/components/prism-javascript';

function Editor({language, text, canEdit}) {

    const [lang, setLang] = useState('')
    const [code, setCode] = useState('')
    const [isEditable, setIsEditable] = useState(true)

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
            <CodeEditor
                value={code}
                onValueChange={code => setCode(code)}
                highlight={code => highlight(code, languages.js)}
                padding={10}
                style={{
                    fontFamily: '"Fira code", "Fira Mono", monospace',
                    fontSize: 12,
                }}
            />
        </div>
    );
}

export default Editor;