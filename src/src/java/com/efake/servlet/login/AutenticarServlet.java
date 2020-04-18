package com.efake.servlet.login;

import com.efake.dao.UsuarioFacade;
import com.efake.entity.Usuario;
import com.efake.service.UsuarioService;
import java.io.IOException;
import java.util.Arrays;
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
@WebServlet(name = "AutenticarServlet", urlPatterns = {"/AutenticarServlet"})
public class AutenticarServlet extends HttpServlet {

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
        //Session control
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("usuario");
        
        if(user != null){
            response.sendRedirect("index.jsp");
        }
        
        
        String correo, status = null, goTo = "index.jsp", contrasena;
        correo = request.getParameter("correo");
        contrasena = request.getParameter("contrasena");
        byte[] contrasenaIntroducida = usuarioService.hashPassword(contrasena);

        RequestDispatcher rd;
        

        try{
           user = usuarioFacade.findByCorreo(correo);
        }
        catch(EJBException ex){
            user = null;
        }
        
        
        if(user == null){
           status = "El correo es incorrecto";
           goTo = "login.jsp";
        }else if(!Arrays.equals(contrasenaIntroducida,user.getPassword())){
           status = "La contraseña es incorrecta";
           goTo = "login.jsp";
        }else{            
            session.setAttribute("usuario", user);
        }
        session.setAttribute("status", status);
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
