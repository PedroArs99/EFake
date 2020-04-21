<%-- 
    Document   : CreateProducts
    Created on : 24-mar-2020, 12:04:02
    Author     : JuMed
--%>

<%@page import="com.efake.entity.Usuario"%>
<%@page import="com.efake.entity.Keywords"%>
<%@page import="com.efake.entity.Producto"%>
<%@page import="com.efake.entity.Subcategoria"%>
<%@page import="com.efake.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    if (user == null) {
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<%
    Producto producto = (Producto) request.getAttribute("producto");

    String nombre = (producto == null) ? "" : producto.getNombre();
    Object precio = (producto == null) ? "" : producto.getPrecio();
    String imagen = (producto == null) ? "" : producto.getImagen();
    String descripcion = (producto == null) ? "" : producto.getDescripcion();
    String categoria = (producto == null) ? "-" : producto.getCategoria().getNombre();
    String subcategoria;
    if (producto == null || producto.getSubcategoria() == null) {
        subcategoria = "-";
    } else {
        subcategoria = producto.getSubcategoria().getNombre();
    }

    String keywords = (producto == null) ? "" : producto.getKeywordsJSP();

    String goTo = (producto == null) ? "/efake/CreateProductsServlet" : "/efake/ModificarProductoServlet";

%>

<html>

    <head>
        <title>EFake</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="/efake/css/styles.css">
        <style>

            .contact-form .form-control{
                border-radius:1rem;
            }
            .contact-form form{
                padding: 14%;
            }
            .contact-form form .row{
                margin-bottom: -7%;
            }
            .contact-form h3{
                margin-bottom: 3%;
                margin-top: -20%;
                text-align: center;
                color: #0062cc;
            }
            .contact-form .btnContact {
                width: 50%;
                border: none;
                border-radius: 1rem;
                padding: 1.5%;
                background: #dc3545;
                font-weight: 600;
                color: #fff;
                cursor: pointer;
            }
            .btnContactSubmit
            {
                width: 50%;
                border-radius: 1rem;
                padding: 1.5%;
                color: #fff;
                background-color: #0062cc;
                border: none;
                cursor: pointer;
            }       

        </style>
    </head>



    <body>
        <%@include file="/components/navbar.jspf"%>
        <div class="container contact-form mt-5">
            <form action="<%= goTo%>" method="post">
                <% if (producto != null) {%>
                <input type="hidden" name="id" value="<%= producto.getId()%>">
                <% }%>
                <h3 class="text-center">Sell new product</h3>
                <div class="row">
                    <div  id="first-stage" class="col-md-6 mx-auto">
                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" name="textNombre" class="form-control" value="<%= nombre%>" required/>
                        </div>

                        <div class="form-group">
                            <label>Price</label>
                            <input type="number" step="0.01" name="textPrecio" class="form-control" value="<%= precio%>" required/>
                        </div>

                        <div class="form-group">
                            <label>Image:</label>
                            <input type="text" name="textImagen" class="form-control" value="<%= imagen%>"/>
                            <small class="form-text text-muted">Please enter an external link.</small>
                        </div> 

                        <div class="form-group">
                            <label>Description:</label>
                            <textarea rows="4" name="descripcion" class="form-control overflow-hidden"><%= descripcion%></textarea>
                        </div>
                        <button id="next-button" class="btn btn-primary mx-auto">Next</button>
                    </div>

                    <div id="second-stage" class="col-md-6 mx-auto d-none">        
                        <div class="form-group">
                            <label>Keywords</label>
                            <input type="text" name="keywords" class="form-control mb-2" value="<%= keywords %>"/>
                            <small class="form-text text-muted">You can enter as many words as you want, separated by comma.</small>
                        </div>

                        <div class="form-group">
                            <label>Category: (current: <%= categoria%>)</label>
                            <select id="category-select" name="Categoria" class="form-control">

                            </select>
                        </div>

                        <div class="form-group">
                            <label>Subcategory: (current: <%= subcategoria%>)</label>
                            <select id="subcategory-select" name="Subcategoria" class="form-control">
                                <option value="0">-</option>
                            </select>
                            
                        </div>



                        <div class="form-group">
                            <button id="back-button" class="btn btn-primary mx-auto">Previous</button>
                            <input id="send-button" type="submit" name="Guardar" class="btn btn-primary mx-auto" value="Send">
                        </div>
                    </div>



                </div>
            </form>
        </div>
        <%@include file="/components/footer.jspf"%>

        <!--Bootstrap -->
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        
        <!-- Own Scripts -->
        <script src="/efake/js/createProduct.js"></script>
    </body>
</html>
