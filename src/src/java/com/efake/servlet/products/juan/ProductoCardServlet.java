/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.servlet.products.juan;

import com.efake.dao.CategoriaFacade;
import com.efake.dao.ProductoFacade;
import com.efake.dao.SubcategoriaFacade;
import com.efake.entity.Categoria;
import com.efake.entity.Producto;
import com.efake.entity.Subcategoria;
import com.efake.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JuMed
 */
@WebServlet(name = "ProductoCardServlet", urlPatterns = {"/ProductoCardServlet"})
public class ProductoCardServlet extends HttpServlet {

    @EJB
    ProductoFacade productofacade;
    @EJB
    CategoriaFacade categoriaFacade;
    @EJB
    SubcategoriaFacade subcategoriaFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Session Control
        HttpSession session = request.getSession();
        Usuario user = (Usuario) request.getSession();
        
        if(user == null){
            response.sendRedirect("/");
        }
        
        
        List<Categoria> categorias = categoriaFacade.findAll();

        List<Subcategoria> subcategorias = subcategoriaFacade.findAll();

        request.setAttribute("categoriaList", categorias);
        request.setAttribute("subcategoriasList", subcategorias);
        int id = Integer.parseInt(request.getParameter("id"));
        Producto p = productofacade.find(id);
        request.setAttribute("producto", p);

        RequestDispatcher rd = request.getRequestDispatcher("ModificacionProductos.jsp");
        rd.forward(request, response);

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
