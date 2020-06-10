<%-- 
    Document   : changePassword
    Created on : 12-abr-2020, 15:48:12
    Author     : laura
--%>

<%@page import="com.efake.entity.Usuario"%>
<%@page import="com.efake.dao.UsuarioFacade"%>
<%@page import="javax.ejb.EJB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    if (user == null) {
        response.sendRedirect("login.jsp");
    } else if (user.getEsAdmin() == 1) {
        response.sendRedirect("/");
    }
%>
<%
    String correo = request.getParameter("correo");
    String status = (String) session.getAttribute("status");
%>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Change Password</title>

        <style>
            .bd-placeholder-img {
                font-size: 1.125rem;
                text-anchor: middle;
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
            }

            @media (min-width: 768px) {
                .bd-placeholder-img-lg {
                    font-size: 3.5rem;
                }
            }
            html,
            body {
                height: 100%;
            }

            body {
                display: -ms-flexbox;
                display: flex;
                -ms-flex-align: center;
                align-items: center;
                padding-top: 40px;
                padding-bottom: 40px;
                background-color: #f5f5f5;
            }

            .form-signin {
                width: 100%;
                max-width: 330px;
                padding: 15px;
                margin: auto;
            }
            .form-signin .checkbox {
                font-weight: 400;
            }
            .form-signin .form-control {
                position: relative;
                box-sizing: border-box;
                height: auto;
                padding: 10px;
                font-size: 16px;
            }
            .form-signin .form-control:focus {
                z-index: 2;
            }
            .form-signin input[type="email"] {
                margin-bottom: -1px;
                border-bottom-right-radius: 0;
                border-bottom-left-radius: 0;
            }
            .form-signin input[type="password"] {
                margin-bottom: 10px;
                border-top-left-radius: 0;
                border-top-right-radius: 0;
            }

        </style>
        <!-- Custom styles for this template -->
        <link href="styles.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body class="text-center">
        <form class="form-signin" action="changePasswordServlet">
            <img class="d-inline-block align-top" src="https://raw.githubusercontent.com/PedroArs99/EFake/master/img/logo.png" alt="" width="72" height="30">
            <h1 class="h3 mb-3 font-weight-normal">Change Password</h1>
            <%if (status != null) {%>
            <div class="alert alert-danger"><%=status%></div>
            <%}%>
            <label for="inputPaswword" class="sr-only">Actual Password</label>
            <input type="password" name="actualPassword" id="inputPassword" class="form-control" placeholder="Actual Password" required="" autofocus="">
            <label for="inputNewPassword" class="sr-only">New Password</label>
            <input type="password" name="nuevaPassword" id="inputNewPassword" class="form-control" placeholder="New Password" required="">
            <label for="inputRepeatPassword" class="sr-only">Repeat Password</label>
            <input type="password" name="repetidaPassword" id="inputRepeatPassword" class="form-control" placeholder="Repeat Password" required="">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Save New Password</button>
            <input type="hidden" name = "correo" value="<%=correo%>"><br/>
            <p class="mt-5 mb-3 text-muted">Copyrigth © 2020 eFake Inc. All Rights Reserved.</p>
        </form>
    </body>
</html>
