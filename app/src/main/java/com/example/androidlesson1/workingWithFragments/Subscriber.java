package com.example.androidlesson1.workingWithFragments;

import android.graphics.drawable.Drawable;

public interface Subscriber {
    void updateData(String newDate, String newDayOfWeek, String newTemperature, Drawable newWeatherPicture);
}
