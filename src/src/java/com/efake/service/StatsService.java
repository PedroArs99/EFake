package com.efake.service;

import com.efake.dao.ProductoFacade;
import com.efake.dao.UsuarioFacade;
import com.efake.dao.ValoracionFacade;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
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

    public Map<String, Integer> getBasicStats() {
        Map<String, Integer> basicStats = new HashMap<>();

        int userCount = userFacade.count();
        int productCount = productFacade.count();
        int ratingCount = ratingFacade.count();

        basicStats.put("userCount", userCount);
        basicStats.put("productCount", productCount);
        basicStats.put("ratingCount", ratingCount);

        return basicStats;
    }

    public Map<String, Integer> getTodayStats() {
        Map<String, Integer> stats = new HashMap<>();
        Date today = new Date();

        int userCount = userFacade.findByUltimaEntrada(new Date()).size();
        int productCount = productFacade.findByFecha(today).size();
        int ratingCount = ratingFacade.findByFecha(today).size();

        stats.put("userCount", userCount);
        stats.put("productCount", productCount);
        stats.put("ratingCount", ratingCount);

        return stats;
    }

    public Map<String, SortedMap<String, Number>> getMonthlyStats(String name) {
        Map<String, SortedMap<String, Number>> stats = new HashMap<>();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM");

        Calendar today = Calendar.getInstance();
        today.setTime(new Date());

        Calendar oneMonthAgo = (Calendar) today.clone();
        oneMonthAgo.roll(Calendar.DAY_OF_YEAR, -30);

        switch (name) {
            case "user":
                //Add Users Count
                SortedMap<String, Number> usersCountMap = new TreeMap<>();
                List<Object[]> usersCount = userFacade.countByDate(oneMonthAgo.getTime(), today.getTime());
                for (Object[] o : usersCount) {
                    usersCountMap.put(dateFormatter.format(o[0]), (Number) o[1]);
                }
                stats.put("userCount", usersCountMap);
                break;
            case "product":
                //Add Products Count
                SortedMap<String, Number> productCountMap = new TreeMap<>();
                List<Object[]> productCount = productFacade.countByDate(oneMonthAgo.getTime(), today.getTime());
                for (Object[] o : productCount) {
                    productCountMap.put(dateFormatter.format(o[0]), (Number) o[1]);
                }
                stats.put("productCount", productCountMap);
                break;
            case "rating":
                //Add Ratings Count
                SortedMap<String, Number> ratingsCountMap = new TreeMap<>();
                List<Object[]> ratingCount = ratingFacade.countByDate(oneMonthAgo.getTime(), today.getTime());
                for (Object[] o : ratingCount) {
                    ratingsCountMap.put(dateFormatter.format(o[0]), (Number) o[1]);
                }
                stats.put("ratingCount", ratingsCountMap);
                break;
        }

        return stats;
    }

}
