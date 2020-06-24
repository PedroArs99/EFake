/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.bean.producto;

import com.efake.bean.login.UsuarioBean;
import com.efake.bean.session.Transport;
import com.efake.dto.CategoriaDTO;
import com.efake.dto.ProductoDTO;
import com.efake.dto.SubCategoriaDTO;
import com.efake.dto.UsuarioDTO;
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
 * @author Carlos Diestro, Laura Rosón (Arreglos DTO)
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
    @Inject
    private UsuarioBean usuarioBean;
    
    private UsuarioDTO usuario;
    private CategoriaDTO categoria;
    private List<ProductoDTO> listaProductos, listaMisProductos;
    private List<SubCategoriaDTO> listaSubCategoriasByCategoria;
    private String status;
    
    
    
    
    @PostConstruct
    public void init(){
        CategoriaDTO categoriaSeleccionada = transport.getCategoriaSeleccionada();
        
        if(categoriaSeleccionada != null){
            categoria = categoriaSeleccionada;
            listaProductos = productoService.findByCategoria(categoria);
            listaSubCategoriasByCategoria = this.subCategoryService.finByCategory(categoriaSeleccionada);
        } 
        
        usuario = usuarioBean.getUsuario();
        if(usuario != null){
           listaMisProductos = this.productoService.findByUsuario(usuario); 
        }
        
        this.status = transport.getStatus();
        transport.setStatus(null);
        
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

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    public List<ProductoDTO> getListaMisProductos() {
        return listaMisProductos;
    }

    public void setListaMisProductos(List<ProductoDTO> listaMisProductos) {
        this.listaMisProductos = listaMisProductos;
    }
    
    public void doFiltrarBySubcategoria(SubCategoriaDTO subcategoria){
        this.listaProductos = this.productoService.findBySubCategoria(subcategoria);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
