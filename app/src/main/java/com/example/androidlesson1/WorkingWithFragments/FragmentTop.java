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
import android.widget.Toast;

import com.example.androidlesson1.Constants;
import com.example.androidlesson1.R;
import com.example.androidlesson1.SingletonForImage;
import com.example.androidlesson1.workingWithWeatherData.WorkingWithWeatherData;
import com.google.android.material.snackbar.Snackbar;

public class FragmentTop extends Fragment implements Constants, Subscriber {

    private ImageView weatherPicture;
    private TextView cityTextView;
    private TextView temperatureTextView;
    private TextView dateTextView;
    private TextView windTextView;
    private TextView pressureTextView;
    private TextView humidityTextView;



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

    private void init() {
        cityTextView = view.findViewById(R.id.city_0);
        temperatureTextView = view.findViewById(R.id.temperature_0);
        dateTextView = view.findViewById(R.id.date_0);
        weatherPicture = view.findViewById(R.id.weather_picture_0);

        windTextView = view.findViewById(R.id.wind_0);
        pressureTextView = view.findViewById(R.id.pressure_0);
        humidityTextView = view.findViewById(R.id.humidity_0);

        workingWithWeatherData = new WorkingWithWeatherData(cityTextView, temperatureTextView,
                windTextView, pressureTextView, humidityTextView);
        workingWithWeatherData.getWeatherData();
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

}