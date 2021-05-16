package com.example.androidlesson1.workingWithWeatherData;

public interface WeatherFromInternet {

    void setWeatherFromInternet(String description, String temp, String wind, String pressure,
                                String humidity, int weatherPicture, String dayText);
}
