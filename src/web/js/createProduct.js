// Pagination between form pages
const nextButton = document.querySelector('#next-button');
const backButton = document.querySelector('#back-button');

const firstStageFields = document.querySelector('#first-stage');
const secondStageFields = document.querySelector('#second-stage');
//On click next button on first stage
nextButton.addEventListener('click', (e) => {
    e.preventDefault();
    secondStageFields.classList.remove('d-none');
    firstStageFields.classList.add('d-none');
});

//On click nexpreviot button on first stage
backButton.addEventListener('click', (e) => {
    e.preventDefault();
    secondStageFields.classList.add('d-none');
    firstStageFields.classList.remove('d-none');
});

// Category & Subcategory select
class category {
    constructor(id, name, subcategoryList) {
        this.id = id;
        this.name = name;
        this.subcategoryList = subcategoryList;
    }
}
const categorySelect = document.querySelector('#category-select');
const subcategorySelect = document.querySelector('#subcategory-select');
let categoryList = [];

//Initialize categories
fetch("/efake/NavCategories")
        .then(response => response.json())
        .then(data => {
            //Load all categories in a list
            data.categories.forEach(element => {
                categoryList.push(new category(element.id, element.name, element.subcategories));
            })

            //Add all categories as a select option
            categoryList.forEach(element => {
                let node = document.createElement('option');
                node.setAttribute("value", element.id);
                node.innerHTML = element.name;

                categorySelect.appendChild(node);
            })

            //trigger selectionchanged event on default categoryList
            categorySelect.dispatchEvent(new Event('change'));

        });

categorySelect.addEventListener('change', (e) => {
    //Clear previous subcategories
    while (subcategorySelect.lastElementChild) {
        subcategorySelect.removeChild(subcategorySelect.lastElementChild);
    }

    //Add default
    let node = document.createElement('option');
    node.setAttribute("value", 0);
    node.innerHTML = "-";
    subcategorySelect.appendChild(node);

    let category = categoryList[e.target.options.selectedIndex];

    category.subcategoryList.forEach(element => {
        let {id, name} = element;

        let node = document.createElement('option');
        node.setAttribute("value", id);
        node.innerHTML = name;
        subcategorySelect.appendChild(node);
    });
})









