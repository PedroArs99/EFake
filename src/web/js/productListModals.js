//Delete Modal
$('#deleteConfirmationModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget); // Button that triggered the modal
    var idRecipient = button.data('id');

    //Load Data extracted in the form  
    var modal = $(this);
    modal.find('#modal-form-product').val(idRecipient);
});






