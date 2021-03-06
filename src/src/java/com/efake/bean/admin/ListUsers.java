package com.efake.bean.admin;

import com.efake.bean.login.UsuarioBean;
import com.efake.bean.session.Transport;
import com.efake.dto.UsuarioDTO;
import com.efake.service.UsuarioService;
import java.util.Date;
import java.util.List;
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
@Named(value = "listUsers")
@RequestScoped
public class ListUsers {

    private static final Logger LOG = Logger.getLogger(ListUsers.class.getName());

    //Beans
    @Inject
    private Transport transportBean;
    @Inject
    private UsuarioBean sessionBean;

    //Services
    @EJB
    private UsuarioService userService;

    //Attributes
    private List<UsuarioDTO> userList;
    private String status;

    //Pagination
    private final int MAX_PAGE_SIZE = 10;
    private int numberOfPages;
    private int currentPage;

    //Filters
    private String emailFilter;
    private String nameFilter;
    private String surnameFilter;
    private Integer ageFilter;
    private Date lastLoginFilter;

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
            //Calculate number of pages
            int usersCount = userService.countByEsAdmin(0);
            numberOfPages = usersCount / MAX_PAGE_SIZE;
            if (usersCount % MAX_PAGE_SIZE != 0) {
                numberOfPages++;
            }

            //Initialize Page 1
            this.changePage(1);

            //Show Success Message
            String transportStatus = transportBean.getStatus();
            if (transportStatus != null) {
                this.status = transportStatus;
                transportBean.setStatus(null);
            }
        }
    }

    //Bean methods
    public String deleteUser(UsuarioDTO user) {
        userService.delete(user);
        transportBean.setStatus("User deleted");
        return "userList.jsf?faces-redirect=true";
    }

    public void filterUsers() {
        this.userList = userService.findByFilters(emailFilter, nameFilter, surnameFilter, ageFilter, lastLoginFilter);
    }

    public void changePage(int pageNumber) {
        currentPage = pageNumber;
        userList = userService.findByEsAdminAndRange(0, pageNumber, MAX_PAGE_SIZE);
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

}
