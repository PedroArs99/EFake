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
    private List<SubCategoriaDTO> listaSubcategorias;
    
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

    public List<SubCategoriaDTO> getListaSubcategorias() {
        return listaSubcategorias;
    }

    public void setListaSubcategorias(List<SubCategoriaDTO> listaSubcategorias) {
        this.listaSubcategorias = listaSubcategorias;
    }
    
}
