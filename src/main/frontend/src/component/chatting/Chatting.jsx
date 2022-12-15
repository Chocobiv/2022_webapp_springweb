import React, {useState, useEffect, useRef} from 'react';

export default function Chatting(props) {

    const [socketConn, setSocketConn] = useState(false) // * 서버소켓과 연결 여부 저장하는 메모리
    const [msgList, setMsgList] = useState([])          // * 서버소켓으로부터 들어온 여러 메시지들을 저장하는 메모리

    // * 클라이언트 소켓 [ 재렌더링될 때마다 상태유지하기 위해서 useRef 사용 ]
    let ws = useRef(null) //useState : 수정될 때마다 해당 컴포넌트 재렌더링됨

    useEffect( ()=>{    // * 컴포넌트가 mount될 때 서버소켓과 연결, unmount 될 때 서버소켓 닫기
        if(!ws.current){    //클라이언트 소켓이 없으면
            ws.current = new WebSocket('ws://localhost:8081/chat')  //클라이언트 소켓 생성 [ 서버소켓 주소 ]

            //1. 클라이언트 소켓이 open 될 때
            ws.current.onopen = () => { setSocketConn(true) }
            //2. 클라이언트 소켓이 close 될 때
            ws.current.onclose = (e) => { console.log("닫기) "+e) }
            //3. 클라이언트 소켓이 error 날 때
            ws.current.onerror = (e) => { console.log("에러) "+e) }
            //4. 서버 소켓으로부터 message 받았을 때
            ws.current.onmessage = (e) => {
                let data = JSON.parse(e.data)       //통신이라 문자로 들어와서 JSON 파싱
                setMsgList((prevMsgList)=>[...prevMsgList,data])//기존값+새로운값 -> 재렌더링       기존거랑 새로운 데이터를 배열에 담아서 새롭게 다시 set해서 재렌더링
            }
        }
    }, [])

    // * 전송버튼 클릭했을 때 send 이벤트 [서버로 message 보냄]
    const onMsg = () => {
        let msg = document.querySelector('.msgInput').value         //1. 입력받은 데이터 가져오기
        ws.current.send(JSON.stringify({message : msg}))      //2. 웹소켓 이용한 send 메소드(원래 지원되는 메소드) 사용 [JSON 형식으로 보내기] [즉, JSON 형식의 문자열 타입]
        document.querySelector('.msgInput').value = ''              //3. 입력 상자 초기화

    }

    return(
        <div>
            접속상태 : <span></span>
            <div>
                내용입력 : <input type={"text"} className={"msgInput"}/>
                <button type={"button"} onClick={onMsg}>전송</button>
            </div>

            <div>
                <h6>채팅창</h6>
                {
                    msgList.map((msg)=>{
                        return <div>{JSON.stringify(msg)}</div>
                    })
                }
            </div>
        </div>
    )
}

