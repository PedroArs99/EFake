/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.bean.login;

import com.efake.dto.UsuarioDTO;
import com.efake.service.UsuarioService;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Inject;

/**
 *
 * @author laura
 */
@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    protected UsuarioDTO usuario;
    
    protected String nombre, apellido, correo, contrasena, status = "";
    protected Date nacimiento;
    
    @EJB
    UsuarioService usuarioService;
    /**
     * Creates a new instance of usuarioBean
     */
    public UsuarioBean() {
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
        //usuarioService.edit(this.usuario);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String doLogIn(String correo, byte[] contrasenaIntroducida){
        
        UsuarioDTO posibleUsuario = usuarioService.findByCorreo(correo);
        
        if(posibleUsuario == null){
            status = "Wrong email address";
            System.out.println(status);
            this.correo = "";
            this.contrasena = "";
            return null;
        }else if(!Arrays.equals(contrasenaIntroducida,posibleUsuario.getPassword())){
           status = "Wrong password";
           System.out.println(status);
           this.contrasena = "";
           return null;
        }else{
            status = "Todo correcto";
            System.out.println(status);
            this.usuario = posibleUsuario;
            usuario.setUltimaEntrada(new Date());
            usuarioService.edit(usuario);
            return "index.jsf";                  
        }        
    }  
    
    public boolean hayStatus(){
        return !status.equals("") && !status.equals("Todo correcto");
    }
    
    public String doLogOut(){
        this.usuario = null;
        this.status = "";
        this.correo = "";
        this.nombre = "";
        this.apellido = "";
        this.contrasena = "";
        this.nacimiento = null;
        return "index?faces-redirect=true";
    }
}
