//1. 세션스토리지 호출
let bno = sessionStorage.getItem("bno")

//2. 수정 전의 게시물 정보 호출
getboard()
function getboard(){
    $.ajax({
        url: '/board/getboard',
        data: {"bno": bno},
        type: 'get',
        success: function(re) {
            document.querySelector('.btitle').value = re.btitle
            document.querySelector('.bcontent').value = re.bcontent
        }
    })
}

//3. 수정버튼 클릭시 호출되는 메소드
function upboard(){
    let data = {
        btitle : document.querySelector('.btitle').value,
        bcontent : document.querySelector('.bcontent').value,
        bfile : document.querySelector('.bfile').value,
        bno : bno
    }
    $.ajax({
        url:'/board/upboard',
        type:'put',
        data:JSON.stringify(data),  //json을 json형태의 문자열로 변환
        contentType: 'application/json',    //@RequestBody
        success: function(re) {
            alert(re)
            if(re == true){ location.href = '/board/view' }
            else{ alert('글수정 실패') }
        }
    })
}
