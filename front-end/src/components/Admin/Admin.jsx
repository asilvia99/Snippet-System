import React, {useEffect, useState} from "react";
import './Admin.css'
import RemoveStale from "./RemoveStale/RemoveStale";
import SnippetList from "./SnippetList/SnippetList";

function Admin(props) {
    const [snippets, setSnippets] = useState([]);
    const [isAuthenticated, setIsAuthenticated] = useState(false)
    const [adminPassword, setAdminPassword] = useState('')

    useEffect(() => {
        // here we will get the s3 password
        setIsAuthenticated(true)  // only for dev
        fetchSnippets()
    }, [props]);

    const refresh = async () => {
        fetchSnippets()
    }

    const fetchSnippets = async () => {
        // console.log('https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/comments/'+sid)
        // const data = await fetch('https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/comments/'+sid)
        // const s = await data.json();
        // const j = JSON.parse(s.response)
        // console.log(j)
        // setComments(j)
    }

    const deleteSnippet = async sID => {
        console.log('Delete comment:', sID)
        try {
            const headers = {
                'Content-Type': 'application/json'
            };
            //const r = await axios.post(`https://3rkdcoc9pe.execute-api.us-east-2.amazonaws.com/beta/comment/${cID}/delete`,
             /*   {},
                {headers})*/
            //console.log(r)
        } catch (e){
            //console.log(e)
        }


    }


    return (
        <div>
            { isAuthenticated &&
            <main className = "adminMain">
                <section> <RemoveStale/> </section>
                <section> <SnippetList/> </section>
            </main>
            }
            {!isAuthenticated &&
            <form onSubmit={(event) => {
                event.preventDefault();
                if(adminPassword == 'password123'){ //need to see how to get it from the bucket
                    setIsAuthenticated(true)
                }
                else{
                    setAdminPassword('')
                }
            }}>
                <input type="password" value={adminPassword}
                       onChange={(e)=>setAdminPassword(e.target.value)} placeholder="Enter the password"/>
                <input type="submit" value="Submit password"/>
            </form>
            }
        </div>
    );
}

export default Admin;