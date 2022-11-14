//alert('ddd')

function setboard(){
    let data = {
        btitle : document.querySelector('.btitle').value,
        bcontent : document.querySelector('.bcontent').value
    }
    $.ajax({
        url:'/board/setboard',
        type:'POST',
        data:JSON.stringify(data),  //json을 json형태의 문자열로 변환
        contentType: 'application/json',    //@RequestBody
        success: function(re) {
            alert(re)
        }
    })
}