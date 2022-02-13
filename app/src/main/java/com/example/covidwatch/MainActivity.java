package com.example.covidwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Method for admin dashboard
        button = (Button)findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDashboardAct();
            }
        });
    }

    public void openDashboardAct(){
        Intent intent = new Intent(this, AdminDashboard.class);
        startActivity(intent);
    }
}
