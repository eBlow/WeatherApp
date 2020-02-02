package com.example.weatherapp.entity;

import androidx.annotation.NonNull;

import java.util.Objects;

public final class Location {

    private final float longitude;
    private final float latitude;
    private final long sunset;
    private final long sunrise;
    @NonNull
    private final String country;
    @NonNull
    private final String city;

    public Location(
            float longitude,
            float latitude,
            long sunset,
            long sunrise,
            @NonNull String country,
            @NonNull String city
    ) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.sunset = sunset;
        this.sunrise = sunrise;
        this.country = country;
        this.city = city;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public long getSunset() {
        return sunset;
    }

    public long getSunrise() {
        return sunrise;
    }

    @NonNull
    public String getCountry() {
        return country;
    }

    @NonNull
    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Float.compare(location.longitude, longitude) == 0 &&
                Float.compare(location.latitude, latitude) == 0 &&
                sunset == location.sunset &&
                sunrise == location.sunrise &&
                country.equals(location.country) &&
                city.equals(location.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude, sunset, sunrise, country, city);
    }

    @Override
    public String toString() {
        return "Location{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", sunset=" + sunset +
                ", sunrise=" + sunrise +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}