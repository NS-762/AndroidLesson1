package com.example.androidlesson1;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.androidlesson1.WorkingWithFragments.FragmentBottom;
import com.example.androidlesson1.WorkingWithFragments.FragmentTop;
import com.example.androidlesson1.WorkingWithFragments.Publisher;
import com.example.androidlesson1.WorkingWithFragments.PublisherGetter;

public class MainActivity extends AppCompatActivity implements Constants, PublisherGetter {

    private final int REQUEST_SETTINGS_ACTIVITY = 1;
    private FragmentTop fragmentTop;
    private FragmentBottom fragmentBottom;
    private Publisher publisher = new Publisher();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentTop = FragmentTop.create();
        fragmentBottom = FragmentBottom.create();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.add(R.id.container_bottom, fragmentBottom);
        fragmentTransaction.add(R.id.container_top, fragmentTop);
        fragmentTransaction.commit();
        publisher.addSubscriber(fragmentTop);
    }

    @Override
    public Publisher getPublisher() {
        return publisher;
    }

    public void onClickImageSettings(View view) {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class); //создание интента для окна настроек
        startActivityForResult(intent, REQUEST_SETTINGS_ACTIVITY); //отправить интент и указать константу окна-отрпавителя
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //сюда приходит результат из настроек

        if (requestCode == REQUEST_SETTINGS_ACTIVITY && resultCode == RESULT_OK) { //проверка, что окно отработало нормально

            String newCity = data.getStringExtra(CITY);
            if (newCity.replaceAll("\\s+","").equals("")) { //если в настройках не был выбран город или введна пустота
                newCity = getString(R.string.city_1); //по умолчанию ставится Москва
            }
            fragmentTop.updateSettings(newCity);
        }

        super.onActivityResult(requestCode, resultCode, data);
        return;
    }

  /*
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) { //загрузка состояния окна
        super.onRestoreInstanceState(savedInstanceState);
        Toast.makeText(getApplicationContext(), "onRestoreInstanceState", Toast.LENGTH_SHORT).show();

        text_for_date_0 = savedInstanceState.getString("TEXT_FOR_date_0");
        text_for_temperature_0 = savedInstanceState.getString("TEXT_FOR_temperature_0");

        date_0.setText(text_for_date_0);
        temperature_0.setText(text_for_temperature_0);

        Drawable drawable = SingletonForImage.getInstance().getweather_picture_0().getDrawable(); //достать изображение из синглтона
        weather_picture_0.setImageDrawable(drawable);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) { //сохранение состояния окна
        super.onSaveInstanceState(outState);
        Toast.makeText(getApplicationContext(), "onSaveInstanceState", Toast.LENGTH_SHORT).show();

        outState.putString("TEXT_FOR_date_0", text_for_date_0); //для сохранения даты
        outState.putString("TEXT_FOR_temperature_0", text_for_temperature_0); //для сохранения температуры

        SingletonForImage.getInstance().setweather_picture_0(weather_picture_0); //сохранить изображение из шапки в синглтон

    }*/
}