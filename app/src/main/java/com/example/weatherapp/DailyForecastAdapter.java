package com.example.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.data.DataParser;
import com.example.weatherapp.model.DailyForecast;

import java.util.List;

public class DailyForecastAdapter extends RecyclerView.Adapter<DailyForecastAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<DailyForecast> forecasts;

    DailyForecastAdapter(Context context, List<DailyForecast> forecasts) {
        this.forecasts = forecasts;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.daily_forecast_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DailyForecastAdapter.ViewHolder holder, int position) {
        DailyForecast forecast = forecasts.get(position);
        holder.date.setText(DataParser.dayMonthToString(forecast.getDay(), forecast.getMonth()));
        //holder.condition.setText(forecast.getCondition().getIconId());
        holder.maxTemperature.setText(Math.round(forecast.getMaxTemperature().getTemperatureCelsius()) + " °C");
        holder.minTemperature.setText(Math.round(forecast.getMinTemperature().getTemperatureCelsius()) + " °C");
        holder.icon.setImageResource(DataParser.getImageResource(forecast.getIcon()));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView date;
        //final TextView condition;
        final TextView maxTemperature;
        final TextView minTemperature;
        final ImageView icon;

        ViewHolder(View view){
            super(view);
            date = view.findViewById(R.id.date);
            //condition = view.findViewById(R.id.condition);
            maxTemperature = view.findViewById(R.id.maxTemperature);
            minTemperature = view.findViewById(R.id.minTemperature);
            icon = view.findViewById(R.id.conditionIcon);
        }
    }
}

