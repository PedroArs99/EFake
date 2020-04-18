package com.efake.servlet.products.juan;

import com.efake.dao.KeywordsFacade;
import com.efake.dao.ProductoFacade;
import com.efake.dao.UsuarioFacade;
import com.efake.entity.Categoria;
import com.efake.entity.Keywords;
import com.efake.entity.Producto;
import com.efake.entity.Subcategoria;
import com.efake.entity.Usuario;
import java.io.IOException;
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
 * @author JuMed
 */
@WebServlet(name = "ModificarProductoServlet", urlPatterns = {"/ModificarProductoServlet"})
public class ModificarProductoServlet extends HttpServlet {

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
        Usuario user = (Usuario) request.getSession();
        
        if(user == null){
            response.sendRedirect("/");
        }
        
        
        // Get form fields
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("textNombre");
        String descripcion = request.getParameter("descripcion");
        Double precio = Double.parseDouble(request.getParameter("textPrecio"));
        String imagen = request.getParameter("textImagen");
        String keywords1 = request.getParameter("textKeywords1");
        String keywords2 = request.getParameter("textKeywords2");
        String keywords3 = request.getParameter("textKeywords3");
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
        Keywords oldK1 = kwList.get(0);
        Keywords oldK2 = kwList.get(1);
        Keywords oldK3 = kwList.get(2);

        Keywords newK1 = keywordsFacade.findOrCreate(keywords1);
        Keywords newK2 = keywordsFacade.findOrCreate(keywords2);
        Keywords newK3 = keywordsFacade.findOrCreate(keywords3);

        if (!oldK1.equals(newK1)) { //Keyword has been edited
            List<Producto> oldK1products = oldK1.getProductoList();
            List<Producto> newK1products = newK1.getProductoList();

            //Remove old association
            oldK1products.remove(p);

            //Add new Association
            newK1products.add(p);

            //save changes on both entities
            keywordsFacade.edit(oldK1);
            keywordsFacade.edit(newK1);
        }

        if (!oldK2.equals(newK2)) { //Keyword has been edited
            List<Producto> oldK2products = oldK2.getProductoList();
            List<Producto> newK2products = newK2.getProductoList();

            //Remove old association
            oldK2products.remove(p);

            //Add new Association
            newK2products.add(p);

            //save changes on both entities
            keywordsFacade.edit(oldK2);
            keywordsFacade.edit(newK2);
        }

        if (!oldK3.equals(newK3)) { //Keyword has been edited
            List<Producto> oldK3products = oldK3.getProductoList();
            List<Producto> newK3products = newK3.getProductoList();

            //Remove old association
            oldK3products.remove(p);

            //Add new Association
            newK3products.add(p);

            //save changes on both entities
            keywordsFacade.edit(oldK3);
            keywordsFacade.edit(newK3);
        }

        //Save changes on PRODUCT Table
        productoFacade.edit(p);

        //Show product
        response.sendRedirect("/efake/ShowProduct?idProducto=" + id + "");
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
