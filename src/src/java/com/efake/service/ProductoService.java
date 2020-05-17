package com.efake.service;

import com.efake.dao.CategoriaFacade;
import com.efake.dao.ProductoFacade;
import com.efake.dto.ProductoDTO;
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
    
    private boolean rated(List<Valoracion> listValoraciones, Usuario user) {
        boolean valorado = false;
        for (int i = 0; i < listValoraciones.size() && !valorado; i++) {
            Usuario u = listValoraciones.get(i).getCliente();
            if (u.equals(user)) {
                valorado = true;
            }
        }
        return valorado;
    }
    
    private void inicializarMapa(Map<Integer, Double> ratings) {
         for (int i = 0; i < 5; i++) { //Pone las estrellas a 0
            ratings.put(i + 1, 0.0);
        }
    }

    private double formatearDecimales(double numero) {
        return Math.round(numero * Math.pow(10, 2)) / Math.pow(10, 2);
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
    
    public Map<Integer, Double> getProductRatings(Integer idProducto){
        Map<Integer, Double> ratings = new HashMap<>();
        inicializarMapa(ratings);
        List<Valoracion> listValoraciones = getRatingList(idProducto);
       
        for(Valoracion v : listValoraciones){
            Double value = ratings.get(v.getPuntuacion());
            ratings.put(v.getPuntuacion(), value + 1);
        }
        return ratings;
    }
    
    public double getMeanRating(Map<Integer, Double> ratings, Integer idProducto){
        List<Valoracion> listValoraciones = getRatingList(idProducto);
        double mediaValoraciones = 0.0;
        
        for(Valoracion v : listValoraciones){
            mediaValoraciones += v.getPuntuacion();
        }
        
        if (!listValoraciones.isEmpty()) {
            ratings.entrySet().forEach((entry) -> {
                Integer key = entry.getKey();
                Double value = entry.getValue();
                value = (value / listValoraciones.size()) * 100;
                ratings.put(key, value);
            });
            mediaValoraciones = mediaValoraciones / listValoraciones.size();
            mediaValoraciones = formatearDecimales(mediaValoraciones);

        }
        
        return mediaValoraciones;
    }    
    /*
     * Change to DTO!!
    */
    public List<Valoracion> getRatingList(Integer idProducto){
        Producto producto = productFacade.find(idProducto);
        List<Valoracion> listValoraciones = producto.getValoracionList();
        
        return listValoraciones;
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
