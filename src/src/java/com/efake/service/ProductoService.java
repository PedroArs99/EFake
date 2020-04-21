package com.efake.service;

import com.efake.dao.ProductoFacade;
import com.efake.entity.Producto;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author PedroArenas
 */
@Stateless
public class ProductoService {

    @EJB
    ProductoFacade productoFacade;

    public List<Producto> getMostRated(int howMany) {
        return productoFacade.findMostRated(5);
    }
}
