<%@page import="java.util.List"%>
<%@page import="com.efake.entity.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<%
    
    List<Categoria> categoryList = (List<Categoria>)request.getAttribute("categoryList");

%>

<html>
    <head>
        <title>EFake</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <style>
            body {
                background-color: #f4f4f4;
            }

            .form-inline .form-control {
                display: inline-block;
                width: 40em;
                vertical-align: middle;
            }  
            
        </style>
    </head>
    <body>
      <div class="mx-5">
        <nav class="navbar navbar-expand-lg navbar-light bg-transparent border-bottom border-secondary">
          <a class="navbar-brand" href="#">
              <img src="https://raw.githubusercontent.com/PedroArs99/EFake/master/img/logo.png" height="30" class="d-inline-block align-top" alt="">
            </a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
        
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
              <li class="nav-item dropdown mr-3">
                  <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Categories
                  </a>
                  <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <%for(Categoria c : categoryList) {%>
                    <a class="dropdown-item" href="#"><%= c.getNombre() %></a>
                    <% } %>
                  </div>
              </li>
              
              <form class="form-inline my-2 my-lg-0">
                  <div class= "form-group">
                      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                      <button class="btn btn-primary my-2 my-sm-0" type="submit">Search</button>
                  </div>
              </form>
            </ul>

            <a href="#" class="mr-3">I don't have an account</a>
            <a href="#" class="btn btn-secondary">Login</a>
          </div>
        </nav>
        <div class= "p-5">

        </div>
        <footer class="footer mt-auto py-3 border-top border-secondary">
          <div class="container">
            <span class="text-muted">Copyright &copy; 2020 eFake Inc. All Rights Reserved.</span>
          </div>
        </footer>
         <hr>
        <a  class="btn btn-secondary" href="<%=request.getContextPath() %>/CreateProducts.jsp">Crear producto</a>
        <br
      </div>  
      
        
        
        
        
        
        
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>
