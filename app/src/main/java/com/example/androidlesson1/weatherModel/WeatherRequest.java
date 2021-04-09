package com.example.androidlesson1.weatherModel;

import com.example.androidlesson1.weatherModelForThirtyDays.Weather;

import java.util.List;

public class WeatherRequest {
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private String name;
    private long dt;

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
