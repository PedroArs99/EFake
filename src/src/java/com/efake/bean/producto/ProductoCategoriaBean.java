/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.bean.producto;

import com.efake.bean.session.Transport;
import com.efake.dto.CategoriaDTO;
import com.efake.dto.ProductoDTO;
import com.efake.dto.SubCategoriaDTO;
import com.efake.service.ProductoService;
import com.efake.service.SubCategoryService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Carlos Diestro
 */
@Named(value = "productoCategoriaBean")
@RequestScoped
public class ProductoCategoriaBean {

    @EJB
    private SubCategoryService subCategoryService;

    @EJB
    private ProductoService productoService;

    @Inject
    private Transport transport;
    
    private CategoriaDTO categoria;
    private List<ProductoDTO> listaProductos;
    private List<SubCategoriaDTO> listaSubCategoriasByCategoria;
    
    public ProductoCategoriaBean() {
    }
    
    
    @PostConstruct
    public void init(){
        CategoriaDTO categoriaSeleccionada = transport.getCategoriaSeleccionada();
        
        if(categoriaSeleccionada != null){
            categoria = categoriaSeleccionada;
            listaProductos = productoService.findByCategoria(categoria);
            listaSubCategoriasByCategoria = this.subCategoryService.finByCategory(categoriaSeleccionada);
        } 
    }
    

    public List<ProductoDTO> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<ProductoDTO> listaProductosByCategoria) {
        this.listaProductos = listaProductosByCategoria;
    }

    public List<SubCategoriaDTO> getListaSubCategoriasByCategoria() {
        return listaSubCategoriasByCategoria;
    }

    public void setListaSubCategoriasByCategoria(List<SubCategoriaDTO> listaSubCategoriasByCategoria) {
        this.listaSubCategoriasByCategoria = listaSubCategoriasByCategoria;
    }
    
    public void doFiltrarBySubcategoria(SubCategoriaDTO subcategoria){
        this.listaProductos = this.productoService.findBySubCategoria(subcategoria);
    }
    
    
    
    
    
}
