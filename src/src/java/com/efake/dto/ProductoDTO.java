package com.efake.dto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.efake.entity.Categoria;
import com.efake.entity.Subcategoria;
import com.efake.entity.Usuario;
import com.efake.entity.Valoracion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author PedroArenas
 */
public class ProductoDTO {
    private String nombre;
    private String descripcion;
    private double precio;
    private String imagen;
    private Date fecha;
    private List<KeywordsDTO> keywordsList;
    private List<Valoracion> valoracionList;
    private Categoria categoria;
    private Usuario owner;
    private Subcategoria subcategoria;
    
    public ProductoDTO() {
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

    public List<KeywordsDTO> getKeywordsList() {
        return keywordsList;
    }

    public void setKeywordsList(List<KeywordsDTO> keywordsList) {
        this.keywordsList = keywordsList;
    }

    public List<Valoracion> getValoracionList() {
        return valoracionList;
    }

    public void setValoracionList(List<Valoracion> valoracionList) {
        this.valoracionList = valoracionList;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getOwner() {
        return owner;
    }

    public void setOwner(Usuario owner) {
        this.owner = owner;
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }
    
}
