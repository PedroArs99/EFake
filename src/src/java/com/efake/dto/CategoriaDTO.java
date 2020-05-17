package com.efake.dto;

import java.util.List;

/**
 *
 * @author PedroArenas
 */
public class CategoriaDTO {
    //Attributes
    private Integer id;
    private String nombre;
    private List<ProductoDTO> productoList;
    private List<Subcategoria> subcategoriaList;

    public CategoriaDTO() {
    }
    
    //Getters & setters
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

    public List<ProductoDTO> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<ProductoDTO> productoList) {
        this.productoList = productoList;
    }

    public List<Subcategoria> getSubcategoriaList() {
        return subcategoriaList;
    }

    public void setSubcategoriaList(List<Subcategoria> subcategoriaList) {
        this.subcategoriaList = subcategoriaList;
    }
    
}
