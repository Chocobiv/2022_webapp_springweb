//setState  vs  prevState

import React, {useState} from 'react'

export default function TestState(props) {
    const [state, setState] = useState(0)

    const stateadd = () => {
        setState(state+1)//실행안됨
        setState(state+2)//실행안됨
        setState(state+3)//실행안됨
        setState(state+4)//실행안됨
        setState(state+5)//실행됨. 즉, 5씩 증가
    }//동시다발적으로 실행하면 비동기라 마지막 것만 실행됨
    const stateadd2 = () => {
        setState((prevState)=>prevState+1)//실행됨
        setState((prevState)=>prevState+2)//실행됨
        setState((prevState)=>prevState+3)//실행됨
        setState((prevState)=>prevState+4)//실행됨
        setState((prevState)=>prevState+5)//실행됨. 즉, 15씩 증가
    }

    return(
        <div>
            <div>state에 저장된 값 : {state}</div>
            <button onClick={stateadd}>클릭이벤트 1</button>
            <button onClick={stateadd2}>클릭이벤트 2</button>
        </div>
    )
}