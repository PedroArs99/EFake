package com.efake.dao;

import com.efake.entity.Categoria;
import com.efake.entity.Producto;
import com.efake.entity.Subcategoria;
import com.efake.entity.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author PedroArenas
 * @author Carlos (findByCategoria)
 * @author Lorenzo (findByFilter)
 * @author Laura (findByOwner)
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
    
    public List<Producto> findBySubCategoria(Subcategoria subcategoria){
        List<Producto> listaProducto;
        Query q = this.getEntityManager().createNamedQuery("Producto.findBySubCategoria");
        q.setParameter("subcategoria", subcategoria);
        listaProducto = q.getResultList();
        
        return listaProducto;
    }
    
    public List<Producto> findByOwner(Usuario id){
        Query q;
        List<Producto> productList;
        
        q = this.getEntityManager().createNamedQuery("Producto.findByOwner");
        q.setParameter("owner", id);
        productList = q.getResultList();
        
        return productList;
    }
    
    public List<Producto> findByFilter(String words){
        List<Producto> productList;
        Query q = this.getEntityManager().createNamedQuery("Producto.findByFilter");
        q.setParameter("words", "%" + words + "%");
        productList = q.getResultList();
        return productList;
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

    public List<Producto> findMostRated(int limit){
        Query q;
        List<Producto> productList;
        
        q= this.getEntityManager().createNamedQuery("Producto.findSortedByRatingsNumber");
        q.setMaxResults(limit);
        productList = q.getResultList();
        
        return productList;
    }
    
    public List<Producto> findByFecha(Date date){
        Query q;
        List<Producto> res;
        
        q = this.getEntityManager().createNamedQuery("Producto.findByFecha");
        q.setParameter("fecha", date);
        res = q.getResultList();
        
        return res;
    }
    
    public List<Object[]> countByDate(Date start, Date end){
        Query q;
        List<Object[]> res;
        
        q = this.getEntityManager().createNamedQuery("Producto.CountByDate");
        q.setParameter("start", start);
        q.setParameter("end", end);
        res = q.getResultList();
        
        return res;
    }
}
