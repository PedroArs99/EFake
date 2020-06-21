package com.efake.dto;

import com.efake.entity.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Diestro
 */
public class KeywordsDTO {


    private String palabra;
    private List<ProductoDTO> ProductoList;
    
    public KeywordsDTO() {
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
    

    public List<ProductoDTO> getProductoList() {
        return ProductoList;
    }

    public void setProductoList(List<ProductoDTO> ProductoList) {
        this.ProductoList = ProductoList;
    }
 
    public List<Producto> getlistaproducto(){
        List<Producto> res = new ArrayList<>();
        for (ProductoDTO k : this.ProductoList){
            //Break infinite recursion
            res.add(new Producto(k,true));
        }
       return res;
        
    }
    
}
