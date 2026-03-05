package com.example.vertixa;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class countDownTimer extends AppCompatActivity {

    EditText seconds, minutes, hours;
    long totalMillis = 0;
    long timeLeftMillis = 0;
    CountDownTimer timer;
    ProgressBar progressBar;
    boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_count_down_timer);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);

        seconds = findViewById(R.id.seconds);
        minutes = findViewById(R.id.minutes);
        hours = findViewById(R.id.hours);

        // START BUTTON
        findViewById(R.id.btnStart).setOnClickListener(view -> {

            if (isRunning) return;

            // If first time starting
            if (timeLeftMillis == 0) {

                int hh = getSafeValue(hours);
                int mm = getSafeValue(minutes);
                int ss = getSafeValue(seconds);

                // ✅ Validate at least one field
                if (hh == 0 && mm == 0 && ss == 0) {
                    return; // do nothing if all empty
                }

                totalMillis = (hh * 3600 + mm * 60 + ss) * 1000;
                timeLeftMillis = totalMillis;
            }

            startTimer();
        });

        // STOP BUTTON (Pause)
        findViewById(R.id.btnStop).setOnClickListener(view -> {
            if (timer != null) {
                timer.cancel();
                isRunning = false;
            }
        });

        // RESET BUTTON
        findViewById(R.id.btnReset).setOnClickListener(view -> {

            if (timer != null) {
                timer.cancel();
                timer = null;
            }

            isRunning = false;

            totalMillis = 0;
            timeLeftMillis = 0;

            progressBar.setProgress(0);

            // Clear fields completely (important)
            hours.setText("");
            minutes.setText("");
            seconds.setText("");

            // Enable editing again
            hours.setEnabled(true);
            minutes.setEnabled(true);
            seconds.setEnabled(true);
        });
    }

    private int getSafeValue(EditText editText) {
        String value = editText.getText().toString();
        return value.isEmpty() ? 0 : Integer.parseInt(value);
    }

    private void startTimer() {

        timer = new CountDownTimer(timeLeftMillis, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                timeLeftMillis = millisUntilFinished;
                isRunning = true;

                long totalSeconds = timeLeftMillis / 1000;

                long h = totalSeconds / 3600;
                long m = (totalSeconds % 3600) / 60;
                long s = totalSeconds % 60;

                int progress = (int) ((totalMillis - timeLeftMillis) * 100 / totalMillis);
                progressBar.setProgress(progress);

                hours.setText(String.format("%02d", h));
                minutes.setText(String.format("%02d", m));
                seconds.setText(String.format("%02d", s));
            }

            @Override
            public void onFinish() {

                isRunning = false;
                timeLeftMillis = 0;

                progressBar.setProgress(100);

                hours.setText("00");
                minutes.setText("00");
                seconds.setText("00");
            }
        };

        timer.start();
    }
}