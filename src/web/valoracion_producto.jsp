<%-- 
    Document   : valoracion_producto
    Created on : 25-mar-2020, 13:17:12
    Author     : carlo
--%>

<%@page import="com.efake.entity.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="css/valoracionStyle.css">
    <title>Producto</title>
</head>
<body class="d-flex flex-column h-100">
    
    <%@include file="/components/navbar.jspf"%>
     
    <div style="padding: 40vh"></div>
    
    <div class="row">
        <div class="col">
            <h2 class="valoracion-titulo">Ratings</h2>
            <div>
                <div>
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
                </div>
            </div>
            <br>
            <div class="progress" style="margin-left: 50px; margin-right: 1000px; margin-bottom: 10px;">
                <div class="progress-bar" role="progressbar" aria-valuenow="70"
                aria-valuemin="0" aria-valuemax="100" style="width:70%">
                    5 stars
                </div>
            </div>
                <div class="progress" style="margin-left: 50px; margin-right: 1000px; margin-bottom: 10px;">
                    <div class="progress-bar" role="progressbar" aria-valuenow="70"
                    aria-valuemin="0" aria-valuemax="100" style="width:70%">
                        4 stars
                    </div>
                </div>
                <div class="progress" style="margin-left: 50px; margin-right: 1000px; margin-bottom: 10px;">
                    <div class="progress-bar" role="progressbar" aria-valuenow="70"
                    aria-valuemin="0" aria-valuemax="100" style="width:70%">
                        3 stars
                    </div>
                </div>
                <div class="progress" style="margin-left: 50px; margin-right: 1000px; margin-bottom: 10px;">
                    <div class="progress-bar" role="progressbar" aria-valuenow="70"
                    aria-valuemin="0" aria-valuemax="100" style="width:70%">
                        2 stars
                    </div>
                </div>
                <div class="progress" style="margin-left: 50px; margin-right: 1000px; margin-bottom: 10px;">
                    <div class="progress-bar" role="progressbar" aria-valuenow="70"
                    aria-valuemin="0" aria-valuemax="100" style="width:70%">
                        1 star
                    </div>
                </div>
            <br>
            <div class="row">
                <div class="col">
                    <button type="button" class="btn rounded btn-primary margen" data-toggle="modal" data-target="#review" style="margin-bottom: 26px;">
                        Review
                    </button>
                </div>
            </div>
        </div>
        <div class="col-7">
            <h2 class="valoracion-titulo">Customer Reviews</h2>
            <br>
            <p class="border rounded border-secondary margen p-3">
                Esto es un ejemplo de comentario...
            </p>
        </div>
    </div>

    
    
    <%@include file="/components/footer.jspf"%>

    <div class="modal fade" id="review" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <form method="POST" action="/efake/doReview">
                <div class="modal-content">
                    <div class="modal-body">
                        <h4>Rate</h4>


                        <input id="modal-form-user" type="hidden" name="user">
                        <form class='text-left' action='POST' method=''>
                            <p class='clasificacion alert'>
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
                            <p>
                                <div class="form-group">
                                    <h4>Comment</h4>
                                    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                                  </div>
                            </p>
                        </form>



                    </div>
                    <div class="modal-footer">
                        <button type="button" class="border-0 background-transparent" data-dismiss="modal">Cancel</button>
                        <input type="submit" class="btn btn-primary" value="Send">
                    </div>
                </div>
            </form>
        </div>
    </div>
    
    <!--Font Awesome-->
    <script src="https://kit.fontawesome.com/998261dc3d.js" crossorigin="anonymous"></script>
    <!--Bootstrap-->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    
</body>
</html>
