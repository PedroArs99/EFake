package com.efake.servlet.login;

import com.efake.dao.UsuarioFacade;
import com.efake.entity.Usuario;
import com.efake.service.EmailService;
import com.efake.service.TemplatesEnum;
import com.efake.service.UsuarioService;
import java.io.IOException;
import java.util.Arrays;
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
@WebServlet(name = "AutenticarServlet", urlPatterns = {"/AutenticarServlet"})
public class AutenticarServlet extends HttpServlet {

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
        String correo, status = "Todo correcto", goTo = "index.jsp", contrasena;
        correo = request.getParameter("correo");
        contrasena = request.getParameter("contrasena");
        byte[] contrasenaIntroducida = usuarioService.hashPassword(contrasena);
        
        RequestDispatcher rd;
        Usuario user;
        
        try{
           user = usuarioFacade.findByCorreo(correo);
        }
        catch(EJBException ex){
            user = null;
        }
        
        if(user == null){
           status = "El usuario no se encuentra en la base de datos";
           request.setAttribute("status", status);
           goTo = "login.jsp";
        }else if(!Arrays.equals(contrasenaIntroducida,user.getPassword())){
           status = "La clave es incorrecta";
           request.setAttribute("status", status);
           goTo = "login.jsp";
        }else{
            HttpSession session = request.getSession();
            session.setAttribute("usuario", user);
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
