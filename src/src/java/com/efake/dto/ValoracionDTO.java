package com.efake.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Carlos Diestro
 */
public class ValoracionDTO implements Serializable {
    private Integer id;
    private int puntuacion;
    private String comentario;
    private Date fecha;
    private UsuarioDTO cliente;
    private ProductoDTO productoValorado;

    public ValoracionDTO() {
    }

    public ValoracionDTO(Integer id) {
        this.id = id;
    }

    public ValoracionDTO(Integer id, int puntuacion, Date fecha) {
        this.id = id;
        this.puntuacion = puntuacion;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public UsuarioDTO getCliente() {
        return cliente;
    }

    public void setCliente(UsuarioDTO cliente) {
        this.cliente = cliente;
    }

    public ProductoDTO getProductoValorado() {
        return productoValorado;
    }

    public void setProductoValorado(ProductoDTO productoValorado) {
        this.productoValorado = productoValorado;
    }
    
}
