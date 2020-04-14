package com.efake.servlet.utils;

import com.efake.entity.Producto;
import com.efake.service.ProductoService;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PedroArenas
 */
@WebServlet(name = "MostRatedProducts", urlPatterns = {"/MostRatedProducts"})
public class MostRatedProducts extends HttpServlet {
    
    @EJB
    ProductoService productService;
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
        List<Producto> productList = productService.getMostRated(5);
        
         // Build Json
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();        
        productList.forEach(p -> {
            //Create product Json object
            JsonObjectBuilder productJsonBuilder = Json.createObjectBuilder();
            productJsonBuilder.add("id", p.getId());
            productJsonBuilder.add("name", p.getNombre());
            productJsonBuilder.add("description", p.getDescripcion());
            productJsonBuilder.add("image", p.getImagen());
            
            //Add product Json Object to Array
            jsonArrayBuilder.add(productJsonBuilder);
        });
        
        
        
        
        
      
        //Wrap array into JsonObject
        JsonObjectBuilder finalJsonBuilder = Json.createObjectBuilder();
        finalJsonBuilder.add("products", jsonArrayBuilder);
        
        //Send Json
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try (JsonWriter jsonWriter = Json.createWriter(out)) {
            jsonWriter.writeObject(finalJsonBuilder.build());
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
