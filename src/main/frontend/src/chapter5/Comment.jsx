//컴포넌트 만들기

//1. 컴포넌트 이름은 첫 글자 대문자로 시작!
// 파일명과 컴포넌트 이름 동일하게
//2. 준비물
// 2-1. 상단 : import React from 'react' [필수]
// 2-2. 하단 : export default Comment [필수]
import React from 'react'
import styles from './Comment.css'

function Comment(props){
    return(
        <div className="wrapper">
            <div className="imgContainer">
                <img src="https://upload.wikimedia.org/wikipedia/commons/8/89/Portrait_Placeholder.png" className="image"/>
            </div>
            <div className="contentContainer">
                <span className="nameText">{props.name}</span>
                <span className={"commentText"}>{props.comment}</span>
            </div>
        </div>
    )
}

//3. 컴포넌트 내보내기
export default Comment
