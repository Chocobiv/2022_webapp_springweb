/*
    리액트 : SPA, 컴포넌트 단위 개발


 */


// * 오류 : return 한번이기때문에 재렌더링 불가능
// -------- 1. 현재 페이지에서 사용될 라이브버리 import
/*import React from 'react';
export default function Counter(props){
    // -------- 2. JS 혹은 라이브러리 --------
    var count = 0

    // -------- 3. 렌더링 되는 HTML + JSX 표현식 { } + 컴포넌트 --------
    return (
        <div>
            <p> 총 {count}번 클릭했습니다. </p>
            <button onClick={() => count++}>{/!*새로운 count는 보여지지 않는다. 왜? 재렌더링하지 않음*!/}
                클릭
            </button>
        </div>
    )
}*/


// * 해결책 : 리액트 훅 이라는 곳에서 useState 라이브러리 사용하자
// -------- 1. 현재 페이지에서 사용될 라이브버리 import
import React,{useState} from 'react';
export default function Counter(props){
    // -------- 2. JS 혹은 라이브러리 --------
    const [count, setCount] = useState(0)

    // -------- 3. 렌더링 되는 HTML + JSX 표현식 { } + 컴포넌트 --------
    return (
        <div>
            <p> 총 {count}번 클릭했습니다. </p>
            <button onClick={() => setCount(count+1)}>{/*새로운 count는 보여지지 않는다. 왜? 재렌더링하지 않음*/}
                클릭
            </button>
        </div>
    )
}