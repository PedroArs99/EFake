<%-- 
    Document   : adminDashboard
    Created on : 25-mar-2020, 16:24:17
    Author     : PedroArenas
--%>

<%@page import="com.efake.entity.Usuario"%>
<% //Session Control
    Usuario user = (Usuario) session.getAttribute("usuario");
    if (user != null && user.getEsAdmin() == 0) {// The user is logged in, but he's not an admin
        response.sendRedirect("/efake/");
    } else if (user == null) { //The user is not logged in
        response.sendRedirect("/efake/login.jsp");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Efake | Dashboard</title>
        <!--Bootstrap -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <!-- Own Css -->
        <link rel="stylesheet" href="/efake/css/styles.css">
        <!--Favicon-->
        <link rel="shortcut icon" href="/efake/img/favicon.png" type="image/png">
    </head>

    <body>
        <%@include file="/components/navbar.jspf"%>
        <div class="container-fluid ">
            <div class="row">
                <!-- Left Nav -->
                <nav class="col-md-2 d-none d-md-block bg-transparent sidebar border-right border-thin">
                    <div class="sidebar-sticky">
                        <h6 
                            class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                                Dashboard
                        </h6>
                        <ul class="nav flex-column">
                            <li class="nav-item mb-2">
                                <a href="/efake/ListUsers?list=all&page=1" class="nav-link btn btn-primary text-light">
                                    <i class="fas fa-users"></i> User List
                                </a>
                            </li>
                            <li class="nav-item mb-2">
                                <a href="/efake/ListAdminProducts?page=1" class="nav-link btn btn-primary text-light">
                                    <i class="fas fa-boxes"></i> Product List
                                </a>
                            </li>
                        </ul>
                        <h6 
                            class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                                Stats
                        </h6>
                        <ul class="nav flex-column">
                            <li class="nav-item mb-2">
                                <button id="global-stats" class="nav-link btn btn-primary text-light w-100">
                                    <i class="fas fa-chart-bar"></i> Global Stats
                                </button>
                            </li>
                            <li class="nav-item mb-2">
                                <button id="today-stats" class="nav-link btn btn-primary text-light w-100">
                                    <i class="fas fa-clock"></i> Today Stats
                                </button>
                            </li>
                        </ul>
                    </div>
                </nav>
                <!-- Stats -->
                <div class="col-md-10">
                    <canvas id="myChart" class="my-5"></canvas>
                </div>

            </div>
        </div>
        <%@include file="/components/footer.jspf"%>


        <!-- Chart Js -->
        <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.3/dist/Chart.min.js"></script>
        <script src="/efake/js/stats.js"></script>
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