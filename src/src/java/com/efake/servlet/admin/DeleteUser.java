package com.efake.servlet.admin;

import com.efake.servlet.utils.EmailSessionBean;
import com.efake.dao.UsuarioFacade;
import com.efake.entity.Usuario;
import com.efake.servlet.utils.TemplatesEnum;
import java.io.IOException;
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
@WebServlet(name = "DeleteUser", urlPatterns = {"/DeleteUser"})
public class DeleteUser extends HttpServlet {

    @EJB
    UsuarioFacade userFacade;
    @EJB
    EmailSessionBean emailBean;

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
        HttpSession session = request.getSession();

        //Delete Account
        Integer userId = Integer.parseInt(request.getParameter("user"));
        Usuario user = userFacade.find(userId); //Email is primary key
        userFacade.remove(user);

        //Send mail notifiying the user 
        String emailTo = user.getCorreo();
        String emailSubject = "Efake account Deleted";
        String emailUserName = user.getNombre();
        String emailBody = request.getParameter("emailBody");
        emailBean.sendEmail(emailTo, emailSubject,emailUserName, emailBody,TemplatesEnum.DELETE_USER);
        
        session.setAttribute("status", "success");
        response.sendRedirect("ListUsers?list=all");

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
