// JavaScript Document
$(document).ready(function () {
    $('#Top .Toolbar1 .CentreBox .Menu .List1 li').mouseenter(function () {
        var index = $(this).parent().children().index(this);
        $(this).parent().children().each(function () {
            if ($(this).hasClass('Select')) {
                $(this).removeClass('Select');
            }
        });
        $(this).addClass('Select');

        $('#Top .Toolbar2 .CentreBox .Menu').each(function () {
            if (!$(this).hasClass('Hide')) {
                $(this).addClass('Hide');
            }
        });
        $('#Top .Toolbar2 .CentreBox .Menu').eq(index).removeClass('Hide').css('left',($(this).offset().left-353));

    });


});