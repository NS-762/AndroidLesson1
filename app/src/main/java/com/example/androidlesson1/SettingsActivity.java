package com.example.androidlesson1;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class SettingsActivity extends AppCompatActivity implements Constants {

    private TextInputEditText selectCity;
    private TextView city_N;
    private MaterialButton saveSettings;

    Pattern patternCheckCity = Pattern.compile("^[A-Z][a-z]{2,}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        selectCity = findViewById(R.id.select_city);
        selectCity.setOnFocusChangeListener(new View.OnFocusChangeListener() { //метод вызывает при смене фокуса
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) return;
                TextView tv = (TextView) v;
                String valueCity = tv.getText().toString();
                if(patternCheckCity.matcher(valueCity).matches()) { //проверка, соответствует ли введенный город заданному паттерну
                    tv.setError(null); //если соответствует, то ошибка убирается
                } else {
                    tv.setError("Error!");
                    saveSettings.setBackgroundColor(123);
                }
            }
        });

        saveSettings = findViewById(R.id.save_settings);
        saveSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentResult = new Intent(); //создание интента
                intentResult.putExtra(CITY, selectCity.getText().toString()); //запись в интент названия города с пометкой CITY_NAME
                setResult(RESULT_OK, intentResult); //установка результата RESULT_OK и отправка интента
//                Snackbar.make(v, "Сохранение настроек", Snackbar.LENGTH_SHORT)
//                        .setAction("Action", null).show();
                finish(); //закрытие окна
            }
        });
    }

    public void onClickCity_1(View view) {
        city_N = findViewById(R.id.city_1);
        selectCity.setText(city_N.getText());
    }

    public void onClickCity_2(View view) {
        city_N = findViewById(R.id.city_2);
        selectCity.setText(city_N.getText());
    }

    public void onClickCity_3(View view) {
        city_N = findViewById(R.id.city_3);
        selectCity.setText(city_N.getText());
    }
}