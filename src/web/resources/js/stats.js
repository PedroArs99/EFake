const globalButton = document.querySelector("[id='form:global-stats']");
const todayButton = document.querySelector("[id='form2:today-stats']");
const monthlyButton = document.querySelector("[id='form3:today-stats']");

const monthlyMenu = document.querySelector("#monthly-panel");

globalButton.addEventListener('click', (e) =>{
    monthlyMenu.classList.add('d-none'); 
});

todayButton.addEventListener('click', (e) =>{
    monthlyMenu.classList.add('d-none');
});

monthlyButton.addEventListener('click', (e) =>{
    monthlyMenu.classList.remove('d-none');
});



