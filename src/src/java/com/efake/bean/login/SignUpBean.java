/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.bean.login;

import com.efake.dto.UsuarioDTO;
import com.efake.service.UsuarioService;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author laura
 */
@Named(value = "signUpBean")
@RequestScoped
public class SignUpBean {
    @EJB
    private UsuarioService usuarioService;
    
    @Inject
    private UsuarioBean usuarioBean;
    
    private UsuarioDTO usuario;
    
    protected String correo, contrasena, telefono, status = "";
    protected Date nacimiento;

    /**
     * Creates a new instance of SignUpBean
     */
    public SignUpBean() {
    }
    
    @PostConstruct
    public void init(){
        usuario = usuarioBean.getUsuario();
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }  

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
    
    
    
    public String doSignUp(){
        UsuarioDTO posibleUser = null;
        try {
            posibleUser = usuarioService.findByCorreo(correo);
        } catch (Exception e) {
            posibleUser = null;
        }
        
        if(posibleUser != null){
            status = "The email address is already registered";
            this.correo = "";
            return null;
        }else if(usuarioService.esMenor(nacimiento)){            
            status = "Sorry, You are under-age";
            return null;
        }else{
            status = "Todo correcto";
            UsuarioDTO usuarioNuevo = new UsuarioDTO();
            byte[] contrasenaHash = usuarioService.hashPassword(contrasena);
            usuarioNuevo.setPassword(contrasenaHash);
            usuarioNuevo.setEdad(usuarioService.calcularEdad(nacimiento));
            usuarioNuevo.setUltimaEntrada(new Date());
            usuarioService.create(usuarioNuevo);
            return usuarioBean.doLogIn(correo, contrasena);
        }       
    }
    
    public boolean hayStatus(){
        return !status.equals("") && !status.equals("Todo correcto");
    }
    
}
