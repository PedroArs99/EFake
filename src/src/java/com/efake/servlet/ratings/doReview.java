package com.efake.servlet.ratings;

import com.efake.dao.ProductoFacade;
import com.efake.dao.UsuarioFacade;
import com.efake.dao.ValoracionFacade;
import com.efake.entity.Producto;
import com.efake.entity.Usuario;
import com.efake.entity.Valoracion;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author carlo
 */
@WebServlet(name = "doReview", urlPatterns = {"/doReview"})
public class doReview extends HttpServlet {
    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    ValoracionFacade valoracionFacade;
    @EJB
    ProductoFacade productoFacade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Session control
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        if(usuario == null){
            response.sendRedirect("/");
        }
        
        Integer rating = Integer.parseInt(request.getParameter("estrellas"));
        
        String comment = request.getParameter("comment");
        Date date = new Date();
        Integer idProducto = Integer.parseInt(request.getParameter("product"));
        Producto producto = productoFacade.find(idProducto);
        List<Valoracion> listaValoraciones = producto.getValoracionList();
        
        Valoracion review = new Valoracion();
        review.setCliente(usuario);
        review.setProductoValorado(producto);
        review.setPuntuacion(rating);
        review.setComentario(comment);
        review.setFecha(date);
        review.setHora(date);
        
        valoracionFacade.create(review);
        listaValoraciones.add(review);
        productoFacade.edit(producto);
        response.sendRedirect("/efake/ShowProduct?idProducto=" + idProducto);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
