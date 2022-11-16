

function getmember(){
    let info = {
        memail: document.querySelector('.memail').value,
        mpassword : document.querySelector('.mpassword').value
    }
    $.ajax({
        url: '/member/getmember',
        type: 'post',           //password 들어가면 post로 하자
        data: JSON.stringify(info),
        contentType: 'application/json',
        success: function(re){
            let output = ''
            switch(re){
                case 0:
                    output = '로그인 실패'
                    break
                case 1:
                    output = '로그인 성공'
                    location.href = '/'
                    break
                case 2:
                    output = '비밀번호가 틀렸습니다.'
                    break
            }
            alert(output)
        }
    })
}

