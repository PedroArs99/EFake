package com.efake.bean.admin;

import com.efake.bean.login.UsuarioBean;
import com.efake.bean.session.Transport;
import com.efake.dto.UsuarioDTO;
import com.efake.service.UsuarioService;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author PedroArenas
 */
@Named(value = "editUser")
@RequestScoped
public class EditUser {

    private static final Logger LOG = Logger.getLogger(EditUser.class.getName());

    //Beans
    @Inject
    private Transport transportBean;
    @Inject
    private UsuarioBean sessionBean;
    
    //Services
    @EJB
    private UsuarioService userService;
    
    //Attributes
    private UsuarioDTO toEditUser;
    private String firstPassword;
    private String secondPassword;
    private String status;

    //Constructor
    @PostConstruct
    public void init() {
        UsuarioDTO sessionUser = sessionBean.getUsuario();

        if (sessionUser == null || sessionUser.getEsAdmin() == 0) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../login.jsf");
            } catch (Exception e) {
                LOG.severe(String.format("Exception: %s", e.getMessage()));
            }
        } else {
            this.toEditUser = transportBean.getToEditUser();            
        }
    }
    
    //Bean Methods
    public String saveChanges(){
        if(firstPassword == null || secondPassword == null){
            this.status = "Please Type both passwords Fields";
        }else{                     
            if(firstPassword.equals(secondPassword)){
                byte[] hashedNewPassword = userService.hashPassword(firstPassword);
                this.toEditUser.setPassword(hashedNewPassword);
                this.status = null;
            }else{
                this.status = "Both passwords doesn't match";
            }
        }
        
        userService.edit(toEditUser);
        
        return "userList?faces-redirect=true";
    }
    
    //Getters & setters
    public UsuarioDTO getToEditUser() {
        return toEditUser;
    }

    public void setToEditUser(UsuarioDTO toEditUser) {
        this.toEditUser = toEditUser;
    }

    public String getFirstPassword() {
        return firstPassword;
    }

    public void setFirstPassword(String firstPassword) {
        this.firstPassword = firstPassword;
    }

    public String getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(String secondPassword) {
        this.secondPassword = secondPassword;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
