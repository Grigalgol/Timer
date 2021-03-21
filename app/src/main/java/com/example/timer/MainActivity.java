package com.example.timer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int second;
    private boolean isRunning;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textViewTimer);
        if(savedInstanceState != null) {
            second = savedInstanceState.getInt("second");
            isRunning = savedInstanceState.getBoolean("isRunning");
        }

        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("second",second);
        outState.putBoolean("isRunning", isRunning);
    }

    public void OnClickStartTimer(View view) {
        isRunning = true;
    }

    public void OnClickPauseTimer(View view) {
        isRunning = false;
    }

    public void OnClickSkipTimer(View view) {
        isRunning = false;
        second = 0;
    }
    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = second/3600;
                int min = second%3600/60;
                int sec = second %60;

                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, min, sec);
                textView.setText(time);

                if(isRunning) second++;
                handler.postDelayed(this,1000);
            }
        });

    }
}