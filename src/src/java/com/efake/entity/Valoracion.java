/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PedroArenas
 */
@Entity
@Table(name = "VALORACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Valoracion.findAll", query = "SELECT v FROM Valoracion v")
    , @NamedQuery(name = "Valoracion.findById", query = "SELECT v FROM Valoracion v WHERE v.id = :id")
    , @NamedQuery(name = "Valoracion.findByPuntuacion", query = "SELECT v FROM Valoracion v WHERE v.puntuacion = :puntuacion")
    , @NamedQuery(name = "Valoracion.findByComentario", query = "SELECT v FROM Valoracion v WHERE v.comentario = :comentario")
    , @NamedQuery(name = "Valoracion.findByFecha", query = "SELECT v FROM Valoracion v WHERE v.fecha = :fecha")
    , @NamedQuery(name = "Valoracion.findByHora", query = "SELECT v FROM Valoracion v WHERE v.hora = :hora")})
public class Valoracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Puntuacion")
    private int puntuacion;
    @Size(max = 255)
    @Column(name = "Comentario")
    private String comentario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @JoinColumn(name = "Cliente", referencedColumnName = "Correo")
    @ManyToOne
    private Usuario cliente;
    @JoinColumn(name = "ProductoValorado", referencedColumnName = "ID")
    @ManyToOne
    private Producto productoValorado;

    public Valoracion() {
    }

    public Valoracion(Integer id) {
        this.id = id;
    }

    public Valoracion(Integer id, int puntuacion, Date fecha, Date hora) {
        this.id = id;
        this.puntuacion = puntuacion;
        this.fecha = fecha;
        this.hora = hora;
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

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Producto getProductoValorado() {
        return productoValorado;
    }

    public void setProductoValorado(Producto productoValorado) {
        this.productoValorado = productoValorado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Valoracion)) {
            return false;
        }
        Valoracion other = (Valoracion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.efake.entity.Valoracion[ id=" + id + " ]";
    }
    
}
