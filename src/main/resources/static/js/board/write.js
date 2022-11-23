//------------------ 전역변수 -------------------
let bcno = 2        //카테고리 번호  //카테고리 기본값

//1. 게시물 등록 메소드 [일반전송]
/*function setboard(){
    let data = {
        btitle : document.querySelector('.btitle').value,
        bcontent : document.querySelector('.bcontent').value,
        bfile : document.querySelector('.bfile').value,
        bcno: bcno
    }
    $.ajax({    //http 사용하는 jquery 비동기통신함수 [contentType default: text/html]
        url : "/board/setboard",
        type : "post",
        data : JSON.stringify(data) ,//json을 json형태의 문자열로 변환
        contentType : "application/json",    //@RequestBody
        success : function(re) {
            if( re == true){
                alert('글작성성공');
                location.href="/board/list";
            }
            else{ alert("글작성실패"); }
        }
    })
}*/

//1. 게시물 등록 메소드 [첨부파일 폼 전송]
function setboard(){
    let boardform = document.querySelector('.boardform')
    let formdata = new FormData(boardform);     //form은 name값으로 매핑함! class는 안됨
    formdata.set("bcno", bcno)    //form 데이터에 데이터 추가

    $.ajax({    //http 사용하는 jquery 비동기통신함수 [contentType default: text/html]
        url : "/board/setboard",
        type : "post",              //Multipart 전송방법1(첨부파일)
        data : formdata,//json을 json형태의 문자열로 변환
        //Content-Type: multipart/formed-data    <- 파일 첨부
        processData: false,         //Multipart 전송방법2(첨부파일)
        contentType : false,        //Multipart 전송방법3(첨부파일)
        success : function(re) {
            if( re == true){
                alert('글작성성공');
                location.href="/board/list";
            }
            else{ alert("글작성실패"); }
        }
    })
}

//2. 게시물 카테고리 등록 메소드
function setbcategory(){
    let data = {
        bcname: document.querySelector('.bcname').value
    }
    $.ajax({
        url: '/board/setbcategory',
        type:'post',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function(re) {
            if(re == true) bcategorylist()
            document.querySelector('.bcname').value = ''
        }
    })
}

//3. 모든 카테고리 출력
bcategorylist()
function bcategorylist(){
    $.ajax({
        url: '/board/bcategorylist',
        type: 'get',
        success: function(re) {
            let html = ''
            re.forEach( (bc) => {
                html += '<button type="button" onclick="bcnochange('+bc.bcno+')" style="margin-right:5px; background-color: bisque; border: solid 1px #999999; height: 30px;">'+bc.bcname+'</button>'
            })
            document.querySelector('.categorybox').innerHTML = html
        }
    })
}

//4. 카테고리 버튼을 클릭했을 때 선택된 카테고리 번호 대입
function bcnochange(cno){
    bcno = cno
    alert(bcno+"의 카테고리 선택")
}