
import React, {useState} from 'react';

export default function Signup(props) {
    //1. 컴포넌트 메모리
    const [name, setName] = useState('')
    const [gender, setGender] = useState('male')
    //2. 이벤트 함수
    const handleChangeName = (e) => {setName(e.target.value);}
    const handleChangeGender = (e) => {setGender(e.target.value);}
    //2. 이벤트 함수
    const handleSubmit = (e) => {
        alert(`이름 : ${name}`)
        e.preventDefault()
    } //state값 호출
    //3. 렌더링
    return (
        <form onSubmit={handleSubmit}>
            <label>
                이름 : <input type={"text"} value={name} onChange={handleChangeName}/>
            </label><br/>
            <label>
                성별 :
                <select value={gender} onChange={handleChangeGender}>
                    <option value="male">남자</option>
                    <option value="female">여자</option>
                </select>
            </label>
            <button type={"submit"}>제출</button>
        </form>
    )
}