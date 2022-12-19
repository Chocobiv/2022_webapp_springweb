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
import BoardView from "./board/BoardView";
//Book
import BookList from "../Book/BookList";
import Library from "../Book/chapter3/Library";                      //3장
import Clock from "../Book/chapter4/Clock";                          //4장
import CommentList from "../Book/chapter5/CommentList";              //5장
import NotificationList from "../Book/chapter6/NotificationList";    //6장
import Counter from "../Book/chapter7/Ex1_Hook";                     //7장
import Accommodate from "../Book/chapter7/Accommodate";              //7장
import ConfirmButton from "../Book/chapter8/ConfirmButton";          //8장
import ConfirmButton2 from "../Book/chapter8/ConfirmButton2";        //8장
import TestState from "../Book/chapter8/TestState";                  //8장
import LandingPage from "../Book/chapter9/LandingPage";              //9장
import AttendanceBook from "../Book/chapter10/AttendanceBook";       //10장
import Ex1_Form from "../Book/chapter11/Ex1_Form";                   //11장
import Ex2_Signup from "../Book/chapter11/SignUp"                    //11장
import Calculator from "../Book/chapter12/Calculator";               //12장
//라우터 설치 npm i react-router-dom
import {HashRouter,BrowserRouter,Routes,Route,Link,Router} from "react-router-dom";
import Ex1_Event from "../Book/chapter8/Ex1_Event";
import BoardUpdate from "./board/BoardUpdate";
import Chatting from "./chatting/Chatting";



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
                        <Route path={"/board/view/:bno"} element={<BoardView/>}></Route>
                        <Route path={"/board/update/:bno"} element={<BoardUpdate/>}></Route>
                        <Route path={"/chatting"} element={<Chatting/>}></Route>

                        <Route path={"/book/list"} element={<BookList/>}></Route>
                        <Route path={"/book/library"} element={<Library/>}></Route>
                        <Route path={"/book/clock"} element={<Clock/>}></Route>
                        <Route path={"/book/commentlist"} element={<CommentList/>}></Route>
                        <Route path={"/book/notificationlist"} element={<NotificationList/>}></Route>
                        <Route path={"/book/ex1hook"} element={<Counter/>}></Route>
                        <Route path={"/book/accommodate"} element={<Accommodate/>}></Route>
                        <Route path={"/book/confirmbutton"} element={<ConfirmButton/>}></Route>
                        <Route path={"/book/confirmbutton2"} element={<ConfirmButton2/>}></Route>
                        <Route path={"/book/teststate"} element={<TestState/>}></Route>
                        <Route path={"/book/landingpage"} element={<LandingPage/>}></Route>
                        <Route path={"/chapter10/attendancebook"} element={<AttendanceBook/>}></Route>
                        <Route path={"/chapter11/form"} element={<Ex1_Form/>}></Route>
                        <Route path={"/chapter11/signup"} element={<Ex2_Signup/>}></Route>
                        <Route path={"/chapter12/calculator"} element={<Calculator/>}></Route>
                    </Routes>
                <Footer/>
            </BrowserRouter>
        </div>
    );
}

export default Index;