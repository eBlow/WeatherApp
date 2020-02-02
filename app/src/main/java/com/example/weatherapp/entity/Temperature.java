package com.example.weatherapp.entity;

import java.util.Objects;

public final class Temperature {

    private final float temperatureKelvin;

    public Temperature(float temperatureKelvin) {
        this.temperatureKelvin = temperatureKelvin;
    }

    public float getTemperature() {
        return temperatureKelvin;
    }

    public float getTemperatureCelsius() {
        return temperatureKelvin - 273.15F;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temperature that = (Temperature) o;
        return Float.compare(that.temperatureKelvin, temperatureKelvin) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperatureKelvin);
    }
}

