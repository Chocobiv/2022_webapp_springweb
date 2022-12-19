//p.347
import React,{useState} from 'react';
import TemperatureInput from './TemperatureInput'

/* 전역변수 */

//1. [p.338] 물의 끓음 여부를 알려주는 컴포넌트
function BoilingVerdict(props) {
    if(props.celsius >= 100){ return <p>물이 끓습니다.</p> }  //매개변수(props)의 온도가 100 이상이면 렌더링
    return <p>물이 끓지 않습니다.</p>                    //매개변수(props)의 온도가 100 이상이 아니면 렌더링
}

//2. 매개변수 화씨->섭씨 변환하는 함수
function toCelsius(fahrenheit) {
    return ((fahrenheit-32)*5)/9
}

//3. [p.339] 매개변수 섭씨->화씨 변환하는 함수
function toFahrenheit(celsius) {
    return ((celsius*9)/5+32)
}

//4. 형변환
function tryConvert(temperature, convert){
    const input = parseFloat(temperature)   //매개변수 temperature 실수형 형변환
    if(Number.isNaN(input)){return ""}      //만약에 매개변수가 없으면 공백 리턴

    const output = convert(input)           //매개변수를 convert 함수에 대입
    const rounded = Math.round(output*1000)/1000  //반올림
    return rounded.toString()
}

//컴포넌트[함수] 만들기
export default function Calculator(props) {
    /*
    공유 state 사용을 위해서 부모 컴포넌트에 state 사용하고,
    대신 자식 컴포넌트에는 state를 변경하는 함수가 담긴 메소드를 보냄
    */
    const [temperature,setTemperature] = useState("")   //온도 메모리
    const [scale, setScale] = useState("c")

    //1. 온도 업데이트 함수
    //자식 컴포넌트로 들어온 이벤트가 발생
    //자식 검포넌트에서 입력받아서 state에 업데이트
    const handleCelsiusChange = (t) =>{ setTemperature(t); setScale("c") }
    const handleFahrenheitChange = (t) => { setTemperature(t); setScale("f") }

    //2. 온도 표시하는 함수
    const fahrenheit = scale == "c" ? tryConvert(temperature,toFahrenheit) : temperature
    const celsius = scale == "f" ? tryConvert(temperature,toCelsius) : temperature

    return(
        <div>
            {/* 섭씨  입력 */}{/* 자식 컴포넌트에게 문자열과 함수 2개 전달 */}
            <TemperatureInput
                scale={"c"}
                temperature = {celsius}
                onTemperatureChange = {handleCelsiusChange}
            />
            {/* 화씨 입력 */}
            <TemperatureInput
                scale={"f"}
                temperature = {fahrenheit}
                onTemperatureChange = {handleFahrenheitChange}
            />
            <BoilingVerdict celsius={parseFloat(celsius)}/>
        </div>
    )
}


