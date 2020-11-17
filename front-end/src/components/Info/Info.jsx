import React, {useEffect, useState} from "react";
import './Info.css'
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';
import InfoForm from "./InfoForm";
import PasswordForm from "./PasswordForm";
import * as ace from "ace-builds";

import {global_languages as options} from "../Editor/themes_languages"
import axios from "axios";

function Info({id, isCreator, information, language}) {
    function handleSelect(e) {
        setLang(e.value);

        ace.edit("code-editor-area").getSession().setMode("ace/mode/" + e.value.toLowerCase())
        updateLang(e.value);
    }

    function handleSubmitInfo(value) {
        setInfo(value);
        updateInfo(value);
    }

    function handleSubmitPass(value) {
        setPass(value);
        updatePassword(value);
    }

    const [info, setInfo] = useState('');
    const [lang, setLang] = useState('');
    const [password, setPass] = useState('');

    useEffect(() => {
        setInfo(information);
    }, [information]);

    useEffect(() => {
        setLang(language);
    }, [language]);



    const updatePassword = async (password) => {
        try {
            const headers = {
                'Content-Type': 'application/json'
            };
            console.log(password)
            console.log(`https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/snippet/${id}/update/password`)
            const r = await axios.post(`https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/snippet/${id}/update/password`,
                {password: password},
                {headers})
            console.log(r)
        } catch (e){
            console.log('=========================')
            console.log(e)
        }
    }

    const updateLang = async (lang) => {
        try {
            const headers = {
                'Content-Type': 'application/json'
            };
            console.log(lang)
            console.log(`https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/snippet/${id}/update/codinglang`)
            const r = await axios.post(`https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/snippet/${id}/update/codinglang`,
                {codingLang: lang},
                {headers})
            console.log(r)
        } catch (e){
            console.log('=========================')
            console.log(e)
        }
    }

    const updateInfo = async (info) => {
        try {
            const headers = {
                'Content-Type': 'application/json'
            };
            console.log(info)
            console.log(`https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/snippet/${id}/update/info`)
            const r = await axios.post(`https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/snippet/${id}/update/info`,
                {info: info},
                {headers})
            console.log(r)
        } catch (e){
            console.log('=========================')
            console.log(e)
        }
    }

    return (
        <div className="info">
            <div className="snippetId"> Snippet Id: {id} </div>
            <div className="section">
                {isCreator &&
                <div className="passwordEditor">
                    <PasswordForm isCreator={isCreator} onSubmit={handleSubmitPass}/>
                </div>
                }
                <div className="infoEditor">
                    {isCreator &&
                    <InfoForm isCreator={isCreator} onSubmit={handleSubmitInfo}/>
                    }
                    <p> Snippet Info: {info} </p>
                </div>
                <div className="languageSelection"> {isCreator && "Language:"}
                    <Dropdown disabled={!isCreator} options={options} onChange={handleSelect}
                              value={lang} placeholder="Text"/>
                    {/*need to update backend*/}
                </div>
            </div>
        </div>
    );
}

export default Info;