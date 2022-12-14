import React, {useEffect, useState} from 'react'
import {useParams} from "react-router-dom";
import axios from "axios";

export default function BoardView(props) {
    const params = useParams()      // useParams() 훅 : 경로[URL]상의 매개변수 가져올 때
    const [board, setBoard] = useState({})      //게시물 메모리
    //1. 서버로부터 해당 게시물번호의 게시물정보 -> useState[board] 요청
    useEffect( //무한루프 방지를 위해서 useEffect사용! 이때 [] 잊으면 안됨!
        () => axios.get('/board/getboard',{ params:{bno:params.bno} }).then( res => {setBoard(res.data)}).catch(err => { console.log(err) } )
        ,[] )

    const [login, setLogin] = useState({})      //로그인정보 메모리
    //2. 서버로부터 해당 로그인된 회원의 아이디 [MemberService : getloginMno() return 반환]
    useEffect(
        () => axios.get('/member/getloginMno').then( res => {setLogin(res.data)} )
        ,[])

    //3. 서버로부터 해당 게시물번호를 이용한 삭제 요청
    const onDelete = () => {
        axios.delete('/board/delboard',{ params:{bno:params.bno} }).then(res=>{alert('게시물 삭제 성공'); window.location.href='/board/list'})
    }

    //4. 해당 게시물번호에 해당하는 업데이트 페이지로 이동
    const getUpdate = () => { window.location.href = '/board/update/'+params.bno }

    return(
        <div>
            <div>{board.btitle}</div>
            <div dangerouslySetInnerHTML={{ __html : board.bcontent }}></div>
            {board.bfilename != '' && <a href={'/board/filedownload?filename='+board.bfilename}>{board.bfilename}</a> }
            <div>
                {login == board.memail && <button type={"button"} onClick={getUpdate}> 수정 </button> }
                {login == board.memail && <button type={"button"} onClick={onDelete}> 삭제 </button> }{/* 로그인된 정보와 작성자가 같으면 버튼 보이게 삼항연산자 */}
            </div>
        </div>
    )
}

