package com.efake.bean.admin;

import com.efake.bean.session.Transport;
import com.efake.dto.UsuarioDTO;
import com.efake.service.UsuarioService;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author PedroArenas
 */
@Named(value = "listUsers")
@RequestScoped
public class ListUsers {

    //Beans
    @Inject
    private Transport transportBean;
    //Services
    @EJB
    private UsuarioService userService;

    //Attributes
    private List<UsuarioDTO> userList;
    private String emailFilter;

    //Constructor
    @PostConstruct
    public void init() {
        userList = userService.findByEsAdminAndRange(0, 1, 16);
        transportBean.setStatus(null);
    }

    //Bean methods
    public String deleteUser(UsuarioDTO user) {
        userService.delete(user);
        transportBean.setStatus("Deleted user");
        return "userList.jsf?faces-redirect=true";
    }
    
    public void filterUsers(){
        this.userList = userService.findByFilters(emailFilter);
    }
    
    //Getter & Setters
    public List<UsuarioDTO> getUserList() {
        return userList;
    }

    public String getEmailFilter() {
        return emailFilter;
    }

    public void setEmailFilter(String emailFilter) {
        this.emailFilter = emailFilter;
    }

    
    
}
