package com.example.androidlesson1;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView date_0, temperature_0;
    private ImageView weatherPicture_0;

    private TextView date_N, dayOfWeek_N, temperature_N;
    private ImageView weatherPicture_N;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date_0 = findViewById(R.id.Date_0);
        temperature_0 = findViewById(R.id.Temperature_0);
        weatherPicture_0 = findViewById(R.id.WeatherPicture_0);
    }


    public void onClickLinerLayout_1(View view) {
        date_N = findViewById(R.id.Date_1);
        dayOfWeek_N = findViewById(R.id.DayOfWeek_1);
        temperature_N = findViewById(R.id.Temperature_1);
        weatherPicture_N = findViewById(R.id.WeatherPicture_1);

        date_0.setText(date_N.getText() + " года, " +  dayOfWeek_N.getText());
        temperature_0.setText(temperature_N.getText());

        Drawable drawable = weatherPicture_N.getDrawable();
        weatherPicture_0.setImageDrawable(drawable);
    }

    public void onClickLinerLayout_2(View view) {
        date_N = findViewById(R.id.Date_2);
        dayOfWeek_N = findViewById(R.id.DayOfWeek_2);
        temperature_N = findViewById(R.id.Temperature_2);
        weatherPicture_N = findViewById(R.id.WeatherPicture_2);

        date_0.setText(date_N.getText() + " года, " +  dayOfWeek_N.getText());
        temperature_0.setText(temperature_N.getText());

        Drawable drawable = weatherPicture_N.getDrawable();
        weatherPicture_0.setImageDrawable(drawable);
    }

    public void onClickLinerLayout_3(View view) {
        date_N = findViewById(R.id.Date_3);
        dayOfWeek_N = findViewById(R.id.DayOfWeek_3);
        temperature_N = findViewById(R.id.Temperature_3);
        weatherPicture_N = findViewById(R.id.WeatherPicture_3);

        date_0.setText(date_N.getText() + " года, " +  dayOfWeek_N.getText());
        temperature_0.setText(temperature_N.getText());

        Drawable drawable = weatherPicture_N.getDrawable();
        weatherPicture_0.setImageDrawable(drawable);
    }

    public void onClickLinerLayout_4(View view) {
        date_N = findViewById(R.id.Date_4);
        dayOfWeek_N = findViewById(R.id.DayOfWeek_4);
        temperature_N = findViewById(R.id.Temperature_4);
        weatherPicture_N = findViewById(R.id.WeatherPicture_4);

        date_0.setText(date_N.getText() + " года, " +  dayOfWeek_N.getText());
        temperature_0.setText(temperature_N.getText());

        Drawable drawable = weatherPicture_N.getDrawable();
        weatherPicture_0.setImageDrawable(drawable);
    }

    public void onClickLinerLayout_5(View view) {
        date_N = findViewById(R.id.Date_5);
        dayOfWeek_N = findViewById(R.id.DayOfWeek_5);
        temperature_N = findViewById(R.id.Temperature_5);
        weatherPicture_N = findViewById(R.id.WeatherPicture_5);

        date_0.setText(date_N.getText() + " года, " +  dayOfWeek_N.getText());
        temperature_0.setText(temperature_N.getText());

        Drawable drawable = weatherPicture_N.getDrawable();
        weatherPicture_0.setImageDrawable(drawable);
    }

    public void onClickLinerLayout_6(View view) {
        date_N = findViewById(R.id.Date_6);
        dayOfWeek_N = findViewById(R.id.DayOfWeek_6);
        temperature_N = findViewById(R.id.Temperature_6);
        weatherPicture_N = findViewById(R.id.WeatherPicture_6);

        date_0.setText(date_N.getText() + " года, " +  dayOfWeek_N.getText());
        temperature_0.setText(temperature_N.getText());

        Drawable drawable = weatherPicture_N.getDrawable();
        weatherPicture_0.setImageDrawable(drawable);
    }

    public void onClickLinerLayout_7(View view) {
        date_N = findViewById(R.id.Date_7);
        dayOfWeek_N = findViewById(R.id.DayOfWeek_7);
        temperature_N = findViewById(R.id.Temperature_7);
        weatherPicture_N = findViewById(R.id.WeatherPicture_7);

        date_0.setText(date_N.getText() + " года, " +  dayOfWeek_N.getText());
        temperature_0.setText(temperature_N.getText());

        Drawable drawable = weatherPicture_N.getDrawable();
        weatherPicture_0.setImageDrawable(drawable);
    }


}