package com.example.androidlesson1.workingWithRecyclerView;

import android.content.res.Resources;
import android.content.res.TypedArray;

import com.example.androidlesson1.R;

import java.util.ArrayList;
import java.util.List;


public class SocSource { //заполнения массива данными для элементов RV

    private List<Soc> dataSource;
    private Resources resources;

    public SocSource(Resources resources) {
        dataSource = new ArrayList<>(7);
        this.resources = resources;
    }

    public SocSource build() {
        String[] dates = resources.getStringArray(R.array.date);
        String[] dayOfWeeks = resources.getStringArray(R.array.day_of_week);
        String[] temperatures = resources.getStringArray(R.array.temperature);
        int[] weatherPictures = getImageArray();

        for (int i = 0; i < dates.length; i++) {
            dataSource.add(new Soc(dates[i], dayOfWeeks[i], temperatures[i], weatherPictures[i]));
        }
        return this;
    }

    public Soc getSoc (int position) {
        return dataSource.get(position);
    }

    public int size() {
        return dataSource.size();
    }

    private int[] getImageArray() {
        TypedArray pictures = resources.obtainTypedArray(R.array.weather_picture);
        int length = pictures.length();
        int[] weatherPictures = new int[length];
        for (int i = 0; i < length; i++) {
            weatherPictures[i] = pictures.getResourceId(i,0);
        }
        return weatherPictures;
    }
}
