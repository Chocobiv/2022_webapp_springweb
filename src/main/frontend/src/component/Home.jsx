import React, {useEffect, useState} from 'react'
import axios from 'axios'

export default function Home(props){
    const [roomList,setRoomList] = useState([])

    useEffect(()=>{
        axios.get("/room/getroomlist")
            .then(res => { setRoomList(res.data); console.log(res.data); })
            .catch(err => console.log(err))
    }, [])

    return (
        <div>
            <h3>메인 페이지</h3>
        </div>
    )
}
