//jsx : 리액트 확장 표현식 파일
//컴포넌트 단위 애플리케이션 제작

//1.
import React from 'react';
import Header from './Header';
import Footer from "./Footer";
import Signup from "./member/Signup";
//라우터 설치 npm i react-router-dom
import {
    HashRouter,
    BrowserRouter,
    Routes,
    Route,
    Link,
    Router
} from "react-router-dom";

//2.
function Index(props) {
    return (
        <div>
            <BrowserRouter>
                <Header/>
                    <Routes>
                        <Route path={"/"}></Route>
                        <Route path={"/member/signup"} element={<Signup/>}></Route>
                    </Routes>
                <Footer/>
            </BrowserRouter>
        </div>
    );
}

export default Index;