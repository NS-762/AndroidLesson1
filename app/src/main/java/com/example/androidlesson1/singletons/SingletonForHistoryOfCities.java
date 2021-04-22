package com.example.androidlesson1.singletons;

import java.util.ArrayList;
import java.util.List;

public class SingletonForHistoryOfCities {

    private static SingletonForHistoryOfCities instance = null;
    private List<String> historyOfCities;


    private SingletonForHistoryOfCities() {
        historyOfCities = new ArrayList<>();
        historyOfCities.add("Moscow");
        historyOfCities.add("Murmansk");

        historyOfCities.add("Novoaltaisk");
        historyOfCities.add("Novokuznetsk");
        historyOfCities.add("Novokuibyshevsk");
        historyOfCities.add("Novomoskovsk");
        historyOfCities.add("Novorossiysk");
        historyOfCities.add("Novosibirsk");
        historyOfCities.add("Novotroitsk");
        historyOfCities.add("Novouralsk");
        historyOfCities.add("Novocheboksarsk");
        historyOfCities.add("Novocherkassk");
        historyOfCities.add("Novoshakhtinsk");

        historyOfCities.add("Sochi");
        historyOfCities.add("Stavropol");
        historyOfCities.add("Stary Oscol");
        historyOfCities.add("Sterlitmak");
        historyOfCities.add("Stupino");

    }

    public void addCityInHistory(String city) {
        if (!historyOfCities.contains(city)) { //если данного города нет в списке ранее просмотренных
            historyOfCities.add(city); //то добавить его
        }
    }

    public void clearHistory() {
        historyOfCities.clear();
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
