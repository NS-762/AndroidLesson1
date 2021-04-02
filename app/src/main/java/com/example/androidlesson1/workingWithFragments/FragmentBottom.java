package com.example.androidlesson1.workingWithFragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidlesson1.R;
import com.example.androidlesson1.weatherModelForThirtyDays.WeatherRequestForThirtyDays;
import com.example.androidlesson1.workingWithRecyclerView.SocSource;
import com.example.androidlesson1.workingWithRecyclerView.SocnetAdapter;
import com.example.androidlesson1.workingWithWeatherData.WeatherDataForThirtyDays;
import com.example.androidlesson1.workingWithWeatherData.WeatherFromInternetForThirtyDays;

public class FragmentBottom extends Fragment implements ItemClickListener, WeatherFromInternetForThirtyDays {

    private Publisher publisher;
    private WeatherRequestForThirtyDays weatherRequestForThirtyDays;

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
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);
        SocSource socSource = new SocSource(getResources());
        initRecyclerView(view, socSource.build()); //передать в RV socSource с заполненным списком внутри

        WeatherDataForThirtyDays weatherDataForThirtyDays = new WeatherDataForThirtyDays(this,"Moscow");
        weatherDataForThirtyDays.getWeatherDataForThirtyDays();

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
    public void notifySubscribers(String newDate, String newDayOfWeek, String newTemperature, Drawable newWeatherPicture) {
        publisher.notifySubscribers(newDate, newDayOfWeek, newTemperature, newWeatherPicture);
    }

    @Override
    public void setWeatherForThirtyDays(WeatherRequestForThirtyDays weatherRequestForThirtyDays) {
        this.weatherRequestForThirtyDays = weatherRequestForThirtyDays;
        Toast.makeText(getActivity(), "Данные на пять дней скачаны", Toast.LENGTH_SHORT).show();
    }
}