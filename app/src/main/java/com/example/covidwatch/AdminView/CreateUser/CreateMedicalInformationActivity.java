package com.example.covidwatch.AdminView.CreateUser;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


import com.example.covidwatch.AdminView.AdminDashboard;
import com.example.covidwatch.DateCalculation;
import com.example.covidwatch.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CreateMedicalInformationActivity extends AppCompatActivity {

    // Initial variable
    EditText  edtMedicalID ,edtPriority, edtSpecimenDate , edtTestReportDate, edtCaseStatus, edtInvestStatus, edtOpenDate ;
    // Extra Variables
    private Calendar calendar;
    private EditText dateView1, dateView2, dateView3;
    private int year, month, day;
    boolean isAllFieldsChecked = false;

    //Firebase Object Create
    private FirebaseAuth fAuth;
    private FirebaseFirestore db;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_medical_information);

        getSupportActionBar().hide();

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // find view by id
        edtMedicalID = findViewById(R.id.edtMedicalId);
        edtPriority = findViewById(R.id.edtPriority);
        edtSpecimenDate = findViewById(R.id.edtSpecimenDate);
        edtTestReportDate = findViewById(R.id.edttestreportdate);
        edtInvestStatus = findViewById(R.id.edtInvestStatus);
        edtCaseStatus = findViewById(R.id.edtStatus);
        edtOpenDate = findViewById(R.id.edtOpenDate);

        // Method for Spinners
        initUI();

        dateView1 = (EditText) findViewById(R.id.edtSpecimenDate);
        dateView2 = (EditText) findViewById(R.id.edttestreportdate);
        dateView3 = (EditText) findViewById(R.id.edtOpenDate);

        DateCalculation dateCalculation = new DateCalculation();
        dateView3.setText(dateCalculation.currentDate());

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }
    // Implementing Spinners
    private void initUI()
    {
        //UI reference of textView
        final AutoCompleteTextView customerAutoTV1 = findViewById(R.id.edtPriority);
        final AutoCompleteTextView customerAutoTV2 = findViewById(R.id.edtStatus);
        final AutoCompleteTextView customerAutoTV3 = findViewById(R.id.edtInvestStatus);

        // create list of customer
        ArrayList<String> priority = new ArrayList<>();
        priority.add("High");
        priority.add("Low");
        priority.add("Medium");
        ArrayList<String> caseType = new ArrayList<>();
        caseType.add("Confirmed");
        caseType.add("Close Contact");
        caseType.add("Probable");
        caseType.add("None");
        ArrayList<String> investigationStatus = new ArrayList<>();
        investigationStatus.add("New");
        investigationStatus.add("Initial Engagement");
        investigationStatus.add("Isolation");
        investigationStatus.add("Closed");
        investigationStatus.add("None");

        //Create adapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(CreateMedicalInformationActivity.this, android.R.layout.simple_spinner_item, priority);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(CreateMedicalInformationActivity.this, android.R.layout.simple_spinner_item, caseType);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(CreateMedicalInformationActivity.this, android.R.layout.simple_spinner_item, investigationStatus);
        //Set adapter
        customerAutoTV1.setAdapter(adapter1);
        customerAutoTV2.setAdapter(adapter2);
        customerAutoTV3.setAdapter(adapter3);

    }

    // Setting date of birth
    @SuppressWarnings("deprecation")
    public void setSpecimenDate(View view) { showDialog(997); }

    // setting specimen date
    @SuppressWarnings("deprecation")
    public void setReportDate(View view) {
        showDialog(998);
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

        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener1 = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    showDate1(arg1, arg2+1, arg3);
                }
            };

    private DatePickerDialog.OnDateSetListener myDateListener2 = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    showDate2(arg1, arg2+1, arg3);
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



    public void onClick_Create(View view){
        isAllFieldsChecked = true;

        if (isAllFieldsChecked) {


            // Shared preference to store data

            SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
            String fName = sh.getString("FirstName","");
            String lName = sh.getString("LastName","");
            String email = sh.getString("Email","");
            String dob   = sh.getString("DOB","");
            String age   = sh.getString("Age","");
            String minor = sh.getString("Minor","");
            String gender = sh.getString("Gender","");
            String phoneNumber = sh.getString("PhoneNumber","");
            String password = sh.getString("Password","");
            String addressType = sh.getString("AddressType","");
            String apartment = sh.getString("Apartment","");
            String street = sh.getString("Street","");
            String city = sh.getString("City","");
            String state = sh.getString("State","");
            String country = sh.getString("Country","");
            String zipCode = sh.getString("ZipCode","");



            fAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    String ID =  fAuth.getUid();
                    String medicalId = edtMedicalID.getText().toString();
                    String priority = edtPriority.getText().toString();
                    String specimenDate = edtSpecimenDate.getText().toString();
                    String testReportDate = edtTestReportDate.getText().toString();
                    String caseType = edtCaseStatus.getText().toString();
                    String investigation = edtInvestStatus.getText().toString();
                    String openDate = edtOpenDate.getText().toString();

                    Map<String,Object> user = new HashMap<>();
                    user.put("Email",email);
                    user.put("DOB",dob);
                    user.put("Admin","1");
                    user.put("First Name",fName);
                    user.put("Last Name", lName);
                    user.put("Age",age);
                    user.put("Gender",gender);
                    user.put("Phone Number",phoneNumber);
                    user.put("Priority",priority);
                    user.put("Minor",minor);
                    user.put("Specimen Date",specimenDate);
                    user.put("Test Report Date",testReportDate);
                    user.put("Address Type",addressType);
                    user.put("Apartment",apartment);
                    user.put("Street",street);
                    user.put("City",city);
                    user.put("State",state);
                    user.put("Country",country);
                    user.put("Zip Code",zipCode);
                    user.put("Investigation Status",investigation);
                    user.put("Case Status",caseType);
                    user.put("Open Date",openDate);
                    user.put("Medical ID",medicalId);
                    user.put("Password",password);
                    user.put("uuid",ID);
                    user.put("Urgent","False");
                    db.collection("users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<DocumentSnapshot> documentSnapshots = queryDocumentSnapshots.getDocuments();
                            ArrayList<Integer> uniqueId = new ArrayList<>();
                            Random rand = new Random();

                            for (DocumentSnapshot snapshot:documentSnapshots) {
                                String id = snapshot.getString("ID");
                                uniqueId.add(Integer.parseInt(id));
                            }

                            int max = 999 ;
                            int min = 100 ;
                            boolean isUnique = true ;
                            while (isUnique){
                                int i = rand.nextInt(max - min) + min ;
                                if(!uniqueId.contains(i)){
                                    user.put("ID",String.valueOf(i));
                                    db.collection("users").document(ID)
                                            .set(user)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    JavaMailAPI javaMailAPI = new JavaMailAPI(getApplicationContext(), email, specimenDate, fName, 0);

                                                    javaMailAPI.execute();
                                                    Intent intent = new Intent(CreateMedicalInformationActivity.this , AdminDashboard.class);
                                                    startActivity(intent);
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(CreateMedicalInformationActivity.this, "Account Failed Created", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                    isUnique = false ;
                                }
                            }
                        }
                    });


                }
            });
        }

        }

    private boolean validateInputFields() {
        if (edtMedicalID.length() == 0) {
            edtMedicalID.setError("First Name should not be empty");
            return false;
        }

        if (edtPriority.length() == 0) {
            edtPriority.setError("Last Name should not be empty");
            return false;
        }

        if (edtSpecimenDate.length() == 0) {
            edtSpecimenDate.setError("DOB should not be empty");
            return false;
        }
        if (edtTestReportDate.length() == 0) {
            edtTestReportDate.setError("Age should not be empty");
            return false;
        }
        if (edtCaseStatus.length() == 0) {
            edtCaseStatus.setError(" Gender should not be empty");
            return false;
        }

        if (edtInvestStatus.length() == 0) {
            edtInvestStatus.setError("Phone NUmber should not be empty");
            return false;
        }
        if (edtOpenDate.length() == 0) {
            edtOpenDate.setError("Minor should not be empty");
            return false;
        }
        // after all validation return true.
        return true;
    }
}