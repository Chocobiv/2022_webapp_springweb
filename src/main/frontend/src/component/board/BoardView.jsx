import React from 'react'
import {HashRouter,BrowserRouter,Routes,Route,Link,Router,useParams} from "react-router-dom";

export default function BoardView(props) {
    const params = useParams()
    return(
        <div>
            뷰 페이지로 들어옴 페이지번호 : {params.bno}
        </div>
    )
}