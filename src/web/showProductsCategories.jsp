<%-- 
    Document   : showProductsCategories
    Created on : 08-abr-2020, 10:27:02
    Author     : carlo
--%>

<%@page import="java.util.List"%>
<%@page import="com.efake.entity.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    List<Producto> listaProductos = (List<Producto>) request.getAttribute("listaProductoCategoria");
    String category = (String) request.getAttribute("category");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--Favicon-->
        <link rel="shortcut icon" href="/efake/img/favicon.png" type="image/png">
        <!--Bootstrap-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <!--Google Fonts-->
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@1,900&display=swap" rel="stylesheet">
        <!--AOS-->
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
        <!--Custom Sytles-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        <title><%= category %></title>
    </head>
    <body>
        <% for(Producto p: listaProductos) { %>
            <div class="d-flex justify-content-between">
                <div class="card" data-aos="zoom-in-left" data-aos-duration="2000" data-aos-delay="0">
                    <img class="card-img-top" src="/efake/img/favicon.png" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title"><%= p.getNombre() %></h5>
                        <p class="card-text"><%= p.getDescripcion() %></p>
                        <a href="selected_product.jsp" class="btn btn-primary">Buy</a>
                    </div>
                </div>
            </div>
        <% } %>
        
        
        
        
        <!--Font Awesome-->
    <script src="https://kit.fontawesome.com/998261dc3d.js" crossorigin="anonymous"></script>
    <!--Bootstrap-->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
    <!--AOS-->
    <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
    <script>
        AOS.init();
    </script>
    </body>
</html>
