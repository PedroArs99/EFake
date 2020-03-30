/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.servlet.utils;

import com.efake.dao.CategoriaFacade;
import com.efake.dao.ProductoFacade;
import com.efake.dao.UsuarioFacade;
import com.efake.entity.Categoria;
import com.efake.entity.Producto;
import com.efake.entity.Usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PedroArenas
 */
@WebServlet(name = "RandProductGenerator", urlPatterns = {"/RandProductGenerator"})
public class RandProductGenerator extends HttpServlet {

    @EJB
    ProductoFacade productFacade;
    @EJB
    UsuarioFacade userFacade;
    @EJB
    CategoriaFacade categoryFacade;

    Random r = new Random();

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
        //Get User 
        List<Usuario> allUsers = userFacade.findAll();
        List<Categoria> allCategories = categoryFacade.findAll();
       

        PrintWriter out = response.getWriter();
        String number = request.getParameter("number");
        try {
            URL api = new URL("https://randomapi.com/api/h630551q?key=93FI-DX9T-3VR3-V47J&results=" + number);

            //Open Connection
            HttpURLConnection connection = (HttpURLConnection) api.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            //Read Response
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder apiResponse = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                apiResponse.append(inputLine);
            }
            in.close();

            //Parse Json
            JsonReader reader = Json.createReader(new StringReader(apiResponse.toString()));
            JsonArray productsJson = reader.readObject().getJsonArray("results");

            for (int i = 0; i < productsJson.size(); i++) {
                JsonObject productJson = productsJson.getJsonObject(i);
                Producto newProduct = new Producto();
                
                newProduct.setNombre(productJson.getString("name"));
                newProduct.setDescripcion(productJson.getString("description"));
                newProduct.setPrecio(r.nextDouble());
                newProduct.setImagen(productJson.getString("image"));
                newProduct.setFecha(new Date());
                Categoria cat = allCategories.get(r.nextInt(allCategories.size()));
                newProduct.setCategoria(cat);
                Usuario owner = allUsers.get(r.nextInt(allUsers.size()));
                newProduct.setOwner(owner);
                
                productFacade.create(newProduct);
            }

            //Show status
            out.print("Task Completed");
        } catch (Exception e) {
            out.println("Something went wrong");
            out.println(e.getMessage());
            e.printStackTrace(out);
        } finally {
            out.close();
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
