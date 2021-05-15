package com.example.androidlesson1.workingWithWeatherData;

import com.example.androidlesson1.R;
import com.example.androidlesson1.weatherModel.WeatherRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParsingWeatherData {

    public String parsingDescription(WeatherRequest weatherRequest) {
        String mainDescription = weatherRequest.getWeather().get(0).getMain();
        return mainDescription;
    }

    public String parsingTemperature(WeatherRequest weatherRequest) {
        int temp = (int) weatherRequest.getMain().getTemp();
        return temp + "\u00B0";
    }

    public String parsingWind(WeatherRequest weatherRequest) {
        float wind = weatherRequest.getWind().getSpeed();
        return wind + "0";

    }

    public String parsingPressure(WeatherRequest weatherRequest) {
        String pressure = Integer.toString(weatherRequest.getMain().getPressure());
        return pressure;
    }

    public String parsingHumidity(WeatherRequest weatherRequest) {
        int humidity = weatherRequest.getMain().getHumidity();
        return humidity + ",0";
    }

    public int parsingWeatherPicture(WeatherRequest weatherRequest) {
        String mainDescription = weatherRequest.getWeather().get(0).getMain();
        int weatherPicture;

        switch (mainDescription) {
            case ("Thunderstorm"):
                weatherPicture = R.drawable.thunderstorm;
                break;
            case ("Drizzle"):
                weatherPicture = R.drawable.drizzle;
                break;
            case ("Rain"):
                weatherPicture = R.drawable.rain; //тут можно сделать ночной/дневной дождь
                break;
            case ("Snow"):
                weatherPicture = R.drawable.snow;
                break;
            case ("Clear"):
                weatherPicture = R.drawable.clear_day; //тут можно луну или солнце
                break;
            case ("Clouds"):
                weatherPicture = R.drawable.clouds_day;
                break;
            default:
                weatherPicture = R.drawable.cyclone;
        }

        return weatherPicture;
    }

    public String parsingDate(WeatherRequest weatherRequest) {
        long unixSeconds = weatherRequest.getDt(); // секунды
        Date dateFormat = new java.util.Date(unixSeconds * 1000);

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        String dayText = sdf.format(dateFormat);
        return dayText;
    }

}
