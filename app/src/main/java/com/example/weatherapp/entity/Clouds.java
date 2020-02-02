package com.example.weatherapp.entity;

import java.util.Objects;

public final class Clouds {
    private final int percents;

    public Clouds(int percents) {
        this.percents = percents;
    }

    public int getPercents() {
        return percents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clouds clouds = (Clouds) o;
        return percents == clouds.percents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(percents);
    }

    @Override
    public String toString() {
        return "Clouds{" +
                "percents=" + percents +
                '}';
    }
}