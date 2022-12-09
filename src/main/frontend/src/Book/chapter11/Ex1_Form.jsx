//p. 313

import React,{useState} from 'react'

export default function NameForm(props) {
    const [value, setValue] = useState('')  //input value
    const [value2, setValue2] = useState('요청사항을 입력하세요.')  //textarea value
    const [value3, setValue3] = useState('strawberry')  //select value

    //e: 이벤트 객체
    const handleChange = (e) => { setValue(e.target.value) }    //input에 입력된 값(발생한 이벤트의 대상의 값)을 value에 저장
    const handleChange2 = (e) => { setValue2(e.target.value) }
    const handleChange3 = (e) => { setValue3(e.target.value) }

    return (
        <form>
            <label>
                이름 : <input type={"text"} value={value} onChange={handleChange}/>
            </label><br/><br/>
            <label>
                요청사항 : <textarea value={value2} onChange={handleChange2}></textarea>
            </label><br/><br/>
            <label>
                과일을 선택하세요 :
                <select value={value3} onChange={handleChange3}>
                    <option value={"blueberry"}>블루베리</option>
                    <option value={"strawberry"}>딸기</option>
                    <option value={"banana"}>바나나</option>
                    <option value={"kiwi"}>키위</option>
                </select>
            </label><br/><br/>
            <button type={"submit"}>제출</button>
        </form>
    )
}
