package com.example.androidlesson1;

import android.widget.ImageView;

import com.example.androidlesson1.weatherModelForThirtyDays.WeatherRequestForThirtyDays;

public class SingletonForWeatherData {

    private static SingletonForWeatherData instance = null;
    private WeatherRequestForThirtyDays weatherRequestForThirtyDays;

    private SingletonForWeatherData() {
    }

    public void setWeatherRequest(WeatherRequestForThirtyDays weatherRequestForThirtyDays) {
        this.weatherRequestForThirtyDays = weatherRequestForThirtyDays;
    }

    public WeatherRequestForThirtyDays getWeatherRequest() {
        return weatherRequestForThirtyDays;
    }

    public static SingletonForWeatherData getInstance() {
        if (instance == null) {
            return instance = new SingletonForWeatherData();
        }
        return instance;
    }
}
