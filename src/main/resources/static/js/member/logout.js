logout()
function logout(){
    $.ajax({
        url: '/member/setlogout',
        type: 'put',
        success: function(re){
            alert("안녕히 가세요")
            location.href="/"
        }
    })
}