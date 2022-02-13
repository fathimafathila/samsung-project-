package com.example.covidwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;


public class AdminDashboard extends AppCompatActivity {
    private MaterialCardView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        tv = (MaterialCardView) this.findViewById(R.id.newRecord);

        // Method for admin dashboard
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDashboardAct();
                }
            });
        }

        public void openDashboardAct(){
            Intent intent = new Intent(this, AddRecordActivity.class);
            startActivity(intent);
        }
}