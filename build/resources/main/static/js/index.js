getloginMno()
function getloginMno(){
    $.ajax({
        url: '/member/getloginMno',
        type: 'get',
        success: function(re){
            let html = '';
            if(re == ''){  //로그인 안했다
                html += '<button type="button" onclick="signup()">회원가입</button>'+
                        '<a href="/member/login"></ㅁ><button type="button">로그인</button></a>'
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

//회원목록
list()
function list(){
    $.ajax({
        url: '/member/list',
        type: 'get',
        success: function(re){
            let html = `<tr> <th>번호</th><th>이메일</th><th>비밀번호</th> </tr>`
            re.forEach((m)=>{
                html += `<tr> <td>${m.mno}</td><td>${m.memail}</td><td>${m.mpassword}</td> </tr>`
            })
            document.querySelector('.mtable').innerHTML = html
        }
    })
}