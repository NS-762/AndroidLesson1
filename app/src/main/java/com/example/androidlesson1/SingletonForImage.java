package com.example.androidlesson1;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public final class SingletonForImage {

    private static SingletonForImage instance = null;
    private ImageView weather_picture_0;

    private SingletonForImage() {
    }

    public void setweather_picture_0(ImageView weather_picture_0) {
        this.weather_picture_0 = weather_picture_0;
    }

    public ImageView getweather_picture_0() {
        return weather_picture_0;
    }

    public static SingletonForImage getInstance() {
        if (instance == null) {
            return instance = new SingletonForImage();
        }
        return instance;
    }
}
