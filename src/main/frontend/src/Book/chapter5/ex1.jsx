//1. props 사용법[p.148]
function App(props){
    //1. <컴포넌트명 />
    //2. <컴포넌트명 속성명="문자열", 속성명={숫자}>
    // props.속성명
    return (
        <Profile name="소플" introduction="안녕하세요, 소플입니다" viewCount={1500} />

    )
}

//2.
function App(props){
    return (
        <Layout                 //Layout : 컴포넌트
            width={2560}        //Layout 컴포넌트에 width속성의 값 전달
            height={1440}       //Layout 컴포넌트에 height속성의 값 전달
            header={ <Header title="소플의 블로그입니다."/> }    //Layout 컴포넌트에 Header 컴포넌트 전달
            footer={ <Footer /> }   //Layout 컴포넌트에 Footer 컴포넌트 전달
        />
    )
}