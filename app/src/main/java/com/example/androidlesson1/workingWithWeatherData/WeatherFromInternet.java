package com.example.androidlesson1.workingWithWeatherData;

import com.example.androidlesson1.weatherModel.WeatherRequest;

public interface WeatherFromInternet {

    void setWeatherFromInternet(String description, String temp, String wind, String pressure,
                                String humidity, int weatherPicture, String dayText);
}
