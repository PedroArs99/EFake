<%-- 
    Document   : signup
    Created on : 25-mar-2020, 12:54:55
    Author     : laura
    Author     : Pedro Arenas (Refactor for reusing code in Administrator functionality)
--%>

<%@page import="com.efake.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    String goTo = "SignupServlet", nombre = "", apellidos = "", email = "", movil = "", edad = "";

    String status = (String) session.getAttribute("status");
    session.removeAttribute("status");

    if (user != null) {
        if(user.getEsAdmin() == 1){
            user = (Usuario) request.getAttribute("user");
        }
        goTo = "ModificarPerfil?correoAntiguo=" + user.getCorreo() + "";
        edad = user.getEdad() + "";
        nombre = user.getNombre();
        email = user.getCorreo();
        apellidos = user.getApellidos();
        if (user.getTelefono() == null) {
            movil = "";
        } else {
            movil = user.getTelefono();
        }
    }
%>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Sign Up</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="/efake/css/styles.css">
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
            
            a{
                color: #0064d3;
            }
            
            a:hover{
                text-decoration: underline;
            }
        </style>
        
        
    </head>
    <body class="text-center">
        <form class="form-signin" action="<%=goTo%>" method="post">
            <img class="d-inline-block align-top" src="https://raw.githubusercontent.com/PedroArs99/EFake/master/img/logo.png" alt="" width="72" height="30"><br/>

            <%if (user == null) {%>
            <h1 class="h3 mb-3 font-weight-normal">Please sign up</h1>
            <%if (status != null) {%>
            <div class="alert alert-danger"><%=status%></div>
            <%}%>
            <input type="text" name="nombre" id="defaultRegisterFormFirstName" class="form-control" placeholder="First name" autofocus="" required>

            <input type="text" name="apellidos" id="defaultRegisterFormLastName" class="form-control" placeholder="Last name" required><br/>

            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="email" name="correo" id="inputEmail" class="form-control" placeholder="Email address" required="">

            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" name="contrasena" id="inputPassword" class="form-control" placeholder="Password" required=""><br/>
            <input type="date" name="edad" class="form-control" id="inputEdad" placeholder="Birth date" required=""><br/>
            <input type="text" name="telefono" id="defaultRegisterPhonePassword" class="form-control" placeholder="Phone number" aria-describedby="defaultRegisterFormPhoneHelpBlock"><br/>

            <button class="btn btn-lg btn-primary btn-block" type="submit" >Sign up</button><br/>
            <a href="login.jsp" type="submit">I already have an account</a><br/><br/>

            <p>By clicking
                <em>Sign up</em> you agree to our
                terms of service
            </p>
            <%} else {%>
            <h1 class="h3 mb-3 font-weight-normal">Your profile</h1>
            <% if (status != null) {%>
            <div class="alert alert-danger"><%=status%></div>
            <%}%>
            <input type="text" name="nombre" id="defaultRegisterFormFirstName" class="form-control" value="<%=nombre%>" autofocus="">

            <input type="text" name="apellidos" id="defaultRegisterFormLastName" class="form-control" value="<%=apellidos%>"><br/>

            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="email" name="correo" id="inputEmail" class="form-control" value="<%=email%>" required="">
            <br/>
            <input type="text" class="form-control" value="<%=edad%>" readonly onmousedown="return false;"><br/>
            <input type="text" name="telefono" id="defaultRegisterPhonePassword" class="form-control" value="<%=movil%>" aria-describedby="defaultRegisterFormPhoneHelpBlock"><br/>
            <a href="changePassword.jsp?correo=<%=user.getCorreo()%>" type="submit">Do you want to change your password?</a><br/><br/>
            <button class="btn btn-lg btn-primary btn-block" type="submit" >Save changes</button><br/>
            <%}%>

            <p class="mt-5 mb-3 text-muted">Copyrigth © 2020 eFake Inc. All Rights Reserved.</p>
        </form>
    </body>
</html>
