
import React from 'react';
import Comment from "./Comment";

//1. 데이터 리스트 [서버 통신과 통신된 결과물]
const comments = [
    {
        name : "이인제",
        comment : "안녕하세요, 소플입니다~"
    },
    {
        name : "유재석",
        comment : "리액트 재미있어요?"
    },
    {
        name : "강호동",
        comment : "저도 리액트 배워보고 싶습니다..?"
    }
]

function CommentList(props){
    //map  vs.  forEach
    //리스트명.map( (반복변수명) => { 실행문 })
    return (
        <div>
            {comments.map((c)=> {
                return(
                    <Comment name={c.name} comment={c.comment}/>
                )
            })}
        </div>
    )
}

export default CommentList
