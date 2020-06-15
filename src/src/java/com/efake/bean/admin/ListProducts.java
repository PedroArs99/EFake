package com.efake.bean.admin;

import com.efake.bean.session.Transport;
import com.efake.dto.ProductoDTO;
import com.efake.service.ProductoService;
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
@Named(value = "listProducts")
@RequestScoped
public class ListProducts {
    //Beans
    @Inject
    private Transport transportBean;
    //Services
    @EJB
    private ProductoService productService;

    //Attributes
    private List<ProductoDTO> productList;

    //Constructor
    public ListProducts() {
    }

    @PostConstruct
    public void init() {
        productList = productService.findAllInRange(1, 16);
        transportBean.setStatus(null);
    }

    //Bean methods
    public String deleteProduct(ProductoDTO product) {
        productService.remove(product.getId());
        transportBean.setStatus("Product Deleted");
        
        return "productList.jsf?faces-redirect=true";
    }
    
    //Getter & Setters
    public List<ProductoDTO> getProductList() {
        return productList;
    }

    
    
}
