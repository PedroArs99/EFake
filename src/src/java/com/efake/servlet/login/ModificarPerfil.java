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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
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
@WebServlet(name = "ModificarPerfil", urlPatterns = {"/ModificarPerfil"})
public class ModificarPerfil extends HttpServlet {
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
        String correoAntiguo, nombre, apellidos, correoNuevo, telefono, status = null, goTo = "index.jsp";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNacimiento = null;
        java.util.Date fechaSistema = new Date();
        int edad, mes, dia;
        
        try{
            fechaNacimiento = formato.parse(request.getParameter("edad"));
        } catch (ParseException ex) {
            System.out.println(ex);
        }       
        
        correoAntiguo = request.getParameter("correoAntiguo");
        nombre = request.getParameter("nombre");
        apellidos = request.getParameter("apellidos"); 
        correoNuevo = request.getParameter("correo");
        telefono = request.getParameter("telefono");
        edad = fechaSistema.getYear() - fechaNacimiento.getYear(); 
        Usuario user = usuarioFacade.findByCorreo(correoAntiguo);
        Usuario posibleUser = usuarioFacade.findByCorreo(correoNuevo);
        
        
        try{
            fechaNacimiento = formato.parse(request.getParameter("edad"));
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        
        boolean esMenor = edad < 18;
        
        if(edad == 18){
            mes = fechaSistema.getMonth() - fechaNacimiento.getMonth();
            if(mes == 0){
                dia = fechaSistema.getDay() - fechaNacimiento.getDay();
                if(dia >= 0){
                    esMenor = false;
                }else{
                    esMenor = true;
                    edad = 17;
                }
            }else if(mes < 0){
                esMenor = true;
                edad = 17;
            }else{
                esMenor = false;
            }
        }
        
        if(posibleUser != null && !correoNuevo.equals(correoAntiguo)){
           status = "El correo ya existe en la base de datos";
           request.setAttribute("status", status);
           goTo = "signup.jsp";
        }else if(esMenor){
           status = "Lo siento, eres menor de edad";
           request.setAttribute("status", status);
           goTo = "signup.jsp";
        }else{
            status = "Todo correcto";
            request.setAttribute("status", status);
            user.setNombre(nombre);
            user.setApellidos(apellidos);            
            user.setTelefono(telefono);           
            user.setCorreo(correoNuevo);            
            user.setEdad(edad);   
            usuarioFacade.edit(user);
            HttpSession session = request.getSession();
            session.setAttribute("usuario", user);            
        }
        
        System.out.print(status);
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
