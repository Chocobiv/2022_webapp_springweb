
//p.104
// ------------------------ 변수 표현식 ------------------------
const name = '소플'       //js 문자 변수
const element = <h1>안녕, {name}</h1> //js+html => jsx
    //변수 호출 표현식 : {변수명}

ReactDOM.render(element, document.querySelector('#root'))

// ------------------------ 함수 표현식 ------------------------
//함수
function formatName(user){
    return user.firstName + ' ' + user.lastName
}

//유저 객체
const user = {firstName: 'Inje', lastName: 'Lee'}
//html 구성
const element2 = (<h1>Hello, {formatName(user)}</h1>)
//해당 id에 html 렌더링(뿌려주기/넣어주기/표시해주기)
ReactDOM.render(element2, document.querySelector('#root'))

//------------------------ 활용 --------------------
function getGreeting(user) {
    if(user){
        return <h1 className="h1box">Hello, {formatName(user)} ! </h1>    //유저가 있을 경우
    }
    return <h1>Hello, Stranger.</h1>    //유저가 없을 경우
}

// ----------------------- JSX 식 HTML ------------------------
const element = (<div> <h1>안녕하세요</h1> <h2>열심히 리액트를 공부해 봅시다!</h2> </div>)
//원래 방식
//const element = '<div> <h1>안녕하세요</h1> <h2>열심히 리액트를 공부해 봅시다!</h2> </div>'



