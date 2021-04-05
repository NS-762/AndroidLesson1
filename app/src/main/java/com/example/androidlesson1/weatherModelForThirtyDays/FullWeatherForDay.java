package com.example.androidlesson1.weatherModelForThirtyDays;

import com.example.androidlesson1.weatherModel.Main;
import com.example.androidlesson1.weatherModel.Wind;

import java.util.List;

public class FullWeatherForDay {
    private Main main;
    private Wind wind;
    private List<Weather> weather;

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

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
}
