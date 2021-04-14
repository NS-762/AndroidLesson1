package com.example.androidlesson1.workingWithRecyclerView.historyOfCities;


import com.example.androidlesson1.SingletonForHistoryOfCities;

import java.util.ArrayList;
import java.util.List;

public class SocSource {

    private List<String> historyOfCities;
    private List<Soc> dataSource;

    public SocSource() {
        historyOfCities = SingletonForHistoryOfCities.getInstance().getHistoryOfCities(); //получить список с городами
        dataSource = new ArrayList<>();
    }

    public SocSource build() {

        for (int i = 0; i < historyOfCities.size(); i++) {

            String city = historyOfCities.get(i); //взять его температуру и тд
            dataSource.add(new Soc(city));
        }
        return this;
    }

    public Soc getSoc(int position) {
        return dataSource.get(position);
    }

    public void deleteCityFromHistory(int position) {
        dataSource.remove(position);
    }

    public int size() {
        return dataSource.size();
    }
}
