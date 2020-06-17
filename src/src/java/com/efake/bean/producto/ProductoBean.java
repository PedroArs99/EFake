package com.efake.bean.producto;

import com.efake.bean.session.Transport;
import com.efake.dao.CategoriaFacade;
import com.efake.dao.KeywordsFacade;
import com.efake.dao.ProductoFacade;
import com.efake.dto.ProductoDTO;
import com.efake.dto.ValoracionDTO;
import com.efake.entity.Categoria;
import com.efake.entity.Keywords;
import com.efake.entity.Usuario;
import com.efake.entity.Valoracion;
import com.efake.service.ProductoService;
import java.util.ArrayList;
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
    
    @EJB
    private CategoriaFacade categoriafacade;
    
    private ProductoDTO producto;
    private double mediaValoraciones;
    private List<Valoracion> listaValoraciones;
    private List<Integer> ratings;
    private boolean valorado;
    private String imagen;
    private String nombre;
    private String descripcion;
    private List<Keywords> listakeywords;
    private Double precio;
    private Integer id;
    private Usuario owner;
 
    
    public ProductoBean() {
    }
    
    @PostConstruct
    public void init(){
        Map<Integer, Integer> ratingsDictionary;
        producto = transport.getProductoSeleccionado();
        //Usuario user = this.usuarioSesion.getUser();
        mediaValoraciones = this.productoService.getMeanRating(producto.getId());
        listaValoraciones = producto.getListaValoraciones();
        ratingsDictionary = this.productoService.getRatings(producto.getId());
        ratings = new ArrayList<>();
        for(Integer i : ratingsDictionary.values()){
            ratings.add(i);
        }
        imagen = producto.getImagen();
        descripcion = producto.getDescripcion();
        nombre = producto.getNombre();
        listakeywords = producto.getListaKeywords();
        precio = producto.getPrecio();
        id = producto.getId();
        owner = producto.getOwner();
        
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

    public List<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(List<Integer> ratings) {
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
    
    public String renderStars(Valoracion comentario) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< comentario.getPuntuacion(); i++) {
            sb.append(    "&#9733;");
        }
        return sb.toString();
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Keywords> getListakeywords() {
        return listakeywords;
    }

    public void setListakeywords(List<Keywords> listakeywords) {
        this.listakeywords = listakeywords;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getOwner() {
        return owner;
    }

    public void setOwner(Usuario owner) {
        this.owner = owner;
    }
    
    
    
    
}