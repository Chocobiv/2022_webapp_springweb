//p.347
import React from 'react';

/* 전역변수 */
const scaleNames = { c:"섭씨", f:"화씨" }

//컴포넌트[함수] 만들기
export default function TemperatureInput(props) {   /* props : 부모로부터 전달받은 데이터 */
    /* 해당 이벤트는 부모로부터 전달 받은 함수를 이용한 입력 받은 값 전달 */
    const handleChange = (e) => { props.onTemperatureChange(e.target.value) }
    return(
        <fieldset>
            <legend>
                온도를 입력해주세요 ( 단위 : {scaleNames[props.scale]} ){/* 값 호출 : jsx 표현식 */}
            </legend>
            <input type={"text"} value={props.temperature} onChange={handleChange}/>
        </fieldset>
    )
}


