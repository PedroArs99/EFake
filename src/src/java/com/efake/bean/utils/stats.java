/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.bean.utils;

import com.efake.bean.login.UsuarioBean;
import com.efake.dto.UsuarioDTO;
import com.efake.service.StatsService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;

/**
 *
 * @author PedroArenas
 */
@Named(value = "stats")
@RequestScoped
public class stats {
    //Logger
    private static final Logger LOG = Logger.getLogger(stats.class.getName());    

    //Services
    @EJB
    private StatsService statsService;

    //Beans
    @Inject
    private UsuarioBean sessionBean;

    //Attributes
    private BarChartModel barChart;
    
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
        }else{
            this.drawGlobalStats();
        }
    }
    
    //Bean Methods
    public void drawGlobalStats() {
        Map<String, Integer> globalStats = statsService.getBasicStats();
        drawBarChart("Global Stats", globalStats, true);
    }

    public void drawTodayStats() {
        Map<String, Integer> todayStats = statsService.getTodayStats();
        drawBarChart("Today Stats", todayStats, true);
    }

    public void drawMonthlyUserStats() {
        SortedMap<String, Integer> monthStats = statsService.getMonthlyUserStats();
        drawBarChart("Users logged in the last month", monthStats, false);
    }

    public void drawMonthlyNewProductStats() {
        SortedMap<String, Integer> monthStats = statsService.getMonthlyNewProductStats();
        drawBarChart("New Products created in the last month", monthStats, false);
    }

    public void drawMonthlyRatingStats() {
        SortedMap<String, Integer> monthStats = statsService.getMonthlyRatingStats();
        drawBarChart("New Ratings maded in the last month", monthStats, false);
    }
    
    //Getters & setters
    public BarChartModel getBarChart() {
        return barChart;
    }
    
    //Private methods
    private void drawBarChart(String chartTitle, Map<String, Integer> stats, boolean manyColors) {
        List<String> labels = new ArrayList<>(stats.keySet());
        List<Number> values = new ArrayList<>(stats.values());

        barChart = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel(chartTitle);
        barDataSet.setData(values);

        if (manyColors) {
            List<String> bgColor = new ArrayList<>();
            bgColor.add("rgba(255, 99, 132, 0.2)");
            bgColor.add("rgba(255, 205, 86, 0.2)");
            bgColor.add("rgba(153, 102, 255, 0.2)");
            barDataSet.setBackgroundColor(bgColor);

            List<String> borderColor = new ArrayList<>();
            borderColor.add("rgb(255, 99, 132)");
            borderColor.add("rgb(255, 205, 86)");
            borderColor.add("rgb(153, 102, 255)");
            barDataSet.setBorderColor(borderColor);
            barDataSet.setBorderWidth(1);
        }

        data.addChartDataSet(barDataSet);

        data.setLabels(labels);

        //Data
        barChart.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        barChart.setOptions(options);

    }

}
