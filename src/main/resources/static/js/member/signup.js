//1. 회원가입 버튼 눌렀을 때 실행되는 메소드
function setmember(){

    let info = {
        memail: document.querySelector('.memail').value,
        mpassword: document.querySelector('.mpassword').value,
        mphone: document.querySelector('.mphone').value
    }

    let timerbox = document.querySelector('.timerbox').innerHTML
    if(timerbox != "인증성공"){
         alert("이메일 인증부터 해주세요~")
         return
    }

    $.ajax({
        url: '/member/setmember',
        type: 'POST',
        data: JSON.stringify(info),
        contentType: 'application/json',
        success: function(re){
            alert(re)
            location.href = '/'
        }
    })
}

//2. 인증코드 받기
//let auth = null         //발급된 인증코드
let auth = 1234         // [ 임시 ]
let timer = 0           //인증 시간
let timerinter = null   //setInterval
function getauth(){
    //1. 입력받은 이메일
    let toemail = document.querySelector('.memail').value

    //2. 입력받은 이메일에게 인증코드 전송하고 전송된 인증코드를
    $.ajax({
        url: '/member/getauth',
        data: {"toemail":toemail},
        type: 'get',
        success: function(re){
            auth = re;  //응답받은 인증코드를 변수에 대입
            console.log("인증코드:"+auth)               //콘솔 창을 못 열도록 막아야 함
            alert('해당 이메일로 인증코드 발송')
            document.querySelector('.getauthbtn').innerHTML = "재인증코드받기" //버튼의 입력된 문자 변경
            timer = 120     //초 단위
            settimer()      //타이머 함수 실행
        }
    })
}

//3. 인증코드 확인
function authcode(){
    let authinput = document.querySelector('.authinput').value  //입력받은 인증코드
    if(authinput == auth){  //입력받은 인증코드와 발급된 인증코드가 동일하면
        alert('인증코드 일치')
        clearInterval(timerinter)   //Interval 클리어
        auth = null
        timer = 0
        document.querySelector('.timerbox').innerHTML = "인증성공"
    }else{
        alert('인증코드 불일치')
    }
}

//4. 타이머 함수

function settimer(){
    // setInterval(function(){ 실행문 }, 밀리초) : 일정간격으로 함수 실행
    // clearInterval(객체명) : Interval 종료
    timerinter = setInterval(function(){
        let minutes
        let seconds
        minutes = parseInt(timer / 60)  //분
        seconds = parseInt(timer % 60)  //초
        minutes = minutes < 10 ? "0"+minutes : minutes      //두자리 수 맞추기
        seconds = seconds < 10 ? "0"+seconds : seconds     //두자리 수 맞추기
        document.querySelector('.timerbox').innerHTML = minutes+" : "+seconds //분 : 초
        document.querySelector('.timerbox').style.color = 'red'
        //종료 조건
        if(--timer < 0){ //시간이 0초가 되면
            clearInterval(timerinter)   //setInterval 사용중인 객체 clear
            timerinter = null
            alert('인증실패')
            auth = null //발급인증코드 초기화
            document.querySelector('.getauthbtn').innerHTML = '인증코드받기'
        }
    }, 1000)    //1초마다 함수 돈다
}