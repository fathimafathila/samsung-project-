package com.example.covidwatch.UsersView.InitialInterview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.covidwatch.AdminView.AdminDashboard;
import com.example.covidwatch.AdminView.PersonalInfoActivity;
import com.example.covidwatch.R;
import com.example.covidwatch.UsersView.UserDashboardActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;
import java.util.Map;

public class informationActivity extends AppCompatActivity {
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_information );
        saveButton = findViewById(R.id.Savebutton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }


        });
    }




    public void back_btn (View view){
        //isAllFieldsChecked = validateInputFields();

        //if (isAllFieldsChecked) {
        Intent intent = new Intent(this, UserDashboardActivity.class);

        startActivity(intent);
    }
}