package com.example.covidwatch.AdminView;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.covidwatch.R;

import java.util.ArrayList;
import java.util.Calendar;

public class PersonalInfoActivity extends AppCompatActivity {
    private DatePicker datePicker;
    private Calendar calendar;
    private EditText dateView1;
    private EditText dateView2;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        initUI();

        dateView1 = (EditText) findViewById(R.id.edtDob);
        dateView2 = (EditText) findViewById(R.id.edtDoj);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate1(year, month+1, day);
        showDate2(year, month+1, day);
    }

    @SuppressWarnings("deprecation")
    public void setDob(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @SuppressWarnings("deprecation")
    public void setDoj(View view) {
        showDialog(998);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener1, year, month, day);
        }
        if (id == 998) {
            return new DatePickerDialog(this,
                    myDateListener2, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener1 = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate1(arg1, arg2+1, arg3);
                }
            };

    private DatePickerDialog.OnDateSetListener myDateListener2 = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate2(arg1, arg2+1, arg3);
                }
            };

    private void showDate1(int year, int month, int day) {
        dateView1.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    private void showDate2(int year, int month, int day) {
        dateView2.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
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