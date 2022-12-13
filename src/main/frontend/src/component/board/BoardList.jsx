import React, {useEffect, useState} from 'react'
import axios from 'axios'
import Pagenation from 'react-js-pagination'

export default function BoardList(props){
    //1. 메모리
    const [pageInfo, setPageInfo] = useState({bcno:0,page:1,key:"",keyword:""})     //1. 요청 정보 객체 state
    const [boardlist, setBoardlist] = useState({list:[]})   //1. 게시물 리스트 state
    const [categorylist, setCategoryList] = useState([])   //1. 카테고리 리스트 state

    // ---------------------------- 1. 게시물 ---------------------------- //
    //2. 서버로부터 게시물 리스트를 가져오는 함수 [실행조건 :: 1.렌더링될때 2.검색할때 3.카테고리선택 4.페이징선택 -> 일반 함수화]
    function getboardlist() {
        axios.post("/board/boardlist", pageInfo)
            .then(res => { console.log(res.data); setBoardlist(res.data); })
            .catch(err => console.error(err))
    }
    //3. 렌더링 될때 그리고 pageInfo 변경될 때마다 작동
    useEffect(getboardlist, [pageInfo])

    // ---------------------------- 2. 카테고리 ---------------------------- //
    function getBcategorylist() {    //4. 모든 카테고리 가져오기
        axios.get("/board/bcategorylist")
            .then(res => { setCategoryList(res.data); })
            .catch(err => console.error(err))
    }
    useEffect(getBcategorylist, [])//실행조건 :: mount

    //카테고리 버튼을 선택했을 때
    const onCategory = (bcno) => { setPageInfo({bcno:bcno,page:1,key:"",keyword:""}) }

    // ---------------------------- 3. 페이징 ---------------------------- //
    const onPage = (page) => { setPageInfo({bcno:pageInfo.bcno, page:page, key:pageInfo.key, keyword:pageInfo.keyword})}//pageInfo.bcno: 기존 카테고리  pageInfo.key: 기존 검색 필드명  pageInfo.keyword: 기존 검색할 단어

    // ---------------------------- 4. 검색 ---------------------------- //
    const onSearch = () => {
        setPageInfo({bcno:pageInfo.bcno, page:1,key:document.querySelector('.key').value,keyword:document.querySelector('.keyword').value})//검색 시 첫페이지부터 보여주기
    }

    const loadView = (bno)=>{
        window.location.href = '/board/view/'+bno
    }

    return (
        <div>
            <a href="/board/write">글쓰기[로그인했을때만표시]</a>
            <h1>글 목록 페이지</h1>
            <div className="bcategorybox">
                <button type="button" onClick={ ()=>onCategory(0)}>전체보기</button>
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
                                <td onClick={ () => loadView(b.bno) }>{b.btitle}</td>
                                <td>{b.memail}</td>
                                <td>{b.bdate}</td>
                                <td>{b.bview}</td>
                            </tr>
                        )
                    })
                }
            </table>
            <Pagenation
                activePage={pageInfo.page}
                itemsCountPerPage={3}
                totalItemsCount={boardlist.totalBoards}
                pageRangeDisplayed={5}
                onChange={onPage}
            />
            <div className={"searchBox"}>
                <select className={"key"}>
                    <option value={"btitle"}>제목</option>
                    <option value={"bcontent"}>내용</option>
                </select>
                <input type={"text"} className={"keyword"}/>
                <button type={"button"} onClick={ onSearch }>검색</button>
            </div>
        </div>
    )
}