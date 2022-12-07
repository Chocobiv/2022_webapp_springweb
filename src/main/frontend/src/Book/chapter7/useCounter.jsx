//p.234
//1. 리액트 커스텀 훅 만들기
import React,{useState} from 'react';

//2. 커스텀 훅 [use훅이름 정의]
export default function useCounter(value){
    //3. useState 훅
        //count 라는 이름으로 변수 선언, setCount는 해당 변수 변경 함수
    const [count, setCount] = useState(value)

    // 이벤트 함수
    const increaseCount = ()=> setCount( (count) => count+1) //count 변수 증가 이벤트      //기존값 + 1
    const decreaseCount = ()=> setCount( (count) => Math.max(count-1,0))//count 변수 감소 이벤트   //기본값-1, 혹은 0 중 가장 큰 값

    return [count, increaseCount, decreaseCount]  //배열 반환
}
