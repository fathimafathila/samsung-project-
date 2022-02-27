package com.example.covidwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddRecordActivity extends AppCompatActivity {
    EditText edtFName,edtLName, edtAge, edtDob, edtGender, edtPhoneNumber, edtPriority, edtMinor , edtSpecimenDate , edtTestReportDate;
    boolean isAllFieldsChecked = false;

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
            edtFName.setError("account Name should not be empty");
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

        if (edtPhoneNumber.length() == 0) {
            edtPhoneNumber.setError("Phone NUmber should not be empty");
            return false;
        }
        if (edtPriority.length() == 6) {
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