import React, {useEffect, useState} from 'react'
import axios from 'axios'

export default function BoardList(props){
    //1. 메모리
    const [pageInfo, setPageInfo] = useState({bcno:0,page:1,key:"",keyword:""})     //1. 요청 정보 객체 state
    const [boardlist, setBoardlist] = useState({list:[]})   //1. 게시물 리스트 state
    const [categorylist, setCategoryList] = useState([])   //1. 게시물 리스트 state

    //2. 서버로부터 게시물 리스트를 가져오는 함수 [실행조건 :: 1.렌더링될때 2.검색할때 3.카테고리선택 4.페이징선택 -> 일반 함수화]
    function getboardlist() {
        axios.post("/board/boardlist", pageInfo)
            .then(res => { console.log(res.data); setBoardlist(res.data); })
            .catch(err => console.error(err))
    }
    //3. 렌더링 될때 그리고 pageInfo 변경될 때마다 작동
    useEffect(getboardlist, [pageInfo])

    function getBcategorylist() {    //4. 모든 카테고리 가져오기
        axios.post("/board/bcategorylist", pageInfo)
            .then(res => { setCategoryList(res.data); })
            .catch(err => console.error(err))
    }
    useEffect(getBcategorylist, [])//실행조건 :: mount

    //카테고리 버튼을 선택했을 때
    const onCategory = (bcno) => { setPageInfo({bcno:bcno,page:1,key:"",keyword:""}) }

    return (
        <div>
            <a href="/board/write">글쓰기[로그인했을때만표시]</a>
            <h1>글 목록 페이지</h1>
            <div className="bcategorybox">
                {
                    categorylist.map((c)=>{
                        return (
                            <button type={"button"} onClick={ ()=> onCategory(c.bcno)}> {c.bcname} </button>
                        )
                    })
                }
            </div>
            <table className="btable">
                {
                    boardlist.list.map((b)=>{
                        return(
                            <tr>
                                <td>{b.bno}</td>
                                <td>{b.btitle}</td>
                                <td>{b.memail}</td>
                                <td>{b.bview}</td>
                            </tr>
                        )
                    })
                }
            </table>
        </div>
    )
}