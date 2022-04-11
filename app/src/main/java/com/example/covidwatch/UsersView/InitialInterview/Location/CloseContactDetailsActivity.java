package com.example.covidwatch.UsersView.InitialInterview.Location;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.covidwatch.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CloseContactDetailsActivity extends AppCompatActivity {

    private Calendar calendar;
    private int year, month, day;

    Button addContact;
    EditText firstName, lastName, emailID,dob,gender,  contactNumber, addressType, street, apartment, city, addInfo ;
    CheckBox sameAddress;
    FirebaseAuth fAuth;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_contact_details);

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        addContact = findViewById(R.id.btnAddContact);
        firstName = findViewById(R.id.edtFirstName);
        lastName  = findViewById(R.id.edtLastName);
        emailID  = findViewById(R.id.edtEmailId);
        contactNumber = findViewById(R.id.edtContactNumber);
        gender = findViewById(R.id.edtGender);
        addressType = findViewById(R.id.edtAddressType);
        street = findViewById(R.id.edtStreet);
        apartment = findViewById(R.id.edtApartment);
        dob = findViewById(R.id.edtDob);
        city = findViewById(R.id.edtCity);
        addInfo = findViewById(R.id.edtAdditionalInfo);
        sameAddress = findViewById(R.id.checkbox);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        initUI();

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String id = sh.getString("uuid","");


        if(!getIntent().getStringExtra("uuid").equals("")) {


            addContact.setText("Update Contact");
            db.collection("users").document(id).collection("Contact").document(getIntent().getStringExtra("uuid"))
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                            firstName.setText(value.getString("First Name"));
                            lastName.setText(value.getString("Last Name"));
                            emailID.setText(value.getString("Email"));
                            contactNumber.setText(value.getString("Phone Number"));
                            gender.setText(value.getString("Gender"));
                            addressType.setText(value.getString("Address Type"));
                            street.setText(value.getString("Street"));
                            apartment.setText(value.getString("Apartment"));
                            dob.setText(value.getString("DOB"));
                            city.setText(value.getString("City"));
                            addInfo.setText(value.getString("Additional Info"));
                        }
                    });
        }
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> contact = new HashMap<>();
                contact.put("First Name", firstName.getText().toString());
                contact.put("Last Name", lastName.getText().toString());
                contact.put("Email", emailID.getText().toString());
                contact.put("DOB", dob.getText().toString());
                contact.put("Gender", gender.getText().toString());
                contact.put("Phone Number", contactNumber.getText().toString());
                contact.put("Address Type",  addressType.getText().toString());
                contact.put("Street", street.getText().toString());
                contact.put("Apartment", apartment.getText().toString());
                contact.put("City", city.getText().toString());
                contact.put("Additional Info", addInfo.getText().toString());


                if(addContact.getText().equals("Add Contact")) {
                    db.collection("users").document(id).collection("Contact").document()
                            .set(contact).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    db.collection("users").document(id).collection("Contact").document(getIntent().getStringExtra("uuid"))
                            .update(contact).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }


    public  void smAddress(View v){

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String id = sh.getString("uuid","");

        String uuid;
        if(!getIntent().getStringExtra("uuid").equals("")){
            uuid = getIntent().getStringExtra("uuid");
        }else{
            uuid = id;
        }
        sameAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (sameAddress.isChecked()) {
                        db.collection("users").document(uuid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                addressType.setText(documentSnapshot.getString("Address Type"));
                                apartment.setText(documentSnapshot.getString("Apartment"));
                                street.setText(documentSnapshot.getString("Street"));
                                city.setText(documentSnapshot.getString("City"));
                            }
                        });
                    }else{
                        addressType.setText("");
                        apartment.setText("");
                        street.setText("");
                        city.setText("");
                    }
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
        dob.setText(new StringBuilder().append(month).append("/")
                .append(day).append("/").append(year));

    }

    private void initUI()
    {
        //UI reference of textView
        final AutoCompleteTextView customerAutoTV1 = findViewById(R.id.edtAddressType);

        // create list of customer
        ArrayList<String> List1 = getAddressType();

        //Create adapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(CloseContactDetailsActivity.this, android.R.layout.simple_spinner_item, List1);

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