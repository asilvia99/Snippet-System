import React, {useState, useEffect} from "react";
import {Link} from 'react-router-dom';

function Shop() {

    useEffect(() => {
        fetchItems();
    }, []);

    const [items, setItems] = useState([]);

    const fetchItems = async () => {
        const data = await fetch('https://fortnite-api.theapinetwork.com/upcoming/get')

        const items = await data.json();
        console.log(items.data);

        setItems(items.data);
    }


    return (
        <div>
            <h1>Shop Page</h1>
            <p>Below is a list of upcoming items:</p>
            {items.map(item => (
                <h3 key={item.itemId}>
                    <Link to={`/shop/${item.itemId}`}> {item.item.name} </Link>
                </h3>
            ))}
        </div>
    );
}

export default Shop;