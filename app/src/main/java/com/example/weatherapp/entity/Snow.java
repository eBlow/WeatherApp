package com.example.weatherapp.entity;

import androidx.annotation.NonNull;

import java.util.Objects;

public final class Snow {

    @NonNull
    private final String time;
    private final float amount;

    public Snow(@NonNull String time, float amount) {
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
        Snow snow = (Snow) o;
        return Float.compare(snow.amount, amount) == 0 &&
                time.equals(snow.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, amount);
    }

    @Override
    public String toString() {
        return "Snow{" +
                "time='" + time + '\'' +
                ", amount=" + amount +
                '}';
    }
}