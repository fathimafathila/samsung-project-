package com.example.covidwatch.AdminView.CreateUser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.covidwatch.DateCalculation;
import com.example.covidwatch.R;

import java.util.ArrayList;
import java.util.Calendar;

public class CreatePersonalInfomationActivity extends AppCompatActivity {

    // Initial variable
       EditText edtFirstName,edtLastName, edtEmail, edtAge, edtDob, edtGender, edtPhoneNumber, edtMinor ;
       EditText dateView1 ;
       int year, month, day;
       String password;
    // Initial Class Object
       private Calendar calendar;
    // Extra Variables
       boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_personal_infomation);

        getSupportActionBar().hide();
        ///FindView
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtEmail = findViewById(R.id.edtEmail);
        edtDob = findViewById(R.id.edtDob);
        edtAge = findViewById(R.id.edtAge);
        edtGender = findViewById(R.id.edtGender);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtMinor = findViewById(R.id.edtMinor);
        dateView1 = (EditText) findViewById(R.id.edtDob);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        initUI();

    }

    // Setting date of birth
    @SuppressWarnings("deprecation")
    public void setDate(View view) { showDialog(997); }
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 997) {
            return new DatePickerDialog(this,
                    myDateListener1, year, month, day);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener myDateListener1 = new DatePickerDialog.OnDateSetListener() {
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
    private void showDate1(int year, int month, int day) {
        dateView1.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
        DateCalculation dateCalculation = new DateCalculation();
        int number = month*10000 + day*100 + year%100 ;
        if(month < 10 ){
            password = "0" + String.valueOf(number);
        }else{
            password = String.valueOf(number);
        }

        edtAge.setText(dateCalculation.findAge(year,month,day));
        edtMinor.setText(dateCalculation.findMinor(Integer.parseInt(dateCalculation.findAge(year,month,day))));

    }

    private void initUI()
    {
        //UI reference of textView
        final AutoCompleteTextView customerAutoTV1 = findViewById(R.id.edtGender);
        final AutoCompleteTextView customerAutoTV3 = findViewById(R.id.edtMinor);
        // create list of customer
            ArrayList<String> genders = new ArrayList<>();
            genders.add("Male");
            genders.add("Female");
            genders.add("Both");
            genders.add("Prefer not to say");
            ArrayList<String> minor = new ArrayList<>();
            minor.add("Yes");
            minor.add("No");
        //Create adapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(CreatePersonalInfomationActivity.this, android.R.layout.simple_spinner_item, genders);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(CreatePersonalInfomationActivity.this, android.R.layout.simple_spinner_item, minor);
        //Set adapter
        customerAutoTV1.setAdapter(adapter1);
        customerAutoTV3.setAdapter(adapter3);
    }


    // On pressing Next Button
    public void btn_next (View view){
        isAllFieldsChecked = true;

        if (isAllFieldsChecked) {
            String firstName = edtFirstName.getText().toString();
            String lastName = edtLastName.getText().toString();
            String email = edtEmail.getText().toString();
            String dob = edtDob.getText().toString();
            String age = edtAge.getText().toString();
            String gender = edtGender.getText().toString();
            String phone = edtPhoneNumber.getText().toString();
            String minor = edtMinor.getText().toString();

            // Shared preference to store data

            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("FirstName", firstName);
            myEdit.putString("LastName", lastName);
            myEdit.putString("Email", email);
            myEdit.putString("DOB", dob);
            myEdit.putString("Gender", gender);
            myEdit.putString("Age", age);
            myEdit.putString("PhoneNumber", phone);
            myEdit.putString("Minor", minor);
            myEdit.putString("Password", password);

            myEdit.commit();


            Intent intent = new Intent(this, CreateAddressActivity.class);
            startActivity(intent);

        }
    }
    private boolean validateInputFields() {
        if (edtFirstName.length() == 0) {
            edtFirstName.setError("First Name should not be empty");
            return false;
        }

        if (edtLastName.length() == 0) {
            edtLastName.setError("Last Name should not be empty");
            return false;
        }

        if (edtDob.length() == 0) {
            edtDob.setError("DOB should not be empty");
            return false;
        }
        if (edtAge.length() == 0) {
            edtAge.setError("Age should not be empty");
            return false;
        }
        if (edtGender.length() == 0) {
            edtGender.setError(" Gender should not be empty");
            return false;
        }

        if (edtPhoneNumber.length() == 0) {
            edtPhoneNumber.setError("Phone NUmber should not be empty");
            return false;
        }
        if (edtMinor.length() == 0) {
            edtMinor.setError("Minor should not be empty");
            return false;
        }
        // after all validation return true.
        return true;
    }
}