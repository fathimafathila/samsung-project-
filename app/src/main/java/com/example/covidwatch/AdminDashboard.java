package com.example.covidwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;


public class AdminDashboard extends AppCompatActivity {
    private MaterialCardView tv;
    TextView edttitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        tv = (MaterialCardView) this.findViewById(R.id.newRecord);
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);

        String s1 = sh.getString("name", "");

// We can then use the data
        edttitle = findViewById(R.id.title);

        edttitle.setText(s1);




        // Method for admin dashboard
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDashboardAct();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.layouts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.personalInfo:
                Intent intent1 = new Intent(this, PersonalInfoActivity.class);
                startActivity(intent1);

                return true;
            case R.id.logOut:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void openDashboardAct(){
        Intent intent = new Intent(this, AddRecordActivity.class);
        startActivity(intent);
    }

    public void btn_viewRecord(View v){
        Intent intent = new Intent(this, ViewRecordActivity.class);
        startActivity(intent);
    }
    public void btn_resolvedCase(View v){
        Intent intent = new Intent(this, ResourceRequestCaseActivity.class);
        startActivity(intent);
    }
    public void btn_escalatedCase(View v){
        Intent intent = new Intent(this, EscalatedCaseActivity.class);
        startActivity(intent);
    }


}