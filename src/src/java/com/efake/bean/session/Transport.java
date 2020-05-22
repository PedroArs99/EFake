/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.bean.session;

import com.efake.dto.CategoriaDTO;
import com.efake.dto.SubCategoriaDTO;
import com.efake.service.CategoryService;
import com.efake.service.SubCategoryService;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author PedroArenas
 */
@Named(value = "transport")
@SessionScoped
public class Transport implements Serializable {
    //Services
    @EJB
    private CategoryService categoriaService;
    @EJB
    private SubCategoryService subCategoriaService;
    
    //Attributes
    private List<CategoriaDTO> listaCategoria;
    private List<SubCategoriaDTO> listaSubCategoria;
    private String status;
    
    //Constructor
    public Transport() {
    }
    
    @PostConstruct
    public void init(){
        this.listaCategoria = this.categoriaService.findAll();
        this.listaSubCategoria = this.subCategoriaService.findAll();
    }
    
    public List<CategoriaDTO> getCategorias(){
        return listaCategoria;
    }
    
    public List<SubCategoriaDTO> getSubCategorias(){
        return listaSubCategoria;
    }
    
    public List<SubCategoriaDTO> getSubCategoriasByCategoria(CategoriaDTO c){
        return this.subCategoriaService.finByCategory(c);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    

}
