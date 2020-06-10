package com.efake.dao;

import com.efake.entity.Categoria;
import com.efake.entity.Subcategoria;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author PedroArenas
 * @author Carlos (findByCategory)
 */
@Stateless
public class SubcategoriaFacade extends AbstractFacade<Subcategoria> {

    @PersistenceContext(unitName = "EfakePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubcategoriaFacade() {
        super(Subcategoria.class);
    }
    
    public List<Subcategoria> findByCategory(Categoria c){
        Query q;
        List<Subcategoria> result;
        
        q = this.getEntityManager().createNamedQuery("Subcategoria.findByCategoria");
        q.setParameter("categoria", c);
        result = q.getResultList();
        
        return result;
    }
    
}
