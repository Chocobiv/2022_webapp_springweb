getlist()
function getlist(){
    $.ajax({
        url: '/board/getboards',
        type: 'get',
        success: function(re) {
            let html = '<tr> <th>글번호</th> <th>제목</th> </tr>'

            for(let i=0; i<re.length; i++){
                html += '<tr onclick="viewboard('+re[i].bno+')"> <td>'+re[i].bno+'</td> <td>'+re[i].btitle+'</td> </tr>'
            }

            document.querySelector('.listbox').innerHTML = html
        }
    })
}

function viewboard(bno){
    alert(bno)
}