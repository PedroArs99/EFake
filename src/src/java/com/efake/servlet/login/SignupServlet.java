/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.servlet.login;

import com.efake.dao.UsuarioFacade;
import com.efake.entity.Usuario;
import com.efake.service.EmailService;
import com.efake.service.TemplatesEnum;
import com.efake.service.UsuarioService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author laura
 */
@WebServlet(name = "SignupServlet", urlPatterns = {"/SignupServlet"})
public class SignupServlet extends HttpServlet {

    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    UsuarioService usuarioService;
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
        //Session control
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("usuario");
        
        if(user != null){
            response.sendRedirect("index.jsp");
        }
        
        response.setContentType("text/html;charset=UTF-8");
        String nombre, apellidos, correo, contrasena, telefono, status = null, goTo = "index.jsp";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNacimiento = null;
        int mes, dia;

        try {
            fechaNacimiento = formato.parse(request.getParameter("edad"));
        } catch (ParseException ex) {
            System.out.println(ex);
        }

        java.util.Date fechaSistema = new Date();
        int edad = fechaSistema.getYear() - fechaNacimiento.getYear();
        RequestDispatcher rd;
        Usuario newUser = null, posibleUser;
        byte[] contrasenaCifrada;
        boolean esMenor = edad < 18;

        if (edad == 18) {
            mes = fechaSistema.getMonth() - fechaNacimiento.getMonth();
            if (mes == 0) {
                dia = fechaSistema.getDay() - fechaNacimiento.getDay();
                if (dia >= 0) {
                    esMenor = false;
                } else {
                    esMenor = true;
                    edad = 17;
                }
            } else if (mes < 0) {
                esMenor = true;
                edad = 17;
            }else{
                esMenor = false;
            }
        }

        correo = request.getParameter("correo");
        try {
            posibleUser = usuarioFacade.findByCorreo(correo);
        } catch (EJBException e) {
            posibleUser = null;
        }

        nombre = request.getParameter("nombre");
        apellidos = request.getParameter("apellidos");
        contrasena = request.getParameter("contrasena");
        contrasenaCifrada = usuarioService.hashPassword(contrasena);
        telefono = request.getParameter("telefono");

        
        
        if(posibleUser != null){
           status = "El correo ya existe en nuestro sistema";
           session.setAttribute("status", status);
           goTo = "signup.jsp";
        }else if(esMenor){
           status = "Lo siento, eres menor de edad";
           session.setAttribute("status", status);
           goTo = "signup.jsp";
        }else{
           status = "Todo correcto";
           request.setAttribute("status", status);
           newUser = new Usuario(correo,contrasenaCifrada,nombre,apellidos,edad,(short)0);
           newUser.setTelefono(telefono);
           usuarioFacade.create(newUser);           
           session.setAttribute("usuario", newUser);
           
           Properties mailProperties = new Properties();
           mailProperties.setProperty("to", newUser.getCorreo());
           mailProperties.setProperty("subject", "Welcome to Efake");
           mailProperties.setProperty("userName", newUser.getNombre());
           mailProperties.setProperty("template", TemplatesEnum.REGISTER_USER.toString());
           
           emailService.sendEmail(mailProperties);
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
