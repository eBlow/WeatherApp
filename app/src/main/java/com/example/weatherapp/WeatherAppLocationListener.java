package com.example.weatherapp;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class WeatherAppLocationListener implements LocationListener {
    private static Location currentLocation;
    private static LocationManager locationManager;
    private static WeatherAppLocationListener locationListener;
    private static Context context;

    public static void SetUpLocationListener(@NonNull Context context) throws SecurityException
    {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new WeatherAppLocationListener();

        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                10000,
                10,
                locationListener);

        currentLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    }

    public static Location getCurrentLocation(){
        return currentLocation;
    }

    @Nullable
    public static String getCurrentCityCountry(){
        if(context == null || currentLocation == null){
            return null;
        }
        String cityName = null;
        String countryName = null;
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(currentLocation.getLatitude(),
                    currentLocation.getLongitude(), 1);
            if (addresses.size() > 0) {
                System.out.println(addresses.get(0).getLocality());
                cityName = addresses.get(0).getLocality();
                countryName = addresses.get(0).getCountryName();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return cityName + ", " + countryName;
    }

    @Override
    public void onLocationChanged(Location location){
        currentLocation = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider){}

    @Override
    public void onProviderDisabled(String provider){}
}
