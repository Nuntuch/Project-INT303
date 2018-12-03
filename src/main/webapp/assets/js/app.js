
var Tawk_API = Tawk_API || {}, Tawk_LoadStart = new Date();



window.loading_screen = window.pleaseWait({
    logo: "",
    backgroundColor: '#000',
    loadingHtml: "<div class='spinner'><div class='bounce1'></div><div class='bounce2'></div><div class='bounce3'></div></div>"
});


$(document)
        .ready(function () {
            console.log('page ready');
            handleDropDown();
            handleCardDimmer();
            handleRating();
            handleCloseMessage()
            showChatSupport();
            addToFav();
        });

$(window).on('load', function () {
    window.loading_screen.finish();
    console.log('page loaded');
});

function handleCloseMessage() {
    $('.message .close')
            .on('click', function () {
                $(this)
                        .closest('.message')
                        .transition('fade')
                        ;
            })
            ;
}


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
