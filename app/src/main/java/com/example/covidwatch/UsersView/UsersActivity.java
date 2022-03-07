package com.example.covidwatch.UsersView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.covidwatch.AdminView.SecurityQuestionActivity;
import com.example.covidwatch.MainActivity;
import com.example.covidwatch.R;

public class UsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        Button clickbtn = (Button) findViewById(R.id.click);
        clickbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UsersActivity.this, UserMenuActivity.class);
                startActivity(i);
            }
        });
    }
}