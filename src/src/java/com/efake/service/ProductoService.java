package com.efake.service;

import com.efake.dao.CategoriaFacade;
import com.efake.dao.ProductoFacade;
import com.efake.dto.ProductoDTO;
import com.efake.dto.ValoracionDTO;
import com.efake.entity.Categoria;
import com.efake.entity.Producto;
import com.efake.entity.Usuario;
import com.efake.entity.Valoracion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author PedroArenas (findMostRated, count, findAllInRange, remove)
 * @author Carlos Diestro (getProductRatings, getMeanRating, getRatingList, findById,
 *                         findByCategory)
 */
@Stateless
public class ProductoService {

    @EJB
    private ValoracionService valoracionService;

    @EJB
    private CategoriaFacade categoryFacade;

    @EJB
    private ProductoFacade productFacade;
    
    
    
    
    //Tool
    private List<ProductoDTO> convertToDTO (List<Producto> listaProducto) {
        List<ProductoDTO> listaDTO = null;
        if (listaProducto != null) {
            listaDTO = new ArrayList<>();
            for (Producto cliente: listaProducto) {
                listaDTO.add(cliente.getDTO());
            }
        }
        return listaDTO;
    }
    
    public boolean rated(List<Valoracion> listValoraciones, Usuario user) {
        boolean valorado = false;
        for (int i = 0; i < listValoraciones.size() && !valorado; i++) {
            Usuario u = listValoraciones.get(i).getCliente();
            if (u.equals(user)) {
                valorado = true;
            }
        }
        return valorado;
    }
    
    public Map<Integer, Double> getRatings(Integer id){
        Map<Integer, Double> ratings = new HashMap<>();
        Producto producto = this.productFacade.find(id);
        double total = producto.getValoracionList().size();
        
        ratings.put(1, ((producto.getEstrella1()*100)/total));
        ratings.put(2, ((producto.getEstrella2()*100)/total));
        ratings.put(3, ((producto.getEstrella3()*100)/total));
        ratings.put(4, ((producto.getEstrella4()*100)/total));
        ratings.put(5, ((producto.getEstrella5()*100)/total));
        ratings.put(0, total);
        
        return ratings;
    }

    //Services
    public void remove(int id){
        Producto product = productFacade.find(id);
        
        productFacade.remove(product);
    }
    
    public int count(){
        int count = productFacade.count();
        
        return count;
    }
   
    public double getMeanRating(Integer idProducto){
        Producto p = this.productFacade.find(idProducto);
        Map<Integer, Double> ratings = this.getRatings(idProducto);
        
        int cont = 1 * p.getEstrella1();
        cont += 2 * p.getEstrella2();
        cont += 3 * p.getEstrella3();
        cont += 4 * p.getEstrella4();
        cont += 5 * p.getEstrella5();
        
        
        return formatearDecimales(cont/(ratings.get(0)));
    }
    
    private double formatearDecimales(double numero) {
        return Math.round(numero * Math.pow(10, 2)) / Math.pow(10, 2);
    }
        
    //Finds
    public List<ProductoDTO> findMostRated(int howMany) {
        List<Producto> mostRatedProducts = productFacade.findMostRated(howMany);
        List<ProductoDTO> mostRatedDTO = convertToDTO(mostRatedProducts);
        
        return mostRatedDTO;
    }
    
    public ProductoDTO findById(Integer idProducto){
        Producto producto = productFacade.find(idProducto);
        
        return producto.getDTO();
    }
    
    public List<ProductoDTO> findByCategory(String category){
        Categoria categoria = categoryFacade.find(category);
        List<Producto> productList = productFacade.findByCategoria(categoria);
        List<ProductoDTO> productDTOList = convertToDTO(productList);
        
        return productDTOList;       
    }
   
    public List<ProductoDTO> findAllInRange(int pageNumber, int pageSize){
        List<Producto> productList = productFacade.findRange(pageNumber, pageSize);
        List<ProductoDTO> dtoList = convertToDTO(productList);
        
        return dtoList;
    }
}
