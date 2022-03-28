package com.example.covidwatch.AdminView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

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

public class PersonalInfoActivity extends AppCompatActivity {

    private Calendar calendar;
    private EditText dateView1,dateView2;
    private int year, month, day;

    Button saveButton;

    EditText edtFirstName, edtLastName, edtDOB, edtDateOfJoining, edtNumber, edtStreet1, edtStreet2, edtCity, edtState, edtCountry, edtZipCode, edtEmail, edtQuestion, edtAns ;
    FirebaseAuth fAuth;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String adminId = sh.getString("AdminId","");

        initUI();

        dateView1 = (EditText) findViewById(R.id.edtDob);
        dateView2 = (EditText) findViewById(R.id.edtDoj);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate1(year, month+1, day);
        showDate2(year, month+1, day);

        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtDOB = findViewById(R.id.edtDob);
        edtDateOfJoining = findViewById(R.id.edtDoj);
        edtNumber = findViewById(R.id.edtCN);
        edtStreet1 = findViewById(R.id.edtAddressst1);
        edtStreet2 = findViewById(R.id.edtAddressst2);
        edtState = findViewById(R.id.edtState);
        edtCity = findViewById(R.id.edtCity);
        edtZipCode = findViewById(R.id.edtZipcode);
        edtCountry = findViewById(R.id.edtCountry);
        edtEmail = findViewById(R.id.edtemailId);
        edtQuestion = findViewById(R.id.edtSQ);
        edtAns = findViewById(R.id.edtSA);

        saveButton = findViewById(R.id.Savebutton);

        DocumentReference documentReference =  db.collection("users").document(adminId);
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        edtFirstName.setText(documentSnapshot.getString("First Name"));
                        edtLastName.setText(documentSnapshot.getString("Last Name"));
                        edtDOB.setText(documentSnapshot.getString("DOB"));
                        edtDateOfJoining.setText(documentSnapshot.getString("Joining Date"));
                        edtNumber.setText(documentSnapshot.getString("Phone Number"));
                        edtStreet1.setText(documentSnapshot.getString("Apartment"));
                        edtStreet2.setText(documentSnapshot.getString("Street"));
                        edtCity.setText(documentSnapshot.getString("City"));
                        edtState.setText(documentSnapshot.getString("State"));
                        edtZipCode.setText(documentSnapshot.getString("Zip Code"));
                        edtCountry.setText(documentSnapshot.getString("Country"));
                        edtEmail.setText(documentSnapshot.getString("Email"));
                        edtQuestion.setText(documentSnapshot.getString("Security Question"));
                        edtAns.setText(documentSnapshot.getString("Security Answer"));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PersonalInfoActivity.this,"Not Working",Toast.LENGTH_SHORT).show();
                    }
                });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> user = new HashMap<>();
                user.put("First Name",edtFirstName.getText().toString());
                user.put("Last Name",edtLastName.getText().toString());
                user.put("DOB",edtDOB.getText().toString());
                user.put("Joining Date",edtDateOfJoining.getText().toString());
                user.put("Contact Number",edtNumber.getText().toString());
                user.put("Apartment",edtStreet1.getText().toString());
                user.put("Street",edtStreet2.getText().toString());
                user.put("City",edtCity.getText().toString());
                user.put("State",edtState.getText().toString());
                user.put("Zip Code",edtZipCode.getText().toString());
                user.put("Country",edtCountry.getText().toString());
                user.put("Email",edtEmail.getText().toString());
                user.put("Security Question",edtQuestion.getText().toString());
                user.put("Security Answer",edtAns.getText().toString().toLowerCase());

                db.collection("users").document(adminId).update(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(PersonalInfoActivity.this,"Working",Toast.LENGTH_SHORT).show();
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(PersonalInfoActivity.this,"Not Working",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    @SuppressWarnings("deprecation")
    public void setDob(View view) {
        showDialog(999);
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