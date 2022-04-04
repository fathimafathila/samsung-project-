package com.example.covidwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class SplashActivity extends AppCompatActivity {

    private Handler handler= new Handler() {
        @Override
        public void publish(LogRecord record) {

        }

        @Override
        public void flush() {

        }

        @Override
        public void close() throws SecurityException {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);





    }
}