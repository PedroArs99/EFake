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
    @Inject
    private LoginBean loginBean;

    protected UsuarioDTO usuario;
    protected UsuarioDTO usuarioLogeado;
    
    protected String nombre, apellido, correo, contrasena, telefono, status = "";
    protected Date nacimiento;
    
    @EJB
    UsuarioService usuarioService;
    /**
     * Creates a new instance of usuarioBean
     */
    public UsuarioBean() {
    }
    
    @PostConstruct
    public void init(){
        usuarioLogeado = loginBean.getUsuario();
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
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

    public UsuarioDTO getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public void setUsuarioLogeado(UsuarioDTO usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
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
            usuarioNuevo.setNombre(nombre);
            usuarioNuevo.setApellidos(apellido);
            usuarioNuevo.setCorreo(correo);
            byte[] contrasenaHash = usuarioService.hashPassword(contrasena);
            usuarioNuevo.setPassword(contrasenaHash);
            usuarioNuevo.setEdad(usuarioService.calcularEdad(nacimiento));
            usuarioNuevo.setUltimaEntrada(new Date());
            System.out.println("EL TELEFONO ES: "+telefono);
            usuarioNuevo.setTelefono(telefono);
            usuarioService.create(usuarioNuevo);
            usuario = usuarioService.findByCorreo(usuarioNuevo.getCorreo());
            return "index.jsf";
        }       
    }   
    
    public boolean hayStatus(){
        return !status.equals("") && !status.equals("Todo correcto");
    }
    
    public String doEditProfile(){
        UsuarioDTO posibleCorreoExistente = usuarioService.findByCorreo(correo);
        if(posibleCorreoExistente != null){
            status = "This email address is already registered";
            return null;
        }else{
            usuarioLogeado.setNombre(nombre);
            usuarioLogeado.setApellidos(apellido);
            usuarioLogeado.setCorreo(correo);
            usuarioLogeado.setTelefono(telefono);
            usuarioLogeado.setEdad(usuarioService.calcularEdad(nacimiento));
            usuarioService.edit(usuario);
            return "index";            
        }
    }
}
