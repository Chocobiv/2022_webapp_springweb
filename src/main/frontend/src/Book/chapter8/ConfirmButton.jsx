//p. 255

import React from 'react'

class ConfirmButton extends React.Component {
    //1. 생성자
    constructor(props) {
        super(props)
        this.state = {isConfirmed: false}     //리액트 컴포넌트의 데이터 변수 [ 재렌더링때문에 사용 ]
        this.handleConfirm = this.handleConfirm.bind(this)
    }

    //3. 이벤트 함수 정의
    handleConfirm(){
        this.setState( (prevState) => ({ isConfirmed : !prevState.isConfirmed}))
    }

    //2. 렌더링 함수
    render() {
        return (
            <button onClick={this.handleConfirm} disabled={this.state.isConfirmed}>
                {this.state.isConfirmed ? '확인됨' : '확인하기'}
            </button>
        )
    }
}
export default ConfirmButton

