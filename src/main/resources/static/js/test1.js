function getMapping1(){
    alert('1버튼 눌림')
    $.ajax({
        url: '/api/v1/get-api/hello',
        success: function(re){
            alert(re)
        }
    })
}

function getMapping2(){
    alert('2버튼 눌림')
    $.ajax({
        url: '/api/v1/get-api/name',
        success: function(re){
            alert(re)
        }
    })
}
function getMapping3(){
    alert('3버튼 눌림')
    $.ajax({
        url: '/api/v1/get-api/variable1/{variable}',
        success: function(re){
            alert(re)
        }
    })
}
function getMapping4(){
    alert('4버튼 눌림')
    $.ajax({
        url: '/api/v1/get-api/variable2/{variable}',
        success: function(re){
            alert(re)
        }
    })
}
function getMapping5(){
    alert('5버튼 눌림')
    $.ajax({
        url: '/api/v1/get-api/variable3?variable=안녕',
        success: function(re){
            alert(re)
        }
    })
}
function getMapping6(){
    alert('6버튼 눌림'),
    $.ajax({
        url: '/api/v1/get-api/request1?name=hong&email=aa@ab.com&organization=aaa',
        success: function(re){
            alert(re)
        }
    })
}
function getMapping7(){
    alert('7버튼 눌림')
    $.ajax({
        url: '/api/v1/get-api/request2',
        success: function(re){
            alert(re)
        }
    })
}
function getMapping8(){
    alert('8버튼 눌림')
    $.ajax({
        url: '/api/v1/get-api/request3',
        success: function(re){
            alert(re)
        }
    })
}