package com.example.androidlesson1.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;

import com.example.androidlesson1.Constants;
import com.example.androidlesson1.R;
import com.example.androidlesson1.SingletonForHistoryOfCities;
import com.example.androidlesson1.workingWithRecyclerView.historyOfCities.SocSource;
import com.example.androidlesson1.workingWithRecyclerView.historyOfCities.SocnetAdapter;

public class HistoryOfCitiesActivity extends AppCompatActivity implements Constants, View.OnCreateContextMenuListener {

    private SocnetAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_of_cities);

        initRecyclerView();
    }


    public void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_history);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new SocnetAdapter(SingletonForHistoryOfCities.getInstance().getHistoryOfCities());
        recyclerView.setAdapter(adapter);
        registerForContextMenu(recyclerView);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) { //контексно меню

        switch (item.getItemId()) {
            case (MENU_DELETE_ITEM): //выбор в меню пункта "Удалить"
                SingletonForHistoryOfCities.getInstance().deleteCityFromHistory(item.getGroupId());
                adapter.notifyItemRemoved(item.getGroupId());
                break;
        }
        return super.onContextItemSelected(item);
    }
}