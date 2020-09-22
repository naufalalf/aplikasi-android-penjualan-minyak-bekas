package com.gigih.wasteoil;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.style.TtsSpan;
import android.widget.ProgressBar;

import java.util.Timer;

import timber.log.Timber;

public class SplashscreenActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        new Thread(new Runnable() {
            @Override
            public void run() {
                progressBar = findViewById(R.id.pgbr);
                for (int progress=0; progress<50; progress+=10){
                    try {
                        Thread.sleep(500);
                        progressBar.setProgress(progress);
                    }catch (Exception e){
                        e.printStackTrace();
                        Timber.e(e.getMessage());
                    }
                }

                Intent intent = new Intent(SplashscreenActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }).start();

    }
}
