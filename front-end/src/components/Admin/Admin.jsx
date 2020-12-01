import React, {useEffect, useState} from "react";
import './Admin.css'
import RemoveStale from "./RemoveStale/RemoveStale";
import SnippetList from "./SnippetList/SnippetList";
import axios from "axios";

function Admin(props) {
    const [snippets, setSnippets] = useState([]);
    const [isAuthenticated, setIsAuthenticated] = useState(false)
    const [adminPassword, setAdminPassword] = useState('')

    useEffect(() => {
        // here we will get the s3 password
        setIsAuthenticated(false)  // only for dev
        fetchSnippets()
    }, [props]);

    const refresh = async () => {
        fetchSnippets()
    }

    const fetchSnippets = async () => {
        console.log('https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/admin/list')
        const data = await fetch('https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/admin/list')
        const s = await data.json();
        const j = JSON.parse(s.response)
        console.log(j)
        setSnippets(j)
        // setSnippets([{ID:"123", info: "example info", codingLang:"Java", lastModified:"fake date"}, {ID:"125", info: "example info", codingLang:"Java", lastModified:"fake date"}, , {ID:"125", info: "example info", codingLang:"Java", lastModified:"fake date"},
        //     , {ID:"125", info: "example info", codingLang:"Java", lastModified:"fake date"},, {ID:"125", info: "example info", codingLang:"Java", lastModified:"fake date"},
        //     , {ID:"125", info: "example info", codingLang:"Java", lastModified:"fake date"},, {ID:"125", info: "example info", codingLang:"Java", lastModified:"fake date"},, {ID:"125", info: "example info", codingLang:"Java", lastModified:"fake date"},
        //     , {ID:"125", info: "example info", codingLang:"Java", lastModified:"fake date"}])
        console.log("Inside fetchSnippets")
    }

    const deleteSnippet = async sID => {
        const result = window.confirm("Are you sure you want to delete?")
        if (result) {
            // call backend to delete snippet
            try {
                const headers = {'Content-Type': 'application/json'};
                const r = await axios.post(`https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/admin/${sID}/delete`,
                    {},
                    {headers})
                console.log(r.data.response)
            } catch (e) {
                console.log(e)
            }
            fetchSnippets()
        }

    }

    const removeStaleSnippets = async days => {
        const result = window.confirm("Are you sure you want to remove these?")
        if (result) {
            // call backend to delete snippets
            try {
                const headers = {'Content-Type': 'application/json'};
                const r = await axios.post(`https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/admin/remove`,
                    {days:days},
                    {headers})
                console.log(days)
                console.log(r.data.response)
                fetchSnippets()
            } catch (e) {
                console.log(e)
            }
        }
    }

    const checkPassword = async (e, password) => {
        e.preventDefault();
        try {
            const headers = {'Content-Type': 'application/json'};
            const r = await axios.post(`https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/admin/check`,
                {password:password},
                {headers})

            if(r.data.response == 'Success') {
                setIsAuthenticated(true)
            } else {
                setAdminPassword('')
                window.alert('Wrong Password!')
            }

        } catch (e) {
            console.log(e)
        }



    }



    return (
        <div>
            { isAuthenticated &&
            <main className = "adminMain">
                <button onClick={refresh}>Refresh</button>
                <section> <RemoveStale removeStaleSnippets={removeStaleSnippets}/> </section>
                <section> <SnippetList snippets={snippets} deleteSnippet={deleteSnippet}/> </section>
            </main>
            }
            {!isAuthenticated &&
            <form onSubmit={(event) => {checkPassword(event, adminPassword)}}>
                <input type="password" value={adminPassword}
                       onChange={(e)=>setAdminPassword(e.target.value)} placeholder="Enter the password"/>
                <input type="submit" value="Submit password"/>
            </form>
            }
        </div>
    );
}

export default Admin;