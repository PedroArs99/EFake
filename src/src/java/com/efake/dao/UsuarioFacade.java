 package com.efake.dao;

import com.efake.entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author PedroArenas
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {
    
    @PersistenceContext(unitName = "EfakePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    //Self Queries
    public Integer findByEsAdminCount(int esAdmin){
        Query q;
        List<Usuario> userList;
        
        q= this.getEntityManager().createNamedQuery("Usuario.findByEsAdmin");
        q.setParameter("esAdmin", esAdmin);
        userList = q.getResultList();
        
        return userList.size();
    }
    
    public List<Usuario> findByEsAdminAndRange(int esAdmin,int page, int pageSize){
        Query q;
        List<Usuario> userList;
        
        q= this.getEntityManager().createNamedQuery("Usuario.findByEsAdmin");
        q.setParameter("esAdmin", esAdmin);
        q.setMaxResults(pageSize);
        q.setFirstResult(page*pageSize);
        userList = q.getResultList();
        
        return userList;
    }
    
    public Usuario findByCorreo(String correo){
        Query q;
        Usuario res;
        
        q = this.getEntityManager().createNamedQuery("Usuario.findByCorreo");
        q.setParameter("correo", correo);
        try{
            res = (Usuario) q.getSingleResult();
        }
        catch(NoResultException ex){
            res = null;
        }
        
        return res;
    }
    
}
