package com.efake.dto;

import com.efake.entity.Subcategoria;

/**
 *
 * @author carlo
 */
public class SubCategoriaDTO {
    //Attributes
    private Integer id;
    private String nombre;
    private CategoriaDTO categoria;
    
    public SubCategoriaDTO() {
    }
    
    public SubCategoriaDTO(Subcategoria s){
        this.id = s.getId();
        this.nombre = s.getNombre();
        this.categoria = s.getCategoria().getDTO();
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

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }
    
}
