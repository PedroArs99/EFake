/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.bean.producto;

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
 * @author Lorenzo
 */
@Named(value = "productoBuscarBean")
@RequestScoped
public class ProductoBuscarBean {
    @Inject
    private Transport transport;
    
    @EJB
    private ProductoService ps;
    
    private String filtro;
    private List<ProductoDTO> productList;
    

    /**
     * Creates a new instance of ProductoBuscarBean
     */
    public ProductoBuscarBean() {
    }
    
    public void setProductList(List<ProductoDTO> pl){
        this.productList = pl;
    }
    
    public List<ProductoDTO> getProductList(){
        return this.productList;
    }
    
    public void setFiltro(String filtro){
        this.filtro = filtro;
    }
    
    public String getFiltro(){
        return this.filtro;
    }
    
    @PostConstruct
    public void init(){
        if(transport.getSearch() != null){
            filtro = transport.getSearch();
            productList = ps.findByFilter(filtro);
        }
    }
    
    public String doBuscar(){
        filtro = transport.getSearch();
        this.productList = ps.findByFilter(filtro);
        
        return "productSearch?faces-redirect=true";
    }
    
}
