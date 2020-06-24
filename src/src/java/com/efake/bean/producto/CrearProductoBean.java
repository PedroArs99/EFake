package com.efake.bean.producto;

import com.efake.bean.login.UsuarioBean;
import com.efake.bean.session.Transport;
import com.efake.dto.CategoriaDTO;
import com.efake.dto.KeywordsDTO;
import com.efake.dto.ProductoDTO;
import com.efake.dto.SubCategoriaDTO;
import com.efake.dto.UsuarioDTO;
import com.efake.service.CategoryService;
import com.efake.service.KeywordService;
import com.efake.service.ProductoService;
import com.efake.service.SubCategoryService;
import com.efake.service.UsuarioService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;


/**
 *
 * @author Juan Medina Moya 
 */
@Named(value = "crearProductoBean")
@RequestScoped
public class CrearProductoBean {
    ProductoDTO productoseleccionado;
    @Inject
    Transport transport;
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
    @EJB UsuarioService usuarioservice;
    @Inject UsuarioBean usuariobean;
    protected UsuarioDTO usuariolog;

    public CrearProductoBean() {
    }

    @PostConstruct
    public void init() {
        this.productoseleccionado = this.transport.getProductoSeleccionado();
        this.usuariolog = this.usuariobean.getUsuario();
        if (this.productoseleccionado == null) {//to create
            this.isEditar = false;
            this.productoseleccionado = new ProductoDTO();
            
        } else {//to edit
            System.out.print(this.isEditar);
            this.isEditar = true;
            this.categoria = this.productoseleccionado.getCategoria().getId();
            
        }

        this.listacategorias = transport.getListaCategoria();
        this.listasubcategoria = transport.getListaSubCategoria();
    }

    public ProductoDTO getProductoseleccionado() {
        return productoseleccionado;
    }

    public UsuarioDTO getUsuariolog() {
        return usuariolog;
    }

    public void setUsuariolog(UsuarioDTO usuariolog) {
        this.usuariolog = usuariolog;
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
            productoseleccionado.setOwner(usuariolog);
            productoseleccionado.setFecha(new Date());
            productoseleccionado.setCategoria(categoryservice.find(this.categoria));
            productoseleccionado.setSubcategoria(subcategoryservice.find(this.subcategoria));
            productoservice.create(productoseleccionado, this.keywords);
            
        } else {//when edit
            //Manage Keywords
            List<KeywordsDTO> kwList = productoseleccionado.getListaKeywords();

            //Delete old list
            for (int i = 0; i < kwList.size(); i++) {
                KeywordsDTO k = kwList.get(i);

                //k.getProductoList().remove(new Producto(productoseleccionado,false));
                 k.getProductoList().remove(productoseleccionado);
                kwList.remove(k);

                keywordservice.edit(k);
            }

            //Add new list
            //StringTokenizer st = new StringTokenizer(this.keywords, ",");
             List<KeywordsDTO> list = new ArrayList<>();
            List<ProductoDTO> lista = new ArrayList<>();
             String[] split = this.keywords.split(",");
            for(int i = 0 ; i<split.length; i++) {
                KeywordsDTO k = keywordservice.find(split[i]);
                if (k==null){
                    KeywordsDTO key = new KeywordsDTO();
                    key.setPalabra(split[i]);
                    key.setProductoList(lista);
                    keywordservice.create(key);
                    lista.add(productoseleccionado);
                    key.setProductoList(lista);
                    keywordservice.edit(key);   
                    list.add(key);
                    lista.remove(productoseleccionado);
                }else{
                    lista=k.getProductoList();
                    lista.add(productoseleccionado);
                    k.setProductoList(lista);
                    keywordservice.edit(k);
                    list.add(k);
                    
                }

            }
            this.productoseleccionado.setListaKeywords(list);
           
            productoservice.edit(productoseleccionado);
            productoseleccionado.setCategoria(categoryservice.find(this.categoria));
            productoseleccionado.setSubcategoria(subcategoryservice.find(subcategoria));
           
            //Save changes on PRODUCT Table
            productoservice.edit(productoseleccionado);
        }
        
        transport.setStatus("Product Created successfully");
        
        return "productGrid?faces-redirect=true";
    }

}
