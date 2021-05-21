package com.example.androidlesson1.workingWithFragments;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

public class Publisher {

    private List<Subscriber> subscribers;

    public Publisher() {
        subscribers = new ArrayList<>();
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void notifySubscribers(String newDayOfWeek, String newTemperature, Drawable newWeatherPicture,
                                  String newWind, String newPressure, String newHumidity,
                                  String newDescription) {
        for (Subscriber s : subscribers) {
            s.updateData(newDayOfWeek, newTemperature, newWeatherPicture, newWind,
                    newPressure, newHumidity, newDescription);
        }
    }
}
