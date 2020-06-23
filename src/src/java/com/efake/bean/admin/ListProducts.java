package com.efake.bean.admin;

import com.efake.bean.login.UsuarioBean;
import com.efake.bean.session.Transport;
import com.efake.dto.CategoriaDTO;
import com.efake.dto.ProductoDTO;
import com.efake.dto.SubCategoriaDTO;
import com.efake.dto.UsuarioDTO;
import com.efake.service.ProductoService;
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
@Named(value = "listProducts")
@RequestScoped
public class ListProducts {

    private static final Logger LOG = Logger.getLogger(ListProducts.class.getName());

    //Beans
    @Inject
    private Transport transportBean;
    @Inject
    private UsuarioBean sessionBean;

    //Services
    @EJB
    private ProductoService productService;

    //Attributes
    private List<ProductoDTO> productList;
    private String status;

    //Pagination
    private final int MAX_PAGE_SIZE = 10;
    private int numberOfPages;
    private int currentPage;

    //Filters
    private String nameFilter;
    private Date dateFilter;
    private Integer categoryIdFilter;
    private SubCategoriaDTO subcategoryFilter;
    private String ownerEmailFilter;

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
            int usersCount = productService.count();
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
    public String deleteProduct(ProductoDTO product) {
        productService.remove(product.getId());
        transportBean.setStatus("Product Deleted");

        return "productList.jsf?faces-redirect=true";
    }

    public void filterProducts() {
        CategoriaDTO categoryFilter = new CategoriaDTO();
        categoryFilter.setId(this.categoryIdFilter);
        
        this.productList = productService.findByMultipleFilters(nameFilter, dateFilter,categoryFilter);       
    }

    public void changePage(int pageNumber) {
        currentPage = pageNumber;

        productList = productService.findAllInRange(pageNumber, MAX_PAGE_SIZE);
    }

    //Getter & Setters
    public List<ProductoDTO> getProductList() {
        return productList;
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

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    public Date getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(Date dateFilter) {
        this.dateFilter = dateFilter;
    }

    public Integer getCategoryIdFilter() {
        return categoryIdFilter;
    }

    public void setCategoryIdFilter(Integer categoryIdFilter) {
        this.categoryIdFilter = categoryIdFilter;
    }


    public SubCategoriaDTO getSubcategoryFilter() {
        return subcategoryFilter;
    }

    public void setSubcategoryFilter(SubCategoriaDTO subcategoryFilter) {
        this.subcategoryFilter = subcategoryFilter;
    }

    public String getOwnerEmailFilter() {
        return ownerEmailFilter;
    }

    public void setOwnerEmailFilter(String ownerEmailFilter) {
        this.ownerEmailFilter = ownerEmailFilter;
    }

}
