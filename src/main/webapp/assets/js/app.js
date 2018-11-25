
// Set Dropdown Trigger
$(document)
        .ready(function () {
            handleDropDown();
            handleCardDimmer();
            handleRating();
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