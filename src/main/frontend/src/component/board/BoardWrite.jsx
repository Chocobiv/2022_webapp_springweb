import React, {useState,useEffect} from 'react'
import axios from 'axios'

//[전역변수]
let bcno = 0    //선택한 카테고리 번호

export default function BoardWrite(props) {
    const [category, setCategory] = useState('')    //입력받은 카테고리명
    const [categoryList, setCategoryList] = useState([])//서버로부터 가져온 카테고리 리스트

    //1. 모든 카테고리 가져오기 함수 [실행조건 : 페이지가 렌더링(열렸을 때) 되었을 때]
    const getbcategory = () => {    //1. 카테고리 목록 가져오기
        axios
            .get("/board/bcategorylist")
            .then(res => {setCategoryList(res.data); console.log(res)})
            .catch(err => console.log(err))
    }
    useEffect(getbcategory, []) //페이지가 mount, unmount되었을 때 실행

    //2. 입력된 카테고리 등록 함수 [실행조건 : 카테고리 등록 버튼 눌렀을 때]
    const setbcategory = () =>{
        if(category == '') { alert("카테고리명을 입력 후 등록해주세요"); return; }

        axios
            .post("/board/setbcategory", {bcname:category})
            .then(res=> {
                if(res.data == true){ alert("카테고리 등록 성공"); console.log(res)}
                else{ alert("카테고리 등록 실패")}
            })
            .catch(err=>console.log(err))
    }

    //3. 입력받은 게시물 등록 함수 [실행조건 : 글쓰기 등록 버튼 눌렀을 때]
    const setboard = () => {
        if(bcno == 0){alert("카테고리를 선택해주세요."); return;}
        let boardform = document.querySelector('.boardform')  //폼 가져오기
        let formdata = new FormData(boardform)
        formdata.set("bcno",bcno)                               //폼 데이터에 카테고리 번호 추가

        axios
            .post("/board/setboard",formdata, {headers: {'Content-Type': 'multipart/form-data'}})
            .then( res => {
                console.log(res.data)
                if(res.data == true){ alert("게시물 작성 성공")}
                else{ alert("게시물 작성 실패")}
            })
            .catch(err => console.log(err))
    }

    return (
        <div>
            <h1>글 쓰기 페이지</h1>
            <input type="text" value={category} onChange={ (e)=> {setCategory(e.target.value)}}/>
            <button type="button" onClick={setbcategory}>카테고리 추가</button>
            <span className="bcategorybox">
                {
                    categoryList.map( (c) => {
                        return (
                            <button type={"button"} onClick={ ()=>{bcno=c.bcno; alert(bcno)} }> {c.bcname} </button>
                        )
                    })
                }
            </span> <br/>
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


