package com.example.covidwatch.AdminView.CreateUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.covidwatch.R;

import java.util.ArrayList;
import java.util.zip.ZipEntry;

public class CreateAddressActivity extends AppCompatActivity {

    // Initial variable
    EditText edtAddressType, edtApartment, edtStreet, edtCity, edtState, edtCountry, edtZipCode;
    // Extra Variables
    boolean isAllFieldsChecked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_address);

        getSupportActionBar().hide();
        // find view by id
        edtAddressType = findViewById(R.id.edtAddressType);
        edtApartment = findViewById(R.id.edtApartment);
        edtStreet = findViewById(R.id.edtStreet);
        edtCity = findViewById(R.id.edtCity);
        edtState = findViewById(R.id.edtState);
        edtCountry = findViewById(R.id.edtCountry);
        edtZipCode = findViewById(R.id.edtZipcode);


        // Method for Spinners
        ;
    }


    // On pressing Next Button
    public void btn_next (View view){
        isAllFieldsChecked = true;

        if (isAllFieldsChecked) {
            String addressType = edtAddressType.getText().toString();
            String apartment = edtApartment.getText().toString();
            String street = edtStreet.getText().toString();
            String city = edtCity.getText().toString();
            String state = edtState.getText().toString();
            String country = edtCountry.getText().toString();
            String zipCode = edtZipCode.getText().toString();

            // Shared preference to store data

            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("AddressType", addressType);
            myEdit.putString("Apartment", apartment);
            myEdit.putString("Street", street);
            myEdit.putString("City", city);
            myEdit.putString("State", state);
            myEdit.putString("Country", country);
            myEdit.putString("ZipCode", zipCode);
            myEdit.commit();


            Intent intent = new Intent(this, CreateMedicalInformationActivity.class);
            startActivity(intent);

        }
    }
    private boolean validateInputFields() {
        if (edtAddressType.length() == 0) {
            edtAddressType.setError("Address should not be empty");
            return false;
        }

        if (edtApartment.length() == 0) {
            edtApartment.setError("Apartment or Home Number should not be empty");
            return false;
        }

        if (edtStreet.length() == 0) {
            edtStreet.setError("Street name should not be empty");
            return false;
        }
        if (edtCity.length() == 0) {
            edtCity.setError("City name should not be empty");
            return false;
        }
        if (edtState.length() == 0) {
            edtState.setError("State name should not be empty");
            return false;
        }

        if (edtCountry.length() == 0) {
            edtCountry.setError("Country name should not be empty");
            return false;
        }
        if (edtZipCode.length() == 0) {
            edtZipCode.setError("Zip Code should not be empty");
            return false;
        }
        // after all validation return true.
        return true;
    }
}