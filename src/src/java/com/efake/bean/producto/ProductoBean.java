package com.efake.bean.producto;

import com.efake.bean.login.UsuarioBean;
import com.efake.bean.session.Transport;
import com.efake.dao.CategoriaFacade;
import com.efake.dto.KeywordsDTO;
import com.efake.dto.ProductoDTO;
import com.efake.dto.UsuarioDTO;
import com.efake.dto.ValoracionDTO;
import com.efake.entity.Valoracion;
import com.efake.service.ProductoService;
import com.efake.service.ValoracionService;
import java.util.ArrayList;
import java.util.Date;
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
    
    @Inject
    private UsuarioBean usuarioBean;
    
    @EJB
    private ProductoService productoService;
    
    @EJB
    private ValoracionService valoracionService;
    
    
    private ProductoDTO producto;
    private double mediaValoraciones;
    private List<ValoracionDTO> listaValoraciones;
    private List<Integer> ratings;
    private boolean valorado;
    private String imagen;
    private String nombre;
    private String descripcion;
    private List<KeywordsDTO> listakeywords;
    private Double precio;
    private Integer id;
    private UsuarioDTO owner;
    private UsuarioDTO user;
 
    private String comentario;
    private Integer puntuacion;
    
    public ProductoBean() {
    }
    
    @PostConstruct
    public void init(){
        Map<Integer, Integer> ratingsDictionary;
        producto = transport.getProductoSeleccionado();
        
        if(this.usuarioBean.getUsuario() != null){
            user = this.usuarioBean.getUsuario();
        }
            
        if(!producto.getListaValoraciones().isEmpty()){
            listaValoraciones = producto.getListaValoraciones();
            mediaValoraciones = this.productoService.getMeanRating(producto.getId());
            ratingsDictionary = this.productoService.getRatings(producto.getId());
            ratings = new ArrayList<>();
            for(Integer i : ratingsDictionary.values()){
                ratings.add(i);
            }
        } else {
            mediaValoraciones = 0.0;
            ratings = new ArrayList<>();
            for(int i = 0; i < 6; i++){
                ratings.add(0);
            }
        }
        
        if(usuarioBean.getUsuario() != null && usuarioBean.getUsuario().getEsAdmin() != 1){
           UsuarioDTO user = this.usuarioBean.getUsuario(); 
           valorado = this.productoService.rated(listaValoraciones, user);
        } else {
            valorado = true;
        }
        
        imagen = producto.getImagen();
        descripcion = producto.getDescripcion();
        nombre = producto.getNombre();
        listakeywords = producto.getListaKeywords();
        precio = this.productoService.formatearDecimales(producto.getPrecio());
        id = producto.getId();
        owner = producto.getOwner();
        
    }
       
    public Object[] createDummyArray(int size){
        return new Object[size];
    }
    
    public double getMediaValoraciones() {
        return mediaValoraciones;
    }

    public void setMediaValoraciones(double mediaValoraciones) {
        this.mediaValoraciones = mediaValoraciones;
    }

    public List<ValoracionDTO> getListaValoraciones() {
        return listaValoraciones;
    }

    public void setListaValoraciones(List<ValoracionDTO> listaValoraciones) {
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

    public List<KeywordsDTO> getListakeywords() {
        return listakeywords;
    }

    public void setListakeywords(List<KeywordsDTO> listakeywords) {
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

    public UsuarioDTO getOwner() {
        return owner;
    }

    public void setOwner(UsuarioDTO owner) {
        this.owner = owner;
    }
    
    
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public UsuarioDTO getUser() {
        return user;
    }

    public void setUser(UsuarioDTO user) {
        this.user = user;
    }
        
    public String doCancel(){
        return "producto?faces-redirect=true";
    }
    
    public String doReview(Integer idProducto, Integer idUsuario){
        this.valoracionService.newRating(idUsuario, idProducto, this.puntuacion, this.comentario, new Date());
        
        
        return transport.redirectProducto(producto.getId());
    }
}