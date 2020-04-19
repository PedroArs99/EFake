<%-- 
    Document   : products
    Created on : 27-mar-2020, 11:30:36
    Author     : lorenzo
--%>

<%@page import="com.efake.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.efake.entity.Producto"%>
<%@page import="java.util.List" %>

<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    List<Producto> productsList = (List<Producto>) request.getAttribute("productsList");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>EFake</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <!--AOS-->
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
        <!--Custom Sytles-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    </head>
    <body>
        <%@include file="/components/navbar.jspf"%>
        
            
            <div class="px-3 py-5">
                <h1 data-aos="fade-up" data-aos-duration="1000">
                    <% if (request.getParameter("search")!=null){ %>
                    Search results for '<%= request.getParameter("search") %>'
                    <% }else{ %>
                    All Products
                    <% } %>
                </h1>
                    <%  
                       for(int i=0; i<productsList.size(); i++){
                           Producto p = productsList.get(i);
                           if(i%5 == 0){ %>
                               <div class="d-flex justify-content-start">
                    <%      } %>
                    <div class="card ml-3" data-aos="zoom-in-left" data-aos-duration="2000" data-aos-delay="0">
                        <img class="card-img-top" src="<%= p.getImagen() %>" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title"><%= p.getNombre() %></h5>
                            <p class="card-text"><%= p.getDescripcion() %></p>
                            <a href="/efake/ShowProduct?idProducto=<%= p.getId() %>" class="btn btn-primary">View Product</a>
                        </div>
                    </div>
                    <% if(i%5 == 4){ %>
                               </div><br>
                    <% }} %>  
                    
            </div>
            <hr></hr> 
        
        <%@include file="/components/footer.jspf"%>
    <!--Font Awesome-->
    <script src="https://kit.fontawesome.com/998261dc3d.js" crossorigin="anonymous"></script>
    <!--Bootstrap-->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <!--AOS-->
    <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
    <script>
        AOS.init();
    </script>
    </body>
</html>
