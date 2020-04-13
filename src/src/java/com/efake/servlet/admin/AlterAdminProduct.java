package com.efake.servlet.admin;

import com.efake.dao.CategoriaFacade;
import com.efake.dao.ProductoFacade;
import com.efake.entity.Producto;
import com.efake.entity.Usuario;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PedroArenas
 */
@WebServlet(name = "AlterUser", urlPatterns = {"/EditProduct"})
public class AlterAdminProduct extends HttpServlet {
    
    @EJB
    ProductoFacade productFacade;
    @EJB
    CategoriaFacade categoryFacade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Check if the user is logged in as admin
        HttpSession session = request.getSession();
        Usuario admin = (Usuario) session.getAttribute("usuario");
        if (admin != null && admin.getEsAdmin() == 0) {// The user is logged in, but he's not an admin
            response.sendRedirect("/efake/");
        } else if (admin == null) { //The user is not logged in
            response.sendRedirect("/efake/login.jsp");
        }
        
        //Load Attributtes from the form
        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Double price = Double.parseDouble(request.getParameter("price"));
        String image = request.getParameter("image");
        Integer category = Integer.parseInt(request.getParameter("category"));
        Integer page = Integer.parseInt(request.getParameter("page"));
       
        
        //Alter User
        Producto alteredUser = productFacade.find(id);
        alteredUser.setNombre(name);
        alteredUser.setDescripcion(description);
        alteredUser.setPrecio(price);
        alteredUser.setImagen(image);
        alteredUser.setCategoria(categoryFacade.find(category));
        productFacade.edit(alteredUser);
        
        System.out.println("passed");
        //Save status & redirect
        session.setAttribute("status", "Product Edited");
        response.sendRedirect("/efake/ListAdminProducts?page="+page);
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
