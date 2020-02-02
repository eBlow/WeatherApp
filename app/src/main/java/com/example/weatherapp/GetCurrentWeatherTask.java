package com.example.weatherapp;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.weatherapp.data.DataParser;
import com.example.weatherapp.data.HttpClient;
import com.example.weatherapp.model.CurrentWeather;

import java.lang.ref.WeakReference;

public final class GetCurrentWeatherTask extends AsyncTask<GetCurrentWeatherTask.Params, Void, CurrentWeather> {

    private final WeakReference<MainActivity> mainActivityWeakReference;

    public GetCurrentWeatherTask(MainActivity mainActivity) {
        mainActivityWeakReference = new WeakReference<>(mainActivity);
    }

    @Nullable
    @Override
    protected CurrentWeather doInBackground(GetCurrentWeatherTask.Params... args) {
        try {
            String city = args[0].getCity();
            String data = HttpClient.getCurrentWeather(mainActivityWeakReference.get());
            return DataParser.parseCurrentWeather(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(@Nullable CurrentWeather currentWeather) {
        MainActivity mainActivity = mainActivityWeakReference.get();
        if (mainActivity == null) return;
        mainActivity.setCurrentWeather(currentWeather);
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