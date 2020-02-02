package com.example.weatherapp.entity;

import androidx.annotation.NonNull;

import java.util.Objects;

public final class Condition {

    private final int weatherId;
    @NonNull
    private final String condition;
    @NonNull
    private final String description;
    @NonNull
    private final String iconId;

    private final float pressure;
    private final float humidity;

    public Condition(
            int weatherId,
            @NonNull String condition,
            @NonNull String description,
            @NonNull String iconId,
            float pressure,
            float humidity
    ) {
        this.weatherId = weatherId;
        this.condition = condition;
        this.description = description;
        this.iconId = iconId;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public int getWeatherId() {
        return weatherId;
    }

    @NonNull
    public String getCondition() {
        return condition;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @NonNull
    public String getIconId() {
        return iconId;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Condition condition1 = (Condition) o;
        return weatherId == condition1.weatherId &&
                Float.compare(condition1.pressure, pressure) == 0 &&
                Float.compare(condition1.humidity, humidity) == 0 &&
                condition.equals(condition1.condition) &&
                description.equals(condition1.description) &&
                iconId.equals(condition1.iconId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weatherId, condition, description, iconId, pressure, humidity);
    }

    @Override
    public String toString() {
        return "Condition{" +
                "weatherId=" + weatherId +
                ", condition='" + condition + '\'' +
                ", description='" + description + '\'' +
                ", iconId='" + iconId + '\'' +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                '}';
    }
}