/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.bean.producto;

import com.efake.dao.ProductoFacade;
import com.efake.dto.CategoriaDTO;
import com.efake.dto.ProductoDTO;
import com.efake.service.ProductoService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Carlos Diestro
 */
@Named(value = "productoCategoriaBean")
@RequestScoped
public class ProductoCategoriaBean {

    @EJB
    private ProductoService productoService;
    
    /**
     * Creates a new instance of ProductoCategoriaBean
     */
    private List<ProductoDTO> listaProductosByCategoria;
    
    public ProductoCategoriaBean() {
    }
    
    
    @PostConstruct
    public void init(){
        
    }
    
    public String doShowProductoByCategory(CategoriaDTO categoria) {
        listaProductosByCategoria = this.productoService.findByCategoria(categoria);
        
        return "productoCategoria";
    }

    public List<ProductoDTO> getListaProductosByCategoria() {
        return listaProductosByCategoria;
    }

    public void setListaProductosByCategoria(List<ProductoDTO> listaProductosByCategoria) {
        this.listaProductosByCategoria = listaProductosByCategoria;
    }
    
    
    
}
