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
%>
<!DOCTYPE html>
<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categoriaList");
    List<Subcategoria> subcategorias = (List<Subcategoria>) request.getAttribute("subcategoriasList");
    Producto producto = (Producto) request.getAttribute("producto");

    String k1palabra = "";
    String k2palabra = "";
    String k3palabra = "";
    if (producto.getKeywordsList() != null) {
        if (producto.getKeywordsList().size() > 0) {
            Keywords k1 = producto.getKeywordsList().get(0);
            k1palabra = k1.getPalabra();
            if (producto.getKeywordsList().size() > 1) {
                Keywords k2 = producto.getKeywordsList().get(1);
                k2palabra = k2.getPalabra();
                if (producto.getKeywordsList().size() > 2) {
                    Keywords k3 = producto.getKeywordsList().get(2);
                    k3palabra = k3.getPalabra();
                }
            }
        }
    }


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

        </style>
    </head>



    <body>
        <%@include file="/components/navbar.jspf"%>
        <div class="container contact-form mt-5">
            <form action="<%=request.getContextPath()%>/ModificarProductoServlet" method="post">
                <input type="hidden" name="id" value="<%=producto.getId()%>">
                <h3 class="text-center">Sell new product</h3>
                <div class="row">
                    <div  id="first-stage" class="col-md-6 mx-auto">
                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" name="textNombre" class="form-control" value="<%= producto.getNombre()%>"/>
                        </div>

                        <div class="form-group">
                            <label>Price</label>
                            <input type="number" step="0.01" name="textPrecio" class="form-control" value="<%= producto.getPrecio()%>"/>
                        </div>

                        <div class="form-group">
                            <label>Image:</label>
                            <input type="text" name="textImagen" class="form-control" value="<%= producto.getImagen()%>"/>
                                   <small class="form-text text-muted">Please enter an external link.</small>
                        </div> 

                        <div class="form-group">
                            <label>Description:</label>
                            <textarea rows="4" name="descripcion" class="form-control overflow-hidden"><%= producto.getDescripcion()%></textarea>
                        </div>
                        <button id="next-button" class="btn btn-primary mx-auto">Next</button>
                    </div>

                    <div id="second-stage" class="col-md-6 mx-auto d-none">        
                        <div class="form-group">
                            <label>Keywords</label>
                            <input type="text" name="textKeywords1" class="form-control mb-2" value="<%= k1palabra%>"/>
                            <input type="text" name="textKeywords2" class="form-control mb-2" value="<%= k2palabra%>"/>
                            <input type="text" name="textKeywords3" class="form-control mb-2" value="<%= k3palabra%>"/>
                        </div>

                        <div class="form-group">
                            <label>Category: (current: <%= producto.getCategoria().getNombre() %>)</label>
                            <select id="category-select" name="Categoria" class="form-control">
                                
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Subcategory: (current: <%= (producto.getSubcategoria() != null)? producto.getSubcategoria().getNombre() : "-" %>)</label>
                            <select id="subcategory-select" name="Subcategoria" class="form-control">
                                <option value="0">-</option>
                            </select>
                            <small class="form-text text-muted">If you choose "Don't Change" on category value won't be edited.</small>
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
        <script src="/efake/js/createProduct.js"></script>
    </body>
</html>
