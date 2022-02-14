package com.example.covidwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecurityQuestionActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_question);

        // Method for admin dashboard
        button = (Button)findViewById(R.id.btnProceed);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSecurityPage();
            }
        });
    }

    public void openSecurityPage(){
        Intent intent = new Intent(this, AdminDashboard.class);
        startActivity(intent);
    }
}