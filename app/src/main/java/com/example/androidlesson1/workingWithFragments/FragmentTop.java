package com.example.androidlesson1.workingWithFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidlesson1.Constants;
import com.example.androidlesson1.R;
import com.example.androidlesson1.SingletonForImage;
import com.example.androidlesson1.weatherModel.WeatherRequest;
import com.example.androidlesson1.workingWithWeatherData.WeatherData;
import com.example.androidlesson1.workingWithWeatherData.WeatherFromInternet;


import java.time.LocalDateTime;

public class FragmentTop extends Fragment implements Constants, Subscriber, WeatherFromInternet {

    private ImageView weatherPictureView;
    private TextView cityTextView;
    private TextView temperatureTextView;
    private TextView dateTextView;
    private TextView windTextView;
    private TextView pressureTextView;
    private TextView humidityTextView;
    private View view;
    private SharedPreferences sharedPreferences;
    private boolean isDataUpdateRequired = true;
    private String cityText;


    public void setDataUpdateRequired(boolean dataUpdateRequired) {
        isDataUpdateRequired = dataUpdateRequired;
    }

    public static FragmentTop create() { //фабричный метод
        FragmentTop fragmentTop = new FragmentTop();
        Bundle args = new Bundle();
        fragmentTop.setArguments(args);
        return fragmentTop;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_top, container, false);
        init();



        if (savedInstanceState != null) {
            isDataUpdateRequired = savedInstanceState.getBoolean("IsDataUpdateRequired");
            cityText = savedInstanceState.getString(CITY); //название говорода, для которого будут скачиваться данные
        }


        if (savedInstanceState != null && !isDataUpdateRequired) { //если есть сохраненные данные и нет необходимости их обновлять
            cityTextView.setText(savedInstanceState.getString(CITY));
            temperatureTextView.setText(savedInstanceState.getString("temperature"));
            windTextView.setText(savedInstanceState.getString("wind"));
            pressureTextView.setText(savedInstanceState.getString("pressure"));
            humidityTextView.setText(savedInstanceState.getString("humidity"));
            weatherPictureView = SingletonForImage.getInstance().getWeatherPicture();
        } else { //в противном случае загрузить данные из интернета
            WeatherData weatherData = new WeatherData(this,
                    cityText);
            weatherData.getWeatherData();
            cityTextView.setText(cityText);
            isDataUpdateRequired = false; //это нужно, чтобы при смене ориентации не скачивались новые данные
        }

        dateTextView.setText(LocalDateTime.now().toString());
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void updateData(String newDate, String newDayOfWeek, String newTemperature, Drawable newWeatherPicture) {
        dateTextView.setText(newDate + ", " + newDayOfWeek);
        temperatureTextView.setText(newTemperature);
        weatherPictureView.setImageDrawable(newWeatherPicture);
    }

    public void updateCity(String newCity) { //то, что меняется в настройках приложения
        cityTextView.setText(newCity);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CITY, cityTextView.getText().toString());
        outState.putString("temperature", temperatureTextView.getText().toString());
        outState.putString("wind", windTextView.getText().toString());
        outState.putString("pressure", pressureTextView.getText().toString());
        outState.putString("humidity", humidityTextView.getText().toString());
        outState.putBoolean("IsDataUpdateRequired", isDataUpdateRequired);
        SingletonForImage.getInstance().setWeatherPicture(weatherPictureView);

//        Snackbar.make(view, "Сохранялка",
//                Snackbar.LENGTH_SHORT).show();
//        Toast.makeText(getActivity(), "сохранялка", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setWeatherFromInternet(WeatherRequest weatherRequest) {
        int temp = (int) weatherRequest.getMain().getTemp();
        int pressure = weatherRequest.getMain().getPressure();
        int humidity = weatherRequest.getMain().getHumidity();
        float wind = weatherRequest.getWind().getSpeed();
        String mainDescription = weatherRequest.getWeather().get(0).getMain();



        int weatherPicture;
        switch (mainDescription) {
            case ("Thunderstorm"):
                weatherPicture = R.drawable.thunderstorm;
                break;
            case ("Drizzle"):
                weatherPicture = R.drawable.rain;
                break;
            case ("Rain"):
                weatherPicture = R.drawable.rain_day; //тут можно сделать ночной/дневной дождь
                break;
            case ("Snow"):
                weatherPicture = R.drawable.snowy;
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

        temperatureTextView.setText(temp + "\u00B0");
        windTextView.setText(Float.toString(wind) + "0");
        pressureTextView.setText(Integer.toString(pressure));
        humidityTextView.setText(Integer.toString(humidity) + ",0");
        weatherPictureView.setImageResource(weatherPicture);

    }

    private void init() {
        cityTextView = view.findViewById(R.id.city_0);
        temperatureTextView = view.findViewById(R.id.temperature_0);
        dateTextView = view.findViewById(R.id.date_0);
        weatherPictureView = view.findViewById(R.id.weather_picture_0);

        windTextView = view.findViewById(R.id.wind_0);
        pressureTextView = view.findViewById(R.id.pressure_0);
        humidityTextView = view.findViewById(R.id.humidity_0);

        cityText = cityTextView.getText().toString();
    }
}