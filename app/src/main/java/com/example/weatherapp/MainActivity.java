package com.example.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.weatherapp.data.DataParser;
import com.example.weatherapp.model.CurrentWeather;
import com.example.weatherapp.model.DailyForecast;
import com.example.weatherapp.model.HourlyForecast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @NonNull
    private TextView conditionTextView;
    @NonNull
    private TextView locationTextView;
    @NonNull
    private TextView dateTextView;
    @NonNull
    private ImageView icon;
    @NonNull
    private TextView temperatureTextView;

    @NonNull
    private RecyclerView hourlyForecastRecyclerView;
    @NonNull
    private RecyclerView dailyForecastRecyclerView;

    @NonNull
    private SwipeRefreshLayout swipeRefreshLayout;
    @NonNull
    private Button button;

    @NonNull
    private List<DailyForecast> weatherForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WeatherAppLocationListener.SetUpLocationListener(this);
        initView();
        loadCurrentWeather();
        loadWeatherForecast();
    }

    public void setCurrentWeather(@Nullable CurrentWeather currentWeather) {
        if (currentWeather == null) {
            startExceptionActivity();
        } else {
            conditionTextView.setText(currentWeather.getCondition().getCondition());
            final String title = currentWeather.getLocation().getCity() +
                    ", " + currentWeather.getLocation().getCountry();
            locationTextView.setText(title);
            //setTitle(title);
            temperatureTextView.setText(currentWeather.getTemperature().getTemperatureCelsius() +
                    " °C");

            icon.setImageResource(DataParser.getImageResource(currentWeather.getCondition().getIconId()));
        }
    }

    public void setWeatherForecast(@Nullable ArrayList<DailyForecast> forecast) {
        if (forecast == null || forecast.isEmpty()) {
            startExceptionActivity();
            return;
        } else {
            HourlyForecast anyForecastForToday = forecast.get(0).weatherByHours.get(0);
            dateTextView.setText(DataParser.intToMonthString(anyForecastForToday.getMonth()) +
                    ", " + anyForecastForToday.getDay());
            //dateTextView.setText(anyForecastForToday.getDay() + "." + anyForecastForToday.getMonth());

            this.weatherForecast = forecast;
            HourlyForecastAdapter hourlyForecastAdapter = new HourlyForecastAdapter(this, firstThreeHourlyForecasts());
            hourlyForecastRecyclerView.setAdapter(hourlyForecastAdapter);

            DailyForecastAdapter dailyForecastAdapter = new DailyForecastAdapter(this, this.weatherForecast);
            dailyForecastRecyclerView.setAdapter(dailyForecastAdapter);
        }
    }

    private List<HourlyForecast> firstThreeHourlyForecasts() {
        if (this.weatherForecast == null || this.weatherForecast.isEmpty()) {
            return null;
        }
        List<HourlyForecast> firstThreeHourlyForecasts = new ArrayList<HourlyForecast>(3);
        for (int i = 0; firstThreeHourlyForecasts.size() < 3 && i < 5; ++i) {
            for (int j = 0; firstThreeHourlyForecasts.size() < 3 &&
                    j < this.weatherForecast.get(i).weatherByHours.size(); ++j) {
                firstThreeHourlyForecasts.add(this.weatherForecast.get(i).weatherByHours.get(j));
            }
        }
        return firstThreeHourlyForecasts;
    }

    private void initView() {
        conditionTextView = findViewById(R.id.conditionText);
        dateTextView = findViewById(R.id.dateText);
        locationTextView = findViewById(R.id.locationText);
        temperatureTextView = findViewById(R.id.temperatureText);

        icon = findViewById(R.id.conditionIcon);

        hourlyForecastRecyclerView = findViewById(R.id.hourlyForecastRecyclerView);
        final LinearLayoutManager hourlyForecastLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        hourlyForecastRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        hourlyForecastRecyclerView.setLayoutManager(hourlyForecastLayoutManager);

        dailyForecastRecyclerView = findViewById(R.id.dailyForecastRecyclerView);
        final LinearLayoutManager dailyForecastLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        dailyForecastRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        dailyForecastRecyclerView.setLayoutManager(dailyForecastLayoutManager);

        /*button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                refreshData();
            }
        });*/

        /*swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshData();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });*/
    }

    private void loadCurrentWeather() {
        GetCurrentWeatherTask getCurrentWeatherTask = new GetCurrentWeatherTask(this);
        getCurrentWeatherTask.execute(new GetCurrentWeatherTask.Params("Minsk"));
    }

    private void loadWeatherForecast() {
        GetWeatherForecastTask getWeatherForecastTask = new GetWeatherForecastTask(this);
        getWeatherForecastTask.execute(new GetWeatherForecastTask.Params("Minsk"));
    }

    public void refreshData(){
        loadCurrentWeather();
        loadWeatherForecast();
    }

    private void startExceptionActivity(){
        Intent intent = new Intent(MainActivity.this, ExceptionActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
       refreshData();
    }
}
