//1.
import React from 'react';

import styles from '../css/header.css'; //css 적용
import Logo from '../img/logo.png'
import {Link} from "react-router-dom";      //이미지 적용
//2.
export default function Header(props) {
    return (<div>
                <img className={"logo"} src={Logo}/>
                <h3 className={"header_name"}>헤더</h3>
                <ul>
                    <li><Link to={"/"}>Home</Link></li>
                    <li><Link to={"/member/signup"}>회원가입</Link></li>
                    <li><a href={"/member/logout"}>로그아웃</a></li>
                </ul>
            </div>)
}