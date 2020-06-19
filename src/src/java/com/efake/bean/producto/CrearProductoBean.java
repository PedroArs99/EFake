/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.bean.producto;

import com.efake.bean.session.Transport;
import com.efake.dto.CategoriaDTO;
import com.efake.dto.KeywordsDTO;
import com.efake.dto.ProductoDTO;
import com.efake.dto.SubCategoriaDTO;
import com.efake.entity.Keywords;
import com.efake.entity.Producto;
import com.efake.service.CategoryService;
import com.efake.service.KeywordService;
import com.efake.service.ProductoService;
import com.efake.service.SubCategoryService;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;


/**
 *
 * @author JuMed
 */
@Named(value = "crearProductoBean")
@RequestScoped
public class CrearProductoBean {

    
    ProductoDTO productoseleccionado;
    @Inject
    Transport productobean;
    @EJB
    CategoryService categoryservice;
    @EJB
    SubCategoryService subcategoryservice;
    protected List<CategoriaDTO> listacategorias;
    protected List<SubCategoriaDTO> listasubcategoria;
    protected boolean isEditar;
    protected Integer categoria;
    protected Integer subcategoria;
    protected String keywords;
    @EJB
    KeywordService keywordservice;
    @EJB
    ProductoService productoservice;

    public CrearProductoBean() {
    }

    @PostConstruct
    public void init() {
        this.productoseleccionado = this.productobean.getProductoSeleccionado();
        if (this.productoseleccionado == null) {//to create
            this.isEditar = false;
            this.productoseleccionado = new ProductoDTO();
        } else {//to edit
            this.isEditar = true;
            this.categoria = this.productoseleccionado.getCategoria().getId();
            this.subcategoria = this.productoseleccionado.getSubcategoria().getId();
            this.keywords = "";
        }

        this.listacategorias = categoryservice.findAll();
        this.listasubcategoria = subcategoryservice.findAll();
    }

    public ProductoDTO getProductoseleccionado() {
        return productoseleccionado;
    }

    public void setProductoseleccionado(ProductoDTO productoseleccionado) {
        this.productoseleccionado = productoseleccionado;
    }

    public boolean isIsEditar() {
        return isEditar;
    }

    public void setIsEditar(boolean isEditar) {
        this.isEditar = isEditar;
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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public List<CategoriaDTO> getListacategorias() {
        return listacategorias;
    }

    public void setListacategorias(List<CategoriaDTO> listacategorias) {
        this.listacategorias = listacategorias;
    }

    public List<SubCategoriaDTO> getListasubcategoria() {
        return listasubcategoria;
    }

    public void setListasubcategoria(List<SubCategoriaDTO> listasubcategoria) {
        this.listasubcategoria = listasubcategoria;
    }
    
    

    public String doEditar() {
        if (!this.isEditar) {//when create
            StringTokenizer st = new StringTokenizer(this.keywords, ",");
            while (st.hasMoreTokens()) {
                KeywordsDTO k = keywordservice.findOrCreate(st.nextToken());

                this.productoseleccionado.getListaKeywords().add(k);
                k.getProductoList().add(productoseleccionado);

                keywordservice.edit(k);
            }
            productoseleccionado.setFecha(new Date());
            productoseleccionado.setCategoria(categoryservice.find(this.categoria));
            productoseleccionado.setSubcategoria(subcategoryservice.find(this.subcategoria));
            productoservice.create(productoseleccionado);
        } else {//when edit
            //Manage Keywords
            List<Keywords> kwList = productoseleccionado.getlistakeywords();

            //Delete old list
            for (int i = 0; i < kwList.size(); i++) {
                Keywords k = kwList.get(i);

                k.getProductoList().remove(new Producto(productoseleccionado));
                kwList.remove(k);

                keywordservice.edit(k.getDTO());
            }

            //Add new list
            StringTokenizer st = new StringTokenizer(this.keywords, ",");
            while (st.hasMoreTokens()) {
                KeywordsDTO k = keywordservice.findOrCreate(st.nextToken());

                this.productoseleccionado.getListaKeywords().add(k);
                k.getProductoList().add(productoseleccionado);

                keywordservice.edit(k);
            }
            productoseleccionado.setCategoria(categoryservice.find(this.categoria));
            productoseleccionado.setSubcategoria(subcategoryservice.find(subcategoria));

            //Save changes on PRODUCT Table
            productoservice.edit(productoseleccionado);
        }
        return "index";
    }

}
