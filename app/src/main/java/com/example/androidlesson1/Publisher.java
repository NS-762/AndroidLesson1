package com.example.androidlesson1;

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

    public void notifySubscriber(String temperature, String date, String dayOfWeek) {
        for (Subscriber s : subscribers) {
            s.updateData(temperature, date, dayOfWeek);
        }
    }
}
