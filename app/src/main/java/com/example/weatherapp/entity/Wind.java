package com.example.weatherapp.entity;

import java.util.Objects;

public final class Wind {
    private final float speed;
    private final float deg;

    public Wind(float speed, float deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public float getSpeed() {
        return speed;
    }

    public float getDeg() {
        return deg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wind wind = (Wind) o;
        return Float.compare(wind.speed, speed) == 0 &&
                Float.compare(wind.deg, deg) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(speed, deg);
    }

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                ", deg=" + deg +
                '}';
    }
}
