$(document).ready(function() {
    $('.galleryImage').hover(
        function() {
            $(this).find('img').animate({width:100, height:100, marginTop:10, marginLeft:10}, 500);
        },
        function() {
            $(this).find('img').animate({width:325, height:260, marginTop:0, marginLeft:0}, 300);
        });
});

                       
                   