const categoriesDropDown = document.querySelector("#categoriesDiv");
const categories = fetch("/efake/NavCategories")
    .then(response => response.json())
    .then(data => {
        data.categories.forEach(element => {
            var node = document.createElement('a');
            node.className = "dropdown-item";
            var textNode = document.createTextNode(element);
            node.appendChild(textNode);
            categoriesDropDown.appendChild(node);
        });
    });






