boardlist()
//1. 게시물 출력
function boardlist(){
    $.ajax({
        url: '/board/boardlist',
        type: 'get',
        success: function(re) {
            console.log(re)

            let html = `<tr> <th>번호</th><th>제목</th><th>작성자</th> </tr>`
            re.forEach((b)=>{
                html += '<tr onclick="getview('+b.bno+')"> <td>'+b.bno+'</td><td>'+b.btitle+'</td><td>'+b.memail+'</td> </tr>'
            })
            document.querySelector('.btable').innerHTML = html
        }
    })
}

//2. 게시물 조회 페이지 [페이지 전환 3가지 방법 -> 자바:세션-서버가 종료될 때 초기화 / 템플릿 이용(JSP,React) / js:페이지 전환 시 초기화, 세션, 쿠키]
function getview(bno){
    //1. 클릭한 게시물번호 저장
    sessionStorage.setItem("bno",bno)
    //2. 페이지 전환
    location.href = '/board/view'
}

getloginMno()
function getloginMno(){
    $.ajax({
        url: '/member/getloginMno',
        type: 'get',
        success: function(re){
            let html = '';
            if(re != '0'){//로그인 했다
                html += '<a href="/board/write"> 글쓰기 </a>'
            }
            document.querySelector('.writebox').innerHTML = html
        }
    })
}