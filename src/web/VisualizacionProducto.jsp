<%@page import="com.efake.entity.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    Producto p = (Producto) request.getAttribute("producto") ;
    String nombre  = p.getNombre();
%>

  <head>
      <title>EFake</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

        
            
    <style>


        img {
          max-width: 100%; }

        .preview {
          display: -webkit-box;
          display: -webkit-flex;
          display: -ms-flexbox;
          display: flex;
          -webkit-box-orient: vertical;
          -webkit-box-direction: normal;
          -webkit-flex-direction: column;
              -ms-flex-direction: column;
                  flex-direction: column; }
          @media screen and (max-width: 996px) {
            .preview {
              margin-bottom: 20px; } }

        .preview-pic {
          -webkit-box-flex: 1;
          -webkit-flex-grow: 1;
              -ms-flex-positive: 1;
                  flex-grow: 1; }

        .preview-thumbnail.nav-tabs {
          border: none;
          margin-top: 15px; }
          .preview-thumbnail.nav-tabs li {
            width: 18%;
            margin-right: 2.5%; }
            .preview-thumbnail.nav-tabs li img {
              max-width: 100%;
              display: block; }
            .preview-thumbnail.nav-tabs li a {
              padding: 0;
              margin: 0; }
            .preview-thumbnail.nav-tabs li:last-of-type {
              margin-right: 0; }

        .tab-content {
          overflow: hidden; }
          .tab-content img {
            width: 100%;
            -webkit-animation-name: opacity;
                    animation-name: opacity;
            -webkit-animation-duration: .3s;
                    animation-duration: .3s; }

        .card {
          margin-top: 50px;
          background: #eee;
          padding: 3em;
          line-height: 1.5em; }

        @media screen and (min-width: 997px) {
          .wrapper {
            display: -webkit-box;
            display: -webkit-flex;
            display: -ms-flexbox;
            display: flex; } }

        .details {
          display: -webkit-box;
          display: -webkit-flex;
          display: -ms-flexbox;
          display: flex;
          -webkit-box-orient: vertical;
          -webkit-box-direction: normal;
          -webkit-flex-direction: column;
              -ms-flex-direction: column;
                  flex-direction: column; }

        .colors {
          -webkit-box-flex: 1;
          -webkit-flex-grow: 1;
              -ms-flex-positive: 1;
                  flex-grow: 1; }

        .product-title, .price, .sizes, .colors {
          text-transform: UPPERCASE;
          font-weight: bold; }

        .checked, .price span {
          color: #ff9f1a; }

        .product-title, .rating, .product-description, .price, .vote, .sizes {
          margin-bottom: 15px; }

        .product-title {
          margin-top: 0; }

        .size {
          margin-right: 10px; }
          .size:first-of-type {
            margin-left: 40px; }


        .add-to-cart, .like {
          background: #0062cc;
          padding: 1.2em 1.5em;
          border: none;
          text-transform: UPPERCASE;
          font-weight: bold;
          color: #fff;
          -webkit-transition: background .3s ease;
                  transition: background .3s ease; }
          .add-to-cart:hover, .like:hover {
            background: #0062cc;
            color: #fff; }
          .modificar{
              background: #0062cc;
              color: #fff;
              padding: 0.3em 0.5em;
              margin-top: 15px;
              border: none;
          }
        .not-available {
          text-align: center;
          line-height: 2em; }
          .not-available:before {
            font-family: fontawesome;
            content: "\f00d";
            color: #fff; }

        .tooltip-inner {
          padding: 1.3em; }

        @-webkit-keyframes opacity {
          0% {
            opacity: 0;
            -webkit-transform: scale(3);
                    transform: scale(3); }
          100% {
            opacity: 1;
            -webkit-transform: scale(1);
                    transform: scale(1); } }

        @keyframes opacity {
          0% {
            opacity: 0;
            -webkit-transform: scale(3);
                    transform: scale(3); }
          100% {
            opacity: 1;
            -webkit-transform: scale(1);
                    transform: scale(1); } }
    </style>
  </head>

            
 
	
	<div class="container">
		<div class="card">
			<div class="container-fliud">
				<div class="wrapper row">
					<div class="preview col-md-6">
						
						<div class="preview-pic tab-content">
						  <div class="tab-pane active" id="pic-1"><img src=<%=p.getImagen()%> /></div>
						 
						</div>
						
						
					</div>
					<div class="details col-md-6">
                                            <h3 class="product-title"><%=p.getNombre()%></h3>
						<p class="product-description"><%=p.getDescripcion()%> </p>
						<h4 class="price">current price: <span><%=p.getPrecio()%></span></h4>
						<h5 class="sizes">Vendedor:
                                                    
                                                    <span class="size" data-toggle="tooltip" title="user"><%=p.getOwner().getNombre() + " " + p.getOwner().getApellidos()%></span>

						</h5>
						<div class="action">
							<button class="add-to-cart btn btn-default" type="button">Buy</button>
							<button class="like btn btn-default" type="button"><span class="fa fa-heart">LIKE</span></button>
						</div>
                                                <div class="action">
                                                        <a class ="modificar" type="submit" href="ProductoCardServlet?id=<%=p.getId()%>">Modificar producto</a>
                                                </div>
					</div>
				</div>
			</div>
		</div>
	</div>
  </body>
 </html>