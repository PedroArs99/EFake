/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.service;

import com.efake.dao.CategoriaFacade;
import com.efake.dao.ProductoFacade;
import com.efake.dto.CategoriaDTO;
import com.efake.entity.Categoria;
import com.efake.entity.Producto;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author carlo
 */
@Stateless
public class CategoryProductService {
    @EJB
    CategoriaFacade categoryFacade;
    @EJB
    ProductoFacade productoFacade;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public CategoriaDTO getCategory(String category){
        Categoria categoria = categoryFacade.find(category);
        return categoria.getDTO();
    }
    
    public List<Producto> getListaCategoriaProducto(String category){
        Categoria categoria = categoryFacade.find(category);
        return productoFacade.findByCategoria(categoria);       
    }
}
