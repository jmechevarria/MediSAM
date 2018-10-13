
//TOGGLE ADMIN USERS-CONTENT
$('#admin-button-group-toggle').on('click', 'label', (e) => {
    let newFocus = $(e.currentTarget).data('toggle');
    let prevFocus = $(e.delegateTarget).find('label.active').data('toggle');
    $('div#' + newFocus).toggle();
    $('div#' + prevFocus).toggle();
}
);
//TOGGLE ADMIN USERS-CONTENT - END
