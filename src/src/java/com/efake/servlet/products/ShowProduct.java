package com.efake.servlet.products;

import com.efake.dao.ProductoFacade;
import com.efake.dao.ValoracionFacade;
import com.efake.entity.Producto;
import com.efake.entity.Usuario;
import com.efake.entity.Valoracion;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author carlo
 * @author Juan
 */
@WebServlet(name = "ShowProduct", urlPatterns = {"/ShowProduct"})
public class ShowProduct extends HttpServlet {
    @EJB
    ValoracionFacade valoricionFacade;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<Integer, Double> ratings = new HashMap<>();
        Integer idProducto = Integer.parseInt(request.getParameter("idProducto"));
        Producto productoBuscado = productoFacade.find(idProducto);
        List<Valoracion> listValoraciones = productoBuscado.getValoracionList();
        Usuario user = (Usuario) session.getAttribute("usuario");
        int valorado = valorado(listValoraciones, user);
        double mediaValoraciones = 0.0;
        
        inicializarMapa(ratings);
        for(Valoracion v: listValoraciones){
            Double value = ratings.get(v.getPuntuacion());
            ratings.put(v.getPuntuacion(), value + 1);
            mediaValoraciones += v.getPuntuacion();
        }

        if(!listValoraciones.isEmpty()) {
            ratings.entrySet().forEach((entry) -> {
                Integer key = entry.getKey();
                Double value = entry.getValue();
                value = (value/listValoraciones.size()) * 100;
                ratings.put(key, value);
            });
            mediaValoraciones = mediaValoraciones/listValoraciones.size();
            mediaValoraciones = formatearDecimales(mediaValoraciones);
            
        }
        
        /* Juan */
        
        Producto p = productoFacade.find(idProducto);
        
      
        request.setAttribute("producto", p);

        
        request.setAttribute("mediaValoraciones", mediaValoraciones);
        request.setAttribute("ratings", ratings);
        request.setAttribute("listValoraciones", listValoraciones);
        request.setAttribute("valorado", valorado);
        RequestDispatcher rd = request.getRequestDispatcher("selected_product.jsp");

        rd.forward(request, response);
    }
    
    private int valorado(List<Valoracion> listValoraciones, Usuario user) {
        int valorado = 1;
        for(int i = 0; i < listValoraciones.size() && valorado == 1; i++) {
            Usuario u = listValoraciones.get(i).getCliente();
            if(u.equals(user)) {
            valorado = 0;
            }
        }
        return valorado;
    }
    
    private void inicializarMapa(Map<Integer, Double> ratings){
        for(int i = 0; i < 5; i++){
            ratings.put(i + 1, 0.0);
        }
    }
    
    private double formatearDecimales(double numero){
        return Math.round(numero * Math.pow(10,2))/Math.pow(10,2);
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
