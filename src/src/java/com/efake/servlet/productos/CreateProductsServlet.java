/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.servlet.productos;

import com.efake.dao.ProductoFacade;
import com.efake.entity.Categoria;
import com.efake.entity.Producto;
import com.efake.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JuMed
 */
@WebServlet(name = "CreateProductsServlet", urlPatterns = {"/CreateProductsServlet"})
public class CreateProductsServlet extends HttpServlet {
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nombre = request.getParameter("textNombre");
        String descripcion = request.getParameter("textDescripcion");
        Double precio =Double.parseDouble(request.getParameter("textPrecio")) ;
        String imagen = request.getParameter("textImagen");
        String keywords = request.getParameter("textKeywords");
        Date now = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date fecha = now;
        Integer categoria =Integer.parseInt(request.getParameter("numberCategoria")) ;
        String subcategoria = request.getParameter("numberSubcategoria");
        String owner = request.getParameter("textowner");
        
        Producto p = new Producto();
        Categoria c = new Categoria(categoria);
        Usuario u = new Usuario("chelseydietrich@efake.com");
        p.setNombre(nombre);
        p.setDescripcion(descripcion);
        p.setPrecio(precio);
        p.setFecha(fecha);
        p.setKeywords(keywords);
        p.setCategoria(c);
        p.setNombre(nombre);
        p.setOwner(u);
        productoFacade.create(p);
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Producto creado con éxito</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateProductsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
