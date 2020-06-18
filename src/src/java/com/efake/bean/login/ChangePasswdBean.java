/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.bean.login;

import com.efake.dto.UsuarioDTO;
import com.efake.service.UsuarioService;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author laura
 */
@Named(value = "changePasswdBean")
@RequestScoped
public class ChangePasswdBean {
    @EJB
    protected UsuarioService usuarioService;
    
    @Inject
    protected UsuarioBean usuarioBean;
    
    protected UsuarioDTO usuarioLogeado;

    private String actual, nueva, repetida, status = "";
    /**
     * Creates a new instance of ChangePasswdBean
     */
    public ChangePasswdBean() {
    }
    
    @PostConstruct
    public void init(){
        usuarioLogeado = usuarioBean.getUsuario();
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public String getNueva() {
        return nueva;
    }

    public void setNueva(String nueva) {
        this.nueva = nueva;
    }

    public String getRepetida() {
        return repetida;
    }

    public void setRepetida(String repetida) {
        this.repetida = repetida;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }   
    
    public String doChangePasswd(){
        byte[] contrasenaHash = usuarioService.hashPassword(actual);
        byte[] nuevaContrasena = usuarioService.hashPassword(nueva);
        if(!repetida.equals(nueva)){
            status = "The new password must match the repeated one";
            return null;
        }else if(!Arrays.equals(contrasenaHash, usuarioLogeado.getPassword())){
            status = "This is not your password";
            return null;
        }else{
            usuarioLogeado.setPassword(nuevaContrasena);
            usuarioService.edit(usuarioLogeado);
            return "signup";
        }
        
    }
}
