package com.example.androidlesson1;

import java.util.ArrayList;
import java.util.List;

public class SingletonForHistoryOfCities {

    private static SingletonForHistoryOfCities instance = null;
    private List<String> historyOfCities;


    private SingletonForHistoryOfCities() {
        historyOfCities = new ArrayList<>();
        historyOfCities.add("Moscow");
        historyOfCities.add("Stav");
    }

    public void addCityInHistory(String city) {
        if (!historyOfCities.contains(city)) { //если данного города нет в списке ранее просмотренных
            historyOfCities.add(city); //то добавить его
        }
    }

    public void deleteCityFromHistory(int position) {
        historyOfCities.remove(position);
    }

    public List<String> getHistoryOfCities() {
        return historyOfCities;
    }

    public static SingletonForHistoryOfCities getInstance() {

        if (instance == null) {
            return instance = new SingletonForHistoryOfCities();
        }
        return instance;
    }
}
