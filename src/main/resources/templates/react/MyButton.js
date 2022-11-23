
//1. root라는 id를 갖는 html 태그 호출
const domContainer = document.querySelector('#root')
console.log(domContainer)

//2. 리액트 렌더링 [ render() ]
ReactDOM.render(React.createElement(MyButton), domContainer)

//3. MyButton 생성 함수
function MyButton(props) {  //props : properties약자
    const[isClicked,setIsClicked] = React.useState(false)
    return React.createElement(
        'button',
        {onClick: () => setIsClicked(true)},
        isClicked ? 'Clicked!' : 'Click here!'
    )
}

