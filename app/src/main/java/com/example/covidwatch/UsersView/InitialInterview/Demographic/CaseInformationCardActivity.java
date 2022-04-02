package com.example.covidwatch.UsersView.InitialInterview.Demographic;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.covidwatch.AdminView.PersonalInfoActivity;
import com.example.covidwatch.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CaseInformationCardActivity extends AppCompatActivity {

    private Calendar calendar;
    private Button btnUpdate;
    private int year, month, day;

    FirebaseAuth fAuth;
    FirebaseFirestore db;
    EditText edtFirstName, edtLastName, edtGender ,edtDob, edtNumber, edtStreet1, edtStreet2, edtCity , edtEmail, edtType ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_information_card);

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        edtDob = findViewById(R.id.edtDob);
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtNumber = findViewById(R.id.edtCN);
        edtGender = findViewById(R.id.edtGender);
        edtStreet1 = findViewById(R.id.edtAddress1);
        edtStreet2 = findViewById(R.id.edtAddress2);
        edtCity = findViewById(R.id.edtCity);
        edtEmail = findViewById(R.id.edtemailId);
        edtType = findViewById(R.id.edtAddressType);


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        initUI();

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String uuid = sh.getString("uuid","");

        DocumentReference documentReference =  db.collection("users").document(uuid);
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        edtFirstName.setText(documentSnapshot.getString("First Name"));
                        edtLastName.setText(documentSnapshot.getString("Last Name"));
                        edtDob.setText(documentSnapshot.getString("DOB"));
                        edtNumber.setText(documentSnapshot.getString("Contact Number"));
                        edtStreet1.setText(documentSnapshot.getString("Apartment"));
                        edtStreet2.setText(documentSnapshot.getString("Street"));
                        edtCity.setText(documentSnapshot.getString("City"));
                        edtEmail.setText(documentSnapshot.getString("Email"));
                        edtGender.setText(documentSnapshot.getString("Gender"));
                        edtType.setText(documentSnapshot.getString("Address Type"));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CaseInformationCardActivity.this,"Not Working",Toast.LENGTH_SHORT).show();
                    }
                });

        btnUpdate = findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> user = new HashMap<>();
                user.put("First Name",edtFirstName.getText().toString());
                user.put("Last Name",edtLastName.getText().toString());
                user.put("DOB",edtDob.getText().toString());
                user.put("Contact Number",edtNumber.getText().toString());
                user.put("Apartment",edtStreet1.getText().toString());
                user.put("Street",edtStreet2.getText().toString());
                user.put("City",edtCity.getText().toString());
                user.put("Email",edtEmail.getText().toString());
                user.put("Gender" , edtGender.getText().toString());
                user.put("Address type", edtType.getText().toString());

                db.collection("users").document(uuid).update(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CaseInformationCardActivity.this,"Not Working",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    // Setting date of birth
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(997);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 997) {
            return new DatePickerDialog(this,
                    myDateListener1, year, month, day);
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


    // Showing date of birth
    private void showDate1(int year, int month, int day) {
        edtDob.setText(new StringBuilder().append(month).append("/")
                .append(day).append("/").append(year));

    }

    private void initUI()
    {
        //UI reference of textView
        final AutoCompleteTextView customerAutoTV1 = findViewById(R.id.edtAddressType);

        // create list of customer
        ArrayList<String> List1 = getAddressType();

        //Create adapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(CaseInformationCardActivity.this, android.R.layout.simple_spinner_item, List1);

        //Set adapter
        customerAutoTV1.setAdapter(adapter1);

    }

    private ArrayList<String> getAddressType()
    {
        ArrayList<String> genders = new ArrayList<>();
        genders.add("Residence");
        genders.add("Work");
        return genders;
    }
}