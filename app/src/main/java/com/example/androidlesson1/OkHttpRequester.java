package com.example.androidlesson1;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.androidlesson1.activity.MainActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpRequester {
    private OnResponseCompleted listener;

    public OkHttpRequester(OnResponseCompleted listener) {
        this.listener = listener;
    }

    public void run(String url) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {

            final Handler handler = new Handler();

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e( "TAG", "ОШИБКА ПОДКЛЮЧЕНИЯ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String answer = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        listener.onCompleted(answer);
                    }
                });
            }
        });
    }

    public interface OnResponseCompleted {
        void onCompleted(String content);
    }
}
