//Alter Product Modal
$('#alterProductModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget); // Button that triggered the modal
    var imageField = document.querySelector('#modal-image'); //Place where put the image live preview
    var categorySelector = document.querySelector('#category-selector');
    //Extract data*- attributes
    var idRecipient = button.data('id');
    var nameRecipient = button.data('name');
    var descriptionRecipient = button.data('description');
    var priceRecipient = button.data('price');
    var imageRecipient = button.data('image');
    

    //Load Data extracted in the form  
    var modal = $(this);
    modal.find('#modal-form-product').val(idRecipient);
    modal.find('#modal-form-name').val(nameRecipient);
    modal.find('#modal-form-description').val(descriptionRecipient);
    modal.find('#modal-form-price').val(priceRecipient);
    modal.find('#modal-form-image').val(imageRecipient);
    imageField.setAttribute("src",imageRecipient);
});

//Delete Modal
$('#deleteConfirmationModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget); // Button that triggered the modal
    var idRecipient = button.data('id');

    //Load Data extracted in the form  
    var modal = $(this);
    modal.find('#modal-form-product').val(idRecipient);
});






