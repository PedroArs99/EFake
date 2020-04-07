/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.dao;

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
    public List<Usuario> findByEsAdmin(int esAdmin){
        Query q;
        List<Usuario> userList;
        
        q= this.getEntityManager().createNamedQuery("Usuario.findByEsAdmin");
        q.setParameter("esAdmin", esAdmin);
        userList = q.getResultList();
        
        return userList;
    }
    
    public Usuario findByCorreo(String correo){
        Query q;
        Usuario res;
        
        q = this.getEntityManager().createNamedQuery("Usuario.findByCorreo");
        q.setParameter("correo", correo);
        res = (Usuario) q.getSingleResult();
        
        return res;
    }
    
}
