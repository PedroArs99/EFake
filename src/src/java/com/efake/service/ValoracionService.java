/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author carlo
 */
@Stateless
public class ValoracionService {
    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    ProductoFacade productoFacade;
    @EJB
    ValoracionFacade valoracionFacade;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
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
