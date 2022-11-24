getbcategory()
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
            document.getElementById("bcategorySelect").options.selectedIndex = re.bcno
        }
    })
}

//3. 수정버튼 클릭시 호출되는 메소드
function upboard(){
    let updateform = document.querySelector('.updateform')
    let data = new FormData(updateform)
    data.set("bno",bno)
    console.log("수정 정보) "+data)
    $.ajax({
        url:'/board/upboard',
        type:'put',
        data:data,  //json을 json형태의 문자열로 변환
        contentType:false,
        processData:false,
        success: function(re) {
            alert(re)
            if(re == true){ location.href = '/board/view' }
            else{ alert('글수정 실패') }
        }
    })
}

//4. 카테고리 목록 select에 출력 메소드

function getbcategory(){
    $.ajax({
        url: '/board/bcategorylist',
        type: 'get',
        success: function(re) {
            let html = '<option name="category">카테고리 선택</option>'
            re.forEach( (bc) => {
                html += '<option name="category" value="'+bc.bcno+'">'+bc.bcname+'</option>'
            })
            document.getElementById('bcategorySelect').innerHTML = html
        }
    })
}