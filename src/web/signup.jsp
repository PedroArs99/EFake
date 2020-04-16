<%-- 
    Document   : signup
    Created on : 25-mar-2020, 12:54:55
    Author     : laura
--%>

<%@page import="com.efake.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    String goTo = "SignupServlet", nombre = "", apellidos = "", email = "", movil = "", edad = "";
    
    String status = (String) session.getAttribute("status");
    session.removeAttribute("status");
    
    if(user!=null){
        goTo = "ModificarPerfil?correoAntiguo="+user.getCorreo()+"";
        edad = user.getEdad()+"";
        nombre = user.getNombre();
        email = user.getCorreo();
        apellidos = user.getApellidos();     
        if(user.getTelefono() == ("null")){            
            movil = "";
        }else{
            movil = user.getTelefono();
        }        
    }
%>

<html lang="en"><head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.6">
    <title>Sign Up</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.4/examples/sign-in/">

    <!-- Bootstrap core CSS -->
    <link href="/docs/4.4/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <!-- Favicons -->
    <link rel="apple-touch-icon" href="/docs/4.4/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
    <link rel="icon" href="/docs/4.4/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
    <link rel="icon" href="/docs/4.4/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
    <link rel="manifest" href="/docs/4.4/assets/img/favicons/manifest.json">
    <link rel="mask-icon" href="/docs/4.4/assets/img/favicons/safari-pinned-tab.svg" color="#563d7c">
    <link rel="icon" href="/docs/4.4/assets/img/favicons/favicon.ico">
    <meta name="msapplication-config" content="/docs/4.4/assets/img/favicons/browserconfig.xml">
    <meta name="theme-color" content="#563d7c">


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
    <link href="ejemploLOGIN.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  </head>
  <body class="text-center">
 
  <form class="form-signin" action="<%=goTo%>" method="post">
  <img class="d-inline-block align-top" src="https://raw.githubusercontent.com/PedroArs99/EFake/master/img/logo.png" alt="" width="72" height="30"><br/>
  
  <%if(user==null){%>
    <h1 class="h3 mb-3 font-weight-normal">Please sign up</h1>
    <%if(status != null){%>
            <div class="alert alert-danger"><%=status%></div>
    <%}%>
    <input type="text" name="nombre" id="defaultRegisterFormFirstName" class="form-control" placeholder="First name" autofocus="">

    <input type="text" name="apellidos" id="defaultRegisterFormLastName" class="form-control" placeholder="Last name"><br/>

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
        <a href="#" target="_blank">terms of service</a>.
    </p>
  <%}else{%>
    <h1 class="h3 mb-3 font-weight-normal">Your profile</h1>
    <%if(status.equals("Todo correcto")){%>
    <%}else if(status != null){%>
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
