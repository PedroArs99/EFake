<%-- 
    Document   : valoracion_producto
    Created on : 25-mar-2020, 13:17:12
    Author     : carlo
--%>

<%@page import="com.efake.entity.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%
    Producto producto = (Producto) request.getAttribute("productoSeleccionado");
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        .imagen {
            float: left;
            margin: 20px;
            margin-right: 100px;
            margin-bottom: 50px;
        }
        
        p {
            text-align: justify;
            margin-right: 100px;
        }

        .valoracion-titulo {
            margin-left: 50px;
        }

        label {
            color: grey;
        }

        input[type="radio"] {
            display: none;
        }

        .clasificacion {
            text-align: left;
            direction: rtl;
            margin-left: 50px
        }

        label:hover {
            color: orange;
        }

        label:hover~label {
            color: orange;
        }

        input[type="radio"]:checked~label {
            color: orange;
        }

        .line {
            border: none;
            background: transparent;
            color: #103567;
        }

        .star {
            color: orange;
        }

        .roundImage {
            margin-top: 5px;
            border-radius: 50%;
            border: 1px solid #103567;
        }
    </style>
    <title><%= producto.getNombre() %></title>
</head>
<body>
    <img class="imagen" src="#" height="200" width="300">
    <h2><%= producto.getNombre() %></h2>
    <p><%= producto.getDescripcion() %></p>

    <p><strong>Precio:</strong> <%= producto.getPrecio() %></p>
    <br>
    <br>
    <br>
    <h2 class= "valoracion-titulo">Valoraciones y Opiniones</h2>
    <form class='text-left' action='POST' method=''>
        <p class='clasificacion'>
            <input id='radio1' type='radio' name='estrellas' value='5'>
            <label for='radio1'>★</label>
            <input id='radio2' type='radio' name='estrellas' value='4'>
            <label for='radio2'>★</label>
            <input id='radio3' type='radio' name='estrellas' value='3'>
            <label for='radio3'>★</label>
            <input id='radio4' type='radio' name='estrellas' value='2'>
            <label for='radio4'>★</label>
            <input id='radio5' type='radio' name='estrellas' value='1'>
            <label for='radio5'>★</label>
        </p>
    </form>
</body>
</html>
