<%@page import="com.efake.entity.Usuario"%>
<!DOCTYPE html>
<% 
    Usuario user = (Usuario) session.getAttribute("usuario");
    if(user != null){
        response.sendRedirect("index.jsp");
    }
%>
<%
    String status = (String) session.getAttribute("status");
    session.removeAttribute("status");
%>

<html lang="en"><head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login</title>

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
      }
    </style>
    
    
  </head>
  <body class="text-center">
    <form class="form-signin" action="AutenticarServlet" method="post">
    
        <img class="d-inline-block align-top" src="https://raw.githubusercontent.com/PedroArs99/EFake/master/img/logo.png" alt="Efake logo" width="72" height="30">
        <h1 class="h5 mb-3 font-weight-normal">Please sign in</h1><br/>
        <%if(status != null){%>
            <div class="alert alert-danger"><%=status%></div>
        <%}%>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" name="correo" class="form-control" placeholder="Email address" required="" autofocus="">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="" name="contrasena">
        
        <input type="submit" class="btn btn-lg btn-primary btn-block" value="Sign in"><br/>
        <a href="signup.jsp" type="submit">Don't you have an account?</a>
        <p class="mt-5 mb-3 text-muted">Copyright &copy; 2020 eFake Inc. All Rights Reserved.</p>
  </form>
</body></html>

