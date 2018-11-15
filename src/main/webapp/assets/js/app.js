
// Set Dropdown Trigger
$(document)
        .ready(function () {
            handleDropDown();
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
