/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.servlet.utils;

import com.efake.dao.CategoriaFacade;
import com.efake.dao.SubcategoriaFacade;
import com.efake.entity.Categoria;
import com.efake.entity.Subcategoria;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Pedro Arenas
 */
@WebServlet(name = "ListNavCategories", urlPatterns = {"/NavCategories"})
public class ListNavCategories extends HttpServlet {
    @EJB
    CategoriaFacade categoryFacade;
    @EJB
    SubcategoriaFacade subcategoryFacade;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get Categories
        List<Categoria> categoryList = categoryFacade.findAll();
        
        JsonArrayBuilder categoryArrayBuilder = Json.createArrayBuilder();
        for(Categoria c : categoryList){
            //Build Category
            JsonObjectBuilder categoryBuilder = Json.createObjectBuilder();
            categoryBuilder.add("id", c.getId());
            categoryBuilder.add("name", c.getNombre());
            
            
            List<Subcategoria> subcategoryList = subcategoryFacade.findByCategory(c);
            //Build subcategoryArray
            JsonArrayBuilder subcategoryArrayBuilder = Json.createArrayBuilder();
            for(Subcategoria s : subcategoryList){
                //Build each subcategory
                JsonObjectBuilder subcategoryBuilder = Json.createObjectBuilder();
                subcategoryBuilder.add("id", s.getId());
                subcategoryBuilder.add("name", s.getNombre());
                
                //add subcategory to array
                subcategoryArrayBuilder.add(subcategoryBuilder);
            }
            categoryBuilder.add("subcategories", subcategoryArrayBuilder);
            
            categoryArrayBuilder.add(categoryBuilder);
        }
        
        //Wrap array into JsonObject
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
        jsonBuilder.add("categories", categoryArrayBuilder);
        
        //Send Json
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try (JsonWriter jsonWriter = Json.createWriter(out)) {
            jsonWriter.writeObject(jsonBuilder.build());
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
