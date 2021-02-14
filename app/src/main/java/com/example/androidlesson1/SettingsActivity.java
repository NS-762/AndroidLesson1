package com.example.androidlesson1;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity implements Constants {

    private EditText city_entry;
    private TextView city_N;
    private Button saveSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        city_entry = findViewById(R.id.CityEntry);

        saveSettings = findViewById(R.id.SaveSettings);
        saveSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentResult = new Intent(); //создание интента
                intentResult.putExtra(CITY, city_entry.getText().toString()); //запись в интент названия города с пометкой CITY_NAME
                setResult(RESULT_OK, intentResult); //установка результата RESULT_OK и отправка интента
                finish(); //закрытие окна
            }
        });
    }

    public void onClickCity_1(View view) {
        city_N = findViewById(R.id.City_1);
        city_entry.setText(city_N.getText());
    }

    public void onClickCity_2(View view) {
        city_N = findViewById(R.id.City_2);
        city_entry.setText(city_N.getText());
    }

    public void onClickCity_3(View view) {
        city_N = findViewById(R.id.City_3);
        city_entry.setText(city_N.getText());
    }
}