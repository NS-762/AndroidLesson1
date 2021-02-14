package com.example.androidlesson1.WorkingWithFragments;

import android.graphics.drawable.Drawable;

public interface ItemClickListener {

    void notifySubscribers(String newDate, String newDayOfWeek, String newTemperature, Drawable newWeatherPicture);

}
