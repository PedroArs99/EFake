package com.efake.bean.producto;

import com.efake.bean.session.Transport;
import com.efake.dao.ProductoFacade;
import com.efake.dto.ProductoDTO;
import com.efake.dto.ValoracionDTO;
import com.efake.entity.Valoracion;
import com.efake.service.ProductoService;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Carlos Diestro
 */
@Named(value = "productoBean")
@RequestScoped
public class ProductoBean {
    @Inject
    private Transport transport;
    
    @EJB
    private ProductoService productoService;

    @EJB
    private ProductoFacade productoFacade;
    
    private ProductoDTO producto;
    private double mediaValoraciones;
    private List<Valoracion> listaValoraciones;
    private Map<Integer, Double> ratings;
    private boolean valorado;
    
    public ProductoBean() {
    }
    
    @PostConstruct
    public void init(){
        producto = transport.getProductoSeleccionado();
        //Usuario user = this.usuarioSesion.getUser();
        mediaValoraciones = this.productoService.getMeanRating(producto.getId());
        listaValoraciones = producto.getListaValoraciones();
        ratings = this.productoService.getRatings(producto.getId());
        //valorado = this.productoService.rated(listaValoraciones, user);
    }

    public double getMediaValoraciones() {
        return mediaValoraciones;
    }

    public void setMediaValoraciones(double mediaValoraciones) {
        this.mediaValoraciones = mediaValoraciones;
    }

    public List<Valoracion> getListaValoraciones() {
        return listaValoraciones;
    }

    public void setListaValoraciones(List<Valoracion> listaValoraciones) {
        this.listaValoraciones = listaValoraciones;
    }

    public Map<Integer, Double> getRatings() {
        return ratings;
    }

    public void setRatings(Map<Integer, Double> ratings) {
        this.ratings = ratings;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public boolean isValorado() {
        return valorado;
    }

    public void setValorado(boolean valorado) {
        this.valorado = valorado;
    }
    
    
    
    
}