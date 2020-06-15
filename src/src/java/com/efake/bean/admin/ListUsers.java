package com.efake.bean.admin;

import com.efake.bean.session.Transport;
import com.efake.dto.UsuarioDTO;
import com.efake.service.UsuarioService;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.MoveEvent;

/**
 *
 * @author PedroArenas
 */
@Named(value = "listUsers")
@Dependent
public class ListUsers {

    //Beans
    @Inject
    private Transport transportBean;
    //Services
    @EJB
    private UsuarioService usuarioService;

    //Attributes
    private List<UsuarioDTO> userList;
    private Date lastLoginFilter;

    //Constructor
    public ListUsers() {
    }

    @PostConstruct
    public void init() {
        userList = usuarioService.findByEsAdminAndRange(0, 1, 16);
        transportBean.setStatus(null);
    }

    //Bean methods
    public String deleteUser(UsuarioDTO user) {
        usuarioService.delete(user);
        transportBean.setStatus("Deleted user");
        return "userList.jsf?faces-redirect=true";
    }
    
    //Getter & Setters
    public List<UsuarioDTO> getUserList() {
        return userList;
    }

    public Date getLastLoginFilter() {
        return lastLoginFilter;
    }

    public void setLastLoginFilter(Date lastLoginFilter) {
        this.lastLoginFilter = lastLoginFilter;
    }
    
    

}
