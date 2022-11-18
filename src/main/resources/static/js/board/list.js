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
                html += `<tr> <td>${b.bno}</td><td>${b.btitle}</td><td>${b.mno}</td> </tr>`
            })
            document.querySelector('.btable').innerHTML = html
        }
    })
}

function viewboard(bno){
    alert(bno)
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