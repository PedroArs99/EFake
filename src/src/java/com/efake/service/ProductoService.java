package com.efake.service;

import com.efake.dao.CategoriaFacade;
import com.efake.dao.ProductoFacade;
import com.efake.dao.SubcategoriaFacade;
import com.efake.dao.UsuarioFacade;
import com.efake.dto.CategoriaDTO;
import com.efake.dto.ProductoDTO;
import com.efake.dto.SubCategoriaDTO;
import com.efake.dto.UsuarioDTO;
import com.efake.dto.ValoracionDTO;
import com.efake.entity.Categoria;
import com.efake.entity.Producto;
import com.efake.entity.Subcategoria;
import com.efake.entity.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author PedroArenas (findMostRated, count, findAllInRange, remove)
 * @author Carlos Diestro (getProductRatings, getMeanRating, getRatingList,
 * findById, findByCategory)
 */
@Stateless
public class ProductoService {

    @EJB
    private SubcategoriaFacade subcategoriaFacade;

    @EJB
    private CategoriaFacade categoryFacade;

    @EJB
    private ProductoFacade productFacade;

    @EJB
    private UsuarioFacade usuarioFacade;

    //Tool
    private List<ProductoDTO> convertToDTO(List<Producto> listaProducto) {
        List<ProductoDTO> listaDTO = new ArrayList<>();
        if (listaProducto != null) {
            listaDTO = new ArrayList<>();
            for (Producto p : listaProducto) {
                listaDTO.add(p.getDTO());
            }
        }
        return listaDTO;
    }

    public boolean rated(List<ValoracionDTO> listValoraciones, UsuarioDTO user) {
        boolean valorado = false;
        if (listValoraciones != null) {
            for (int i = 0; i < listValoraciones.size() && !valorado; i++) {
                UsuarioDTO u = listValoraciones.get(i).getCliente();
                if (u.equals(user)) {
                    valorado = true;
                }
            }
        }

        return valorado;
    }

    public Map<Integer, Integer> getRatings(Integer id) {
        Map<Integer, Integer> ratings = new HashMap<>();
        Producto producto = this.productFacade.find(id);
        int total = producto.getValoracionList().size();

        ratings.put(0, total);
        ratings.put(1, ((producto.getEstrella1() * 100) / total));
        ratings.put(2, ((producto.getEstrella2() * 100) / total));
        ratings.put(3, ((producto.getEstrella3() * 100) / total));
        ratings.put(4, ((producto.getEstrella4() * 100) / total));
        ratings.put(5, ((producto.getEstrella5() * 100) / total));

        return ratings;
    }

    //Services
    public void remove(int id) {
        Producto product = productFacade.find(id);

        productFacade.remove(product);
    }

    public int count() {
        int count = productFacade.count();

        return count;
    }

    public double getMeanRating(Integer idProducto) {
        Producto p = this.productFacade.find(idProducto);
        Map<Integer, Integer> ratings = this.getRatings(idProducto);

        int cont = 1 * p.getEstrella1();
        cont += 2 * p.getEstrella2();
        cont += 3 * p.getEstrella3();
        cont += 4 * p.getEstrella4();
        cont += 5 * p.getEstrella5();

        return formatearDecimales(cont / (double) (ratings.get(0)));
    }

    public double formatearDecimales(double numero) {
        return Math.round(numero * Math.pow(10, 2)) / Math.pow(10, 2);
    }

    //Finds
    public List<ProductoDTO> findMostRated(int howMany) {
        List<Producto> mostRatedProducts = productFacade.findMostRated(howMany);
        List<ProductoDTO> mostRatedDTO = convertToDTO(mostRatedProducts);

        return mostRatedDTO;
    }

    public ProductoDTO findById(Integer idProducto) {
        Producto producto = productFacade.find(idProducto);

        return producto.getDTO();
    }

    public List<ProductoDTO> findAllInRange(int pageNumber, int pageSize) {
        List<Producto> productList = productFacade.findRange(pageNumber, pageSize);
        List<ProductoDTO> dtoList = convertToDTO(productList);

        return dtoList;
    }

    public List<ProductoDTO> findByCategoria(CategoriaDTO categoria) {
        Categoria cat = this.categoryFacade.find(categoria.getId());
        List<Producto> lista = this.productFacade.findByCategoria(cat);
        List<ProductoDTO> dtoList = convertToDTO(lista);

        return dtoList;
    }

    public List<ProductoDTO> findBySubCategoria(SubCategoriaDTO subcategoria) {
        Subcategoria subcat = this.subcategoriaFacade.find(subcategoria.getId());
        List<Producto> lista = this.productFacade.findBySubCategoria(subcat);
        List<ProductoDTO> dtoList = convertToDTO(lista);

        return dtoList;

    }

    public List<ProductoDTO> findByFilter(String filter) {
        List<Producto> lista = this.productFacade.findByFilter(filter);
        List<ProductoDTO> dtoList = convertToDTO(lista);

        return dtoList;
    }
    
    public List<ProductoDTO> findByMultipleFilters(String name, Date date, CategoriaDTO categoryDTO, String ownerEmail){
        Categoria category = this.categoryFacade.find(categoryDTO.getId());
        
        List<Producto> lista = this.productFacade.findByMultipleFilters(name, date, category, ownerEmail);
        List<ProductoDTO> dtoList = convertToDTO(lista);

        return dtoList;
    }

    public void create(ProductoDTO productoDTO) {
        Producto p = new Producto(productoDTO, false);

        productFacade.create(p);
    }

    public void edit(ProductoDTO productoDTO) {
        Producto p = new Producto(productoDTO, false);

        productFacade.edit(p);
    }

    public List<ProductoDTO> findByUsuario(UsuarioDTO usuario) {
        Usuario user = this.usuarioFacade.find(usuario.getId());
        List<Producto> lista = this.productFacade.findByOwner(user);
        List<ProductoDTO> dtoList = convertToDTO(lista);

        return dtoList;
    }

    public void delete(ProductoDTO productoSeleccionado) {
        Producto p = new Producto(productoSeleccionado, false);

        productFacade.remove(p);
    }

}
