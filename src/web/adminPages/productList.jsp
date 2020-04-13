<%-- 
    Document   : productList
    Created on : 11-abr-2020, 1:42:01
    Author     : PedroArenas
--%>
<%@page import="com.efake.entity.Subcategoria"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.efake.entity.Producto"%>
<%@page import="com.efake.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="com.efake.entity.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% //Check Login
    Usuario user = (Usuario) session.getAttribute("usuario");
    if (user != null && user.getEsAdmin() == 0) {// The user is logged in, but he's not an admin
        response.sendRedirect("/efake/");
    } else if (user == null) { //The user is not logged in
        response.sendRedirect("/efake/login.jsp");
    }
%>

<% //Load Attributes
    List<Producto> productList = (List<Producto>) request.getAttribute("productList");
    List<Categoria> categoryList = (List<Categoria>) request.getAttribute("categoryList");
    Integer numberOfPages = (Integer) request.getAttribute("numberOfPages");
    Integer currentPage = Integer.parseInt(request.getParameter("page"));

    String status = (String) session.getAttribute("status");
    session.removeAttribute("status");
%>

<% //Other tools
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
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
                        <h1 class="h2">Product List</h1>
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
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th>Date</th>
                                    <th>Category</th>
                                    <th>Seller</th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Producto p : productList) {%>
                                <tr>
                                    <td><%= p.getNombre()%></td>
                                    <td><%= p.getPrecio()%></td>
                                    <td><%= dateFormatter.format(p.getFecha())%></td>
                                    <td><%= p.getCategoria().getNombre()%></td>
                                    <td><%= p.getOwner().getCorreo()%></td>
                                    <td>
                                        <a href="/efake/ShowProduct?idProducto=<%= p.getId()%>">
                                            <i class="fas fa-ellipsis-h"></i>
                                        </a>

                                    </td>
                                    <td>
                                        <button type="button" class="bg-transparent border-0" data-toggle="modal"
                                                data-target="#deleteConfirmationModal"
                                                data-id="<%= p.getId()%>">
                                            <i class="fas fa-trash"></i>
                                        </button>
                                    </td>
                                    <td>
                                        <button type="button" class="bg-transparent border-0" data-toggle="modal"
                                                data-target="#alterProductModal"
                                                data-id="<%= p.getId()%>"
                                                data-name="<%= p.getNombre()%>"
                                                data-description="<%= p.getDescripcion()%>"
                                                data-price="<%= p.getPrecio()%>"
                                                data-image="<%= p.getImagen()%>"
                                                data-date="<%= p.getFecha()%>"
                                                data-category="<%= p.getCategoria().getId()%>"
                                                data-owner="<%= p.getOwner().getCorreo()%>">
                                            <i class="fas fa-edit"></i>
                                        </button>
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
                                <a class="page-link" href="/efake/ListAdminProducts?&page=<%= i%>"><%= i%></a>
                            </li>
                            <%  }%>

                        </ul>
                    </nav>
                </main>
            </div>
        </div>
        <%@include file="/components/footer.jspf"%>

        <!-- Modals -->
        <div class="modal fade" id="alterProductModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                <form class="w-100" method="POST" action="/efake/EditProduct">
                    <div class="modal-content">
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-6">
                                <input id="modal-form-product" type="hidden" name="id">
                                <input type="hidden" name="page" value="<%= currentPage %>">
                                <div class="form-group">
                                    <label for="name">Name</label>
                                    <input id="modal-form-name" class="form-control" type="text" name="name">
                                </div>
                                <div class="form-group">
                                    <label for="description">Description</label>
                                    <textarea id="modal-form-description" class="form-control" name="description"></textarea>
                                </div>
                                <div class="form-group">
                                    <label for="price">Price</label>
                                    <input id="modal-form-price" class="form-control" type="number" name="price" step="0.01">
                                </div>
                                
                                <div class="form-group">
                                    <label for="category">Category</label>
                                    <select id="category-selector" class="form-control">
                                        <% for (Categoria c : categoryList) {%>
                                        <option value="<%= c.getId()%>"> <%= c.getNombre()%></option>
                                        <% }%>
                                    </select>
                                </div>
                                
                            </div>
                            <div class="col-md-6">
                                <img id="modal-image"src="" class="img-thumbnail">
                                <div class="form-group">
                                    <label for="image">Image</label>
                                    <input id="modal-form-image" class="form-control" type="text" name="image">
                                </div>
                            </div>
                            </div>
                            
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="border-0 background-transparent"
                                    data-dismiss="modal">Cancel</button>
                            <input type="submit" class="btn btn-primary" value="Save">
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="modal fade" id="deleteConfirmationModal" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <form method="POST" action="/efake/DeleteProduct">
                    <div class="modal-content">
                        <div class="modal-body">
                            Are you sure you want to delete this product?
                            This action can't be undone.
                            <input id="modal-form-product" type="hidden" name="id">
                            <input type="hidden" name="page" value="<%= currentPage%>"
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
        <script src="${pageContext.request.contextPath}/js/productListModals.js"></script>
    </body>

</html>