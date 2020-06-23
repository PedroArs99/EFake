/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.bean.login;

import com.efake.service.UsuarioService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author laura
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean implements Serializable{
    @Inject UsuarioBean usuarioBean;
    
    @EJB UsuarioService usuarioService;
    
    protected String correo;
    protected String contrasena;
    protected String status = "";

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

    public String getStatus() {
        return usuarioBean.getStatus();
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String doLogIn(){
        return usuarioBean.doLogIn(correo,usuarioService.hashPassword(contrasena));
    }
    
    public boolean hayStatus(){
        return usuarioBean.hayStatus();
    }
}
