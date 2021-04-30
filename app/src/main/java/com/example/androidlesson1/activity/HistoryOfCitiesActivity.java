package com.example.androidlesson1.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.androidlesson1.Constants;
import com.example.androidlesson1.R;
import com.example.androidlesson1.ThermometerView;
import com.example.androidlesson1.singletons.SingletonForHistoryOfCities;
import com.example.androidlesson1.workingWithFragments.FragmentDialogBuilder;
import com.example.androidlesson1.workingWithRecyclerView.historyOfCities.SocnetAdapter;
import com.google.android.material.textfield.TextInputEditText;

public class HistoryOfCitiesActivity extends AppCompatActivity implements Constants, View.OnCreateContextMenuListener {

    private SocnetAdapter adapter;
    private FragmentDialogBuilder fragmentDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_of_cities);

//        fragmentDialogBuilder = new FragmentDialogBuilder();
//        Button clearListButton = findViewById(R.id.clear_list_button);
//        clearListButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fragmentDialogBuilder.show(getSupportFragmentManager(), "fragmentDialogBuilder");
//            }
//        });
//
//        initRecyclerView();
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

    public void onDialogResult(String result) { //сюда приходит результат из диалогового окна
        if (result.equals(getString(R.string.yes))) { // если "да", то удалить историю городов
            SingletonForHistoryOfCities.getInstance().clearHistory();
            initRecyclerView();
        } else {
            //...
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_in_setting_activity, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            adapter.getFilter().filter(newText);
            return false;
        }
    });
        return true;
    }


    public void setTemperature(View view) {
        ThermometerView thermometerView = findViewById(R.id.thermometerView);
        TextInputEditText inputTemperatureET = findViewById(R.id.input_temperature);

        thermometerView.changeTemperature(Integer.parseInt(inputTemperatureET.getText().toString()));
    }
}