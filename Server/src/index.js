$(function() {
    addAppsMenu();
    addAuth();
});

function addAppsMenu() {
    $('#menuInfo').append('<h1>Micelius frontend menu!</h1>');
    $('#menuInfo').append('[' + apps.length + '] applications.');
    for (var i = 0; i <= apps.length - 1; i++) {
        var app = '<div class="galleryImage">';
        app += '<img src=' + apps[i].linkToPic + ' width=325 height=260>';
        app += '<div class="info">';
        app += '<h2><a href="' + apps[i].linkToXml+ '">' + apps[i].name + '</a></h2>';
        app += '<p>' + apps[i].info + '</p>';
        app += '</div>';
        app += '</div>';
        $('#galleryContainer').append(app);
    }

}

function addAuth(){
   var cookie  = get_cookie("micelius-auth");
   console.log(cookie);
   if (cookie){
       var nameValue = cookie.split("&")[0];
       console.log(nameValue);
        if (nameValue && nameValue != "null"){
              var name = nameValue.split(">>")[1];
                 $('.auth').append('<p>'+ name +' | ' +'<a href="log_out">  Logout</a> </p> </div>' );
         } else{
            $('.auth').append('<a href="../auth/auth.xml">Auth</a>');
         }
   } else{
     $('.auth').append('<a href="../auth/auth.xml">Auth</a>');
   }


}


function get_cookie ( cookie_name ){
     var results = document.cookie.match ( '(^|;) ?' + cookie_name + '=([^;]*)(;|$)' );
     if ( results )
        return ( unescape ( results[2] ) );
     else
        return null;
}