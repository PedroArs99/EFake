package com.efake.service;

import com.efake.dao.ProductoFacade;
import com.efake.dao.UsuarioFacade;
import com.efake.dao.ValoracionFacade;
import com.efake.dto.ValoracionDTO;
import com.efake.entity.Producto;
import com.efake.entity.Usuario;
import com.efake.entity.Valoracion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    
    private List<ValoracionDTO> convertToDTO (List<Valoracion> listaValoracion) {
        List<ValoracionDTO> listaDTO = null;
        if (listaValoracion != null) {
            listaDTO = new ArrayList<>();
            for (Valoracion valoracion: listaValoracion) {
                listaDTO.add(valoracion.getDTO());
            }
        }
        return listaDTO;
    }
    
    public List<ValoracionDTO> getRatingList(Integer idProducto){
        Producto producto = productoFacade.find(idProducto);
        List<Valoracion> listValoraciones = producto.getValoracionList();
        List<ValoracionDTO> listValoracionesDTO = this.convertToDTO(listValoraciones);
                
        return listValoracionesDTO;
    }
    
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
