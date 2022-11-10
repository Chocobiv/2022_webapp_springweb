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

// ----------------------------------------------------------- //
function postMapping1(){
    alert('1버튼 눌림')
    $.ajax({
        url: '/api/v1/post-api/domain',
        type: 'POST',
        success: function(re){ alert(re) }
    })
}
function postMapping2(){
    alert('2버튼 눌림')
    let member = {
        name: "유재석",
        email: "유재석@qq.com",
        organization: "qweqwe"
    }
    $.ajax({
        url: '/api/v1/post-api/member',
        type: 'POST',
        data: JSON.stringify(member),
        contentType: "application/json",    //전송타입 : application/json
        success: function(re){ alert(re) }
    })
}
function postMapping3(){
    alert('3버튼 눌림')
    let member = {
        name: "유재석",
        email: "유재석@qq.com",
        organization: "qweqwe"
    }
    $.ajax({
        url: '/api/v1/post-api/member2',
        type: 'POST',
        data: JSON.stringify(member),
        contentType: "application/json",    //전송타입 : application/json
        success: function(re){ alert(re) }
    })
}

// ----------------------------------------------------------- //
function putMapping1(){
    alert('1버튼 눌림')
    let member = { name: "유재석", email: "유재석@qq.com", organization: "qweqwe" }
    $.ajax({
        url: '/api/v1/put-api/member',
        type: 'put',
        data: JSON.stringify(member),
        contentType: "application/json",    //전송타입 : application/json
        success: function(re){ alert(re) }
    })
}
function putMapping2(){
    alert('2버튼 눌림')
    let member = { name: "유재석", email: "유재석@qq.com", organization: "qweqwe" }
    $.ajax({
        url: '/api/v1/put-api/member1',
        type: 'put',
        data: JSON.stringify(member),
        contentType: "application/json",    //전송타입 : application/json
        success: function(re){ alert(re) }
    })
}
function putMapping3(){
    alert('3버튼 눌림')
    let member = { name: "유재석", email: "유재석@qq.com", organization: "qweqwe" }
    $.ajax({
        url: '/api/v1/put-api/member2',
        type: 'put',
        data: JSON.stringify(member),
        contentType: "application/json",    //전송타입 : application/json
        success: function(re){
            console.log(re)
            console.log(re.name)
        }
    })
}

// ----------------------------------------------------------- //

function deleteMapping1(){
    alert('1버튼 눌림')
    $.ajax({
        url: '/api/v1/delete-api/하하하하',
        type: 'delete',
        success: function(re){ alert(re) }
    })
}
function deleteMapping2(){
    alert('2버튼 눌림')
    $.ajax({
        url: '/api/v1/delete-api/request1?variable=히히히히히',
        type: 'delete',
        success: function(re){ alert(re) }
    })
}