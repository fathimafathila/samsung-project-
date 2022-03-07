package com.example.covidwatch.UsersView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.covidwatch.AdminView.ViewRecordActivity;
import com.example.covidwatch.R;

public class UserDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
    }

    public void btn_initialInterview(View v){
        Intent intent = new Intent(this, ViewRecordActivity.class);
        startActivity(intent);
    }

    public void btn_assessment(View v){
        Intent intent = new Intent(this, DailyHealthActivity.class);
        startActivity(intent);
    }
}