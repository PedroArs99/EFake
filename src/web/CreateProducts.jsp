<%-- 
    Document   : CreateProducts
    Created on : 24-mar-2020, 12:04:02
    Author     : JuMed
--%>

<%@page import="com.efake.entity.Usuario"%>
<%@page import="com.efake.entity.Subcategoria"%>
<%@page import="com.efake.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    if(user == null ){
        response.sendRedirect("login.jsp");
    } else if(user.getEsAdmin() == 1){
        response.sendRedirect("/");
    }

    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categoriaList");
%>

<html> 
    <head>
        <title>EFake</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
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

            textarea{
                resize: none;
            }
        </style>
    </head>
    <body class="d-flex flex-column h-100">
        <%@include file="/components/navbar.jspf"%>
        <div class="container my-5 mx-auto">
            <form action="<%=request.getContextPath()%>/CreateProductsServlet" method="post">
                <h3 class="text-center">Sell new product</h3>
                <div class="row">
                    <div  id="first-stage" class="col-md-6 mx-auto">
                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" name="textNombre" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label>Price</label>
                            <input type="number" step="0.01" name="textPrecio" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label>Image:</label>
                            <input type="text" name="textImagen" class="form-control"/>
                            <small id="emailHelp" class="form-text text-muted">Please enter an external link.</small>
                        </div> 

                        <div class="form-group">
                            <label>Description:</label>
                            <textarea rows="4" name="descripcion" class="form-control overflow-hidden"></textarea>
                        </div>
                        <button id="next-button" class="btn btn-primary mx-auto">Next</button>
                    </div>

                    <div id="second-stage" class="col-md-6 mx-auto d-none">        
                        <div class="form-group">
                            <label>Keywords</label>
                            <input type="text" name="textKeywords1" class="form-control mb-2"/>
                            <input type="text" name="textKeywords2" class="form-control mb-2"/>
                            <input type="text" name="textKeywords3" class="form-control mb-2"/>
                        </div>

                        <div class="form-group">
                            <label>Category:</label>
                            <select id="category-select" name="Categoria" class="form-control">
                                
                            </select>
                        </div>
                        
                        <div class="form-group">
                            <label>Subcategory:</label>
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

        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <!--Own scripts -->
        <script src="/efake/js/createProduct.js"></script>
    </body>
</html>
