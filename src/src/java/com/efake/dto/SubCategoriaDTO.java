package com.efake.dto;

/**
 *
 * @author carlo
 */
public class SubCategoriaDTO {
    //Attributes
    private Integer id;
    private String nombre;
    
    public SubCategoriaDTO() {
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
}
