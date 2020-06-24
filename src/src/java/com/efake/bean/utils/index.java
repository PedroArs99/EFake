package com.efake.bean.utils;

import com.efake.dto.ProductoDTO;
import com.efake.service.ProductoService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author PedroArenas
 */
@Named(value = "index")
@RequestScoped
public class index {
    //Services
    @EJB
    private ProductoService productoService;
    
    //Constraints
    private final int NUMBER_OF_MOST_RATED = 5;
    
    //Attributes
    private List<ProductoDTO> mostRatedProductList;
    
    //Constructor
    @PostConstruct
    public void init(){
        this.mostRatedProductList = productoService.findMostRated(NUMBER_OF_MOST_RATED);
    }
    
    //Getters & Setters
    public List<ProductoDTO> getMostRatedProductList() {
        return mostRatedProductList;
    }

    public void setMostRatedProductList(List<ProductoDTO> mostRatedProductList) {
        this.mostRatedProductList = mostRatedProductList;
    }
}
