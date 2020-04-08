/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.dao;

import com.efake.entity.Producto;
import com.efake.entity.Valoracion;
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
public class ValoracionFacade extends AbstractFacade<Valoracion> {

    @PersistenceContext(unitName = "EfakePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ValoracionFacade() {
        super(Valoracion.class);
    }
    
    public List<Valoracion> findByProducto(Producto producto){
        List<Valoracion> listaValoracion;
        Query q = this.getEntityManager().createNamedQuery("Valoracion.findByProducto");
        q.setParameter("producto", producto);
        listaValoracion = q.getResultList();
        return listaValoracion;
    }
    
}
