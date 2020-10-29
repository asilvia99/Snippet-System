import React, {useEffect, useState} from "react";
import './Info.css'
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';
import InfoForm from "./InfoForm";



function Info(props) {
    const options = [ 'C','C++','Java','Python'];
    const defaultOption = options[0];

    function handleSelect(e) {
        alert(e.value);
    }

    return (
        <div className="info">
            <div className = "snippetId"> Snippet Id: {props.id} </div>
            <div className = "section">
                <div className="infoEditor">
                   <InfoForm isCreator={props.isCreator}/>
                </div>
                <div className = "languageSelection"> {props.isCreator && "Select language:"}
                    <Dropdown disabled={!props.isCreator} options={options} onChange={handleSelect} value={defaultOption} placeholder="Select an option" />
                </div>
            </div>
        </div>
    );
}

export default Info;