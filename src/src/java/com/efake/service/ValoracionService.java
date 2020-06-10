package com.efake.service;

import com.efake.dao.ProductoFacade;
import com.efake.dao.UsuarioFacade;
import com.efake.dao.ValoracionFacade;
import com.efake.entity.Producto;
import com.efake.entity.Usuario;
import com.efake.entity.Valoracion;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;



/**
 *
 * @author Carlos Diestro
 */
@Stateless
public class ValoracionService {
    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    ProductoFacade productoFacade;
    @EJB
    ValoracionFacade valoracionFacade;
    
    public void newRating(Integer idUsuario, Integer idProducto, Integer rating, String comment, Date date){
        Usuario usuario = usuarioFacade.find(idUsuario);
        Producto producto = productoFacade.find(idProducto);
        Valoracion review = new Valoracion();
        review.setCliente(usuario);
        review.setProductoValorado(producto);
        review.setPuntuacion(rating);
        review.setComentario(comment);
        review.setFecha(date);
        
        valoracionFacade.create(review);
        producto.getValoracionList().add(review);
        productoFacade.edit(producto);
    }
}
