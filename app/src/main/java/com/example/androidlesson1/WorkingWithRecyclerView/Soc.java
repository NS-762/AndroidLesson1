package com.example.androidlesson1.WorkingWithRecyclerView;

public class Soc {
    private String date;
    private String dayOfWeek;
    private String temperature;
    private int weatherPicture;

    public Soc(String date, String dayOfWeek, String temperature, int weatherPicture) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.temperature = temperature;
        this.weatherPicture = weatherPicture;
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
}
