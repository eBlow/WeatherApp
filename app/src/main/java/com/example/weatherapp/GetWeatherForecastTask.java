package com.example.weatherapp;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.weatherapp.data.DataParser;
import com.example.weatherapp.data.HttpClient;
import com.example.weatherapp.model.DailyForecast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public final class GetWeatherForecastTask extends AsyncTask<GetWeatherForecastTask.Params, Void, ArrayList<DailyForecast>> {

    private final WeakReference<MainActivity> mainActivityWeakReference;

    public GetWeatherForecastTask(MainActivity mainActivity) {
        mainActivityWeakReference = new WeakReference<>(mainActivity);
    }

    @Nullable
    @Override
    protected ArrayList<DailyForecast> doInBackground(GetWeatherForecastTask.Params... args) {
        try {
            String city = args[0].getCity();
            String data = HttpClient.getForecast(mainActivityWeakReference.get());
            return DataParser.parseWeatherForecast(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(@Nullable ArrayList<DailyForecast> forecast) {
        MainActivity mainActivity = mainActivityWeakReference.get();
        if (mainActivity == null) return;
        mainActivity.setWeatherForecast(forecast);
    }

    public static final class Params {
        @NonNull
        private final String city;

        public Params(@NonNull String city) {
            this.city = city;
        }

        @NonNull
        public String getCity() {
            return city;
        }
    }
}