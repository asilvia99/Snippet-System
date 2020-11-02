import React, {useEffect, useState} from "react";
import './Info.css'
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';
import InfoForm from "./InfoForm";
import PasswordForm from "./PasswordForm";
import * as ace from "ace-builds";


const options = ['None', 'C', 'C++', 'Java', 'Python', 'SQL'];


function Info({id, isCreator, information, language}) {
    function handleSelect(e) {
        setLang(e.value);

        ace.edit("code-editor-area").getSession().setMode("ace/mode/" + e.value.toLowerCase())
    }

    function handleSubmitInfo(value) {
        setInfo(value);
    }

    function handleSubmitPass(value) {
        setPass(value);
    }

    const [info, setInfo] = useState('');
    const [lang, setLang] = useState('None');
    const [password, setPass] = useState('');

    useEffect(() => {
        setInfo(information);
    }, [information]);

    useEffect(() => {
        setLang(language);
    }, [language]);

    return (
        <div className="info">
            <div className="snippetId"> Snippet Id: {id} </div>
            <div className="section">
                <div className="passwordEditor">
                    <PasswordForm isCreator={isCreator} onSubmit = {handleSubmitPass}/>
                    {/*need to update backend*/}
                    <p> {password} </p>
                </div>
                <div className="infoEditor">
                    <InfoForm isCreator={isCreator} onSubmit = {handleSubmitInfo}/>
                    {/*need to update backend*/}
                    <p> {info} </p>
                </div>
                <div className="languageSelection"> {isCreator && "Language:"}
                    <Dropdown disabled={!isCreator} options={options} onChange={handleSelect}
                              value={lang} placeholder="Select an option"/>
                    {/*need to update backend*/}
                </div>
            </div>
        </div>
    );
}

export default Info;