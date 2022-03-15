package com.example.covidwatch.AdminView.ViewRecord;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.covidwatch.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;

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

        db.collection("users").document(getIntent().getStringExtra("uuid")).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
               edtAddressType.setText(documentSnapshot.getString("Address type"));
               edtStreet1.setText(documentSnapshot.getString("Street1"));
               edtStreet2.setText(documentSnapshot.getString("Street2"));
               edtCity.setText(documentSnapshot.getString("City"));
               edtState.setText(documentSnapshot.getString("State"));
               edtCountry.setText(documentSnapshot.getString("Country"));
               edtZipCode.setText(documentSnapshot.getString("Zip Code"));
               edtInvestStatus.setText(documentSnapshot.getString("Investigation Status"));
               edtCaseStatus.setText(documentSnapshot.getString("Case Status"));
               edtOpenDate.setText(documentSnapshot.getString("Open Date"));
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