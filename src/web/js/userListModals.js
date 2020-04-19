//Delete Modal
$('#deleteConfirmationModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget); // Button that triggered the modal
  var emailRecipient = button.data('email'); // Extract info from data-* attributes
  var idRecipient = button.data('id');
  
  //Load Data extracted in the form  
  var modal = $(this);
  modal.find('#modal-user').text(emailRecipient);
  modal.find('#modal-form-user').val(idRecipient);
});






