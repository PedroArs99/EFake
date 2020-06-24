package com.efake.service;

import com.efake.dao.CategoriaFacade;
import com.efake.dto.CategoriaDTO;
import com.efake.entity.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Pedro Arenas (findAll)
 * @author Carlos Diestro (findByName)
 */
@Stateless
public class CategoryService {
    @EJB
    CategoriaFacade categoryFacade;
    
    //Tools
    private List<CategoriaDTO> convertToDTO(List<Categoria> categoryList){
        List<CategoriaDTO> listaDTO = null;
        if (categoryList != null) {
            listaDTO = new ArrayList<>();
            for (Categoria categoria: categoryList) {
                listaDTO.add(categoria.getDTO());
            }
        }
        return listaDTO;
    }
    //Finds
    public List<CategoriaDTO> findAll(){
        List<Categoria> categoryList = categoryFacade.findAll();
        List<CategoriaDTO> dtoList = convertToDTO(categoryList);
        
        return dtoList;
    }
    
    public CategoriaDTO findByName(String category){
        Categoria categoria = categoryFacade.find(category);
        return categoria.getDTO();
    }
    public CategoriaDTO find(Integer category){
        Categoria categoria = categoryFacade.find(category);
        return categoria.getDTO();
    }
}
