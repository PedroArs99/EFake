/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.bean.login;

import com.efake.dto.UsuarioDTO;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author laura
 */
@Named(value = "menuBean")
@RequestScoped
public class MenuBean {
    
    @Inject
    protected LoginBean loginBean;
    @Inject 
    protected UsuarioBean usuarioBean;
    
    protected UsuarioDTO usuario;

    /**
     * Creates a new instance of MenuBean
     */
    public MenuBean() {
    }
    
    @PostConstruct
    public void init(){
        usuario = loginBean.getUsuario();
        usuario = usuarioBean.getUsuario();//En un futuro hay que quitarlo
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
    
    public String doLogOut(){
        this.usuario = null;
        return "";
    }
}
