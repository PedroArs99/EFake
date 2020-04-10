<%-- 
    Document   : adminDashboard
    Created on : 25-mar-2020, 16:24:17
    Author     : PedroArenas
--%>

<%@page import="com.efake.entity.Usuario"%>
<% 
    Usuario user = (Usuario) session.getAttribute("usuario");
    if(user != null && user.getEsAdmin() == 0){// The user is logged in, but he's not an admin
        response.sendRedirect("/efake/");
    }else if (user == null){ //The user is not logged in
        response.sendRedirect("/efake/login.jsp");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Efake</title>
    <!--Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>

<body class="d-flex flex-column ">
    <%@include file="/components/navbar.jspf"%>
    <div class="container-md mt-3">
        <h5 class="text-left py-3">User Management</h5>
        <div class="row p-3 ">
            <div class="col-md-4">
                <div class="card text-center bg-transparent border-0">
                    <i class="fas fa-users fa-7x"></i>
                    <div class="card-body">
                        <a href="${pageContext.request.contextPath}/ListUsers?list=all&page=1" class="btn btn-primary">User List</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center bg-transparent border-0">
                    <i class="fas fa-user-clock fa-7x"></i>
                    <div class="card-body">
                        <a href="#" class="btn btn-primary">Inactive Users</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center bg-transparent border-0">
                    <i class="fas fa-user-slash fa-7x"></i>
                    <div class="card-body">
                        <a href="#" class="btn btn-primary">Reported Users</a>
                    </div>
                </div>
            </div>
        </div>

        <h5 class="text-left py-3">Product Management</h5>
            <div class="row p-3 ">
                <div class="col-md-4">
                    <div class="card text-center bg-transparent border-0">
                        <i class="fas fa-boxes fa-7x"></i>
                        <div class="card-body">
                            <a href="#" class="btn btn-primary">Product List</a>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-4">
                    <div class="card text-center bg-transparent border-0">
                        <i class="fas fa-exclamation-triangle fa-7x"></i>
                        <div class="card-body">
                            <a href="#" class="btn btn-primary">Reported Products</a>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-4">
                    <div class="card text-center bg-transparent border-0">
                        <i class="fas fa-comment-slash fa-7x"></i>
                        <div class="card-body">
                            <a href="#" class="btn btn-primary">Reported Comments</a>
                        </div>
                    </div>
                </div>
            </div>     
    </div>
    <%@include file="/components/footer.jspf"%>

    <!--Font Awesome-->
    <script src="https://kit.fontawesome.com/998261dc3d.js" crossorigin="anonymous"></script>
    <!--Bootstrap-->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
    <body>

</html