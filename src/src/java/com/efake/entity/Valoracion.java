/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.entity;

import com.efake.dto.ValoracionDTO;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
    , @NamedQuery(name = "Valoracion.findByFecha", query = "SELECT v FROM Valoracion v WHERE v.fecha = :fecha")
    , @NamedQuery(name = "Valoracion.findByProducto", query = "SELECT v FROM Valoracion v WHERE v.productoValorado = :productoValorado")
    , @NamedQuery(name = "Valoracion.CountByDate", query = "SELECT v.fecha, count(v) FROM  Valoracion v WHERE v.fecha BETWEEN :start and :end GROUP BY v.fecha ORDER BY v.fecha ASC")})
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
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Comentario")
    private String comentario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "Cliente", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario cliente;
    @JoinColumn(name = "ProductoValorado", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto productoValorado;

    public Valoracion() {
    }

    public Valoracion(Integer id) {
        this.id = id;
    }

    public Valoracion(Integer id, int puntuacion, Date fecha) {
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

    public ValoracionDTO getDTO() {
        ValoracionDTO valDTO = new ValoracionDTO();
        valDTO.setCliente(this.cliente);
        valDTO.setComentario(this.comentario);
        valDTO.setFecha(this.fecha);
        valDTO.setId(this.id);
        valDTO.setProductoValorado(this.productoValorado);
        valDTO.setPuntuacion(this.puntuacion);
        
        return valDTO;
    }
    
}
