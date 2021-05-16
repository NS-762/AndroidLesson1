package com.example.androidlesson1.workingWithWeatherData;

import android.os.Handler;

import com.example.androidlesson1.R;
import com.example.androidlesson1.weatherModel.WeatherRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParsingWeatherData {

    private WeatherFromInternet weatherFromInternet;
    private Handler handler;
    private WeatherRequest weatherRequest;

    public ParsingWeatherData(WeatherFromInternet weatherFromInternet, Handler handler,
                              WeatherRequest weatherRequest) {
        this.weatherFromInternet = weatherFromInternet;
        this.handler = handler;
        this.weatherRequest = weatherRequest;
    }

    public void parsingAndSendData() {
        String mainDescription = weatherRequest.getWeather().get(0).getMain();
        String temp = (int) weatherRequest.getMain().getTemp() + "\u00B0";
        String wind = weatherRequest.getWind().getSpeed() + "0";
        String pressure = Integer.toString(weatherRequest.getMain().getPressure());
        String humidity = weatherRequest.getMain().getHumidity() +  ",0";

        int weatherPicture;
        switch (mainDescription) {
            case ("Thunderstorm"):
                weatherPicture = R.drawable.thunderstorm;
                break;
            case ("Drizzle"):
                weatherPicture = R.drawable.drizzle;
                break;
            case ("Rain"):
                weatherPicture = R.drawable.rain; //можно сделать ночной/дневной дождь
                break;
            case ("Snow"):
                weatherPicture = R.drawable.snow;
                break;
            case ("Clear"):
                weatherPicture = R.drawable.clear_day; //можно сделать смену луны и солнца
                break;
            case ("Clouds"):
                weatherPicture = R.drawable.clouds_day;
                break;
            default:
                weatherPicture = R.drawable.cyclone;
        }

        long unixSeconds = weatherRequest.getDt(); // секунды
        Date dateFormat = new java.util.Date(unixSeconds * 1000);

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        String dayText = sdf.format(dateFormat);

        handler.post(new Runnable() {
            @Override
            public void run() {
                weatherFromInternet.setWeatherFromInternet(mainDescription, temp, wind, pressure,
                        humidity, weatherPicture, dayText);
            }
        });
    }
}
