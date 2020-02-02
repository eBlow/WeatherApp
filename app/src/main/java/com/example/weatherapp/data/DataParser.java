package com.example.weatherapp.data;

import com.example.weatherapp.R;
import com.example.weatherapp.entity.Condition;
import com.example.weatherapp.entity.Temperature;
import com.example.weatherapp.model.CurrentWeather;
import com.example.weatherapp.entity.Location;
import com.example.weatherapp.entity.Weather;
import com.example.weatherapp.model.DailyForecast;
import com.example.weatherapp.model.HourlyForecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DataParser {

    public static CurrentWeather parseCurrentWeather(String data) throws  JSONException {
        JSONObject rootJsonObject = new JSONObject(data);
        Location location = getLocation(rootJsonObject);
        return new CurrentWeather(
                getWeather(rootJsonObject),
                location
        );
    }

    public static ArrayList<DailyForecast> parseWeatherForecast(String data) throws JSONException {
        ArrayList<DailyForecast> forecast = new ArrayList<DailyForecast>(5);
        JSONObject jsonObject = new JSONObject(data);
        JSONArray jsonArray = jsonObject.getJSONArray("list");

        int cnt = jsonObject.getInt("cnt");
        int hourlyForecastIndex = 0;
        HourlyForecast hourlyForecast = getHourlyForecast(jsonArray.getJSONObject(hourlyForecastIndex++));
        for (int day = 0; day < 5 && hourlyForecastIndex < cnt; ++day) {
            forecast.add(new DailyForecast());
            DailyForecast dailyForecast = forecast.get(day);
            dailyForecast.setMonth(hourlyForecast.getMonth());
            dailyForecast.setDay(hourlyForecast.getDay());

            while (hourlyForecastIndex < cnt) {
                dailyForecast.weatherByHours.add(hourlyForecast);
                hourlyForecast = getHourlyForecast(jsonArray.getJSONObject(hourlyForecastIndex++));
                if (hourlyForecast.getDay() != dailyForecast.getDay()) {
                    break;
                }
            }
            dailyForecast.calculateMaxTemperature();
            dailyForecast.calculateMinTemperature();
            dailyForecast.calculateIcon();
        }

        return forecast;
    }

    private static HourlyForecast getHourlyForecast(JSONObject rootJsonObject) throws JSONException {
        String dateTime = rootJsonObject.getString("dt_txt");
        Integer month = Integer.parseInt(dateTime.substring(5, 7));
        Integer day = Integer.parseInt(dateTime.substring(8, 10));
        Integer hours = Integer.parseInt(dateTime.substring(11, 13));
        return new HourlyForecast(
                getWeather(rootJsonObject),
                day,
                month,
                hours
        );
    }

    private static Weather getWeather(JSONObject rootJsonObject) throws JSONException {
        Condition condition = getCondition(rootJsonObject);
        Temperature temperature = getTemperature(rootJsonObject);
        return new Weather(
                condition,
                temperature
        );
    }

    private static Location getLocation(JSONObject root) throws JSONException {
        JSONObject coordinatesJsonObject = root.getJSONObject("coord");
        JSONObject sysJsonObject = root.getJSONObject("sys");
        return new Location(
                (float) coordinatesJsonObject.getDouble("lon"),
                (float) coordinatesJsonObject.getDouble("lat"),
                sysJsonObject.getLong("sunset"),
                sysJsonObject.getLong("sunrise"),
                sysJsonObject.getString("country"),
                root.getString("name")
        );
    }

    private static Condition getCondition(JSONObject root) throws JSONException {
        JSONObject weatherJsonObject = root.getJSONArray("weather").getJSONObject(0);
        JSONObject weatherParamsJsonObject = root.getJSONObject("main");
        return new Condition(
                weatherJsonObject.getInt("id"),
                weatherJsonObject.getString("main"),
                weatherJsonObject.getString("description"),
                weatherJsonObject.getString("icon"),
                (float) weatherParamsJsonObject.getDouble("pressure"),
                (float) weatherParamsJsonObject.getDouble("humidity")
        );
    }

    private static Temperature getTemperature(JSONObject root) throws JSONException {
        JSONObject weatherParamsJsonObject = root.getJSONObject("main");
        return new Temperature(
                (float) weatherParamsJsonObject.getDouble("temp")
        );
    }

    public static String dayMonthToString(int day, int month){
        StringBuilder result = new StringBuilder("00.00");
        if (day < 1 || day > 31 || month < 1 || month > 12){
            return result.toString();
        }
        if (day > 9) result.setCharAt(0, Integer.toString(day%100).charAt(0));
        result.setCharAt(1, Integer.toString(day%10).charAt(0));
        if (month > 9) result.setCharAt(3, Integer.toString(month%100).charAt(0));
        result.setCharAt(4, Integer.toString(month%10).charAt(0));
        return result.toString();
    }

    public static String intToMonthString(int month){
        switch (month){
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
        }
        return null;
    }

    public static int getImageResource(String iconId){
        if(iconId == null){
            return R.drawable.d50;
        }
        switch(iconId) {
            case "01d": return R.drawable.d01;
            case "01n": return R.drawable.n01;
            case "02d": return R.drawable.d02;
            case "02n": return R.drawable.n02;
            case "03d": return R.drawable.d03;
            case "03n": return R.drawable.n03;
            case "04d": return R.drawable.d04;
            case "04n": return R.drawable.n04;
            case "09d": return R.drawable.d09;
            case "09n": return R.drawable.n09;
            case "10d": return R.drawable.d10;
            case "10n": return R.drawable.n10;
            case "11d": return R.drawable.d11;
            case "11n": return R.drawable.n11;
            case "13d": return R.drawable.d13;
            case "13n": return R.drawable.n13;
            case "50d": return R.drawable.d50;
            case "50n": return R.drawable.n50;
        }
        return R.drawable.d01;
    }
}
