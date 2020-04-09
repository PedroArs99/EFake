package com.efake.servlet.admin;

import com.efake.dao.UsuarioFacade;
import com.efake.entity.Usuario;
import com.efake.service.EmailService;
import com.efake.service.UsuarioService;
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
@WebServlet(name = "AlterUser", urlPatterns = {"/AlterUser"})
public class AlterUser extends HttpServlet {
    
    @EJB
    UsuarioFacade userFacade;
    @EJB
    UsuarioService userService;
    @EJB
    EmailService emailService;
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
        
        //Load Attributtes from the form
        Integer id = Integer.parseInt(request.getParameter("user"));
        String email = request.getParameter("email");
        String fname = request.getParameter("fname");
        String sname = request.getParameter("sname");
        Integer age = Integer.parseInt(request.getParameter("age"));
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        
        //Alter User
        Usuario alteredUser = userFacade.find(id);
        alteredUser.setCorreo(email);
        alteredUser.setNombre(fname);
        alteredUser.setApellidos(sname);
        alteredUser.setEdad(age);
        alteredUser.setTelefono(phone);
        if(!password.equals("")){ //The password is going to be modified
            byte[] hashedPassword = userService.hashPassword(password);
            alteredUser.setPassword(hashedPassword);
        }
        userFacade.edit(alteredUser);
        
        //Send mail Notifiying changes
        Properties mailProperties = new Properties();
        mailProperties.setProperty("to", email);
        mailProperties.setProperty("subject", "Your Efake account has been modified");
        mailProperties.setProperty("email", email);
        mailProperties.setProperty("password", password);
        mailProperties.setProperty("fname", fname);
        mailProperties.setProperty("sname", sname);
        mailProperties.setProperty("age", age.toString());
        mailProperties.setProperty("phone", phone);
        mailProperties.setProperty("template", TemplatesEnum.EDIT_USER.toString());
        emailService.sendEmail(mailProperties);
        //Save status & redirect
        session.setAttribute("status", "User Edited");
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
