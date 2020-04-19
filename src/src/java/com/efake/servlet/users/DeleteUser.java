package com.efake.servlet.users;

import com.efake.service.EmailService;
import com.efake.dao.UsuarioFacade;
import com.efake.entity.Usuario;
import com.efake.service.TemplatesEnum;
import java.io.IOException;
import java.util.Properties;
import javax.ejb.EJB;
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
@WebServlet(name = "DeleteUser", urlPatterns = {"/DeleteUser"})
public class DeleteUser extends HttpServlet {

    @EJB
    UsuarioFacade userFacade;
    @EJB
    EmailService emailBean;

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

        //Delete Account
        Integer userId = Integer.parseInt(request.getParameter("user"));
        Usuario user = userFacade.find(userId); //Email is primary key
        userFacade.remove(user);

        //Send mail notifiying the user 
        Properties mailProperties = new Properties();
        mailProperties.setProperty("to", user.getCorreo());
        mailProperties.setProperty("subject", "Efake account Deleted");
        mailProperties.setProperty("userName", user.getNombre());
        mailProperties.setProperty("body", request.getParameter("emailBody"));
        mailProperties.setProperty("template", TemplatesEnum.DELETE_USER.toString());
                
        emailBean.sendEmail(mailProperties);
        
        //Send status & redirect
        session.setAttribute("status", "User Deleted");
        
        //Go back to users list
        String page = request.getParameter("page");
        response.sendRedirect("ListUsers?list=all&page="+page);

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
