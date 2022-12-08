
//1. 컴포넌트 첫글자 대문자
//2. 클래스컴포넌트 / 함수컴포넌트

import React from 'react';

class Ex1_Event extends React.Component {
    // ------------------------ [방법1] p.249 ------------------------ //
    //1. 생성자
    constructor(props) {
        super(props);
        this.state = {isToggleOn: true};//메모리 관리
        this.handleClick = this.handleClick.bind(this);
    }

    //2. 이벤트 함수
    handleClick(){          //prevState : state 값
        this.setState(prevState=>({
            isToggleOn : !prevState.isToggleOn
        }))
    }

    // ------------------------ [방법2] p.250 ------------------------ //
    //2. 이벤트 함수
    handleClick = () => {          //prevState : state 값
        this.setState(prevState=>({
            isToggleOn : !prevState.isToggleOn
        }))
    }

    render() {
        return (
            <button onClick={this.handleClick}>
                {this.state.isToggleOn ? '켜짐' : '꺼짐'}
            </button>
        )
    }
    // ------------------------ [방법3] p.251 ------------------------ //
    //2. 이벤트 함수
    handleClick(){          //prevState : state 값
        this.setState(prevState=>({
            isToggleOn : !prevState.isToggleOn
        }))
    }

    //3. 렌더링 함수
    render() {
        return (
            <button onClick={()=>this.state.handleClick()}>
                {this.state.isToggleOn ? '켜짐' : '꺼짐'}
            </button>
        )
    }
}
export default Ex1_Event