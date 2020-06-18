/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.bean.login;

import com.efake.dto.UsuarioDTO;
import com.efake.service.UsuarioService;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author laura
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{
    
    protected String correo;
    protected String contrasena;
    protected String status = "";
    
    protected UsuarioDTO usuario;
    
    @EJB private UsuarioService usuarioService;

    /**
     * Creates a new instance of loginBean
     */
    public LoginBean() {
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String doSignIn(){
        
        UsuarioDTO posibleUsuario = usuarioService.findByCorreo(correo); 
        
        byte[] contrasenaIntroducida = usuarioService.hashPassword(contrasena);
        
        if(posibleUsuario == null){
            status = "Wrong email address";
            this.correo = "";
            this.contrasena = "";
            return null;
        }else if(!Arrays.equals(contrasenaIntroducida,posibleUsuario.getPassword())){
           status = "Wrong password";
           this.contrasena = "";
           return null;
        }else{
            status = "Todo correcto";
            usuario = posibleUsuario;
            usuario.setUltimaEntrada(new Date());
            usuarioService.edit(usuario);
            return "index.jsf";                  
        }        
    }
    
    public boolean hayStatus(){
        return !status.equals("");
    }
}
