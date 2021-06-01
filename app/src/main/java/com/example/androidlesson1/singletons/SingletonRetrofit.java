package com.example.androidlesson1.singletons;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingletonRetrofit {

    private static SingletonRetrofit instance = null;
    private static final String BASE_URL = "https://api.openweathermap.org/";
    private Retrofit retrofit;


    private SingletonRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static SingletonRetrofit getInstance() {
        if (instance == null) {
            instance = new SingletonRetrofit();
        }
        return instance;
    }

}
