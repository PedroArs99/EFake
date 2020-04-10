/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.servlet.productos.juan;

import com.efake.dao.ProductoFacade;
import com.efake.dao.UsuarioFacade;
import com.efake.entity.Categoria;
import com.efake.entity.Producto;
import com.efake.entity.Subcategoria;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JuMed
 */
@WebServlet(name = "ModificarProductoServlet", urlPatterns = {"/ModificarProductoServlet"})
public class ModificarProductoServlet extends HttpServlet {

@EJB
ProductoFacade productoFacade;
@EJB
UsuarioFacade usuarioFacade;
    
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
        int id = Integer.parseInt(request.getParameter("id"));
        Producto p = productoFacade.find(id);
        
        String nombre = request.getParameter("textNombre");
        String descripcion = request.getParameter("descripcion");
        Double precio =Double.parseDouble(request.getParameter("textPrecio"));
        String imagen = request.getParameter("textImagen");
        String keywords = request.getParameter("textKeywords");
        Integer categoria =Integer.parseInt(request.getParameter("Categoria"));
        Integer subcategoria =Integer.parseInt(request.getParameter("Subcategoria"));
        
        Categoria c = new Categoria(categoria);
        Subcategoria s = new Subcategoria(subcategoria);
        
        p.setNombre(nombre);
        p.setDescripcion(descripcion);
        p.setPrecio(precio);
        p.setImagen(imagen);
        p.setCategoria(c);
        p.setSubcategoria(s);
        productoFacade.edit(p);
        
        request.setAttribute("producto", p);
        RequestDispatcher rd = request.getRequestDispatcher("VisualizacionProducto.jsp");
        rd.forward(request, response);
        response.setContentType("text/html;charset=UTF-8");
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
