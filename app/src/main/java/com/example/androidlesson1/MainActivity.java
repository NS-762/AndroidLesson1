package com.example.androidlesson1;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.androidlesson1.WorkingWithFragments.FragmentBottom;
import com.example.androidlesson1.WorkingWithFragments.FragmentTop;
import com.example.androidlesson1.WorkingWithFragments.Publisher;
import com.example.androidlesson1.WorkingWithFragments.PublisherGetter;

public class MainActivity extends BaseActivity implements Constants, PublisherGetter {

    private final int REQUEST_SETTINGS_ACTIVITY = 1;
    private FragmentTop fragmentTop;
    private FragmentBottom fragmentBottom;
    private Publisher publisher = new Publisher();
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(), "onCreate", Toast.LENGTH_SHORT).show();

        if (savedInstanceState != null) {
            fragmentTop = (FragmentTop) getSupportFragmentManager()
                    .getFragment(savedInstanceState, "FragmentTop");
            fragmentBottom = (FragmentBottom) getSupportFragmentManager()
                    .getFragment(savedInstanceState, "FragmentBottom");
        } else {
            fragmentTop = FragmentTop.create();
            fragmentBottom = FragmentBottom.create();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.add(R.id.container_top, fragmentTop);
            fragmentTransaction.add(R.id.container_bottom, fragmentBottom);
            fragmentTransaction.commit();
        }
        publisher.addSubscriber(fragmentTop);



    }

    @Override
    public Publisher getPublisher() {
        return publisher;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //сюда приходит результат из настроек

        if (requestCode == REQUEST_SETTINGS_ACTIVITY && resultCode == RESULT_OK) { //проверка, что окно отработало нормально

            String newCity = data.getStringExtra(CITY);
            if (newCity.replaceAll("\\s+", "").equals("")) { //если в настройках не был выбран город или введна пустота
                newCity = getString(R.string.city_1); //по умолчанию ставится Москва
            }
            fragmentTop.updateSettings(newCity);
            recreate();
        }

        super.onActivityResult(requestCode, resultCode, data);
        return;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //обработка нажатий на пункты меню
        int id = item.getItemId();
        if (id == R.id.menu_settings) {
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class); //создание интента для окна настроек
            startActivityForResult(intent, REQUEST_SETTINGS_ACTIVITY); //отправить интент и указать константу окна-отрпавителя
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "FragmentTop", fragmentTop);
        getSupportFragmentManager().putFragment(outState, "FragmentBottom", fragmentBottom);
    }
}

