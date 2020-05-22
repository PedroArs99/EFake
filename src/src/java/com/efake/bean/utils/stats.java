/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.efake.bean.utils;

import com.efake.service.StatsService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
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

    //Services
    @EJB
    private StatsService statsService;

    //Beans
    //Attributes
    private BarChartModel barChart;
    

    @PostConstruct
    public void init() {
        Map<String, Integer> globalStats = statsService.getBasicStats();
        List<String> labels = new ArrayList<>(globalStats.keySet());
        List<Number> values = new ArrayList<>(globalStats.values());
        
        drawChart("Global Stats", labels, values);
        
    }

    public BarChartModel getGlobalStats() {
        return barChart;
    }
    
    
    private void drawChart(String chartTitle, List<String> labels, List<Number> values){
        barChart = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel(chartTitle);
        barDataSet.setData(values);

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
