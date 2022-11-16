getloginMno()
function getloginMno(){
    $.ajax({
        url: '/member/getloginMno',
        type: 'get',
        success: function(re){
            let html = '';
            if(re == '0'){  //로그인 안했다
                html += '<button type="button" onclick="signup()">회원가입</button>'+
                            '<button type="button" onclick="login()">로그인</button>'
            }else{  //로그인 했다
                html += '<button type="button" onclick="logout()" class="btnlogout">로그아웃</button>'+
                         '<button type="button" onclick="findpassword()" class="btnfindpassword">비밀번호찾기</button>'+
                         '<button type="button" onclick="update()" class="btnupdate">비밀번호수정</button>'+
                         '<button type="button" onclick="mdelete()" class="btndelete">회원탈퇴</button>'
            }
            document.querySelector('.headerbox').innerHTML = html
        }
    })
}



function signup(){
    location.href="/member/signup"
}

function login(){
    location.href="/member/login"
}

function logout(){
    location.href="/member/logout"
}

function findpassword(){
    location.href="/member/findpassword"
}

function update(){
    location.href="/member/update"
}

function mdelete(){
    location.href="/member/delete"
}