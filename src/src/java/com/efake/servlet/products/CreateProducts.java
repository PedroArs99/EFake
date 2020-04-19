package com.efake.servlet.products;

import com.efake.dao.KeywordsFacade;
import com.efake.dao.ProductoFacade;
import com.efake.dao.UsuarioFacade;
import com.efake.entity.Categoria;
import com.efake.entity.Keywords;
import com.efake.entity.Producto;
import com.efake.entity.Subcategoria;
import com.efake.entity.Usuario;
import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Juan
 */
@WebServlet(name = "CreateProductsServlet", urlPatterns = {"/CreateProductsServlet"})
public class CreateProducts extends HttpServlet {

    @EJB
    ProductoFacade productoFacade;
    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    KeywordsFacade keywordsFacade;

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
        //Session Control 
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("usuario");
        if (user == null) {
            response.sendRedirect("login.jsp");
        }

        //Load form attributes
        String nombre = request.getParameter("textNombre");
        String descripcion = request.getParameter("descripcion");
        Double precio = Double.parseDouble(request.getParameter("textPrecio"));
        String imagen = request.getParameter("textImagen");
        String keywords = request.getParameter("keywords");
        Date now = new Date();
        Integer categoria = Integer.parseInt(request.getParameter("Categoria"));
        Integer subcategoria = Integer.parseInt(request.getParameter("Subcategoria"));

        //Create Product entity & set Attributtes
        Producto p = new Producto();

        p.setNombre(nombre);
        p.setDescripcion(descripcion);
        p.setPrecio(precio);
        p.setFecha(now);
        p.setImagen(imagen);
        Categoria c = new Categoria(categoria);
        p.setCategoria(c);
        if (subcategoria != 0) {
            Subcategoria s = new Subcategoria(subcategoria);
            p.setSubcategoria(s);
        }
        p.setNombre(nombre);
        p.setOwner(user);

        productoFacade.create(p); //It's mandatory create product before managing keywords since we need a id to manage relationships
        //Manage keywords, relations must be added within keyword entity
        StringTokenizer st = new StringTokenizer(keywords, ",");
        while (st.hasMoreTokens()) {
            Keywords k = keywordsFacade.findOrCreate(st.nextToken());
            
            p.getKeywordsList().add(k);
            k.getProductoList().add(p);
            
            keywordsFacade.edit(k);
        }

        //Update product on db & exit
        productoFacade.edit(p);
        int idProducto = p.getId();
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
