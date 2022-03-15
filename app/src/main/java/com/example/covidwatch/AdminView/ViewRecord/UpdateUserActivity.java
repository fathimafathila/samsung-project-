package com.example.covidwatch.AdminView.ViewRecord;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.covidwatch.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;

public class UpdateUserActivity extends AppCompatActivity {

    EditText edtFName,edtLName, edtEmail, edtAge, edtDob, edtGender, edtPhoneNumber, edtPriority, edtMinor , edtSpecimenDate , edtTestReportDate;
    boolean isAllFieldsChecked = false;
    private EditText dateView1, dateView2, dateView3;
    private int year, month, day;
    private Calendar calendar;
    private FirebaseAuth fAuth;
    private FirebaseFirestore db;
    String password ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        // Instance of Firebase
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();



        //defining reference objects for the UI controls
        edtFName = findViewById(R.id.FirstName);
        edtLName = findViewById(R.id.LastName);
        edtEmail = findViewById(R.id.Email);
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

        db.collection("users").document(getIntent().getStringExtra("uuid")).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
               edtFName.setText(documentSnapshot.getString("First Name"));
               edtLName.setText(documentSnapshot.getString("Last Name"));
               edtEmail.setText(documentSnapshot.getString("email"));
               edtDob.setText(documentSnapshot.getString("dob"));
               edtAge.setText(documentSnapshot.getString("Age"));
               edtGender.setText(documentSnapshot.getString("Gender"));
               edtPhoneNumber.setText(documentSnapshot.getString("Phone Number"));
               edtPriority.setText(documentSnapshot.getString("Priority"));
               edtMinor.setText(documentSnapshot.getString("Minor"));
               edtSpecimenDate.setText(documentSnapshot.getString("Specimen Date"));
               edtTestReportDate.setText(documentSnapshot.getString("Test Report Date"));
            }
        });
    }
    // Setting date of birth
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(997);
    }

    // setting specimen date
    @SuppressWarnings("deprecation")
    public void setSpecimenDate(View view) {
        showDialog(998);
    }

    // Setting test report date
    @SuppressWarnings("deprecation")
    public void setReportDate(View view) {
        showDialog(999);
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

        int number = month*10000 + day*100 + year%100 ;
        if(month < 10 ){
            password = "0" + String.valueOf(number);
        }else{
            password = String.valueOf(number);
        }
        int y =calculateAge(year,month,day);
        String ye = String.valueOf(y);
        edtAge.setText(ye);

        if( y < 18 ){
            edtMinor.setText("Yes");
        }else{
            edtMinor.setText("No");
        }



    }
    //Calculate Age
    private int calculateAge(int year, int month, int days){
        LocalDate today = null;
        LocalDate past = null;
        int y = 0 ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            today = LocalDate.now();
            past = LocalDate.of(year,month,days);
            y = Period.between(past,today).getYears();
            Toast.makeText(getApplicationContext(),String.valueOf(Period.between(past,today).getYears()), Toast.LENGTH_SHORT).show();
        }
        return y;
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
        final AutoCompleteTextView customerAutoTV1 = findViewById(R.id.edtGender);
        final AutoCompleteTextView customerAutoTV2 = findViewById(R.id.edtPriority);
        final AutoCompleteTextView customerAutoTV3 = findViewById(R.id.edtMinor);
        // create list of customer
        ArrayList<String> customerList1 = getGendersList();
        ArrayList<String> customerList2 = getPriorityList();
        ArrayList<String> customerList3 = getMinorList();
        //Create adapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(UpdateUserActivity.this, android.R.layout.simple_spinner_item, customerList1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(UpdateUserActivity.this, android.R.layout.simple_spinner_item, customerList2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(UpdateUserActivity.this, android.R.layout.simple_spinner_item, customerList3);
        //Set adapter
        customerAutoTV1.setAdapter(adapter1);
        customerAutoTV2.setAdapter(adapter2);
        customerAutoTV3.setAdapter(adapter3);
    }
    private ArrayList<String> getGendersList()
    {
        ArrayList<String> genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");
        genders.add("Both");
        genders.add("Prefer not to say");
        return genders;
    }

    private ArrayList<String> getPriorityList()
    {
        ArrayList<String> priority = new ArrayList<>();
        priority.add("High");
        priority.add("Low");
        priority.add("Medium");
        return priority;
    }

    private ArrayList<String> getMinorList()
    {
        ArrayList<String> minor = new ArrayList<>();
        minor.add("Yes");
        minor.add("No");
        return minor;
    }
    public void btn_next (View view){
        Intent intent = new Intent(this, UpdateUser2Activity.class);
        intent.putExtra("uuid",getIntent().getStringExtra("uuid"));
        startActivity(intent);
    }
}
