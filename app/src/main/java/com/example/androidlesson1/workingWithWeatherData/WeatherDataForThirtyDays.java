package com.example.androidlesson1.workingWithWeatherData;

import android.os.Build;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.androidlesson1.BuildConfig;
import com.example.androidlesson1.weatherModelForThirtyDays.WeatherRequestForThirtyDays;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class WeatherDataForThirtyDays {
    private static final String TAG = "WEATHER";
    private static String WEATHER_URL;
    private String city = "Moscow";
    private WeatherRequestForThirtyDays weatherRequestForThirtyDays; //объект, куда запишутся все данные
    private WeatherFromInternetForThirtyDays weatherFromInternetForThirtyDays; //реализация интерфейса

    public WeatherDataForThirtyDays(WeatherFromInternetForThirtyDays weatherFromInternetForThirtyDays,
                                    String city) {
        this.weatherFromInternetForThirtyDays = weatherFromInternetForThirtyDays;
        if (city != null)
            this.city = city;
        WEATHER_URL = "https://api.openweathermap.org/data/2.5/forecast?q=" + this.city
                + "&cnt=5&units=metric&appid=";
    }

//    https://api.openweathermap.org/data/2.5/forecast?q=Moscow&units=metric&cnt=4&appid=4219fe39ece20b9a2e46b76729303c56

    public void getWeatherDataForThirtyDays() {
        try {
            final URL uri = new URL(WEATHER_URL + BuildConfig.WEATHER_API_KEY);
            final Handler handler = new Handler();

            new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void run() {
                    HttpsURLConnection urlConnection = null;
                    try {
                        urlConnection = (HttpsURLConnection) uri.openConnection();
                        urlConnection.setRequestMethod("GET");
                        urlConnection.setReadTimeout(10000);
                        BufferedReader in = new BufferedReader(new
                                InputStreamReader(urlConnection.getInputStream()));
                        String result = getLines(in);
                        Gson gson = new Gson();
                        weatherRequestForThirtyDays = gson.fromJson(result, WeatherRequestForThirtyDays.class);

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                weatherFromInternetForThirtyDays.setWeatherForThirtyDays(weatherRequestForThirtyDays);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (null != urlConnection) {
                            urlConnection.disconnect();
                        }
                    }

                }
            }).start();


        } catch (MalformedURLException e) {
            Log.e(TAG, "Fail connection", e);
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }

    public WeatherRequestForThirtyDays getWeatherRequestForThirtyDays() {
        return weatherRequestForThirtyDays;
    }
}
