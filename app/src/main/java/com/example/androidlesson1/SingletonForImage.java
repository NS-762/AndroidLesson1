package com.example.androidlesson1;

import android.widget.ImageView;

public final class SingletonForImage {

    private static SingletonForImage instance = null;
    private ImageView weatherPicture;

    private SingletonForImage() {
    }

    public void setWeatherPicture(ImageView weatherPicture) {
        this.weatherPicture = weatherPicture;
    }

    public ImageView getWeatherPicture() {
        return weatherPicture;
    }

    public static SingletonForImage getInstance() {
        if (instance == null) {
            return instance = new SingletonForImage();
        }
        return instance;
    }
}
