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
}

//4. 방명록 등록
function setvisitlog(){
    let data = {
        vcontent: document.querySelector('.vcontent').value,
        vwriter: document.querySelector('.vwriter').value,
        vcno: vcno
    }
    $.ajax({
        url: '/visitlog/setvisitlog',
        data: JSON.stringify(data),
        contentType: 'application/json',
        type: 'POST',
        success: function(re){

            if(re == true){
                alert('글작성 성공')
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
            let html = `<tr> <th>번호</th><th>작성자</th><th>내용</th> </tr>`
            re.forEach((v)=>{
                html += '<tr> <td>'+v.vno+'</td><td>'+v.vwriter+'</td><td>'+v.vcontent+'</td> </tr>'
            })
            document.querySelector('.vtable').innerHTML = html
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
