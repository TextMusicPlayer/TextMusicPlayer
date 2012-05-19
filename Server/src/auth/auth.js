var blocks = [];
blocks[0] = "#signup";
blocks[1] = "#signin";
blocks[2] = "#remindPass";
var cur = 1;

$(document).ready(function() {
    $('.additional a').click(function(e) {
        e.preventDefault();
        var x;
        switch ($(this).attr('href')) {
            case ("signup.html"):
                x = 0;
                break;
            case ("index.html"):
                x = 1;
                break;
            case ("remind.html"):
                x = 2;
                break;
        };
        $(blocks[x]).css({ 'visibility': 'visible' });
        GoTo(x);
    });

    $('#loginEmail').focus();
});


function WriteLine(x) {
    $('#info').append('<p>' + x + '</p');
}

function GoTo(num) {
    margin = num * 382;
    $('#wr').animate(
        { marginLeft: -margin }, 800, 'easeInOutBack', function() {
            switch (num) {
                case 0:
                    $('#email').focus();
                    break;
                case 1:
                    $('#loginEmail').focus();
                    break;
                case 2:
                    $('#remindEmail').focus();
                    break;
            };
            $(blocks[cur]).css({ 'visibility': 'hidden' });
            cur = num;
        });
}
// --------
//  ERRORS
// --------
var er = [];
er[0] = "Неправильный формат email'a"; //0
er[1] = "Пароли не совпадают"; //0
er[2] = "Пользователь с таким email'ом уже зарегистрирован"; //0
er[3] = "Не угадали пароль. Или email. Попробуйте еще раз"; //1
er[4] = "Пользователя с таким email'oм у нас еще нету"; //2
er[5] = "Неправильный формат email'a"; //2
er[6] = "какие-то данные неверные"//0
er[7] = "ведите пароль!!"//1
er[8]  = "введите ФИО!!"
function HideError() {
    $('.error').hide();
}
function ShowError(code,to) {
    HideError();
     $('#error'+to).html(er[code]).slideDown();
    switch (code) {

       /* case 0:
            $('#error'+to).html(er[0]).slideDown();
            $('#email').select().focus();
            break;
        case 1:
            $('#error'+to).html(er[1]).slideDown();
            break;
        case 2:
            $('#error'+to).html(er[2]).slideDown();
            break;
        case 3:
            $('#error'+to).html(er[3]).slideDown();
            break;
        case 4:
            $('#error'+to).html(er[4]).slideDown();
            break;
        case 5:
            $('#error'+to).html(er[5]).slideDown();
            break;
        case 6:
            $('#error'+to).html(er[6]).slideDown();
            break;
        case 7:
             $('#error'+to).html(er[6]).slideDown();
            break;*/
    }
}


// ----------
//  MESSAGES
// ----------

var m = [];
m[0] = "Письмо с напоминанием пароля выслано вам на email";
m[1] = "Извените данная функция пока не работает";

function ShowMessage(code,to) {
    HideError();
    $('#message'+to).html(m[code]).slideDown();

}

function ValidEmail(email) {
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    return reg.test(email)
}

function SignUp() {
    var email = $('#email').val();
    var pass = $('#password').val();
    var passAgain = $('#passwordAgain').val();
    var name = $('#name').val();
    if (!ValidEmail(email)) {
        ShowError(0,0);
    }
    else {
        if ((pass != "") && (pass == passAgain) && (name != "" )) {
            ShowIndicator();
            $.ajax({
                dataType:"json",
                type: "POST",
                url: "add_user",
                data: "user_login="+email+"&user_name="+name + "&user_pass="+pass,
                success: function(user){
                   //console.log(user );
                   if (user && user.id ){
                     if (user.id <0 ){
                         HideIndicator();
                         ShowError(2,0);
                     } else{
                          HideIndicator();
                          HideError();
                         // alert("OK");
                          document.location.href="/"; //todo!!!
                     }

                   }else{
                      HideIndicator();
                      ShowError(6,0);
                      console.log(" do not right connection! shood not be");
                   }
                }
            });
         }else{
            HideError();
            if (pass == ""){
                ShowError(7,0);
            } else if (pass != passAgain ) {
               ShowError(1,0);
            } else if (name == ""){
                ShowError(8,0);
            }
         }
    }
 }

function SignIn() {
    pass = $('#loginPass').val();
    email = $('#loginEmail').val();
    if (!ValidEmail(email)) {
        ShowError(0,1);
    } else  if (pass != ""){
          $.ajax({
                dataType:"json",
                type: "POST",
                url: "auth_user",
                data: "user_login="+email+"&user_pass="+pass,
                success: function(user){
                  // console.log(user );
                   if (user && user.id ){
                        if (user.id <0 ){
                         HideIndicator();
                         ShowError(3,1);
                     } else{
                          HideIndicator();
                          HideError();
                          //alert("OK");
                         document.location.href="/"; //todo!!!
                     }
                   }else{
                      HideIndicator();
                      ShowError(6,1);
                      console.log(" do not right connection! shood not be");
                   }
                }
         });

    }else{
       HideIndicator();
       ShowError(7,1);
    }




    ShowIndicator();
    if ((email != "") && (CheckPassword(email, pass))) {
        //Sign User In
        HideIndicator();
        HideError();
    } else {
        ShowError(3,1);
        HideIndicator();
    }
}

function RemindPassword() {
    var email = $('#remindEmail').val();
    if (!ValidEmail(email)) {
        ShowError(5,2);
    } else {
        ShowIndicator();
        ShowMessage(1,0);
    }
}

function ShowIndicator() {
    $('#indicator').show();
}

function HideIndicator() {
    $('#indicator').hide();
}




function UserWithEmailExists(email) {
    //check if user with such email exists
    //if true, return true, otherwise false



    return false;
}

function CheckPassword(email, pass) {
    //check if email and pass match
    return true;
}

function SendRemind(email) {

    //check if email exists, send email
    //return true if it goes okay, false otherwise

    return false;
}