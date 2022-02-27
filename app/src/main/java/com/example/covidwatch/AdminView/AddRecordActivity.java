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

public class AddRecordActivity extends AppCompatActivity {
    EditText edtFName,edtLName, edtAge, edtDob, edtGender, edtPhoneNumber, edtPriority, edtMinor , edtSpecimenDate , edtTestReportDate;
    boolean isAllFieldsChecked = false;
    private DatePicker datePicker;
    private Calendar calendar;
    private EditText dateView1;
    private EditText dateView2;
    private EditText dateView3;
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

        dateView1 = (EditText) findViewById(R.id.edtDob);
        dateView2 = (EditText) findViewById(R.id.edtspecimendate);
        dateView3 = (EditText) findViewById(R.id.edttestreportdate);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate1(year, month+1, day);
        showDate2(year, month+1, day);
        showDate3(year, month+1, day);

    }

    // Setting date of birth
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(997);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    // setting specimen date
    @SuppressWarnings("deprecation")
    public void setSpecimenDate(View view) {
        showDialog(998);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    // Setting test report date
    @SuppressWarnings("deprecation")
    public void setReportDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 997) {
            return new DatePickerDialog(this,
                    myDateListener1, year, month, day);
        }
        if (id == 998) {
            return new DatePickerDialog(this,
                    myDateListener2, year, month, day);
        }
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener3, year, month, day);
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

    private DatePickerDialog.OnDateSetListener myDateListener3 = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate3(arg1, arg2+1, arg3);
                }
            };

    // Showing date of birth
    private void showDate1(int year, int month, int day) {
        dateView1.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    // Showing specimen date
    private void showDate2(int year, int month, int day) {
        dateView2.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    // Showing test report date
    private void showDate3(int year, int month, int day) {
        dateView3.setText(new StringBuilder().append(day).append("/")
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
    // Validation check
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
        if (edtPriority.length() == 0) {
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
        if (edtTestReportDate.length() == 0) {
            edtTestReportDate.setError("Test Report Date should not be empty");
            return false;
        }

        // after all validation return true.
        return true;
    }
}