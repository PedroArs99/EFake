package com.efake.service;

import com.efake.dao.CategoriaFacade;
import com.efake.dao.SubcategoriaFacade;
import com.efake.dto.CategoriaDTO;
import com.efake.dto.SubCategoriaDTO;
import com.efake.entity.Categoria;
import com.efake.entity.Subcategoria;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Carlos Diestro
 */
@Stateless
public class SubCategoryService {
    @EJB
    SubcategoriaFacade subCategoryFacade;
    @EJB
    CategoriaFacade categoriaFacade;
    
    //Tools
    private List<SubCategoriaDTO> convertToDTO(List<Subcategoria> subcategoryList){
        List<SubCategoriaDTO> listaDTO = null;
        if (subcategoryList != null) {
            listaDTO = new ArrayList<>();
            for (Subcategoria subcategoria: subcategoryList) {
                listaDTO.add(subcategoria.getDTO());
            }
        }
        return listaDTO;
    }
    //Finds
    public List<SubCategoriaDTO> findAll(){
        List<Subcategoria> subcategoryList = subCategoryFacade.findAll();
        List<SubCategoriaDTO> dtoList = convertToDTO(subcategoryList);
        
        return dtoList;
    }
    
    public SubCategoriaDTO findByName(String subcategory){
        Subcategoria subcategoria = subCategoryFacade.find(subcategory);
        return subcategoria.getDTO();
    }
    
    public List<SubCategoriaDTO> finByCategory(CategoriaDTO category){
        Categoria c = categoriaFacade.find(category.getId());
        List<Subcategoria> subcategoryList = subCategoryFacade.findByCategory(c);
        List<SubCategoriaDTO> dtoList = convertToDTO(subcategoryList);
        
        return dtoList;
    }
}

