package com.example.weatherapp;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.recyclerview.widget.RecyclerView;

        import com.example.weatherapp.data.DataParser;
        import com.example.weatherapp.model.HourlyForecast;

        import java.util.List;

public class HourlyForecastAdapter extends RecyclerView.Adapter<HourlyForecastAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<HourlyForecast> forecasts;

    HourlyForecastAdapter(Context context, List<HourlyForecast> forecasts) {
        this.forecasts = forecasts;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.hourly_forecast_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HourlyForecastAdapter.ViewHolder holder, int position) {
        HourlyForecast forecast = forecasts.get(position);
        holder.time.setText(forecast.getHours() + "h");
        //holder.condition.setText(forecast.getCondition().getIconId());
        holder.temperature.setText(Math.round(forecast.getTemperature().getTemperatureCelsius()) + " °C");
        holder.icon.setImageResource(DataParser.getImageResource(forecast.getCondition().getIconId()));
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
        final TextView time;
        //final TextView condition;
        final TextView temperature;
        final ImageView icon;

        ViewHolder(View view){
            super(view);
            time = view.findViewById(R.id.time);
            //condition = view.findViewById(R.id.condition);
            temperature = view.findViewById(R.id.temperature);
            icon = view.findViewById(R.id.conditionIcon);
        }
    }
}
