import React from 'react'

export default function BoardList(props){
    return (
        <div>
            <a href="/board/write">글쓰기[로그인했을때만표시]</a>
            <h1>글 목록 페이지</h1>
            <div className="bcategorybox"></div>
            <table className="btable"> </table>
        </div>
    )
}