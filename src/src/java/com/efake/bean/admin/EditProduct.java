package com.efake.bean.admin;

import com.efake.bean.login.UsuarioBean;
import com.efake.bean.session.Transport;
import com.efake.dto.CategoriaDTO;
import com.efake.dto.ProductoDTO;
import com.efake.dto.SubCategoriaDTO;
import com.efake.dto.UsuarioDTO;
import com.efake.entity.Keywords;
import com.efake.service.ProductoService;
import com.efake.service.SubCategoryService;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author PedroArenas
 */
@Named(value = "editProduct")
@RequestScoped
public class EditProduct {

    private static final Logger LOG = Logger.getLogger(EditProduct.class.getName());

    //Beans
    @Inject
    private Transport transportBean;
    @Inject
    private UsuarioBean sessionBean;

    //Services
    @EJB
    private ProductoService productService;
    
    @EJB
    private SubCategoryService subcategoryService;

    //Attributes
    private ProductoDTO toEditProduct;
    private String keywordList;
    private Integer selectedCategory;
    private Integer selectedSubcategory = -1;

    //Constructor
    @PostConstruct
    public void init() {
        UsuarioDTO sessionUser = sessionBean.getUsuario();

        if (sessionUser == null || sessionUser.getEsAdmin() == 0) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../login.jsf");
            } catch (Exception e) {
                LOG.severe(String.format("Exception: %s", e.getMessage()));
            }
        } else {
            toEditProduct = transportBean.getProductoSeleccionado();

            if (toEditProduct == null) {
                toEditProduct = new ProductoDTO();
            } else {
                keywordList = this.getFormatedKeywords();
                this.selectedCategory = toEditProduct.getCategoria().getId();

                SubCategoriaDTO subcategory = toEditProduct.getSubcategoria();
                if (subcategory != null) {
                    this.selectedSubcategory = subcategory.getId();
                }
                
            }    
        }
    }
    
    //Bean Methods
    public String saveChanges(){
        CategoriaDTO newCategory = new CategoriaDTO();
        newCategory.setId(selectedCategory);
        toEditProduct.setCategoria(newCategory);
        
        if(selectedSubcategory == -1){
            toEditProduct.setSubcategoria(null);
        }else{
            SubCategoriaDTO newSubCategory = new SubCategoriaDTO();
            newSubCategory.setId(selectedSubcategory);
            toEditProduct.setSubcategoria(newSubCategory);
        }
        
        this.productService.edit(toEditProduct);
        this.productService.manageKeywords(keywordList, toEditProduct);
        this.transportBean.setProductoSeleccionado(null);
        
        return "productList?faces-redirect=true";
    }
    
    public String discardChanges(){
        this.transportBean.setProductoSeleccionado(null);
        
        return "productList?faces-redirect=true";
    }

    //Getters & setters
    public ProductoDTO getToEditProduct() {
        return toEditProduct;
    }

    public void setToEditProduct(ProductoDTO toEditProduct) {
        this.toEditProduct = toEditProduct;
    }

    public String getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(String keywordList) {
        this.keywordList = keywordList;
    }

    public Integer getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Integer selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public Integer getSelectedSubcategory() {
        return selectedSubcategory;
    }

    public void setSelectedSubcategory(Integer selectedSubcategory) {
        this.selectedSubcategory = selectedSubcategory;
    }

    private String getFormatedKeywords() {
        StringBuilder sb = new StringBuilder();
        
        for(Keywords k: toEditProduct.getlistakeywords()){
            sb.append(k.getPalabra()).append(",");
        }
        
        return sb.toString();
    }

}
