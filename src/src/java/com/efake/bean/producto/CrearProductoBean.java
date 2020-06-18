/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.bean.producto;

import com.efake.dao.CategoriaFacade;
import com.efake.dao.KeywordsFacade;
import com.efake.dao.ProductoFacade;
import com.efake.dao.SubcategoriaFacade;
import com.efake.dao.UsuarioFacade;
import com.efake.entity.Categoria;
import com.efake.entity.Keywords;
import com.efake.entity.Producto;
import com.efake.entity.Subcategoria;
import com.efake.entity.Usuario;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author JuMed
 */
@Named(value = "crearProductoBean")
@RequestScoped
public class CrearProductoBean {

    @EJB CategoriaFacade categoriafacade;
    protected List<Categoria> listacategorias;
    @EJB SubcategoriaFacade subcategoriafacade;
    protected List<Subcategoria> listasubcategoria;
    protected String nombre;
    protected String descripcion;
    protected double precio;
    protected String imagen;
    protected String keywords;
    protected Integer categoria;
    protected Integer subcategoria;
    @EJB ProductoFacade productofacade;
    @EJB UsuarioFacade usuariofacade;
    @EJB KeywordsFacade keywordsfacade;
    
    
    public CrearProductoBean() {
    }
    
    @PostConstruct
    public void init(){
      this.listacategorias = categoriafacade.findAll();
      this.listasubcategoria = subcategoriafacade.findAll();
    }

    public List<Categoria> getListacategorias() {
        return listacategorias;
    }

    public void setListacategorias(List<Categoria> listacategorias) {
        this.listacategorias = listacategorias;
    }

    public List<Subcategoria> getListasubcategoria() {
        return listasubcategoria;
    }

    public void setListasubcategoria(List<Subcategoria> listasubcategoria) {
        this.listasubcategoria = listasubcategoria;
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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Integer subcategoria) {
        this.subcategoria = subcategoria;
    }
    
    public String doCrear(){     
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setDescripcion(descripcion);
        p.setPrecio(precio);
        Date now = new Date();
        p.setFecha(now);
        p.setImagen(imagen);
        Categoria c = new Categoria(categoria);
        p.setCategoria(c);
        if (subcategoria != 0) {
            Subcategoria s = new Subcategoria(subcategoria);
            p.setSubcategoria(s);
        }
        Usuario user = usuariofacade.find(130);
        p.setOwner(user);
        productofacade.create(p);
        StringTokenizer st = new StringTokenizer(keywords, ",");
        while (st.hasMoreTokens()) {
            Keywords k = keywordsfacade.findOrCreate(st.nextToken());
            
            p.getKeywordsList().add(k);
            k.getProductoList().add(p);
            
            keywordsfacade.edit(k);
        }

        //Update product on db & exit
        productofacade.edit(p);
        return "transport";
    }
    
}
