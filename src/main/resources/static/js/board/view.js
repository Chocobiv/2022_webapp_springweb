//1. list.js에서 클릭된 게시물 번호 호출
let bno = sessionStorage.getItem("bno")
if(bno == null){ alert("비정상적인 접근입니다.") }
else{ getboard() }

//2. 클릭된 게시물번호의 게시물정보를 호출하는 메소드
function getboard(){
    $.ajax({
        url: '/board/getboard',
        data: {"bno": bno},
        type: 'get',
        success: function(re) {
            console.log(re);
            let html = '제목 : '+re.btitle+'<Br>작성자 : '+re.memail+'<br>내용 : '+re.bcontent+'<br>첨부파일 : '+re.bfilename+'<br>조회수 : '+re.bview
            document.querySelector('.viewbox').innerHTML = html
        }
    })
}

//3. 삭제버튼 클릭시 호출되는 메소드
function delboard(){
    $.ajax({
        url: '/board/delboard',
        data: {"bno": bno},
        type: 'delete',
        success: function(re) {
            location.href = '/board/list'
        }
    })
}