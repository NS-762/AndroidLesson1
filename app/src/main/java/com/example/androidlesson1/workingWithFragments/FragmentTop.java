package com.example.androidlesson1.workingWithFragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidlesson1.Constants;
import com.example.androidlesson1.R;
import com.example.androidlesson1.SingletonForImage;
import com.example.androidlesson1.weatherModel.WeatherRequest;
import com.example.androidlesson1.weatherModel.WorkingWithWeatherData;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.CyclicBarrier;

public class FragmentTop extends Fragment implements Constants, Subscriber {

    private ImageView weatherPicture;
    private TextView cityTextView;
    private TextView temperatureTextView;
    private TextView dateTextView;
    private TextView windTextView;
    private TextView pressureTextView;
    private TextView humidityTextView;
    WeatherRequest weatherRequest;

    private View view;
    private WorkingWithWeatherData workingWithWeatherData;

    public static FragmentTop create() { //фабричный метод
        FragmentTop fragmentTop = new FragmentTop();
        Bundle args = new Bundle();
        fragmentTop.setArguments(args);
        return fragmentTop;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_top, container, false);
        init();

        if (savedInstanceState != null) {
            cityTextView.setText(savedInstanceState.getString(CITY));
            temperatureTextView.setText(savedInstanceState.getString(TEMPERATURE));
            dateTextView.setText(savedInstanceState.getString(DATE));
            Drawable drawable = SingletonForImage.getInstance().getWeatherPicture().getDrawable(); //картинка из синлтона
            weatherPicture.setImageDrawable(drawable);
        }
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
        outState.putString(TEMPERATURE, temperatureTextView.getText().toString());
        outState.putString(DATE, dateTextView.getText().toString());
        SingletonForImage.getInstance().setWeatherPicture(weatherPicture); //сохранить картинку в синглтоне

//        Snackbar.make(view, "Сохранялка",
//                Snackbar.LENGTH_SHORT).show();
//        Toast.makeText(getActivity(), "сохранялка", Toast.LENGTH_SHORT).show();
    }

    private void init() {
        cityTextView = view.findViewById(R.id.city_0);
        temperatureTextView = view.findViewById(R.id.temperature_0);
        dateTextView = view.findViewById(R.id.date_0);
        weatherPicture = view.findViewById(R.id.weather_picture_0);

        windTextView = view.findViewById(R.id.wind_0);
        pressureTextView = view.findViewById(R.id.pressure_0);
        humidityTextView = view.findViewById(R.id.humidity_0);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        workingWithWeatherData = new WorkingWithWeatherData(cyclicBarrier);
        workingWithWeatherData.getWeatherData(); //получение данных из интернета

        try {
            cyclicBarrier.await(); //тут барьер для потоков, потому что получалось, что данные из интернета не успевали загружаться
        } catch (Exception e) {
            e.printStackTrace();
        }

        weatherRequest = workingWithWeatherData.getWeatherRequest(); //взять полученные данные из апи класса
        displayWeather(weatherRequest); //установка полученных данных во фрагмент
    }

    public void displayWeather(WeatherRequest weatherRequest) {
        String city = weatherRequest.getName();
        int temp = (int)weatherRequest.getMain().getTemp() - 273;
        int pressure = weatherRequest.getMain().getPressure();
        int humidity = weatherRequest.getMain().getHumidity();
        int wind = weatherRequest.getWind().getSpeed();

        cityTextView.setText(city);
        temperatureTextView.setText(temp + "\u00B0");
        pressureTextView.setText(Integer.toString(pressure));
        humidityTextView.setText(Integer.toString(humidity));
        windTextView.setText(Integer.toString(wind));
    }
}