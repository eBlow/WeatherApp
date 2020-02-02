package com.example.weatherapp.model;

import com.example.weatherapp.entity.Temperature;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DailyForecast {

    public LinkedList<HourlyForecast> weatherByHours = new LinkedList<HourlyForecast>();
    private Integer day;
    private Integer month;
    private Temperature maxTemperature = new Temperature(Float.MIN_VALUE);
    private Temperature minTemperature = new Temperature(Float.MAX_VALUE);
    private String icon;

    public Temperature getMaxTemperature() {
        return maxTemperature;
    }

    public Temperature getMinTemperature() {
        return minTemperature;
    }

    public String getIcon(){return this.icon;}

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public void calculateMaxTemperature() {
        for (int i = 0; i < weatherByHours.size(); ++i) {
           if (weatherByHours.get(i).getTemperature().getTemperature() > maxTemperature.getTemperature()) {
                maxTemperature = weatherByHours.get(i).getTemperature();
           }
        }
    }

    public void calculateMinTemperature() {
        for (int i = 0; i < weatherByHours.size(); ++i) {
           if (weatherByHours.get(i).getTemperature().getTemperature() < minTemperature.getTemperature()) {
               minTemperature = weatherByHours.get(i).getTemperature();
           }
        }
    }

    public void calculateIcon() {
        Map<String, Integer> allIcons = new HashMap<>();
        for(HourlyForecast i: this.weatherByHours){
            String key = i.getCondition().getIconId().substring(0,2);
            if(allIcons.containsKey(key)){
                allIcons.put(key, allIcons.get(key) + 1);
                continue;
            }
            allIcons.put(key, 0);
        }
        Map.Entry<String, Integer> iconEntry = null;
        for (Map.Entry<String, Integer> entry : allIcons.entrySet()){
            if (iconEntry == null || entry.getValue()
                    .compareTo(iconEntry.getValue()) > 0) {
                iconEntry = entry;
            }
        }
        this.icon = ((new StringBuilder(iconEntry.getKey())).append("d")).toString();
    }

}
