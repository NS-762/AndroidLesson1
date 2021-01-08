package com.example.androidlesson1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements Constants {

    private final int REQUEST_SETTINGS_ACTIVITY = 1;

    private TextView city;

    private TextView date_0;
    private TextView temperature_0;
    private ImageView weatherPicture_0;

    private LinearLayout linearLayout_1;

    private TextView date_N;
    private TextView dayOfWeek_N;
    private TextView temperature_N;
    private ImageView weatherPicture_N;

    private ImageView imageSettings;

    private String text_for_date_0;
    private String text_for_temperature_0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = findViewById(R.id.City_0);

        date_0 = findViewById(R.id.Date_0);
        temperature_0 = findViewById(R.id.Temperature_0);
        weatherPicture_0 = findViewById(R.id.WeatherPicture_0);
        linearLayout_1 = findViewById(R.id.LinearLayout_1);

        imageSettings = findViewById(R.id.ImageSettings);


        if (savedInstanceState == null) { //если запуск первый, то в шапку будут выведены данные из первого дня списка (типа это сегодняшний день)
            onClickLinerLayout_1(linearLayout_1);
        }

    }

    public void onClickImageSettings(View view) {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivityForResult(intent, REQUEST_SETTINGS_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == REQUEST_SETTINGS_ACTIVITY && resultCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "Сюда зашли", Toast.LENGTH_SHORT).show();
            city.setText(data.getStringExtra(CITY_NAME));
        }

        super.onActivityResult(requestCode, resultCode, data);
        return;
    }

    public void onClickLinerLayout_1(View view) {
        date_N = findViewById(R.id.Date_1);
        dayOfWeek_N = findViewById(R.id.DayOfWeek_1);
        temperature_N = findViewById(R.id.Temperature_1);
        weatherPicture_N = findViewById(R.id.WeatherPicture_1);

        text_for_date_0 = date_N.getText() + ", " + dayOfWeek_N.getText();
        date_0.setText(text_for_date_0);

        text_for_temperature_0 = temperature_N.getText() + "";
        temperature_0.setText(text_for_temperature_0);

        Drawable drawable = weatherPicture_N.getDrawable();
        weatherPicture_0.setImageDrawable(drawable);
    }

    public void onClickLinerLayout_2(View view) {
        date_N = findViewById(R.id.Date_2);
        dayOfWeek_N = findViewById(R.id.DayOfWeek_2);
        temperature_N = findViewById(R.id.Temperature_2);
        weatherPicture_N = findViewById(R.id.WeatherPicture_2);

        text_for_date_0 = date_N.getText() + ", " + dayOfWeek_N.getText();
        date_0.setText(text_for_date_0);

        text_for_temperature_0 = temperature_N.getText() + "";
        temperature_0.setText(text_for_temperature_0);

        Drawable drawable = weatherPicture_N.getDrawable();
        weatherPicture_0.setImageDrawable(drawable);
    }

    public void onClickLinerLayout_3(View view) {
        date_N = findViewById(R.id.Date_3);
        dayOfWeek_N = findViewById(R.id.DayOfWeek_3);
        temperature_N = findViewById(R.id.Temperature_3);
        weatherPicture_N = findViewById(R.id.WeatherPicture_3);

        text_for_date_0 = date_N.getText() + ", " + dayOfWeek_N.getText();
        date_0.setText(text_for_date_0);

        text_for_temperature_0 = temperature_N.getText() + "";
        temperature_0.setText(text_for_temperature_0);

        Drawable drawable = weatherPicture_N.getDrawable();
        weatherPicture_0.setImageDrawable(drawable);
    }

    public void onClickLinerLayout_4(View view) {
        date_N = findViewById(R.id.Date_4);
        dayOfWeek_N = findViewById(R.id.DayOfWeek_4);
        temperature_N = findViewById(R.id.Temperature_4);
        weatherPicture_N = findViewById(R.id.WeatherPicture_4);

        text_for_date_0 = date_N.getText() + ", " + dayOfWeek_N.getText();
        date_0.setText(text_for_date_0);

        text_for_temperature_0 = temperature_N.getText() + "";
        temperature_0.setText(text_for_temperature_0);

        Drawable drawable = weatherPicture_N.getDrawable();
        weatherPicture_0.setImageDrawable(drawable);
    }

    public void onClickLinerLayout_5(View view) {
        date_N = findViewById(R.id.Date_5);
        dayOfWeek_N = findViewById(R.id.DayOfWeek_5);
        temperature_N = findViewById(R.id.Temperature_5);
        weatherPicture_N = findViewById(R.id.WeatherPicture_5);

        text_for_date_0 = date_N.getText() + ", " + dayOfWeek_N.getText();
        date_0.setText(text_for_date_0);

        text_for_temperature_0 = temperature_N.getText() + "";
        temperature_0.setText(text_for_temperature_0);

        Drawable drawable = weatherPicture_N.getDrawable();
        weatherPicture_0.setImageDrawable(drawable);
    }

    public void onClickLinerLayout_6(View view) {
        date_N = findViewById(R.id.Date_6);
        dayOfWeek_N = findViewById(R.id.DayOfWeek_6);
        temperature_N = findViewById(R.id.Temperature_6);
        weatherPicture_N = findViewById(R.id.WeatherPicture_6);

        text_for_date_0 = date_N.getText() + ", " + dayOfWeek_N.getText();
        date_0.setText(text_for_date_0);

        text_for_temperature_0 = temperature_N.getText() + "";
        temperature_0.setText(text_for_temperature_0);

        Drawable drawable = weatherPicture_N.getDrawable();
        weatherPicture_0.setImageDrawable(drawable);
    }

    public void onClickLinerLayout_7(View view) {
        date_N = findViewById(R.id.Date_7);
        dayOfWeek_N = findViewById(R.id.DayOfWeek_7);
        temperature_N = findViewById(R.id.Temperature_7);
        weatherPicture_N = findViewById(R.id.WeatherPicture_7);

        text_for_date_0 = date_N.getText() + ", " + dayOfWeek_N.getText();
        date_0.setText(text_for_date_0);

        text_for_temperature_0 = temperature_N.getText() + "";
        temperature_0.setText(text_for_temperature_0);

        Drawable drawable = weatherPicture_N.getDrawable();
        weatherPicture_0.setImageDrawable(drawable);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Toast.makeText(getApplicationContext(), "onRestoreInstanceState", Toast.LENGTH_SHORT).show();

        text_for_date_0 = savedInstanceState.getString("TEXT_FOR_DATE_0");
        text_for_temperature_0 = savedInstanceState.getString("TEXT_FOR_TEMPERATURE_0");

        date_0.setText(text_for_date_0);
        temperature_0.setText(text_for_temperature_0);

        Drawable drawable = SingletonForImage.getInstance().getWeatherPicture_0().getDrawable(); //достать изображение из синглтона
        weatherPicture_0.setImageDrawable(drawable);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Toast.makeText(getApplicationContext(), "onSaveInstanceState", Toast.LENGTH_SHORT).show();

        outState.putString("TEXT_FOR_DATE_0", text_for_date_0); //для сохранения даты
        outState.putString("TEXT_FOR_TEMPERATURE_0", text_for_temperature_0); //для сохранения температуры
        SingletonForImage.getInstance().setWeatherPicture_0(weatherPicture_0); //сохранить изображение из шапки в синглтон

    }
}