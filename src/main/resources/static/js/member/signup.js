
function setmember(){

    let info = {
        memail: document.querySelector('.memail').value,
        mpassword: document.querySelector('.mpassword').value
    }

    $.ajax({
        url: '/member/setmember',
        type: 'POST',
        data: JSON.stringify(info),
        contentType: 'application/json',
        success: function(re){
            alert(re)
        }
    })
}