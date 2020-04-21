<%-- 
    Document   : userList
    Created on : 28-mar-2020, 1:11:13
    Author     : PedroArenas
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.efake.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="com.efake.entity.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% //Session Control
    Usuario user = (Usuario) session.getAttribute("usuario");
    if (user != null && user.getEsAdmin() == 0) {// The user is logged in, but he's not an admin
        response.sendRedirect("/efake/");
    } else if (user == null) { //The user is not logged in
        response.sendRedirect("/efake/login.jsp");
    }
%>

<% //Load Attributes
    List<Usuario> userList = (List<Usuario>) request.getAttribute("userList");
    Integer numberOfPages = (Integer) request.getAttribute("numberOfPages");
    Integer currentPage = Integer.parseInt(request.getParameter("page"));
    String whichList = request.getParameter("list");

    String status = (String) session.getAttribute("status");
    session.removeAttribute("status"); 
%>

<% //Other Tools
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
%>
<!DOCTYPE html>
<html>

    <head>
        <title>EFake</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <!--Favicon-->
        <link rel="shortcut icon" href="/efake/img/favicon.png" type="image/png">
        <link rel="stylesheet" href="/efake/css/styles.css">
    </head>

    <body class="d-flex flex-column h-100">
        <%@include file="/components/navbar.jspf"%>
        <div class="container-fluid ">
            <div class="row">


                <main role="main" class="col-md-9  col-lg-10 px-4">
                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3">
                        <h1 class="h2">User List</h1>
                    </div>
                    <% if (status != null) {%>
                    <div class="alert alert-success" role="alert">
                        <i class="fas fa-check"></i> <%= status%>
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
                                    <td><%= (u.getUltimaEntrada() == null) ? '-' : dateFormatter.format(u.getUltimaEntrada())%></td>
                                    <td>
                                        <button type="button" class="bg-transparent border-0" data-toggle="modal"
                                                data-target="#deleteConfirmationModal" data-user="<%= u.getCorreo()%>"
                                                data-id="<%= u.getId()%>">
                                            <i class="fas fa-trash"></i>
                                        </button>
                                    </td>
                                    <td>
                                        <a href="/efake/AlterUser?id=<%= u.getId()%>">
                                            <i class="fas fa-edit"></i>
                                        </a>

                                    </td>
                                </tr>
                                <% }%>
                            </tbody>
                        </table>
                    </div>
                    <nav aria-label="...">
                        <ul class="pagination justify-content-center">
                            <% for (int i = 1; i <= numberOfPages; i++) {%>
                            <li class="page-item <%= (i == currentPage) ? "active" : ""%>">
                                <a class="page-link" href="/efake/ListUsers?list=<%=whichList%>&page=<%= i%>"><%= i%></a>
                            </li>
                            <%  }%>

                        </ul>
                    </nav>
                </main>
            </div>
        </div>
        <%@include file="/components/footer.jspf"%>

        <!-- Modals -->
        <div class="modal fade" id="deleteConfirmationModal" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <form method="POST" action="/efake/DeleteUser">
                    <input type="hidden" name="page" value="<%= currentPage%>">
                    <div class="modal-content">
                        <div class="modal-body">
                            Are you sure you want to delete <span id="modal-user"></span> account?
                            This action can't be undone.


                            <input id="modal-form-user" type="hidden" name="user">
                            <div class="form-group">
                                <label for="message-text" class="col-form-label">Please leave a message telling the user why
                                    you are deleting his account:</label>
                                <textarea class="form-control" id="message-text" name="emailBody"></textarea>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="border-0 background-transparent"
                                    data-dismiss="modal">Cancel</button>
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