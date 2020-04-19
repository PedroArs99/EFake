//Declare MostRatedProducts Div
const MostRatedProductsDiv = document.querySelector("#mostRatedProducts");

//Fetch Api & get Products
fetch("/efake/MostRatedProducts")
    .then(response => response.json())
    .then(data => {
        let i = 0;
        data.products.forEach(product => {
            createCard(product, i);
            i++;
        });
    });

function createCard(product, index) {
    //Create Div node
    let cardNode = document.createElement('div');
    //Add Bootstrap classname
    cardNode.className = "card ml-3 ";
    //Add animation properties
    cardNode.setAttribute("data-aos", "zoom-in-left");
    cardNode.setAttribute("data-aos-duration", "2000");
    cardNode.setAttribute("data-aos-delay", `${index * 200}`);

    //Add image
    let image = document.createElement('img');
    image.className = "card-img-top h-50";
    image.setAttribute("src", product.image);
    cardNode.appendChild(image);

    //Add card body 
    let bodyNode = document.createElement("div");
    bodyNode.className = "card-body h-50";

    let cardTitle = document.createElement("h5");
    cardTitle.className = "card-title h-30";
    cardTitle.innerHTML = product.name;
    bodyNode.appendChild(cardTitle);

    let cardText = document.createElement("p");
    cardText.className = "card-text h-15";
    cardText.innerHTML = product.description;
    bodyNode.appendChild(cardText);

    let cardLink = document.createElement("a");
    cardLink.className = "btn btn-primary h-25";
    cardLink.setAttribute("href", `/efake/ShowProduct?idProducto=${product.id}`);
    cardLink.innerHTML = "View Product";
    bodyNode.appendChild(cardLink);

    cardNode.appendChild(bodyNode);

    //Append child on div
    MostRatedProductsDiv.appendChild(cardNode);
}

