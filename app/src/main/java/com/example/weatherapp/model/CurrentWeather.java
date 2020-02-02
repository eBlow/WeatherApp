package com.example.weatherapp.model;

import androidx.annotation.NonNull;

import com.example.weatherapp.entity.Condition;
import com.example.weatherapp.entity.Location;
import com.example.weatherapp.entity.Temperature;
import com.example.weatherapp.entity.Weather;

import java.util.Objects;

public class CurrentWeather extends Weather {

    @NonNull
    private final Location location;

    public CurrentWeather(@NonNull Weather weather,
                          @NonNull Location location){
        super(weather);
        this.location = location;
    }

    @NonNull
    public Location getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentWeather currentWeather = (CurrentWeather) o;
        return location.equals(currentWeather.location) &&
                ((Weather) this).equals((Weather) currentWeather);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), location);
    }

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "location=" + location +
                ", condition=" + getCondition() +
                ", temperature=" + getTemperature() +
                '}';
    }
}
