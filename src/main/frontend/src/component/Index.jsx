//jsx : 리액트 확장 표현식 파일
//컴포넌트 단위 애플리케이션 제작

//1.
import React from 'react';
import Header from './Header';
import Footer from "./Footer";
import Signup from "./member/Signup";
import Home from "./Home";
import Login from "./member/Login";
import BoardList from "./board/BoardList";
import BoardWrite from "./board/BoardWrite";
//라우터 설치 npm i react-router-dom
import {HashRouter,BrowserRouter,Routes,Route,Link,Router} from "react-router-dom";

//2.
function Index(props) {
    return (
        <div className={"webbox"}>
            <BrowserRouter>
                <Header/>
                    <Routes>
                        <Route path={"/"} element={<Home/>}></Route>
                        <Route path={"/member/signup"} element={<Signup/>}></Route>
                        <Route path={"/member/login"} element={<Login/>}></Route>
                        <Route path={"/board/list"} element={<BoardList/>}></Route>
                        <Route path={"/board/write"} element={<BoardWrite/>}></Route>
                    </Routes>
                <Footer/>
            </BrowserRouter>
        </div>
    );
}

export default Index;