
function findpassword(){
    let memail = document.querySelector('.memail').value
    $.ajax({
        url: '/member/getpassword',
        type: 'get',
        data: {"memail":memail},
        success: function(re) {
            alert('비밀번호: '+re)
        }
    })
}