package com.example.weatherapp.entity;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Weather {

    @NonNull
    private final Condition condition;
    @NonNull
    private final Temperature temperature;

    public Weather(
            @NonNull Condition condition,
            @NonNull Temperature temperature
    ) {
        this.condition = condition;
        this.temperature = temperature;
    }

    public Weather(Weather newWeather){
        this.condition = newWeather.condition;
        this.temperature = newWeather.temperature;
    }

    @NonNull
    public Condition getCondition() {
        return condition;
    }

    @NonNull
    public Temperature getTemperature() {
        return temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return condition.equals(weather.condition) &&
                temperature.equals(weather.temperature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(condition, temperature);
    }

    @Override
    public String toString() {
        return "Weather{" +
                ", condition=" + condition +
                ", temperature=" + temperature +
                '}';
    }
}
