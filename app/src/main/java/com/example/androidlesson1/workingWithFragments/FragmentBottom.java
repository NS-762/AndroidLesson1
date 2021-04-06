package com.example.androidlesson1.workingWithFragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidlesson1.R;
import com.example.androidlesson1.SingletonForImage;
import com.example.androidlesson1.SingletonForWeatherData;
import com.example.androidlesson1.weatherModelForThirtyDays.WeatherRequestForThirtyDays;
import com.example.androidlesson1.workingWithRecyclerView.SocSource;
import com.example.androidlesson1.workingWithRecyclerView.SocnetAdapter;
import com.example.androidlesson1.workingWithWeatherData.WeatherData;
import com.example.androidlesson1.workingWithWeatherData.WeatherDataForThirtyDays;
import com.example.androidlesson1.workingWithWeatherData.WeatherFromInternetForThirtyDays;

import java.time.LocalDateTime;

public class FragmentBottom extends Fragment implements ItemClickListener, WeatherFromInternetForThirtyDays {

    private Publisher publisher;
    private WeatherRequestForThirtyDays weatherRequestForThirtyDays;
    private View view;
    private String cityText;
    private boolean isDataUpdateRequired = true;

    public static FragmentBottom create() { //фабричный метод
        FragmentBottom fragmentBottom = new FragmentBottom();
        Bundle args = new Bundle();
        fragmentBottom.setArguments(args);
        return fragmentBottom;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        publisher = ((PublisherGetter) context).getPublisher();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bottom, container, false);


        if (savedInstanceState != null) {
            isDataUpdateRequired = savedInstanceState.getBoolean("IsDataUpdateRequired");
            cityText = savedInstanceState.getString("city"); //название говорода, для которого будут скачиваться данные
        }


        if (savedInstanceState != null && !isDataUpdateRequired) { //если есть сохраненные данные и нет необходимости их обновлять (переворот)
            cityText = savedInstanceState.getString("city");
            weatherRequestForThirtyDays = SingletonForWeatherData.getInstance().getWeatherRequest();
            SocSource socSource = new SocSource(weatherRequestForThirtyDays, getResources());
            initRecyclerView(view, socSource.build()); //передать в RV socSource с заполненным списком внутри
        } else { //в противном случае загрузить данные из интернета
            WeatherDataForThirtyDays weatherDataForThirtyDays = new WeatherDataForThirtyDays(this, cityText);
            weatherDataForThirtyDays.getWeatherDataForThirtyDays();
            isDataUpdateRequired = false; //это нужно, чтобы при смене ориентации не скачивались новые данные
        }




        return view;
    }

    public void initRecyclerView(View view, SocSource socSource) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        SocnetAdapter adapter = new SocnetAdapter(socSource, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void notifySubscribers(String newDayOfWeek, String newTemperature,
                                  Drawable newWeatherPicture, String newWind, String newPressure,
                                  String newHumidity, String newDescription) {
        publisher.notifySubscribers(newDayOfWeek, newTemperature, newWeatherPicture, newWind,
                newPressure, newHumidity, newDescription);
    }

    @Override
    public void setWeatherForThirtyDays(WeatherRequestForThirtyDays weatherRequestForThirtyDays) {
        this.weatherRequestForThirtyDays = weatherRequestForThirtyDays;
        Toast.makeText(getActivity(), "Данные на пять дней скачаны", Toast.LENGTH_SHORT).show();

        SocSource socSource = new SocSource(weatherRequestForThirtyDays, getResources());
        initRecyclerView(view, socSource.build()); //передать в RV socSource с заполненным списком внутри
    }

    public void updateCity(String newCity) {
        cityText = newCity;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("city", cityText);
        outState.putBoolean("IsDataUpdateRequired", isDataUpdateRequired);
        SingletonForWeatherData.getInstance().setWeatherRequest(weatherRequestForThirtyDays);
    }

    public void setDataUpdateRequired(boolean dataUpdateRequired) {
        isDataUpdateRequired = dataUpdateRequired;
    }

}