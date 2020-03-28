$('#deleteConfirmationModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget); // Button that triggered the modal
  var recipient = button.data('email'); // Extract info from data-* attributes
  var modal = $(this);
  modal.find('#modal-user').text(recipient);
  modal.find('#modal-form-user').val(recipient);
})


