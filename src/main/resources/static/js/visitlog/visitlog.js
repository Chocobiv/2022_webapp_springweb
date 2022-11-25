let vcno = 1

//1. 모든 카테고리 출력
getvcategory()
function getvcategory(){
    $.ajax({
        url: '/visitlog/getvcategory',
        type: 'GET',
        success: function(re){
            let html = ''
            re.forEach( (vc) => {
                html += '<button type="button" onclick="vcnochange('+vc.vcno+')" style="margin-right:5px; background-color: bisque; border: solid 1px #999999; height: 30px;">'+vc.vcname+'</button>'
            })
            document.querySelector('.categorybox').innerHTML = html
        }
    })

}

//2. 카테고리 등록
function setvcategory(){
    let data = {
        vcname: document.querySelector('.vcname').value
    }
    console.log(data.vcname)
    $.ajax({
        url: '/visitlog/setvcategory',
        data: JSON.stringify(data),
        contentType: 'application/json',
        type: 'POST',
        success: function(re) {
            if(re == true){
                getvcategory()
                document.querySelector('.vcname').value = ''
                vcategorylist()
            }
        }
    })
}

//3. 카테고리 변경
function vcnochange(cno){
    vcno = cno
    alert(vcno)
}

//4. 방명록 등록
function setvisitlog(){
    let visitlogdata = document.querySelector(".visitlogdata")
    let formdata = new FormData(visitlogdata)
    formdata.set("vcno", vcno)
    $.ajax({
        url: '/visitlog/setvisitlog',
        data: formdata,
        type: 'POST',
        processData: false,
        contentType: false,
        success: function(re){
            if(re == true){
                alert('글작성 성공')
                boardlist()
            }else alert(re)
        }
    })
}

let vno = 0 //방명록 목록 조회용 카테고리 번호
boardlist()
//5. 방명록 목록
function boardlist(){
    $.ajax({
        url: '/visitlog/getvisitlog',
        data: {'vcno': vno},
        type: 'GET',
        success: function(re){
            let html = `<tr> <th>번호</th><th>작성자</th><th>내용</th><th>수정</th><th>삭제</th> </tr>`
            re.forEach((v)=>{
                html += '<tr> <td>'+v.vno+'</td><td>'+v.vwriter+'</td><td>'+v.vcontent+'</td><td><button onclick="getupdatevisitlog('+v.vno+')">수정</button></td><td><button onclick="deletevisitlog('+v.vno+')">삭제</button></td> </tr>'
            })
            document.querySelector('.vtable').innerHTML = html
        }
    })
}

//6. 방명록 수정 띄우기
function getupdatevisitlog(vno){

    $.ajax({
        url: '/visitlog/viewvisitlog',
        data: {"vno": vno},
        type: 'get',
        success: function(re) {
            let html = '<h1>방명록 수정</h1>'
            html += '작성자 : <input type="text" class="vwriter2" style="width: 220px;" value="'+re.vwriter+'"><br> 내용 : <textarea type="text" class="vcontent2" style="width: 220px; vertical-align: top; height: 150px;">'+re.vcontent+'</textarea><br><button type="submit" onclick="updatevisitlog('+vno+')">수정</button>'
            document.querySelector('.updatebox').innerHTML = html
        }
    })
}

//7. 방명록 수정
function updatevisitlog(vno){
    let data = {
        vno: vno,
        vwriter: document.querySelector('.vwriter2').value,
        vcontent: document.querySelector('.vcontent2').value
    }
    $.ajax({
        url: '/visitlog/updatevisitlog',
        data: JSON.stringify(data),
        contentType: 'application/json',
        type: 'put',
        success: function(re){
            if(re == true){
                alert('방명록 수정 성공')
                document.querySelector('.updatebox').innerHTML = ''
                boardlist()
            }else alert(re)
        }
    })
}

//8. 방명록 삭제
function deletevisitlog(vno){
    $.ajax({
        url: '/visitlog/deletevisitlog',
        data: {"vno": vno},
        type: 'delete',
        success: function(re) {
            if(re == true){
                alert('방명록 삭제 성공')
                boardlist()
            }else alert(re)
        }
    })
}




//1. 모든 카테고리 출력
vcategorylist()
function vcategorylist(){
    $.ajax({
        url: '/visitlog/getvcategory',
        type: 'GET',
        success: function(re){
            let html = '<button type="button" onclick="vcnochange2(0)" style="margin-right:5px; background-color: bisque; border: solid 1px #999999; height: 30px;">전체보기</button>'
            re.forEach( (vc) => {
                html += '<button type="button" onclick="vcnochange2('+vc.vcno+')" style="margin-right:5px; background-color: bisque; border: solid 1px #999999; height: 30px;">'+vc.vcname+'</button>'
            })
            document.querySelector('.vcategorybox').innerHTML = html
        }
    })

}

//3. 카테고리 변경
function vcnochange2(cno){
    vno = cno
    boardlist()
}
