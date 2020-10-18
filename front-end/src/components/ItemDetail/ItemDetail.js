import React, {useState, useEffect} from "react";
import './ItemDetail.css'

function ItemDetail({match}) {

    useEffect(() => {
        fetchItem();
        console.log(match)
    }, []);

    const [item, setItem] = useState({
        images: {}
    });

    const fetchItem = async () => {
        const fetchItem = await fetch(`https://fortnite-api.theapinetwork.com/item/get?id=${
            match.params.id}`
        );
        const item = await fetchItem.json();

        setItem(item.data.item)

        console.log(item.data.item);
    }

    return (
        <div className='page'>
            <h1>Item Details</h1>
            <h2>{item.name}</h2>
            <h3>Item Image (The image may be white):</h3>
            <img className='img-back' src={item.images.background} alt=""/>
        </div>
    );
}

export default ItemDetail;