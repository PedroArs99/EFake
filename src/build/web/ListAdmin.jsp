<%-- 
    Document   : ListAdmin
    Created on : 23-mar-2020, 11:08:22
    Author     : PedroArenas
--%>
<%@page import="com.efake.entity.Administrador"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    List<Administrador> adminList = (List<Administrador>) request.getAttribute("adminList");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <table class="table">
  <thead>
    <tr>
      <th scope="col">Correo</th>
      <th scope="col">Nombre</th>
      <th scope="col">Apellidos</th>
      
    </tr>
  </thead>
  <tbody>
    <% for(Administrador a : adminList){ %>
        <tr>
      <td><%= a.getCorreo() %></td>
      <td><%= a.getNombre() %></td>
      <td><%= a.getApellidos() %></td>
    </tr>
    <% } %>
</table>
    </body>
</html>
