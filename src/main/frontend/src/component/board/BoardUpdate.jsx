import React, {useState,useEffect} from 'react'
import {useParams} from "react-router-dom";
import axios from 'axios'
// ckeditor5
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

let bcontent = ''
export default function BoardUpdate(props) {
    const params = useParams()

    const [board, setBoard] = useState({})      //게시물 메모리
    //1. 서버로부터 해당 게시물번호의 게시물정보 -> useState[board] 요청
    useEffect( //무한루프 방지를 위해서 useEffect사용! 이때 [] 잊으면 안됨!
        () => axios.get('/board/getboard',{ params:{bno:params.bno} }).then( res => {setBoard(res.data)}).catch(err => { console.log(err) } )
        ,[] )

    //2. 서버로부터 수정된 정보를 이용한 게시물 수정 요청
    const upboard = () => {
        let boardform = document.querySelector('.boardform')    //폼 가져오기
        let formdata = new FormData(boardform)
        formdata.set("bno",board.bno)       //수정할 게시물 번호
        formdata.set("bcontent",bcontent)   //수정할 게시물 내용

        axios.put('/board/upboard', formdata, {headers: {'Content-Type': 'multipart/form-data'}})
            .then(res => {
                if(res.data == true){alert('게시물 수정 성공'); window.location.href='/board/view/'+board.bno;}
                else{ alert("게시물 수정 실패") }
            })
            .catch(err => console.log(err))
    }

    return(
        <div>
            <h1>수정 페이지</h1>
            <form className="boardform">
                제목 : <input type="text" name="btitle" defaultValue={board.btitle} style={{width: 220}}/><br/>
                <CKEditor
                    editor={ ClassicEditor }
                    data={board.bcontent}
                    onChange={ ( event, editor ) => { const data = editor.getData(); bcontent = data; } }
                />
                첨부파일 : <input type="file" name="bfile"/><br/>
                <button type="button" onClick={upboard}>수정</button>
            </form>
            <a href="/board/list">목록보기</a>
        </div>
    )
}