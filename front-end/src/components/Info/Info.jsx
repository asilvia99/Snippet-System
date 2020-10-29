import React, {useEffect, useState} from "react";
import './Info.css'
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';
import InfoForm from "./InfoForm";


function handleSelect(e) {
    alert(e.value);
}

const options = [ 'None','C','C++','Java','Python'];
const defaultOption = options[0];

function Info(props) {
    return (
        <div className="info">
            <div className = "snippetId"> Snippet Id: {props.id} </div>
            < div className = "section">
                <div className="infoEditor">
                   <InfoForm/>
                </div>
                <div className = "languageSelection"> Select language:
                    <Dropdown options={options} onChange={handleSelect} value={defaultOption} placeholder="Select an option" />
                </div>
            </div>
        </div>
    );
}

export default Info;