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

    //Filters
    private String emailFilter;
    private String nameFilter;
    private String surnameFilter;
    private Integer ageFilter;
    private Date lastLoginFilter;

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

    public void filterUsers() {
        this.userList = userService.findByFilters(emailFilter, nameFilter, surnameFilter, ageFilter, lastLoginFilter);
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

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    public String getSurnameFilter() {
        return surnameFilter;
    }

    public void setSurnameFilter(String surnameFilter) {
        this.surnameFilter = surnameFilter;
    }

    public Integer getAgeFilter() {
        return ageFilter;
    }

    public void setAgeFilter(Integer ageFilter) {
        this.ageFilter = ageFilter;
    }

    public Date getLastLoginFilter() {
        return lastLoginFilter;
    }

    public void setLastLoginFilter(Date lastLoginFilter) {
        this.lastLoginFilter = lastLoginFilter;
    }

}
