package com.example.androidlesson1.weatherModelForThirtyDays;

import java.util.List;

public class WeatherRequestForThirtyDays {
    private String message;
    private List<FullWeatherForDay> list;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FullWeatherForDay> getList() {
        return list;
    }

    public void setList(List<FullWeatherForDay> list) {
        this.list = list;
    }
}
