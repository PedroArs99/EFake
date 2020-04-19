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
import java.util.ArrayList;
import java.util.List;
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
 * @author JuMed
 */
@WebServlet(name = "ModificarProductoServlet", urlPatterns = {"/ModificarProductoServlet"})
public class ModificarProducto extends HttpServlet {

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
            response.sendRedirect("/efake/");
        }

        // Get form fields
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("textNombre");
        String descripcion = request.getParameter("descripcion");
        Double precio = Double.parseDouble(request.getParameter("textPrecio"));
        String imagen = request.getParameter("textImagen");
        String keywords = request.getParameter("keywords");

        Integer categoria = Integer.parseInt(request.getParameter("Categoria"));
        Integer subcategoria = Integer.parseInt(request.getParameter("Subcategoria"));

        //Get product from DB
        Producto p = productoFacade.find(id);

        //Set Attr on PRODUCT Table
        p.setNombre(nombre);
        p.setDescripcion(descripcion);
        p.setPrecio(precio);
        p.setImagen(imagen);
        Categoria c = new Categoria(categoria);
        p.setCategoria(c);
        if (subcategoria != 0) {
            Subcategoria s = new Subcategoria(subcategoria);
            p.setSubcategoria(s);
        }

        //Manage Keywords
        List<Keywords> kwList = p.getKeywordsList();
        
        //Delete old list
        for(int i = 0; i<kwList.size(); i++){
            Keywords k = kwList.get(i);
            
            k.getProductoList().remove(p);
            kwList.remove(k);
            
            keywordsFacade.edit(k);
        }
        
        //Add new list
        StringTokenizer st = new StringTokenizer(keywords, ",");
        while (st.hasMoreTokens()) {
            Keywords k = keywordsFacade.findOrCreate(st.nextToken());
            
            kwList.add(k);
            k.getProductoList().add(p);
            
            keywordsFacade.edit(k);
        }
       
     
        //Save changes on PRODUCT Table
        productoFacade.edit(p);

        //Show product
        //Show product List if admin
        if (user.getEsAdmin() == 0) {
            response.sendRedirect("/efake/ShowProduct?idProducto=" + id + "");
        } else {
            session.setAttribute("status", "Updated Product");
            response.sendRedirect("/efake/ListAdminProducts?page=1");
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
