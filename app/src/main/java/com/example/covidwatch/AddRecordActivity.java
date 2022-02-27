package com.example.covidwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class AddRecordActivity extends AppCompatActivity {
    EditText edtFName,edtLName, edtAge, edtDob, edtGender, edtPhoneNumber, edtPriority, edtMinor , edtSpecimenDate , edtTestReportDate;
    boolean isAllFieldsChecked = false;
    private DatePicker datePicker;
    private Calendar calendar;
    private EditText dateView;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);


        //defining reference objects for the UI controls
        edtFName = findViewById(R.id.FirstName);
        edtLName = findViewById(R.id.LastName);
        edtDob = findViewById(R.id.edtDob);
        edtAge = findViewById(R.id.edtAge);
        edtGender = findViewById(R.id.edtGender);
        edtPhoneNumber = findViewById(R.id.edtPhonenumber);
        edtPriority = findViewById(R.id.edtPriority);
        edtMinor = findViewById(R.id.edtMinor);
        edtSpecimenDate = findViewById(R.id.edtspecimendate);
        edtTestReportDate = findViewById(R.id.edttestreportdate);
        initUI();

        dateView = (EditText) findViewById(R.id.edtDob);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);



    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    private void initUI()
    {
        //UI reference of textView
        final AutoCompleteTextView customerAutoTV = findViewById(R.id.edtGender);
        // create list of customer
        ArrayList<String> customerList = getGendersList();
        //Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddRecordActivity.this, android.R.layout.simple_spinner_item, customerList);
        //Set adapter
        customerAutoTV.setAdapter(adapter);
    }

    private ArrayList<String> getGendersList()
    {
        ArrayList<String> genders = new ArrayList<>();
        genders.add("male");
        genders.add("female");
        genders.add("both");
        genders.add("prefer not to say");
        return genders;
    }

    // On pressing Next Button
    public void btn_next (View view){
        isAllFieldsChecked = validateInputFields();

        if (isAllFieldsChecked) {
            Intent intent = new Intent(this, AddRecord2Activity.class);

            startActivity(intent);
        }
    }
    // Valodation check
    private boolean validateInputFields() {
        if (edtFName.length() == 0) {
            edtFName.setError("First Name should not be empty");
            return false;
        }

        if (edtLName.length() == 0) {
            edtLName.setError("Last Name should not be empty");
            return false;
        }

        if (edtDob.length() == 0) {
            edtDob.setError("DOB should not be empty");
            return false;
        }
        if (edtAge.length() != 6) {
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
        if (edtPriority.length() != 6) {
            edtPriority.setError("Priority should not be empty");
            return false;
        }
        if (edtMinor.length() == 0) {
            edtMinor.setError("Minor should not be empty");
            return false;
        }

        if (edtSpecimenDate.length() == 0) {
            edtSpecimenDate.setError("Specimen Date should not be empty");
            return false;
        }
        if (edtTestReportDate.length() != 6) {
            edtTestReportDate.setError("Test Report Date should not be empty");
            return false;
        }

        // after all validation return true.
        return true;
    }
}