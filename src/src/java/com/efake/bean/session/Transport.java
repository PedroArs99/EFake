package com.efake.bean.session;

import com.efake.dto.CategoriaDTO;
import com.efake.dto.ProductoDTO;
import com.efake.dto.SubCategoriaDTO;
import com.efake.dto.UsuarioDTO;
import com.efake.service.CategoryService;
import com.efake.service.ProductoService;
import com.efake.service.SubCategoryService;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Carlos Diestro
 */
@Named(value = "transport")
@SessionScoped
public class Transport implements Serializable {

    //Services
    @EJB
    private CategoryService categoriaService;
    @EJB
    private SubCategoryService subCategoriaService;
    @EJB
    private ProductoService productoService;

    //Attributes
    private List<CategoriaDTO> listaCategoria;
    private List<SubCategoriaDTO> listaSubCategoria;
    private String status;
    private ProductoDTO productoSeleccionado;
    private CategoriaDTO categoriaSeleccionada;
    private String search;
    private UsuarioDTO toEditUser;

    //Constructor
    public Transport() {
    }

    @PostConstruct
    public void init() {
        this.listaCategoria = this.categoriaService.findAll();
        this.listaSubCategoria = this.subCategoriaService.findAll();
    }

    public List<SubCategoriaDTO> getSubCategoriasByCategoria(CategoriaDTO c) {
        return this.subCategoriaService.finByCategory(c);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CategoriaDTO> getListaCategoria() {
        return listaCategoria;
    }

    public void setListaCategoria(List<CategoriaDTO> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public List<SubCategoriaDTO> getListaSubCategoria() {
        return listaSubCategoria;
    }

    public void setListaSubCategoria(List<SubCategoriaDTO> listaSubCategoria) {
        this.listaSubCategoria = listaSubCategoria;
    }

    public ProductoDTO getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(ProductoDTO productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public CategoriaDTO getCategoriaSeleccionada() {
        return categoriaSeleccionada;
    }

    public void setCategoriaSeleccionada(CategoriaDTO categoriaSeleccionada) {
        this.categoriaSeleccionada = categoriaSeleccionada;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public UsuarioDTO getToEditUser() {
        return toEditUser;
    }

    public void setToEditUser(UsuarioDTO toEditUser) {
        this.toEditUser = toEditUser;
    }

    public String redirectCategory(CategoriaDTO categoria) {
        this.categoriaSeleccionada = categoria;

        return "productGrid?faces-redirect=true";
    }

    public String redirectProducto(Integer idProducto) {
        productoSeleccionado = this.productoService.findById(idProducto);

        return "producto?faces-redirect=true";
    }

    public String redirectEditUser(UsuarioDTO user) {
        toEditUser = user;

        return "editUser?faces-redirect=true";
    }

    public String sellProduct() {
        productoSeleccionado = null;

        return "productForm?faces-redirect=true";
    }

    public String modifyProcut(Integer idProducto) {
        productoSeleccionado = this.productoService.findById(idProducto);

        return "productForm?faces-redirect=true";
    }

    public String deleteProduct(Integer idProducto) {
        productoSeleccionado = this.productoService.findById(idProducto);
        productoService.delete(productoSeleccionado);

        return "index?faces-redirect=true";
    }
}
