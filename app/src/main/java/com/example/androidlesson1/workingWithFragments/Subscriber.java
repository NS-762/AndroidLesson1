package com.example.androidlesson1.workingWithFragments;

import android.graphics.drawable.Drawable;

public interface Subscriber {
    void updateData(String newDayOfWeek, String newTemperature, Drawable newWeatherPicture, String newWind,
                    String newPressure, String newHumidity, String newDescription);
}
