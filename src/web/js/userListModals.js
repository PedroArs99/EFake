//Alter User Modal
$('#alterUserModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget); // Button that triggered the modal
  // Extract info from data-* attributes
  var idRecipient = button.data('id');
  var emailRecipient = button.data('user');
  var fNameRecipient = button.data('fname');
  var sNameRecipient = button.data('sname');
  var ageRecipient = button.data('age');
  var phoneRecipient = button.data('phone');
  (phoneRecipient === "null") ? "-" : phoneRecipient;
  var lastLoginRecipient = button.data('lastLogin');
  
  //Load Data extracted in the form
  var modal = $(this);
  modal.find('#modal-form-user').val(idRecipient);
  modal.find('#modal-form-email').val(emailRecipient);
  modal.find('#modal-form-fname').val(fNameRecipient);
  modal.find('#modal-form-sname').val(sNameRecipient);
  modal.find('#modal-form-age').val(ageRecipient);
  modal.find('#modal-form-phone').val(phoneRecipient);
  if (lastLoginRecipient === "null") {
    const datePicker = document.querySelector('#modal-form-password');
    datePicker.setAttribute('type', 'text');
    datePicker.val("-");
  } else {
    modal.find('#modal-form-lastLogin').val(lastLoginRecipient);
  }
  modal.find('#modal-form-password').val("");
});

//Eye Button
const eyeButton = $('#eyeButton');
const passwordField = document.querySelector('#modal-form-password');
var showPassword = false;

eyeButton.on('click', event => {
  showPassword = !showPassword;

  if (showPassword) {
    passwordField.setAttribute("type", "text");
  } else {
    passwordField.setAttribute("type", "password");
  }
});

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






