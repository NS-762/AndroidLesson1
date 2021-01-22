package com.example.androidlesson1;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FragmentBottom extends Fragment {

    private String temperature;
    private String date;
    private String dayOfWeek;
    private ImageView weatherPicture;

    private TextView cityTextView;
    private TextView temperatureTextView;
    private TextView dayOfWeekTextView;
    private TextView dateTextView;

    private Publisher publisher;

//    private List<Subscriber> subscribers = new ArrayList<>();

    public static FragmentBottom create() { //фабричный метод
        FragmentBottom fragmentBottom = new FragmentBottom();
        Bundle args = new Bundle();
/*        args.putString(CITY, city);
        args.putString(temperature, temperature);
        args.putString(date, date);*/

        fragmentBottom.setArguments(args);

        return fragmentBottom;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        publisher = ((PublisherGetter)context).getPublisher();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bottom, container, false);

        LinearLayout linearLayoutTwo = view.findViewById(R.id.linear_layout_2);
        linearLayoutTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "клик!", Toast.LENGTH_SHORT).show();
                temperatureTextView = view.findViewById(R.id.temperature_2);
                dateTextView = view.findViewById(R.id.date_2);
                dayOfWeekTextView = view.findViewById(R.id.day_of_week_2);

                temperature = (String) temperatureTextView.getText();
                date = (String) dateTextView.getText();
                dayOfWeek = (String) dayOfWeekTextView.getText();

                publisher.notifySubscriber(temperature, date, dayOfWeek); //оповестить подписчика об изменении
            }
        });

        return view;
    }

/*    View.OnClickListener onClickListenerLinearLayoutOne = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            temperatureTextView = view.findViewById(R.id.temperature_1);
            dateTextView = view.findViewById(R.id.date_1);
            dayOfWeekTextView = view.findViewById(R.id.day_of_week_1);

            temperature = (String) temperatureTextView.getText();
            date = (String) dateTextView.getText();
            dayOfWeek = (String) dayOfWeekTextView.getText();

            notifySubscriber(temperature, date, dayOfWeek); //оповестить подписчика об изменении
        }
    };*/

/*    public void onClickLinearLayoutOne(View view) {
        temperatureTextView = view.findViewById(R.id.temperature_1);
        dateTextView = view.findViewById(R.id.date_1);
        dayOfWeekTextView = view.findViewById(R.id.day_of_week_1);
        *//*        weather_picture_N = findViewById(R.id.weather_picture_1);*//*

        temperature = (String) temperatureTextView.getText();
        date = (String) dateTextView.getText();
        dayOfWeek = (String) dayOfWeekTextView.getText();

        notifySubscriber(temperature, date, dayOfWeek); //оповестить подписчика об изменении
    }*/

/*    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void notifySubscriber(String temperature, String date, String dayOfWeek) {
        for (Subscriber s : subscribers) {
            s.updateData(temperature, date, dayOfWeek);
        }
    }*/

    public void onClickLinearLayoutTwo(View view) {
    }

    public void onClickLinearLayoutThree(View view) {
    }

    public void onClickLinearLayoutFour(View view) {
    }

    public void onClickLinearLayoutFive(View view) {
    }

    public void onClickLinearLayoutSix(View view) {
    }

    public void onClickLinearLayoutSeven(View view) {
    }
}