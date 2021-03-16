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
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class WeatherData {
    private static final String TAG = "WEATHER";
    private static final String WEATHER_URL =
            "https://api.openweathermap.org/data/2.5/weather?lat=55.75&lon=37.62&appid=";
    //    private static final String WEATHER_API_KEY = "4219fe39ece20b9a2e46b76729303c56";
    private WeatherRequest weatherRequest;
    private WeatherFromInternet weatherFromInternet;


    public WeatherData(WeatherFromInternet weatherFromInternet) {
        this.weatherFromInternet = weatherFromInternet;
    }

    public void getWeatherData() {
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
                        weatherRequest = gson.fromJson(result, WeatherRequest.class);

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                weatherFromInternet.setWeatherFromInternet(weatherRequest);
                            }
                        });

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
    }
}
