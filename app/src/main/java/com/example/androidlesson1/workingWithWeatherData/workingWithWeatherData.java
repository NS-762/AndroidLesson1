package com.example.androidlesson1.workingWithWeatherData;

import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.androidlesson1.weatherModel.WeatherRequest;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class WorkingWithWeatherData {
    private static final String TAG = "WEATHER";
    private static final String WEATHER_URL =
            "https://api.openweathermap.org/data/2.5/weather?lat=55.75&lon=37.62&appid=";
    private static final String WEATHER_API_KEY = "4219fe39ece20b9a2e46b76729303c56";

    private ImageView weatherPicture;
    private TextView cityTextView;
    private TextView temperatureTextView;
    private TextView windTextView;
    private TextView pressureTextView;
    private TextView humidityTextView;


    public WorkingWithWeatherData(TextView cityTextView, TextView temperatureTextView, TextView windTextView, TextView pressureTextView, TextView humidityTextView) {
        this.cityTextView = cityTextView;
        this.temperatureTextView = temperatureTextView;
        this.windTextView = windTextView;
        this.pressureTextView = pressureTextView;
        this.humidityTextView = humidityTextView;
    }

    public void getWeatherData() {
        try {
            final URL uri = new URL(WEATHER_URL + WEATHER_API_KEY);
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
                        final WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                displayWeather(weatherRequest);
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

    public void displayWeather(WeatherRequest weatherRequest) {
        String city = weatherRequest.getName();
        int temp = (int)weatherRequest.getMain().getTemp() - 273;
        int pressure = weatherRequest.getMain().getPressure();
        int humidity = weatherRequest.getMain().getHumidity();
        int wind = weatherRequest.getWind().getSpeed();

        cityTextView.setText(city);
        temperatureTextView.setText(temp+ "\u2103");
        pressureTextView.setText(Integer.toString(pressure));
        humidityTextView.setText(Integer.toString(humidity));
        windTextView.setText(Integer.toString(wind));
    }

}
