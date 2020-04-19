package com.efake.dao;

import com.efake.entity.Keywords;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author PedroArenas
 * @author Juan Medina (findOrCreate)
 */
@Stateless
public class KeywordsFacade extends AbstractFacade<Keywords> {

    @PersistenceContext(unitName = "EfakePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KeywordsFacade() {
        super(Keywords.class);
    }
    
    public Keywords findOrCreate(String kw){
        Keywords k = this.find(kw);
        
        if(k == null){
            k = new Keywords(kw);
            this.create(k);
        }
        
        return k;
    }
    
}
