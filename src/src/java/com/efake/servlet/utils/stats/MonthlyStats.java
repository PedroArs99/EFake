package com.efake.servlet.utils.stats;

import com.efake.service.StatsService;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
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
@WebServlet(name = "MonthlyStats", urlPatterns = {"/MonthlyStats"})
public class MonthlyStats extends HttpServlet {

    @EJB
    StatsService statsService;

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
        //Load parameter
        String name = request.getParameter("name");
        
        //Json to send
        JsonObjectBuilder statsBuilder = Json.createObjectBuilder();
        
        //Get Map
        Map<String, SortedMap<String, Number>> monthlyStats = statsService.getMonthlyStats(name);

        switch (name) {
            case "user":
                //Build Users Json
                JsonObjectBuilder usersBuilder = Json.createObjectBuilder();
                Map<String, Number> usersValues = monthlyStats.get("userCount");
                for (Entry<String, Number> e : usersValues.entrySet()) {
                    usersBuilder.add(e.getKey(), (Long) e.getValue());
                }
                statsBuilder.add("userCount", usersBuilder);
                break;
            case "product":
                //Build Products Json
                JsonObjectBuilder productBuilder = Json.createObjectBuilder();
                Map<String, Number> productValues = monthlyStats.get("productCount");
                for (Entry<String, Number> e : productValues.entrySet()) {
                    productBuilder.add(e.getKey(), (Long) e.getValue());
                }
                statsBuilder.add("productCount", productBuilder);
                break;
            case "rating":
                //Build Ratings Json
                JsonObjectBuilder ratingBuilder = Json.createObjectBuilder();
                Map<String, Number> ratingValues = monthlyStats.get("ratingCount");
                for (Entry<String, Number> e : ratingValues.entrySet()) {
                    ratingBuilder.add(e.getKey(), (Long) e.getValue());
                }
                statsBuilder.add("ratingCount", ratingBuilder);
                break;
                

        }
                
        //Send Json
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try (JsonWriter jsonWriter = Json.createWriter(out)) {
            jsonWriter.writeObject(statsBuilder.build());
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
