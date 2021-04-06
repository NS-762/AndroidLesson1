package com.example.androidlesson1.workingWithFragments;

import android.graphics.drawable.Drawable;

public interface ItemClickListener {

    void notifySubscribers(String newDayOfWeek, String newTemperature, Drawable newWeatherPicture,
                           String wind, String pressure, String humidity, String description);

}
