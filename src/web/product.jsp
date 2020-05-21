<%-- 
    Document   : valoracion_producto
    Created on : 25-mar-2020, 13:17:12
    Author     : Carlos (Reviews & Ratings)
    Author     : Juan (Product view)
    Author     : Pedro (Refactor for reusing view in Administrator functionality)
--%>

<%@page import="com.efake.entity.Keywords"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.efake.entity.Usuario"%>
<%@page import="com.efake.entity.Valoracion"%>
<%@page import="com.efake.entity.Producto"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%  // @author Carlos
    List<Valoracion> listValoracion = (List<Valoracion>) request.getAttribute("listValoraciones");
    Usuario user = (Usuario) session.getAttribute("usuario");
    int valorado = (Integer) request.getAttribute("valorado");
    Map<Integer, Double> ratings = (Map<Integer, Double>) request.getAttribute("ratings");
    double mediaValoraciones = (Double) request.getAttribute("mediaValoraciones");
    int idProducto = Integer.parseInt(request.getParameter("idProducto"));
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    // @author Juan
    Producto p = (Producto) request.getAttribute("producto");
    String nombre = p.getNombre();
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Producto</title>
        <link rel="stylesheet" href="css/valoracionStyle.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    </head>
    <body>

        <%@include file="/components/navbar.jspf"%>
        <!-- @author Juan -->
        <div class="wrapper row my-5 ml-5">
            <div class="preview col-md-4">
                <div class="preview-pic tab-content">
                    <div class="tab-pane active" id="pic-1">
                        <img src="<%=p.getImagen()%>" width="400px" height="300px"/>
                    </div>
                </div>
            </div>
            <div class="details col-md-8">
                <h3 class="product-title"><%=p.getNombre()%></h3>
                <div class="row-md-4 ">
                    <% for (Keywords kw : p.getKeywordsList()) { %>
                    <span class="badge badge-dark">#<%= kw.getPalabra() %></span>
                    <% }%>
                </div>
                
                <h4>Current price: <span><%=p.getPrecio()%> &euro;</span></h4>
                <h5>Seller:
                    <span><%=p.getOwner().getNombre() + " " + p.getOwner().getApellidos()%></span>
                </h5>
                <p class="product-description"><%=p.getDescripcion()%> </p>
                <%  if (user != null && (user.equals(p.getOwner()) || user.getEsAdmin() == 1)) {%>
                <div class="action">
                    <a class ="btn btn-primary" type="submit" href="ProductoCardServlet?id=<%=p.getId()%>">Modify Product</a>
                    <a class ="btn btn-primary" type="submit" href="DeleteProduct?id=<%=p.getId()%>">Delete Product</a>
                </div>
                <% }%>
            </div>
        </div>

        <!-- @author Carlos -->
        <div class="row mb-3">
            <div class="col-4">
                <h2 class="titles">Ratings</h2>
                <div style="margin-left: 50px; margin-bottom: 10px;">
                    <strong> <%= mediaValoraciones%> </strong> stars <strong> <%= listValoracion.size()%> </strong> votes
                </div>
                <%  int aux = 4;
                    for (Map.Entry<Integer, Double> entry : ratings.entrySet()) {
                        Integer key = entry.getKey() + aux;
                        Double value = ratings.get(key);
                        aux = aux - 2;
                %>
                <div class="progress margen-barras">
                    <div class="progress-bar bg-color text-center" role="progressbar" aria-valuenow="<%= value%>"
                         aria-valuemin="0" aria-valuemax="100" style="width: <%= value%>%; color: black; overflow: visible;">
                        <%= key%> stars
                    </div>
                </div>
                <% } %>
                <br>
                <% if (user != null && user.getEsAdmin() == 0 && valorado == 1) { %>
                <div class="row">
                    <div class="col">
                        <button type="button" class="btn rounded btn-primary margen-boton" data-toggle="modal" data-target="#review">
                            Review
                        </button>
                    </div>
                </div>
                <% } %>
            </div>
            <div class="col-8">
                <h2>Customer Reviews</h2>
                <br>
                <% if (listValoracion.isEmpty()) { %>
                <p>
                    Be the first one rating this product!
                </p>
                <% } else { %>
                <% for (Valoracion v : listValoracion) {%>
                <div class="border border-primary rounded rounded-primary p-3 margen-comentarios">
                    <strong style="font-size: 20px; margin-right: 15px;"> <%= v.getCliente().getNombre() + " " + v.getCliente().getApellidos()%> </strong> 
                    <span> <%= dateFormatter.format(v.getFecha())%> </span> <br>
                    <p>
                        <% if (v.getPuntuacion() == 0) {
                                for (int i = 1; i <= 5; i++) {%>
                        <span class="comment-stars cero-stars">★</span>
                        <% }
                        } else {
                            for (int i = 1; i <= 5; i++) {
                                if (i <= v.getPuntuacion()) { %>
                        <span class="comment-stars filled-star">★</span>
                        <% } else { %>
                        <span class="comment-stars cero-stars">★</span>
                        <% }
                                }
                            }%>
                    </p>
                    <p>
                        <%= v.getComentario()%>
                    </p>
                </div>
                <% } %>
                <% } %>
            </div>
        </div>   
        <%@include file="/components/footer.jspf"%>

        <% if (user != null && user.getEsAdmin() == 0 && valorado == 1) {%>
        <div class="modal fade" id="review" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <form class="w-100" method="POST" action="/efake/doReview">
                    <input type="hidden" name="product" value="<%= idProducto%>">
                    <div class="modal-content">
                        <div class="modal-body">
                            <input id="modal-form-user" type="hidden" name="user">
                            <div class="form-group">
                                <label class="col-form-label">Rating</label>
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
                                <label class="col-form-label">Comments</label>
                                <textarea class="form-control" name="comment" required></textarea>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="border-0 background-transparent" data-dismiss="modal">Cancel</button>
                            <input type="submit" class="btn btn-primary" value="Send">
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <% }%>

        <!--Font Awesome-->
        <script src="https://kit.fontawesome.com/998261dc3d.js" crossorigin="anonymous"></script>
        <!--Bootstrap-->
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    </body>
</html>