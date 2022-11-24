//p.108     컴포넌트=함수 만들기 [HTML 구성]
//1. React 라이브러리 임포트
import React from "react";

//2. 컴포넌트 만들기 [함수 만들기]
function Book(props) {
    // -- 비동기[백엔드 서버와 통신] ajax vs. fetch -- //
    fetch("http://localhost:8081/member/list")  //통신할 url
        .then(response => response.json())          //응답 자료형
        .then(data => console.log(data))            //응답
        //CORS 오류

    //---------------------------- //
    return (
        <div>
            <h1>이 책의 이름은{props.name}입니다</h1>
            <h2>이 책은 총 {props.numOfPage}페이지로 이뤄져 있습니다.</h2>
        </div>
    )
}

//3. 내보내기 할 함수명
export default Book