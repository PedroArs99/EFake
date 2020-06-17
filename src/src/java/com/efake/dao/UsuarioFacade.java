 package com.efake.dao;

import com.efake.entity.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author PedroArenas
 * @author Laura (findByCorreo)
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
    
    public List<Usuario> findByUltimaEntrada(Date date){
        Query q;
        List<Usuario> res;
        
        q = this.getEntityManager().createNamedQuery("Usuario.findByUltimaEntrada");
        q.setParameter("fecha", date);
        res = q.getResultList();
        
        return res;
    }
    
    public List<Object[]> countByDate(Date start, Date end){
        Query q;
        List<Object[]> res;
        
        q = this.getEntityManager().createNamedQuery("Usuario.CountByDate");
        q.setParameter("start", start);
        q.setParameter("end", end);
        res = q.getResultList();
        
        return res;
    }

    public List<Usuario> findByFilters(String email) {
        //Create Criteria Builder
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
       
        //Create Query
        CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
        
        //Initialize Query to all users in DB
        Root<Usuario> allUsers = query.from(Usuario.class);
        
        //Add Conditions
        Predicate allConditions;
        
        //1. Only non admin users
        Predicate nonAdmin = cb.equal(allUsers.get("esAdmin"), 0);
        allConditions = nonAdmin;
        
        //2. By email
        if(email != null){
            Predicate emailFilter = cb.like(allUsers.get("correo"), '%' + email + '%');
            allConditions = cb.and(allConditions,emailFilter);
        }
        
        
        
        //Dump query to DB
        query.select(allUsers).where(allConditions);
        //Get Results
        List<Usuario> userList = this.getEntityManager().createQuery(query).getResultList();
        
        return userList;
    }
}
