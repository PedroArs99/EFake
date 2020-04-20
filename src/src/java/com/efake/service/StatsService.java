package com.efake.service;

import com.efake.dao.ProductoFacade;
import com.efake.dao.UsuarioFacade;
import com.efake.dao.ValoracionFacade;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author PedroArenas
 */
@Stateless
public class StatsService {
    @EJB
    UsuarioFacade userFacade;
    @EJB
    ProductoFacade productFacade;
    @EJB
    ValoracionFacade ratingFacade;
    
    public Map<String,Integer> getBasicStats(){
        Map<String,Integer> basicStats = new HashMap<>();
        
        int userCount = userFacade.count();
        int productCount = productFacade.count();
        int ratingCount = ratingFacade.count();
        
        basicStats.put("userCount", userCount);
        basicStats.put("productCount", productCount);
        basicStats.put("ratingCount", ratingCount);
        
        return basicStats;
    }
    
    public Map<String,Integer> getTodayStats(){
        Map<String,Integer> basicStats = new HashMap<>();
        Date today = new Date();
        
        int userCount = userFacade.findByUltimaEntrada(new Date()).size();
        int productCount = productFacade.findByFecha(today).size();
        int ratingCount = ratingFacade.findByFecha(today).size();
        
        basicStats.put("userCount", userCount);
        basicStats.put("productCount", productCount);
        basicStats.put("ratingCount", ratingCount);
        
        return basicStats;
    }
    
    
}
