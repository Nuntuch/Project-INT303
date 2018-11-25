
var Tawk_API = Tawk_API || {}, Tawk_LoadStart = new Date();

$(document)
        .ready(function () {
            handleDropDown();
            handleCardDimmer();
            handleRating();
            showChatSupport();
        });

function handleDropDown() {
    $('.ui.menu .ui.dropdown').dropdown({
        on: 'hover'
    });
    $('.ui.menu a.item')
            .on('click', function () {
                $(this)
                        .addClass('active')
                        .siblings()
                        .removeClass('active')
                        ;
            })
            ;
}

function handleCardDimmer() {
    $('.special.card .image').dimmer({
        on: 'hover'
    });
    $('.card .dimmer')
            .dimmer({
                on: 'hover'
            })
            ;
}

function handleRating() {
    $('.star.rating')
            .rating()
            ;
}

function showChatSupport() {
    var s1 = document.createElement("script"), s0 = document.getElementsByTagName("script")[0];
    s1.async = true;
    s1.src = 'https://embed.tawk.to/5bf9732240105007f3796cbd/default';
    s1.charset = 'UTF-8';
    s1.setAttribute('crossorigin', '*');
    s0.parentNode.insertBefore(s1, s0);
}