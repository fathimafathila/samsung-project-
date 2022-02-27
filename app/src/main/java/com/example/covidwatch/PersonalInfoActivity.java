package com.example.covidwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

public class PersonalInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        initUI();
    }
    private void initUI()
    {
        //UI reference of textView
        final AutoCompleteTextView customerAutoTV = findViewById(R.id.edtSQ);
        // create list of customer
        ArrayList<String> customerList = getSqList();
        //Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(PersonalInfoActivity.this, android.R.layout.simple_spinner_item, customerList);
        //Set adapter
        customerAutoTV.setAdapter(adapter);
    }

    private ArrayList<String> getSqList()
    {
        ArrayList<String> sq = new ArrayList<>();
        sq.add("What is your Favourite animal?");
        sq.add("What is your Favourite Sport?");
        sq.add("What is your Childhood Nick Name? ");
        sq.add("What is your Maiden Name?");
        return sq;
    }

    // On pressing Next Button
    public void back_btn (View view){
        //isAllFieldsChecked = validateInputFields();

        //if (isAllFieldsChecked) {
            Intent intent = new Intent(this, AdminDashboard.class);

            startActivity(intent);
        }

}