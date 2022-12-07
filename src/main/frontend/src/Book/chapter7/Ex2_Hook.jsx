/*import React, {useState,useEffect} from 'react';

// state 상태에 따른 온라인인지 오프라인인지 텍스트로 보여주는 컴포넌트
export default function UserStatus(props) {
            //변수명, 변경함수명                초기값
    const [isOnline, setIsOnline] = useState(null)  //메모리 관리

    //const isOnline = true  => 안됨. 업데이트가 자동으로 안됨! 변수는 변하지만 변환 변수의 값은 재렌더링이 안됨.

    //생명주기 : mount:렌더링 후 1번, update:재렌더링마다, unmount:렌더링 삭제전 1번
    useEffect(() => {   //status, props 사용 가능
        function handleStateChange(status) {
            setIsOnline(status.isOnline)
        }

        ServerAPI.subscribeUserStatus(props.user.id, handleStateChange)
        return () => {
            ServerAPI.unsubscribeUserStatus(props.user.id, handleStateChange)
        }
    })

    //1.
    if(isOnline==null) {return '대기중...'}
    return isOnline ? '온라인' : '오프라인'
    //2.
    return (
        <li style={{color : isOnline ? 'green' : 'black'}}>
            {props.user.name}
        </li>
    )
}*/

import React, {useState,useEffect} from 'react';

const userList = [
    {id : 1, name : 'Inje'},
    {id : 2, name : 'Mike'},
    {id : 3, name : 'Steve'}
]

function chatUserStatus(props) {
    const [userId, setUserId] = useState(1)     //select가 체인지 될 때마다 회원아이디가 변경됨
    const isOnline = useUserStatus(userId)              //해당 유저의 온라인 상태 여부 가져오기
}

// 1. 커스텀 훅 [여러 컴포넌트에서 동일한 훅을 사용시 중복 훅 제거, 즉 재사용성을 위해 사용]
export default function useUserStatus(props) {
    const [isOnline, setIsOnline] = useState(null)  //메모리 관리

    useEffect(() => {   //status, props 사용 가능
        function handleStateChange(status) { setIsOnline(status.isOnline) }
        ServerAPI.subscribeUserStatus(props.user.id, handleStateChange)
        return () => { ServerAPI.unsubscribeUserStatus(props.user.id, handleStateChange) }
    })
    return isOnline
}

//2. 온라인 상태를 문자로 표시하는 컴포넌트
function UserStatus(props){
    const isOnline = useUserStatus(props.user.id)
    if(isOnline==null){return '대기중...'}
    return isOnline ? '온라인' : '오프라인'
}

//3. 온라인 상태를 색상으로 표시하는 컴포넌트
function UserListItem(props){
    const isOnline = useUserStatus(props.user.id)
    return (
        <li style={{color : isOnline ? 'green' : 'black'}}>
            {props.user.name}
        </li>
    )
}