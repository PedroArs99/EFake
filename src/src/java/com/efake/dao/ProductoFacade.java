/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.dao;

import com.efake.entity.Categoria;
import com.efake.entity.Producto;
import com.efake.entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author PedroArenas
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "EfakePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
    public List<Producto> findByCategoria(Categoria categoria){
        List<Producto> listaProducto;
        Query q = this.getEntityManager().createNamedQuery("Producto.findByCategoria");
        q.setParameter("categoria", categoria);
        listaProducto = q.getResultList();
        return listaProducto;
    }
    
    
    public List<Producto> findRange(int page, int pageSize) {
        Query q;
        List<Producto> productList;
        
        q= this.getEntityManager().createNamedQuery("Producto.findAll");
        q.setMaxResults(pageSize);
        q.setFirstResult(page*pageSize);
        productList = q.getResultList();
        
        return productList;
    }
}
