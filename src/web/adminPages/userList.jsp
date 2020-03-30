<%-- 
    Document   : userList
    Created on : 28-mar-2020, 1:11:13
    Author     : PedroArenas
--%>
<%@page import="com.efake.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="com.efake.entity.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Usuario> userList = (List<Usuario>) request.getAttribute("userList");
    String status = (String) session.getAttribute("status");
    session.removeAttribute("status");
%>
<!DOCTYPE html>
<html>

    <head>
        <title>EFake</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    </head>

    <body class="d-flex flex-column h-100">
        <%@include file="/components/navbar.jspf"%>
        <div class="container-fluid ">
            <div class="row">
                <nav class="col-md-2 d-none d-md-block bg-transparent sidebar border-right border-thin">
                    <div class="sidebar-sticky">
                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <a class="nav-link active" href="#">
                                    <span data-feather="home"></span>
                                    Dashboard <span class="sr-only">(current)</span>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    <span data-feather="file"></span>
                                    Orders
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    <span data-feather="shopping-cart"></span>
                                    Products
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    <span data-feather="users"></span>
                                    Customers
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    <span data-feather="bar-chart-2"></span>
                                    Reports
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    <span data-feather="layers"></span>
                                    Integrations
                                </a>
                            </li>
                        </ul>

                        <h6
                            class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                            <span>Saved reports</span>
                            <a class="d-flex align-items-center text-muted" href="#" aria-label="Add a new report">
                                <span data-feather="plus-circle"></span>
                            </a>
                        </h6>
                        <ul class="nav flex-column mb-2">
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    <span data-feather="file-text"></span>
                                    Current month
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    <span data-feather="file-text"></span>
                                    Last quarter
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    <span data-feather="file-text"></span>
                                    Social engagement
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    <span data-feather="file-text"></span>
                                    Year-end sale
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>

                <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3">
                        <h1 class="h2">User List</h1>
                    </div>
                    <% if (status != null) {%>
                        <div class="alert alert-success" role="alert">
                            <i class="fas fa-check"></i> <%= status %>
                        </div>
                        <% } %>
                    <div class="table-responsive">
                        <table class="table table-striped table-sm">
                            <thead>
                                <tr>
                                    <th>Email</th>
                                    <th>Name</th>
                                    <th>Surname</th>
                                    <th>Age</th>
                                    <th>Phone Number</th>
                                    <th>Last Login</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Usuario u : userList) {%>
                                <tr>
                                    <td><%= u.getCorreo()%></td>
                                    <td><%= u.getNombre()%></td>
                                    <td><%= u.getApellidos()%></td>
                                    <td><%= u.getEdad()%></td>
                                    <td><%= u.getTelefono()%></td>
                                    <td><%= u.getUltimaEntrada()%></td>
                                    <td>
                                        <a href="#">
                                            <i class="fas fa-edit"></i>
                                        </a>
                                            
                                    </td>
                                    <td>
                                        <button type="button" class="bg-transparent border-0" 
                                                data-toggle="modal" 
                                                data-target="#deleteConfirmationModal"
                                                data-user="<%= u.getCorreo() %>>"
                                                data-id="<%= u.getId()%>">
                                            <i class="fas fa-trash"></i>
                                        </button>
                                    </td>
                                </tr>
                                <% }%>
                            </tbody>
                        </table>
                    </div>
                </main>
            </div>
        </div>
        <%@include file="/components/footer.jspf"%>

        <!-- Modals -->
        <div class="modal fade" id="deleteConfirmationModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <form method="POST" action="/efake/DeleteUser">
                    <div class="modal-content">
                        <div class="modal-body">
                            Are you sure you want to delete <span id="modal-user"></span> account?
                            This action can't be undone.


                            <input id="modal-form-user" type="hidden" name="user">
                            <div class="form-group">
                                <label for="message-text" class="col-form-label">Please leave a message telling the user why you are deleting his account:</label>
                                <textarea class="form-control" id="message-text" name="emailBody"></textarea>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="border-0 background-transparent" data-dismiss="modal">Cancel</button>
                            <input type="submit" class="btn btn-primary" value="Delete">
                        </div>
                    </div>
                </form>
            </div>
        </div>

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
        <script src="${pageContext.request.contextPath}/js/userListModals.js"></script>
    </body>

</html>