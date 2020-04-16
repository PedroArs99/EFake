<%-- 
    Document   : CreateProducts
    Created on : 24-mar-2020, 12:04:02
    Author     : JuMed
--%>

<%@page import="com.efake.entity.Keywords"%>
<%@page import="com.efake.entity.Producto"%>
<%@page import="com.efake.entity.Subcategoria"%>
<%@page import="com.efake.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
  List<Categoria> categorias = (List<Categoria>) request.getAttribute("categoriaList"); 
  List<Subcategoria> subcategorias = (List<Subcategoria>) request.getAttribute("subcategoriasList"); 
  Producto producto = (Producto) request.getAttribute("producto");
 
  String k1palabra = "";
  String k2palabra = "";
  String k3palabra = "";
  if(producto.getKeywordsList()!=null){
       if(producto.getKeywordsList().size()>0){
            Keywords k1 = producto.getKeywordsList().get(0);
            k1palabra=k1.getPalabra();
            if(producto.getKeywordsList().size()>1){
                Keywords k2 = producto.getKeywordsList().get(1);
                k2palabra=k2.getPalabra();
                 if(producto.getKeywordsList().size()>2){
                    Keywords k3 = producto.getKeywordsList().get(2);
                    k3palabra=k3.getPalabra();
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
            .contact-form{
                background: #fff;
               
                margin-bottom: 5%;
                width: 70%;
            }
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

    <!--Font Awesome-->

    <body>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        
        <hr>
        
        <div class="container contact-form">
         <form action="<%=request.getContextPath()%>/ModificarProductoServlet?id=<%=producto.getId()%>" method="post">
            <div>
                <h3>Modificar producto</h3>
            </div>
            <div class="row">
                <div class="col-md-6">
                    
                        Nombre:
                        <input type="text" name="textNombre" class="form-control" value=<%=producto.getNombre()%>/> <br/>

                        Precio:
                        <input type="number" name="textPrecio" class="form-control" value=<%=producto.getPrecio()%>/><br/>

                        Imagen :
                        <input type="text" name="textImagen" class="form-control" value=<%=producto.getImagen()%>/><br/>

                        Keywords :
                        <input type="text" name="textKeywords1" class="form-control" value=<%=k1palabra%>/><br/>
                        <input type="text" name="textKeywords2" class="form-control" value=<%=k2palabra%>/><br/>
                        <input type="text" name="textKeywords3" class="form-control" value=<%=k3palabra%>/>

                        Categoria:
                        <select name="Categoria" class="form-control">
                        <%for(Categoria c: categorias){ %>
                        <option value=<%=c.getId()%>> <%=c.getNombre()%></option>
                        <% ;} %> 
                        </select><br/>

                        Subcategoria:
                        <select name="Subcategoria" class="form-control">
                        <%for(Subcategoria c: subcategorias){ %>
                        <option value=<%=c.getId()%>> <%=c.getNombre()%></option>
                        <% ;} %>
                        </select><br/>

                        <p>
                        <input type="submit" name="Modificar" value="Modificar" class="form-control"
                        <p>
                    </div>  
            
                <div class="col-md-6">        
                    Descripción:<br/>
                    <textarea rows="10" cols="30" name="descripcion" value=<%=producto.getDescripcion() %>/>
                    </textarea><br/>
                </div>
           
            </div>    
        </form>
        </div>
        
    </body>
</html>
