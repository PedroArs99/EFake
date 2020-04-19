<%@page import="com.efake.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="com.efake.entity.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Usuario user = (Usuario) session.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>

    <head>
        <title>EFake</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!--Favicon-->
        <link rel="shortcut icon" href="/efake/img/favicon.png" type="image/png">
        <!--Bootstrap-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <!--Google Fonts-->
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@1,8&display=swap" rel="stylesheet">
        <!--AOS-->
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
        <!--Custom Sytles-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    </head>

    <body class="d-flex flex-column h-100">
        <%@include file="/components/navbar.jspf"%>
        <div class="jumbotron bg-transparent">
            <h1 class="display-3 text-center">Welcome to the newest product social media!</h1>
        </div>
        <div class="px-3 py-5">
            <h1 data-aos="fade-up" data-aos-duration="1000">
                Most Rated Products <i class="fab fa-free-code-camp"></i>
            </h1>
            <div id="mostRatedProducts" class="d-flex">
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
        <!--AOS-->
        <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
        <script>
            AOS.init();
        </script>
        <!-- Own Scripts -->
        <script src="js/mostRatedProducts.js"></script>
    </body>

</html>