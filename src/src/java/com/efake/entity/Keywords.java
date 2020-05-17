/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.entity;

import com.efake.dto.KeywordsDTO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author PedroArenas
 */
@Entity
@Table(name = "KEYWORDS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Keywords.findAll", query = "SELECT k FROM Keywords k")
    , @NamedQuery(name = "Keywords.findByPalabra", query = "SELECT k FROM Keywords k WHERE k.palabra = :palabra")})
public class Keywords implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Palabra")
    private String palabra;
    @JoinTable(name = "PRODUCTO_KEYWORDS", joinColumns = {
        @JoinColumn(name = "Palabra", referencedColumnName = "Palabra")}, inverseJoinColumns = {
        @JoinColumn(name = "Producto", referencedColumnName = "ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Producto> productoList;

    public Keywords() {
    }

    public Keywords(String palabra) {
        this.palabra = palabra;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (palabra != null ? palabra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Keywords)) {
            return false;
        }
        Keywords other = (Keywords) object;
        if ((this.palabra == null && other.palabra != null) || (this.palabra != null && !this.palabra.equals(other.palabra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.efake.entity.Keywords[ palabra=" + palabra + " ]";
    }
    
    public KeywordsDTO getDTO(){
        KeywordsDTO keyDTO = new KeywordsDTO();
        keyDTO.setPalabra(this.palabra);
        
        return keyDTO;
    }
    
}
