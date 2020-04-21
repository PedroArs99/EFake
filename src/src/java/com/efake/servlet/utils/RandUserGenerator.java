package com.efake.servlet.utils;

import com.efake.dao.UsuarioFacade;
import com.efake.entity.Usuario;
import com.efake.service.UsuarioService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;
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
@WebServlet(name = "JsonParser", urlPatterns = {"/GenerateUsers"})
public class RandUserGenerator extends HttpServlet {
    
    @EJB
    UsuarioFacade userFacade;
    @EJB
    UsuarioService userService;

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
        PrintWriter out = response.getWriter();
        String number = request.getParameter("number");
        try {
            URL api = new URL("https://randomapi.com/api/h630551q?key=93FI-DX9T-3VR3-V47J&results=" + number);

            //Open Connection
            HttpURLConnection connection = (HttpURLConnection) api.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            //Read Resoonse
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
            JsonArray usersJson = reader.readObject().getJsonArray("results");
            
            for (int i = 0; i < usersJson.size(); i++) {
                JsonObject userJson = usersJson.getJsonObject(i);
                Usuario newUser = new Usuario();
                
                newUser.setCorreo(userJson.getString("email"));
                byte[] hashedPassword = userService.hashPassword("usuario");
                newUser.setPassword(hashedPassword);
                
                newUser.setNombre(userJson.getString("firstName"));
                newUser.setApellidos(userJson.getString("lastName"));
                
                newUser.setEdad(userJson.getInt("age"));
                newUser.setTelefono(userJson.getString("phone"));
                
                Calendar today = Calendar.getInstance();
                today.setTime(new Date());
                
                Calendar oneMonthAgo = (Calendar) today.clone();
                oneMonthAgo.roll(Calendar.DAY_OF_YEAR, -30);
                
                newUser.setUltimaEntrada(this.dateBetween(oneMonthAgo.getTime(), today.getTime()));
                
                userFacade.create(newUser);
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
    
    public Date dateBetween(Date startInclusive, Date endExclusive) {
        long startMillis = startInclusive.getTime();
        long endMillis = endExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);
        
        return new Date(randomMillisSinceEpoch);
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
