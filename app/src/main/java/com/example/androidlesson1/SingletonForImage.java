package com.example.androidlesson1;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public final class SingletonForImage {

    private static SingletonForImage instance = null;
    private ImageView weatherPicture_0;

    private SingletonForImage() {
    }

    public void setWeatherPicture_0(ImageView weatherPicture_0) {
        this.weatherPicture_0 = weatherPicture_0;
    }

    public ImageView getWeatherPicture_0() {
        return weatherPicture_0;
    }

    public static SingletonForImage getInstance() {
        if (instance == null) {
            return instance = new SingletonForImage();
        }
        return instance;
    }
}
