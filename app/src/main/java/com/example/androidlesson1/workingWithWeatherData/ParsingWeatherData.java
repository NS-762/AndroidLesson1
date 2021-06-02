package com.example.androidlesson1.workingWithWeatherData;

import android.os.Handler;

import com.example.androidlesson1.R;
import com.example.androidlesson1.weatherModel.WeatherRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Response;

public class ParsingWeatherData {

    private WeatherFromInternet weatherFromInternet;
    private WeatherRequest weatherRequest;

    private Response<WeatherRequest> response; //данные, полученные с помощью ретрофита

/*    public ParsingWeatherData(WeatherFromInternet weatherFromInternet, WeatherRequest weatherRequest) {
        this.weatherFromInternet = weatherFromInternet;
        this.weatherRequest = weatherRequest;
    }*/

    public ParsingWeatherData(WeatherFromInternet weatherFromInternet, Response<WeatherRequest> response) {
        this.weatherFromInternet = weatherFromInternet;
        this.response = response;
    }

    public void parsingAndSendData() {
        String mainDescription = response.body().getWeather().get(0).getMain();
        String temp = (int) response.body().getMain().getTemp() + "\u00B0";
        String wind = response.body().getWind().getSpeed() + "0";
        String pressure = Integer.toString(response.body().getMain().getPressure());
        String humidity = response.body().getMain().getHumidity() + ",0";

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

        long unixSeconds = response.body().getDt(); // секунды
        Date dateFormat = new java.util.Date(unixSeconds * 1000);

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        String dayText = sdf.format(dateFormat);


        weatherFromInternet.setWeatherFromInternet(mainDescription, temp, wind, pressure,
                humidity, weatherPicture, dayText);

    }
}
