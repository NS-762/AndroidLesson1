package com.example.androidlesson1.WorkingWithFragments;

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

    public void notifySubscribers(String newDate, String newDayOfWeek, String newTemperature, Drawable newWeatherPicture) {
        for (Subscriber s : subscribers) {
            s.updateData(newDate, newDayOfWeek, newTemperature, newWeatherPicture);
        }
    }
}
