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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

    public List<Producto> findByCategoria(Categoria categoria) {
        List<Producto> listaProducto;
        Query q = this.getEntityManager().createNamedQuery("Producto.findByCategoria");
        q.setParameter("categoria", categoria);
        listaProducto = q.getResultList();
        return listaProducto;
    }

    public List<Producto> findBySubCategoria(Subcategoria subcategoria) {
        List<Producto> listaProducto;
        Query q = this.getEntityManager().createNamedQuery("Producto.findBySubCategoria");
        q.setParameter("subcategoria", subcategoria);
        listaProducto = q.getResultList();

        return listaProducto;
    }

    public List<Producto> findByOwner(Usuario id) {
        Query q;
        List<Producto> productList;

        q = this.getEntityManager().createNamedQuery("Producto.findByOwner");
        q.setParameter("owner", id);
        productList = q.getResultList();

        return productList;
    }

    public List<Producto> findByFilter(String words) {
        List<Producto> productList;
        Query q = this.getEntityManager().createNamedQuery("Producto.findByFilter");
        q.setParameter("words", "%" + words + "%");
        productList = q.getResultList();
        return productList;
    }

    public List<Producto> findRange(int page, int pageSize) {
        Query q;
        List<Producto> productList;

        q = this.getEntityManager().createNamedQuery("Producto.findAll");
        q.setMaxResults(pageSize);
        //Pages are 1 indexed but results are 0 indexed
        q.setFirstResult((page - 1) * pageSize);
        productList = q.getResultList();

        return productList;
    }

    public List<Producto> findMostRated(int limit) {
        Query q;
        List<Producto> productList;

        q = this.getEntityManager().createNamedQuery("Producto.findSortedByRatingsNumber");
        q.setMaxResults(limit);
        productList = q.getResultList();

        return productList;
    }

    public List<Producto> findByFecha(Date date) {
        Query q;
        List<Producto> res;

        q = this.getEntityManager().createNamedQuery("Producto.findByFecha");
        q.setParameter("fecha", date);
        res = q.getResultList();

        return res;
    }

    public List<Object[]> countByDate(Date start, Date end) {
        Query q;
        List<Object[]> res;

        q = this.getEntityManager().createNamedQuery("Producto.CountByDate");
        q.setParameter("start", start);
        q.setParameter("end", end);
        res = q.getResultList();

        return res;
    }

    public List<Producto> findByMultipleFilters(String name, Date date, Categoria category, String ownerEmail) {
        //Create Criteria Builder
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();

        //Create Query
        CriteriaQuery<Producto> query = cb.createQuery(Producto.class);

        //Initialize Query to all users in DB
        Root<Producto> allProducts = query.from(Producto.class);

        //Add Conditions
        Predicate allConditions = null;

        //1. By Name
        if (name != null) {
            Predicate nameFilter = cb.like(allProducts.get("nombre"), '%' + name + '%');
            allConditions = nameFilter;
        }

        //2. By date
        if (date != null) {
            Predicate dateFilter = cb.equal(allProducts.get("fecha"), date);

            if (allConditions == null) {
                allConditions = dateFilter;
            } else {
                allConditions = cb.and(allConditions, dateFilter);
            }

        }
        
        //3. By category
        if (category != null) {
            Predicate categoryFilter = cb.equal(allProducts.get("categoria"), category);

            if (allConditions == null) {
                allConditions = categoryFilter;
            } else {
                allConditions = cb.and(allConditions, categoryFilter);
            }

        }
        
        if (ownerEmail != null) {
            Predicate ownerFilter = cb.like(allProducts.get("owner").get("correo"), '%'+ ownerEmail +'%');

            if (allConditions == null) {
                allConditions = ownerFilter;
            } else {
                allConditions = cb.and(allConditions, ownerFilter);
            }

        }
        
        
        //Dump query to DB
        query.select(allProducts).where(allConditions);
        

        //Get Results
        List<Producto> productList = this.getEntityManager().createQuery(query).getResultList();

        return productList;
    }
}
