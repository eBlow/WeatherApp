package com.example.weatherapp.model;

import androidx.annotation.NonNull;
import com.example.weatherapp.entity.Weather;
import java.util.Objects;

public class HourlyForecast extends Weather{

    private Integer day;
    private Integer month;
    private Integer hours;

    public HourlyForecast(@NonNull Weather weather,
                          @NonNull Integer day,
                          @NonNull Integer month,
                          @NonNull Integer hours){
        super(weather);
        this.day = day;
        this.month = month;
        this.hours = hours;
    }

    public Integer getDay() {
        return day;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getHours() {
        return hours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HourlyForecast hourlyForecast = (HourlyForecast) o;
        return day.equals(hourlyForecast.day) &&
                month.equals(hourlyForecast.month) &&
                hours.equals(hourlyForecast.hours) &&
                ((Weather) this).equals((Weather) hourlyForecast);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), day, month, hours);
    }

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "day=" + day +
                ", month=" + month +
                ", hours=" + hours +
                ", condition=" + getCondition() +
                ", temperature=" + getTemperature() +
                '}';
    }

}
