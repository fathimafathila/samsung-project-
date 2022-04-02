package com.example.covidwatch.UsersView.InitialInterview.Location;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.covidwatch.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class AddIsolationLocationactivity extends AppCompatActivity {;
    FirebaseAuth fAuth;
    FirebaseFirestore db;
    Button addLocation;
    EditText locationName, locationType, apartmentNumber , street,city,province,country,zipCode,fromDate, toDate;
    EditText adults, kids , safeIsolation, addInfo;
    CheckBox check1, check2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_isolation_locationactivity );

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        addLocation = findViewById(R.id.btnAddLocation);
        locationName = findViewById(R.id.edtLocationName);
        locationType = findViewById(R.id.edtLocationType);
        apartmentNumber = findViewById(R.id.edtApartment);
        street = findViewById(R.id.edtStreet);
        city = findViewById(R.id.edtCity);
        province = findViewById(R.id.edtProvince);
        country = findViewById(R.id.edtCountry);
        zipCode =findViewById(R.id.edtZipcode);
        fromDate =findViewById(R.id.edtFromDate);
        toDate = findViewById(R.id.edtToDate);
        adults = findViewById(R.id.edtHouseHoldAdults);
        kids = findViewById(R.id.edtHouseHoldKids);
        safeIsolation = findViewById(R.id.edtIsolateCheck);
        addInfo = findViewById(R.id.edtAdditionalInfo);
        check1 = findViewById(R.id.checkbox1);
        check2 = findViewById(R.id.checkbox2);
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String id = sh.getString("uuid","");
        if(!getIntent().getStringExtra("uuid").equals("")) {


            addLocation.setText("Update Location");
            db.collection("users").document(id).collection("Location").document(getIntent().getStringExtra("uuid"))
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                            locationName.setText(value.getString("Location Name"));
                            locationType.setText(value.getString("Location Type"));
                            apartmentNumber.setText(value.getString("Apartment Number"));
                            street.setText(value.getString("Street"));
                            city.setText(value.getString("City"));
                            province.setText(value.getString("Province"));
                            country.setText(value.getString("Country"));
                            zipCode.setText(value.getString("ZipCode"));
                            fromDate.setText(value.getString("From Date"));
                            toDate.setText(value.getString("To Date"));
                            adults.setText(value.getString("Adults"));
                            kids.setText(value.getString("Kids"));
                            safeIsolation.setText(value.getString("Safe Location"));
                            addInfo.setText(value.getString("Additional Info"));
                        }
                    });
        }
        addLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,Object> location = new HashMap<>();
                location.put("Location Name", locationName.getText().toString());
                location.put("Location Type", locationType.getText().toString());
                location.put("Apartment Number", apartmentNumber.getText().toString());
                location.put("Street", street.getText().toString());
                location.put("City", city.getText().toString());
                location.put("Province", province.getText().toString());
                location.put("Country", country.getText().toString());
                location.put("ZipCode", zipCode.getText().toString());
                location.put("From Date", fromDate.getText().toString());
                location.put("To Date", toDate.getText().toString());
                location.put("Adults", adults.getText().toString());
                location.put("Kids", kids.getText().toString());
                location.put("Safe Location", safeIsolation.getText().toString());
                location.put("Additional Info", addInfo.getText().toString());
                location.put("Check 1", check1.getText().toString());
                location.put("Check 2", check2.getText().toString());

                if(addLocation.getText().equals("Add Location")) {
                    db.collection("users").document(id).collection("Location").document().set(location)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(), "Working", Toast.LENGTH_SHORT).show();

                                }
                            });
                }else{
                    db.collection("users").document(id).collection("Location").document(getIntent().getStringExtra("uuid")).update(location)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(), "Working", Toast.LENGTH_SHORT).show();

                                }
                            });
                }
            }
        });

    }
}