<%-- 
    Document   : CreateProducts
    Created on : 24-mar-2020, 12:04:02
    Author     : JuMed
--%>

<%@page import="com.efake.entity.Subcategoria"%>
<%@page import="com.efake.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
  List<Categoria> categorias = (List<Categoria>) request.getAttribute("categoriaList"); 
  List<Subcategoria> subcategorias = (List<Subcategoria>) request.getAttribute("subcategoriasList"); 
%>

<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <div>
        <h1>Añadir producto</h1>
        <hr>
        <form action="<%=request.getContextPath()%>/CreateProductsServlet" method="post">
            Nombre:
            <input type="text" name="textNombre"/> <br/>
            Descripción:<br/>
            <textarea rows="10" cols="30" name="breve cv"/>
		</textarea><br/>
            Precio:
            <input type="number" name="textPrecio"/><br/>
            Imagen :
            <input type="text" name="textImagen"/><br/>
            Keywords :
            <input type="text" name="textKeywords"/><br/>
            Fecha:
            <input type="datetime" name="fecha"/><br/>

            Categoria:
            <select name="Categoria">
            <%for(Categoria c: categorias){ %>
            <option value=<%=c.getId()%>> <%=c.getNombre()%></option>
            <% ;} %>
            </select><br/>
            Subcategoria:
            <select name="Subcategoria">
            <%for(Subcategoria c: subcategorias){ %>
            <option value=<%=c.getId()%>> <%=c.getNombre()%></option>
            <% ;} %>
            </select><br/>
            Owner:
            <input type="text" name="textOwner"/><br/>
            <input type="submit" name="Guardar"
            
        </form>
        </div>
    </body>
</html>
