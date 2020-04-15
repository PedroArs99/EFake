/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.servlet.login;

import com.efake.dao.UsuarioFacade;
import com.efake.entity.Usuario;
import com.efake.service.UsuarioService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author laura
 */
@WebServlet(name = "changePasswordServlet", urlPatterns = {"/changePasswordServlet"})
public class changePasswordServlet extends HttpServlet {
    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    UsuarioService usuarioService;
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
        response.setContentType("text/html;charset=UTF-8");
        String status = "Todo correcto", goTo = "signup.jsp";
        String correo = request.getParameter("correo");
        String passwordAntigua = request.getParameter("actualPassword");
        String passwordNueva = request.getParameter("nuevaPassword");
        String passwordRepetida = request.getParameter("repetidaPassword");
        Usuario user = usuarioFacade.findByCorreo(correo);
        byte[] passwordAntiguaHash = usuarioService.hashPassword(passwordAntigua);
        
        if(!Arrays.equals(passwordAntiguaHash, user.getPassword())){
            status = "Contraseña incorrecta";
            request.setAttribute("status", status);
            goTo = "changePassword.jsp";
        }else if(!passwordNueva.equals(passwordRepetida)){
            status = "Las contraseñas no coinciden";
            request.setAttribute("status", status);
            goTo = "changePassword.jsp";
        }else{
            request.setAttribute("status",status);
            user.setPassword(usuarioService.hashPassword(passwordNueva));
            usuarioFacade.edit(user);
        }
            
        response.sendRedirect(goTo);
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
