package com.example.weatherapp.entity;

import androidx.annotation.NonNull;

import java.util.Objects;

public final class Rain {

    @NonNull
    private final String time;
    private final float amount;

    public Rain(@NonNull String time, float amount) {
        this.time = time;
        this.amount = amount;
    }

    @NonNull
    public String getTime() {
        return time;
    }

    public float getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rain rain = (Rain) o;
        return Float.compare(rain.amount, amount) == 0 &&
                time.equals(rain.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, amount);
    }

    @Override
    public String toString() {
        return "Rain{" +
                "time='" + time + '\'' +
                ", amount=" + amount +
                '}';
    }
}