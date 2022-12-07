// p.235
import React , { useState , useEffect} from 'react'
import useCounter from './useCounter' // 커스텀 훅 가져오기

const MAX_CAPACITY = 10; // 전역변수 // 최대 수용인원

// 1. 컴포넌트 선언
export default function Accommodate( props ) {

    // 1. 커스텀 훅 호출해서 [ 인원수 , 증가함수 , 감소함수 ] 배열 반환
    const [ count , increaseCount , decreaseCount] = useCounter(0);
    // 2. 최대수용인원 여부 상태 메모리
    const [ isFull , setIsFull  ] = useState( false );

    // 3.
    // 의존성배열 없음[생략]
    // 실행조건 : 1. mount[컴포넌트 렌더링된 직후] , 2.update 컴포넌트가 업데이트 될때
    useEffect( () => {
        console.log("-----------------------")
        console.log("이펙트1 훅 실행 ")
        console.log("isFull : " + isFull )
    } )
    // 3. 만약에 현재인원이 최대수용 인원보다 크거나같으면 true 가 isFull변수에 true저장되고 아니면 false 가 저장
    // 의존성배열 존재   [count]
    // 실행조건 : 1. mount[컴포넌트 렌더링된 직후]  2.update 의존성배열이 업데이트 될때
    useEffect( () => { console.log("이펙트2 훅 실행 "); setIsFull( count >= MAX_CAPACITY ) } , [count] )

    // 실행조건 : 1. 1. mount[컴포넌트 렌더링된 직후] 2. x    3. unmount : 렌더링 종료된 전
    useEffect( () => { console.log("이펙트3 훅 실행 "); setIsFull( count >= MAX_CAPACITY ) } , [] )

    // 4. 렌더링 되는 구역
    return (
        <div style={{ padding:16 }}>
            <p> 총 { count } 명 수용했습니다. </p>
            <button onClick={ increaseCount } disabled = {isFull} >입장</button>
            <button onClick={ decreaseCount } >퇴장</button>
            { isFull && <p style={{ color:"red" }}>정원이 가득 찼습니다.</p>  }
        </div>
    );
}