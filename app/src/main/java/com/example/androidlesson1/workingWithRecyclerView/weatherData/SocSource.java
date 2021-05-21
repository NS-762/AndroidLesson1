package com.example.androidlesson1.workingWithRecyclerView.weatherData;

import android.content.res.Resources;

import com.example.androidlesson1.R;
import com.example.androidlesson1.weatherModelForThirtyDays.FullWeatherForDay;
import com.example.androidlesson1.weatherModelForThirtyDays.WeatherRequestForThirtyDays;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SocSource { //заполнения массива данными для элементов RV

    private int AMOUNT = 40;
    private List<Soc> dataSource;
    private Resources resources;

    private WeatherRequestForThirtyDays weatherRequest;

    public SocSource(WeatherRequestForThirtyDays weatherRequest, Resources resources) {
        dataSource = new ArrayList<>(AMOUNT);
        this.weatherRequest = weatherRequest;
        this.resources = resources;
    }

    public SocSource build() {
        List<FullWeatherForDay> listFullWeatherForDays = weatherRequest.getList(); //из подкачанных данных взять лист с погодой на несколько дней

        for (int i = 0; i < AMOUNT; i += 4) {

            FullWeatherForDay weatherForDay = listFullWeatherForDays.get(i); //из листа погод взять конкретный день

            String temperature = Integer.toString((int) weatherForDay.getMain().getTemp_max()); //взять его температуру и тд
            String wind = Float.toString(weatherForDay.getWind().getSpeed());
            String pressure = Float.toString(weatherForDay.getMain().getPressure());
            String humidity = Float.toString(weatherForDay.getMain().getHumidity());
            String mainDescription = weatherForDay.getWeather().get(0).getMain();
            String date = getDateForThisDay(weatherForDay);

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
                    weatherPicture = R.drawable.clouds_day; //тут можно сделать ночные/дневные облака
                    break;
                default:
                    weatherPicture = R.drawable.cyclone;
            }

            dataSource.add(new Soc(date, temperature,
                    weatherPicture, wind, pressure, humidity, mainDescription));
        }
        return this;
    }

    public String getDateForThisDay(FullWeatherForDay weatherForDay) {
        long unixSeconds = weatherForDay.getDt(); // секунды из api
        Date dateFormat = new java.util.Date(unixSeconds * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        String dayText = sdf.format(dateFormat);
        return dayText;
    }

    public Soc getSoc(int position) {
        return dataSource.get(position);
    }

    public int size() {
        return dataSource.size();
    }
}
