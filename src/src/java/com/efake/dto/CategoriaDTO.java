package com.efake.dto;


/**
 *
 * @author PedroArenas
 */
public class CategoriaDTO {
    //Attributes
    private Integer id;
    private String nombre;
    
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
}
