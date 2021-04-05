package com.example.androidlesson1.workingWithRecyclerView;

import android.graphics.drawable.Drawable;

public class Soc {
    private String date;
    private String dayOfWeek;
    private String temperature;
    private int weatherPicture;

    private String wind;
    private String pressure;
    private String humidity;

    public Soc(String date, String dayOfWeek, String temperature, int weatherPicture, String wind, String pressure, String humidity) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.temperature = temperature;
        this.weatherPicture = weatherPicture;
        this.wind = wind;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public String getDate() {
        return date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getTemperature() {
        return temperature;
    }

    public int getWeatherPicture() {
        return weatherPicture;
    }


    public String getWind() {
        return wind;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }
}
