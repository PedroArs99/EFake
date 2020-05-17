package com.efake.service;

import com.efake.dao.ProductoFacade;
import com.efake.dto.ProductoDTO;
import com.efake.entity.Producto;
import com.efake.entity.Usuario;
import com.efake.entity.Valoracion;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author PedroArenas
 */
@Stateless
public class ProductoService {

    @EJB
    ProductoFacade productoFacade;

    public List<Producto> getMostRated(int howMany) {
        return productoFacade.findMostRated(5);
    }
    
    private boolean valorado(List<Valoracion> listValoraciones, Usuario user) {
        boolean valorado = false;
        for (int i = 0; i < listValoraciones.size() && !valorado; i++) {
            Usuario u = listValoraciones.get(i).getCliente();
            if (u.equals(user)) {
                valorado = true;
            }
        }
        return valorado;
    }
    
    public Map<Integer, Double> getRatings(Integer idProducto){
        Map<Integer, Double> ratings = new HashMap<>();
        inicializarMapa(ratings);
        List<Valoracion> listValoraciones = getListaValoraciones(idProducto);
       
        for(Valoracion v : listValoraciones){
            Double value = ratings.get(v.getPuntuacion());
            ratings.put(v.getPuntuacion(), value + 1);
        }
        return ratings;
    }
    
    public double getMediaValoraciones(Map<Integer, Double> ratings, Integer idProducto){
        List<Valoracion> listValoraciones = getListaValoraciones(idProducto);
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
    
    public List<Valoracion> getListaValoraciones(Integer idProducto){
        Producto producto = productoFacade.find(idProducto);
        List<Valoracion> listValoraciones = producto.getValoracionList();
        
        return listValoraciones;
    }
    
    public ProductoDTO getProducto(Integer idProducto){
        Producto producto = productoFacade.find(idProducto);
        return producto.getDTO();
    }

    private void inicializarMapa(Map<Integer, Double> ratings) {
         for (int i = 0; i < 5; i++) { //Pone las estrellas a 0
            ratings.put(i + 1, 0.0);
        }
    }

    private double formatearDecimales(double numero) {
        return Math.round(numero * Math.pow(10, 2)) / Math.pow(10, 2);
    }
}
