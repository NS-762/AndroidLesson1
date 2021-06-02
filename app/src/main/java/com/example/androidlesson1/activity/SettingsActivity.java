package com.example.androidlesson1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.androidlesson1.Constants;
import com.example.androidlesson1.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class SettingsActivity extends BaseActivity implements Constants {

    private TextInputEditText selectCityEditText;
    private TextView cityN;
    private MaterialButton saveSettings;
    private MaterialButton GPSButton;
    private Switch switchDarkTheme;

    Pattern patternCheckCity = Pattern.compile("^[A-Z][a-z]{2,}$");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        init();
    }

    public void onClickCity_1(View view) {
        cityN = findViewById(R.id.city_1);
        selectCityEditText.setText(cityN.getText());
    }

    public void onClickCity_2(View view) {
        cityN = findViewById(R.id.city_2);
        selectCityEditText.setText(cityN.getText());
    }

    public void onClickCity_3(View view) {
        cityN = findViewById(R.id.city_3);
        selectCityEditText.setText(cityN.getText());
    }

    public void init() {
        saveSettings = findViewById(R.id.save_settings);
        saveSettings.setOnClickListener(saveSettingsClickListener);

        switchDarkTheme = findViewById(R.id.switch_light_theme);
        switchDarkTheme.setChecked(isLightTheme());
        switchDarkTheme.setOnCheckedChangeListener(switchDarkThemeCheckedListener);

        GPSButton = findViewById(R.id.GPS);
        GPSButton.setOnClickListener(GPSButtonClickListener);

        selectCityEditText = findViewById(R.id.input_temperture);
        selectCityEditText.setOnFocusChangeListener(selectCityEditTextFocusChangeListener);
    }

    View.OnClickListener saveSettingsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intentResult = new Intent(); //создание интента
            String selectCity = selectCityEditText.getText().toString();
            intentResult.putExtra(CITY, selectCity); //запись в интент названия города с пометкой cityNAME
            setResult(RESULT_OK, intentResult); //установка результата RESULT_OK и отправка интента
            finish();
        }
    };

    CompoundButton.OnCheckedChangeListener switchDarkThemeCheckedListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Snackbar.make(, "Вы установили светлую тему", Snackbar.LENGTH_SHORT).show();
            setLightTheme(isChecked);
            recreate();
        }
    };

    View.OnClickListener GPSButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Snackbar.make(v, "Определение текущего местоположения...", Snackbar.LENGTH_LONG).show();
        }
    };

    View.OnFocusChangeListener selectCityEditTextFocusChangeListener = new View.OnFocusChangeListener() {
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
    };
}