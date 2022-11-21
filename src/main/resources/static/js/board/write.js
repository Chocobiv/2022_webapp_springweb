//------------------ 전역변수 -------------------
let bcno = 2        //카테고리 번호  //카테고리 기본값

function setboard(){
    let data = {
        btitle : document.querySelector('.btitle').value,
        bcontent : document.querySelector('.bcontent').value,
        bfile : document.querySelector('.bfile').value,
        bcno: bcno
    }
    $.ajax({
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