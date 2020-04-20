package com.efake.dao;

import com.efake.entity.Producto;
import com.efake.entity.Usuario;
import com.efake.entity.Valoracion;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author PedroArenas
 * @author Carlos (findByProducto)
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
    public List<Valoracion> findByFecha(Date date){
        Query q;
        List<Valoracion> res;
        
        q = this.getEntityManager().createNamedQuery("Valoracion.findByFecha");
        q.setParameter("fecha", date);
        res = q.getResultList();
        
        return res;
    }
    
}
