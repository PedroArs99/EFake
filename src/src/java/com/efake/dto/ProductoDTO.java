package com.efake.dto;

import com.efake.entity.Keywords;
import com.efake.entity.Valoracion;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Carlos Diestro
 */
public class ProductoDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String imagen;
    private Date fecha;
    private List<KeywordsDTO> listaKeywords;
    private CategoriaDTO categoria;
    private UsuarioDTO owner;
    private SubCategoriaDTO subcategoria;
    private List<ValoracionDTO> listaValoraciones;
    private int estrella1;
    private int estrella2;
    private int estrella3;
    private int estrella4;
    private int estrella5;
    
    public ProductoDTO(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<KeywordsDTO> getListaKeywords() {
        List<KeywordsDTO> res = new ArrayList<>();
        if (this.listaKeywords==null){
            return res;
        }
        for (KeywordsDTO k : this.listaKeywords){
            // Add this to break infinite recursion
            res.add((k));
        }
       return res;
    }

    public void setListaKeywords(List<KeywordsDTO> listaKeywords) {
        this.listaKeywords = listaKeywords;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    public UsuarioDTO getOwner() {
        return owner;
    }

    public void setOwner(UsuarioDTO owner) {
        this.owner = owner;
    }

    public SubCategoriaDTO getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(SubCategoriaDTO subcategoria) {
        this.subcategoria = subcategoria;
    }

    public List<ValoracionDTO> getListaValoraciones() {
        return listaValoraciones;
    }

    public void setListaValoraciones(List<ValoracionDTO> listaValoraciones) {
        this.listaValoraciones = listaValoraciones;
    }

    public int getEstrella1() {
        return estrella1;
    }

    public void setEstrella1(int estrella1) {
        this.estrella1 = estrella1;
    }

    public int getEstrella2() {
        return estrella2;
    }

    public void setEstrella2(int estrella2) {
        this.estrella2 = estrella2;
    }

    public int getEstrella3() {
        return estrella3;
    }

    public void setEstrella3(int estrella3) {
        this.estrella3 = estrella3;
    }

    public int getEstrella4() {
        return estrella4;
    }

    public void setEstrella4(int estrella4) {
        this.estrella4 = estrella4;
    }

    public int getEstrella5() {
        return estrella5;
    }

    public void setEstrella5(int estrella5) {
        this.estrella5 = estrella5;
    }
    
    public List<Keywords> getlistakeywords(){
        List<Keywords> res = new ArrayList<>();
        if (this.listaKeywords==null){
            return res;
        }
        for (KeywordsDTO k : this.listaKeywords){
            // Add this to break infinite recursion
            res.add(new Keywords(k));
        }
       return res;
                
    }
    
    public List<Valoracion> getlistavaloraciones(){
        List<Valoracion> res = new ArrayList<>();
        for (ValoracionDTO k : this.listaValoraciones){
            res.add(new Valoracion(k));
        }
       return res;
                
    }
    
}
