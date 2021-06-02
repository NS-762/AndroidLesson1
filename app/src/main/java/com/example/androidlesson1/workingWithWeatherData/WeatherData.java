package com.example.androidlesson1.workingWithWeatherData;

import android.os.Build;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.androidlesson1.BuildConfig;
import com.example.androidlesson1.weatherModel.WeatherRequest;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class WeatherData {
/*    private static final String TAG = "WEATHER";
    private static String WEATHER_URL;
    private String city = "Moscow"; //по умолчанию погода для Мск
    private WeatherRequest weatherRequest;
    private WeatherFromInternet weatherFromInternet;


    public WeatherData(WeatherFromInternet weatherFromInternet, String city) {
        this.weatherFromInternet = weatherFromInternet;
        if (city != null)
            this.city = city;
//        WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=" + this.city + ",RU&appid=";
        WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=" + this.city + "&units=metric&appid=";
    }

   //для одоного дня https://api.openweathermap.org/data/2.5/weather?q=Moscow&units=metric&appid=4219fe39ece20b9a2e46b76729303c56

  //для четырех дней  https://api.openweathermap.org/data/2.5/find?q=Moscow&units=metric&cnt=4&appid=4219fe39ece20b9a2e46b76729303c56

    public void getWeatherData() {
        try {
            final URL uri = new URL(WEATHER_URL +  BuildConfig.WEATHER_API_KEY);
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
                        weatherRequest = gson.fromJson(result, WeatherRequest.class);

                        *//*ParsingWeatherData parsingWeatherData =
                                new ParsingWeatherData(weatherFromInternet, weatherRequest); //класс для парсинга данных
                        parsingWeatherData.parsingAndSendData();*//*

                    } catch (Exception e) {
                        Log.e(TAG, "Fail connection", e);
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
    }*/


}
