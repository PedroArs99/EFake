package com.efake.dao;

import com.efake.entity.Categoria;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author PedroArenas
 * @author Carlos (findByName)
 */
@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> {

    @PersistenceContext(unitName = "EfakePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }
    
    public Categoria findByName(String nombre){
        Categoria c;
        Query q = this.getEntityManager().createNamedQuery("Categoria.findByNombre");
        q.setParameter("nombre", nombre);
        c = (Categoria) q.getSingleResult();
        return c;
    }
    
}
