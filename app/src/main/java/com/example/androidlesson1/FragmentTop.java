package com.example.androidlesson1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentTop extends Fragment implements Constants, Subscriber {

    private String city;
    private String temperature;
    private String date;
    private String dayOfWeek;
    private ImageView weatherPicture;

    private TextView cityTextView;
    private TextView temperatureTextView;
    private TextView dateTextView;

    private String newTemperature = "ahaha";
    private String newDate;


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

        LinearLayout linearLayout = view.findViewById(R.id.linear_layout_0);
        linearLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                temperatureTextView.setText(newTemperature);
                dateTextView.setText(newDate);
            }
        });


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
    public void updateData(String temperature, String date, String dayOfWeek) {
        Toast.makeText(getActivity(), "Метод из класса верхнего фрагмента", Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), temperature, Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), date, Toast.LENGTH_SHORT).show();

        newTemperature = temperature;
        newDate = date + ", " + dayOfWeek;

        temperatureTextView.setText(newTemperature);
        dateTextView.setText(newDate);
    }


}