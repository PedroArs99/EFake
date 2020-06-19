/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.bean.login;

import com.efake.dto.UsuarioDTO;
import com.efake.service.UsuarioService;
import java.util.Arrays;
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
    
    protected String contrasena, status = "", actual, nueva, repetida;
    protected Date nacimiento;

    /**
     * Creates a new instance of SignUpBean
     */
    public SignUpBean() {
    }
    
    @PostConstruct
    public void init(){
        usuario = usuarioBean.getUsuario();
        if(usuario == null){
            usuario = new UsuarioDTO();
        }
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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
    
    public String doSignUp(){
        UsuarioDTO posibleUser = null;
        try {
            posibleUser = usuarioService.findByCorreo(usuario.getCorreo());
        } catch (Exception e) {
            posibleUser = null;
        }
        
        if(posibleUser != null){
            status = "The email address is already registered";
            this.usuario.setCorreo("");
            return null;
        }else if(usuarioService.esMenor(nacimiento)){            
            status = "Sorry, You are under-age";
            return null;
        }else{
            status = "Todo correcto";
            byte[] contrasenaHash = usuarioService.hashPassword(contrasena);
            usuario.setPassword(contrasenaHash);
            usuario.setEdad(usuarioService.calcularEdad(nacimiento));
            usuario.setUltimaEntrada(new Date());
            usuarioService.create(usuario);
            return usuarioBean.doLogIn(usuario.getCorreo(), usuarioService.hashPassword(contrasena));
        }       
    }
    
    
    
    public String doEditProfile(){
        UsuarioDTO posibleCorreoExistente = usuarioService.findByCorreo(usuario.getCorreo());
        
        if(posibleCorreoExistente != null && usuario.getId() != posibleCorreoExistente.getId()){
            status = "This email address is already registered";            
            return null;
        }else{
            usuario.setUltimaEntrada(new Date());
            usuarioService.edit(usuario);
            //usuarioBean.setUsuario(usuario);
            //return usuarioBean.doLogIn(usuario.getCorreo(), usuario.getPassword());
            return "index";            
        }
    }
    
    public String doChangePasswd(){
        byte[] contrasenaHash = usuarioService.hashPassword(actual);
        byte[] nuevaContrasena = usuarioService.hashPassword(nueva);
        if (!Arrays.equals(contrasenaHash, usuario.getPassword())) {
            status = "This is not your password";
            return null;
        } else if (!repetida.equals(nueva)) {
            status = "The new password must match the repeated one";
            return null;
        } else {
            status = "Todo correcto";
            usuario.setPassword(nuevaContrasena);
            System.out.println("LA CONTRASEÑA NUEVA ANTES DE EDITARLA: "+nuevaContrasena);
            usuarioService.edit(usuario);
            System.out.println("LA CONTRASEÑA NUEVA DESPUES DE EDITARLA: "+usuario.getPassword());
            return "signup";
        }
    }
    
    public boolean hayStatus(){
        return !status.equals("") && !status.equals("Todo correcto");
    }
    
}
