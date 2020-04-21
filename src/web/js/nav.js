const categoriesDropDown = document.querySelector("#categoriesDiv");
const categories = fetch("/efake/NavCategories")
        .then(response => response.json())
        .then(data => {
            data.categories.forEach(element => {
                var node = document.createElement('a');
                node.className = "dropdown-item";
                node.href = "/efake/ShowProductsCategory?categories=" + element.name;
                var textNode = document.createTextNode(element.name);
                node.appendChild(textNode);
                categoriesDropDown.appendChild(node);
            });
        });






