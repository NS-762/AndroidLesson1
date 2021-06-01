package com.example.androidlesson1;

import com.example.androidlesson1.weatherModel.WeatherRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeather {
    @GET("data/2.5/weather")
    Call<WeatherRequest> loadWeatherForOneDay(@Query("q") String city, @Query("units") String units,
                                              @Query("appid") String keyApi);
}
