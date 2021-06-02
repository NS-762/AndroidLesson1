package com.example.androidlesson1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidlesson1.R;
import com.example.androidlesson1.ThermometerView;
import com.google.android.material.textfield.TextInputEditText;

public class ThermometerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermometer);

        ThermometerView thermometerView = findViewById(R.id.thermometerView);
        TextInputEditText inputTemperatureET = findViewById(R.id.input_temperature);
        Button btnSetTemperature = findViewById(R.id.set_temperature);


        btnSetTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thermometerView.changeTemperature(Integer.parseInt(inputTemperatureET.getText().toString()));
            }
        });

    }
}