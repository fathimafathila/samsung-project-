package com.example.covidwatch.AdminView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.covidwatch.AdminView.CreateUser.AddRecord2Activity;
import com.example.covidwatch.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class UpdateUser2Activity extends AppCompatActivity {


    private Calendar calendar;
    private EditText dateView;
    private int year, month, day;

    private Button CreateUsear ;

    //Firebase Object Create
    private FirebaseAuth fAuth;
    private FirebaseFirestore db;
    EditText edtAddressType, edtStreet1, edtStreet2, edtCity, edtState, edtCountry, edtZipCode, edtCaseStatus, edtInvestStatus, edtOpenDate ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user2);

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        CreateUsear = findViewById(R.id.submitbutton);
        dateView = (EditText) findViewById(R.id.edtOpenDate);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        //showDate(year, month+1, day);

        edtAddressType = findViewById(R.id.edtAddressType);
        edtStreet1 = findViewById(R.id.edtAddressStreet1);
        edtStreet2 = findViewById(R.id.edtAddressStreet2);
        edtCity = findViewById(R.id.edtCity);
        edtState = findViewById(R.id.edtState);
        edtCountry = findViewById(R.id.edtCountry);
        edtZipCode = findViewById(R.id.edtZipcode);
        edtInvestStatus = findViewById(R.id.edtInvestStatus);
        edtCaseStatus = findViewById(R.id.edtStatus);
        edtOpenDate = findViewById(R.id.edtOpenDate);

        // Method for Spinners
        initUI();

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String email = sh.getString("Email","");
        String dob = sh.getString("DOB","");
        String firstName = sh.getString("FirstName","");
        String lastName = sh.getString("LastName","");
        String age = sh.getString("Age","");
        String gender = sh.getString("Gender","");
        String phone = sh.getString("PhoneNumber","");
        String priority = sh.getString("Priority","");
        String minor = sh.getString("Minor","");
        String specimenDate = sh.getString("SpecimenDate","");
        String testReportDate = sh.getString("TestReportDate","");
        String password= sh.getString("password","");



        // Toast.makeText(AddRecord2Activity.this, date, Toast.LENGTH_SHORT).show();
        CreateUsear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        String id =  fAuth.getUid();
                        String addressType = edtAddressType.getText().toString();
                        String street1 = edtStreet1.getText().toString();
                        String street2 = edtStreet2.getText().toString();
                        String city = edtCity.getText().toString();
                        String state = edtState.getText().toString();
                        String country = edtCountry.getText().toString();
                        String zipCode = edtZipCode.getText().toString();
                        String investStatus = edtInvestStatus.getText().toString();
                        String caseStatus =edtCaseStatus.getText().toString();
                        String openDate = edtOpenDate.getText().toString();

                        Map<String,Object> user = new HashMap<>();
                        user.put("email",email);
                        user.put("dob",dob);
                        user.put("admin","1");
                        user.put("First Name",firstName);
                        user.put("Last Name", lastName);
                        user.put("Age",age);
                        user.put("Gender",gender);
                        user.put("Phone Number",phone);
                        user.put("Priority",priority);
                        user.put("Minor",minor);
                        user.put("Specimen Date",specimenDate);
                        user.put("Test Report Date",testReportDate);
                        user.put("Address type",addressType);
                        user.put("Street1",street1);
                        user.put("Street2",street2);
                        user.put("City",city);
                        user.put("State",state);
                        user.put("Country",country);
                        user.put("Zip Code",zipCode);
                        user.put("Investigation Status",investStatus);
                        user.put("Case Status",caseStatus);
                        user.put("Open Date",openDate);


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

                                int max = 10 ;
                                int min = 1 ;
                                boolean isUnique = true ;
                                while (isUnique){
                                    int i = rand.nextInt(max - min) + min ;
                                    if(!uniqueId.contains(i)){
                                        user.put("ID",String.valueOf(i));
                                        db.collection("users").document(id)
                                                .set(user)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(UpdateUser2Activity.this, "Account Has been Created", Toast.LENGTH_SHORT).show();

                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(UpdateUser2Activity.this, "Account Failed Created", Toast.LENGTH_SHORT).show();
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
        });
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
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

    // Implementing Spinners
    private void initUI()
    {
        //UI reference of textView
        final AutoCompleteTextView customerAutoTV1 = findViewById(R.id.edtStatus);
        final AutoCompleteTextView customerAutoTV2 = findViewById(R.id.edtInvestStatus);

        // create list of customer
        ArrayList<String> customerList1 = getCaseType();
        ArrayList<String> customerList2 = getInvestigationStatus();

        //Create adapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(UpdateUser2Activity.this, android.R.layout.simple_spinner_item, customerList1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(UpdateUser2Activity.this, android.R.layout.simple_spinner_item, customerList2);

        //Set adapter
        customerAutoTV1.setAdapter(adapter1);
        customerAutoTV2.setAdapter(adapter2);

    }

    private ArrayList<String> getCaseType()
    {
        ArrayList<String> caseType = new ArrayList<>();
        caseType.add("Confirmed");
        caseType.add("Close Contact");
        caseType.add("Probable");
        caseType.add("None");
        return caseType;
    }

    private ArrayList<String> getInvestigationStatus()
    {
        ArrayList<String> investigationStatus = new ArrayList<>();
        investigationStatus.add("New");
        investigationStatus.add("Initial Engagement");
        investigationStatus.add("Isolation");
        investigationStatus.add("Closed");
        investigationStatus.add("None");
        return investigationStatus;
    }
}