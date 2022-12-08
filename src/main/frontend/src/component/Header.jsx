//1.
import React, {useState} from 'react';

import styles from '../css/header.css';     //css 적용
import Logo from '../img/logo.png'
import { HashRouter,BrowserRouter,Routes,Route,Link,Router} from "react-router-dom";
import axios from "axios";  //react 비동기 통신 라이브러리
//2.
export default function Header(props) {
    const [login, setLogin] = useState(null)//로그인된 회원정보 state 생명주기 -> 변경(setter) 시 (현재 컴포넌트)재렌더링됨

    //1. 비동기 통신 [AJAX, fetch(리액트내장라이브러리), axios(설치형라이브러리)] axios : Data type = json(default)
    axios
        .get("/member/getloginMno")
        //.get("URL")
        //.post("URL",data)
        //.put("URL",data)
        //.delete("URL")
        .then( (response) => {setLogin(response.data);})
        //.then(옵션메소드)
        //.then( (응답객채명) => { 응답 실행문 } )
            //응답객채명 : http 응답 객체 반환
                //응답데이터 호출 : 객체명.data

    //1. 서버와 통신 [ axios ]
    //axios.get('/member/getlogin').then(res=>{alert("서버와 통신됨")})   //axios.type('URL').then( res => {응답})

    return (
        <div className={"webbox"}>
            <div className={"header"}>
                <div className={"header_logo"}>
                    <Link to={"/"}><img className={"logo"} src={Logo}/></Link>
                </div>
                <ul className={"top_menu"}>
                    {login == "" ?
                        (
                            <>
                                <li><Link to={"/member/signup"}>회원가입</Link></li>
                                <li><Link to={"/member/login"}>로그인</Link></li>
                            </>
                        ) : (
                            <>
                                <li>{login}</li>
                                <li><a href={"/member/logout"}>로그아웃</a></li>
                            </>
                        )
                    }
                    <li><Link to={"/board/list"}>자유게시판</Link></li>

                </ul>
            </div>
        </div>
    )
}