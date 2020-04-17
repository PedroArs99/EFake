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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * @author JuMed
 */
@WebServlet(name = "CreateProductsServlet", urlPatterns = {"/CreateProductsServlet"})
public class CreateProductsServlet extends HttpServlet {
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
        if(user == null){
            response.sendRedirect("login.jsp");
        }
        
        
        String nombre = request.getParameter("textNombre");
        String descripcion = request.getParameter("descripcion");
        Double precio = Double.parseDouble(request.getParameter("textPrecio"));
        String imagen = request.getParameter("textImagen");
        String keywords1 = request.getParameter("textKeywords1");
        String keywords2 = request.getParameter("textKeywords2");
        String keywords3 = request.getParameter("textKeywords3");
        Date now = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date fecha = now;
        Integer categoria =Integer.parseInt(request.getParameter("Categoria"));
        Integer subcategoria =Integer.parseInt(request.getParameter("Subcategoria"));
        
        Producto p = new Producto();
        Categoria c = new Categoria(categoria);
        Subcategoria s = new Subcategoria(subcategoria);
        Usuario u = usuarioFacade.find(user.getId());
        
        Keywords key1 = keywordsFacade.find(keywords1);
        Keywords key2 = keywordsFacade.find(keywords2);
        Keywords key3 = keywordsFacade.find(keywords3);
        
        
        List<Producto> k1List =new ArrayList<>();
        List<Producto> k2List =new ArrayList<>();
        List<Producto> k3List =new ArrayList<>();
        
        p.setNombre(nombre);
        p.setDescripcion(descripcion);
        p.setPrecio(precio);
        p.setFecha(fecha);
        p.setImagen(imagen);
        p.setCategoria(c);
        p.setSubcategoria(s);
        p.setNombre(nombre);
        p.setOwner(u);
        
        p.setReportado(Short.parseShort("0"));
        productoFacade.create(p);
        
        List<Keywords> keywordsList = new ArrayList<>();
       if(!keywords1.equals("") ){
            if(key1 == null){ 
                Keywords k1 = new Keywords(keywords1);
                keywordsFacade.create(k1);
                key1= keywordsFacade.find(keywords1);
                
            }
            k1List = key1.getProductoList();
            k1List.add(p);
            key1.setProductoList(k1List);
            keywordsFacade.edit(key1);
            keywordsList.add(key1);
        }
       
       if(!keywords2.equals("") ){
            if(key2 == null){
                
                Keywords k2 = new Keywords(keywords2);
                keywordsFacade.create(k2);
                key2= keywordsFacade.find(keywords2);
            }
            k2List = key2.getProductoList();
            
            k2List.add(p);
            key2.setProductoList(k2List);
            keywordsFacade.edit(key2);
            keywordsList.add(key2);
        }
       
       if(!keywords3.equals("") ){
            if(key3 == null){
                
                Keywords k3 = new Keywords(keywords3);
                keywordsFacade.create(k3);
                key3= keywordsFacade.find(keywords3);
            }
            k3List = key3.getProductoList();
               
            
            k3List.add(p);
            key3.setProductoList(k3List);
            keywordsFacade.edit(key3);
            keywordsList.add(key3);
        }
        p.setKeywordsList(keywordsList);
        productoFacade.edit(p);
        
        
        int idProducto= p.getId();
        response.sendRedirect("/efake/ShowProduct?idProducto="+idProducto+"");
        
       
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
