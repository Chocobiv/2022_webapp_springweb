import React from 'react'

export default function BoardWrite(props) {
    const setbcategory = () => { alert('카테고리 추가합니다.') }
    const setboard = () => { alert('카테고리 추가합니다.') }

    return (
        <div>
            <h1>글 쓰기 페이지</h1>
            <input type="text" className="bcname"/>
            <button type="button" onClick={setbcategory}>카테고리 추가</button>
            <span className="bcategorybox"></span> <br/>
            <form className="boardform">
                제목 : <input type="text" name="btitle" style={{width: 220}}/><br/>
                내용 : <textarea type="text" name="bcontent" style={{width: 220, height: 150}}></textarea><br/>
                첨부파일 : <input type="file" name="bfile"/><br/>
                <button type="button" onClick={setboard}>등록</button>
            </form>
            <a href="/board/list">목록보기</a>
        </div>
    )
}


