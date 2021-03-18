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

import com.example.androidlesson1.Constants;
import com.example.androidlesson1.R;
import com.example.androidlesson1.weatherModel.WeatherRequest;
import com.example.androidlesson1.workingWithWeatherData.WeatherData;
import com.example.androidlesson1.workingWithWeatherData.WeatherFromInternet;


import java.time.LocalDateTime;

public class FragmentTop extends Fragment implements Constants, Subscriber, WeatherFromInternet {

    private ImageView weatherPicture;
    private TextView cityTextView;
    private TextView temperatureTextView;
    private TextView dateTextView;
    private TextView windTextView;
    private TextView pressureTextView;
    private TextView humidityTextView;
    private View view;
    private SharedPreferences sharedPreferences;

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
            cityTextView.setText(savedInstanceState.getString(CITY));
        }

        dateTextView.setText(LocalDateTime.now().toString());


        WeatherData weatherData = new WeatherData(this,
                cityTextView.getText().toString());
        weatherData.getWeatherData();

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
        weatherPicture.setImageDrawable(newWeatherPicture);
    }

    public void updateSettings(String newCity) { //то, что меняется в настройках приложения
        cityTextView.setText(newCity);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CITY, cityTextView.getText().toString());

//        Snackbar.make(view, "Сохранялка",
//                Snackbar.LENGTH_SHORT).show();
//        Toast.makeText(getActivity(), "сохранялка", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setWeatherFromInternet(WeatherRequest weatherRequest) {
        int temp = (int)weatherRequest.getMain().getTemp();
        int pressure = weatherRequest.getMain().getPressure();
        int humidity = weatherRequest.getMain().getHumidity();
        int wind = weatherRequest.getWind().getSpeed();

        temperatureTextView.setText(temp + "\u00B0");
        pressureTextView.setText(Integer.toString(pressure));
        humidityTextView.setText(Integer.toString(humidity));
        windTextView.setText(Integer.toString(wind));
    }

    private void init() {
        cityTextView = view.findViewById(R.id.city_0);
        temperatureTextView = view.findViewById(R.id.temperature_0);
        dateTextView = view.findViewById(R.id.date_0);
        weatherPicture = view.findViewById(R.id.weather_picture_0);

        windTextView = view.findViewById(R.id.wind_0);
        pressureTextView = view.findViewById(R.id.pressure_0);
        humidityTextView = view.findViewById(R.id.humidity_0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}