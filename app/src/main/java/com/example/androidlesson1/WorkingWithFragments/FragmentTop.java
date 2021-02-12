package com.example.androidlesson1.WorkingWithFragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidlesson1.Constants;
import com.example.androidlesson1.R;

public class FragmentTop extends Fragment implements Constants, Subscriber {

    private ImageView weatherPicture;

    private TextView cityTextView;
    private TextView temperatureTextView;
    private TextView dateTextView;

    public static FragmentTop create() { //фабричный метод
        FragmentTop fragmentTop = new FragmentTop();
        Bundle args = new Bundle();
        fragmentTop.setArguments(args);

        return fragmentTop;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top, container, false);

        temperatureTextView = view.findViewById(R.id.temperature_0);
        dateTextView = view.findViewById(R.id.date_0);
        cityTextView = view.findViewById(R.id.city_0);
        weatherPicture = view.findViewById(R.id.weather_picture_0);
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

/*    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) { //сохранение состояния

        outState.putString(CITY, city);
        outState.putString(TEMPERATURE, temperature);
        outState.putString(DATE, date);

        super.onSaveInstanceState(outState);
    }*/

    @Override
    public void updateData(String newDate, String newDayOfWeek, String newTemperature, Drawable newWeatherPicture) {

        dateTextView.setText(newDate + ", " + newDayOfWeek);
        temperatureTextView.setText(newTemperature);
        weatherPicture.setImageDrawable(newWeatherPicture);
    }
}