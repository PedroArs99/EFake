package com.efake.servlet.admin;

import com.efake.dao.CategoriaFacade;
import com.efake.dao.ProductoFacade;

import com.efake.entity.Producto;
import com.efake.entity.Usuario;
import java.io.IOException;
import java.util.List;
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
 * @author PedroArenas
 */
@WebServlet(name = "ListAdminProducts", urlPatterns = {"/ListAdminProducts"})
public class ListProducts extends HttpServlet {

    //Number of products that can be shown in a users list page
    private static final int PAGE_SIZE = 16;

    @EJB
    ProductoFacade productFacade;
    @EJB
    CategoriaFacade categoryFacade;

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
        //Check if the user is logged in as admin
        HttpSession session = request.getSession();
        Usuario admin = (Usuario) session.getAttribute("usuario");
        if (admin != null && admin.getEsAdmin() == 0) {// The user is logged in, but he's not an admin
            response.sendRedirect("/efake/");
        } else if (admin == null) { //The user is not logged in
            response.sendRedirect("/efake/login.jsp");
        }

        //Load Attributes from request
        List<Producto> productList = null;
        Integer numberOfProducts = productFacade.count();
        
        //Prepare pagination
        Integer numberOfPages = 0;
        if (numberOfProducts % PAGE_SIZE == 0) {
            numberOfPages = numberOfProducts / PAGE_SIZE;
        } else {
            numberOfPages = (numberOfProducts / PAGE_SIZE) + 1;
        }
        //Pages start at 1 but lists are 0 indexed
        Integer pageNumber = Integer.parseInt(request.getParameter("page")) - 1;
        
        //Load products
        productList = productFacade.findRange(pageNumber,PAGE_SIZE);
             

        //Load List on request and redirect
        request.setAttribute("productList", productList);
        request.setAttribute("categoryList", categoryFacade.findAll());
        request.setAttribute("numberOfPages", numberOfPages);
        RequestDispatcher rd = request.getRequestDispatcher("adminPages/productList.jsp");
        rd.forward(request, response);
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
