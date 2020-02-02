package com.example.weatherapp.data;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.weatherapp.WeatherAppLocationListener;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.LOCATION_SERVICE;

public abstract class HttpClient {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String WEATHER_ENDPOINT = "weather";
    private static final String FORECAST_ENDPOINT = "forecast";
    private static final String IMG_URL = "http://openweathermap.org/img/wn/";
    private static final String API_KEY = "39839ae9239775a9a2cc9f7b1a940373";

    private static Location currentLocation;

    private static Location getLastKnownLocation(@NonNull Context context) throws SecurityException{
        LocationManager lManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);

        Location gpsLocation = lManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location netLocation = lManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if(gpsLocation != null) {
            if(netLocation != null){
                if(netLocation.getTime() < gpsLocation.getTime()){
                    currentLocation = netLocation;
                    return currentLocation;
                }
            }
            currentLocation = gpsLocation;
            return currentLocation;
        }

        currentLocation = netLocation;
        return currentLocation;
    }

    public static String getCurrentWeather(String city) throws Exception {
        return getData(WEATHER_ENDPOINT, getRequestParameters(city));
    }

    public static String getCurrentWeather(Context context) throws Exception {

        if((currentLocation = WeatherAppLocationListener.getCurrentLocation())
                == null){
            if(getLastKnownLocation(context) == null){
                throw new Exception();
            }
        }

        return getData(WEATHER_ENDPOINT, getRequestParameters(currentLocation));
    }


    public static String getForecast(String city) throws Exception {
        return getData(FORECAST_ENDPOINT, getRequestParameters(city));
    }

    public static String getForecast(Context context) throws Exception {

        if((currentLocation = WeatherAppLocationListener.getCurrentLocation())
                == null){
            if(getLastKnownLocation(context) == null){
                throw new Exception();
            }
        }

        return getData(FORECAST_ENDPOINT, getRequestParameters(currentLocation));
    }


    public static byte[] getImage(@NonNull String code) throws Exception {
        HttpURLConnection con = null;
        InputStream is = null;
        try {
            con = (HttpURLConnection) (new URL(IMG_URL + code + "@2x.png")).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            // Let's read the response
            is = con.getInputStream();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while (is.read(buffer) != -1)
                baos.write(buffer);

            return baos.toByteArray();
        } finally {
            try {
                is.close();
            } catch (Throwable t) {
            }
            try {
                con.disconnect();
            } catch (Throwable t) {
            }
        }
    }

    @Nullable
    private static String getData(@NonNull String endpoint, @Nullable Map<String, String> params) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        urlBuilder.append(endpoint);
        if (params != null && !params.isEmpty()) {
            urlBuilder.append('?');
            for (Map.Entry<String, String> parameter : params.entrySet()) {
                urlBuilder
                        .append(parameter.getKey())
                        .append('=')
                        .append(parameter.getValue())
                        .append('&');
            }
        }
        String resultUrl = urlBuilder.toString();

        try {
            connection = (HttpURLConnection) (new URL(resultUrl)).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            StringBuilder buffer = new StringBuilder();
            inputStream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = br.readLine()) != null) {
                buffer.append(line).append("\r\n");
            }

            return buffer.toString();
        } catch (Throwable t) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Throwable ignored) {
            }
        }
    }

    private static Map<String, String> getRequestParameters(@NonNull Location location){
        Map<String, String> params = new HashMap<>();
        params.put("APPID", API_KEY);
        params.put("lat", Double.toString(location.getLatitude()));
        params.put("lon", Double.toString(location.getLongitude()));

        return params;
    }

    private static Map<String, String> getRequestParameters(@NonNull String city){
        Map<String, String> params = new HashMap<>();
        params.put("APPID", API_KEY);
        params.put("q", city);

        return params;
    }


}