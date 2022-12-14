
import React from 'react';
import axios from "axios";

function Signup(props) {    // * 회원가입 컴포넌트 *
    //class -> className 변경
    //onclick -> onClick 변경
    //태그 닫기 /tag명
    //함수호출 -> {} jsx 표현식


    const setmemeber = () =>{   //1. setmemeber 이벤트 함수 정의 [화살표함수 정의]
        let info = {            //2. 입력받은 값 가져오기
            memail: document.querySelector('.memail').value,
            mpassword: document.querySelector('.mpassword').value,
            mphone: document.querySelector('.mphone').value
        }
        //비동기 통신 [ ajax vs fetch[react내장] vs axios[react외장(별도설치)->json]]
        axios                   //3. axios 비동기통신 이용한 서버[Spring] 통신
            .post("/member/setmember", info)       //요청메소드 (url,data)
            .then(res => {                                                  //응답
                let result = res.data
                if (result != 0){   //회원가입 성공하면
                    alert('회원가입 성공')
                }else{              //회원가입 실패하면
                    alert('회원가입 실패')
                }
            })
            .catch(err => { console.log(err)})                              //예외처리
    }
    //2. 인증코드 요청 함수 정의 [화살표함수 정의]
    const getauth = () =>{ alert("클릭 이벤트") }
    //3. 타이머 함수 정의 [화살표함수 정의]
    const settimer = () =>{ alert("클릭 이벤트") }
    //4. 인증 버튼 확인 함수 정의 [화살표함수 정의]
    const authcode = () =>{ alert("클릭 이벤트") }

    return(
        <div>
            <h3>회원가입</h3>
            <div>
                이메일 : <input type="text" className="memail" />
                <button type="button" onClick={getauth} className="getauthbtn">인증코드받기</button> <br/>
                <div className="authbox">
                    <input type="text" className="authinput" />
                    <button type="button" className="authbtn" onClick={authcode}>인증</button>
                    <span className="timerbox"></span>
                </div>
            </div>
            핸드폰 : <input type="text" className="mphone" />
            <br/>
            비밀번호 : <input type="text" className="mpassword" />
            <br/>
            <button type="button" onClick={setmemeber}>가입하기</button>
        </div>
    )
}

export default Signup
