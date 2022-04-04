package com.example.covidwatch.UsersView.InitialInterview.Demographic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.covidwatch.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;




public class CaseInformationFragment extends Fragment {

    FirebaseAuth fAuth ;
    FirebaseFirestore db ;
    TextView name,id ,  address ,  number , dob , age, gender, email, openDate,  status , priority ;
    EditText consent, reInfected, gaurdianName, deseaed, race, deseaedDutTo ,primaryLanguage, deseaedDate, primaryOccupation ;
    Button update ;
    ImageButton calDeceasedDate;

    final Calendar calendar = Calendar.getInstance();
    int yy = calendar.get(Calendar.YEAR);
    int mm = calendar.get(Calendar.MONTH);
    int dd = calendar.get(Calendar.DAY_OF_MONTH);




    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        View view = inflater.inflate(R.layout.fragment_case_information, container, false);

        update = view.findViewById(R.id.btnUpdate);
        name = view.findViewById(R.id.txtFullName);
        id = view.findViewById(R.id.txtUserId);
        address = view.findViewById(R.id.txtAddress);
        number =view.findViewById(R.id.txtContact);
        dob = view.findViewById(R.id.txtDob);
        age = view.findViewById(R.id.txtAge);
        gender = view.findViewById(R.id.txtGender);
        email = view.findViewById(R.id.txtEmail);
        openDate = view.findViewById(R.id.txtStartDate);
        status = view.findViewById(R.id.txtInvestigationStatusV);
        priority = view.findViewById(R.id.txtPriorityV);

        consent = view.findViewById(R.id.edtConsent);
        reInfected = view.findViewById(R.id.edtReinfected);
        gaurdianName = view.findViewById(R.id.edtGuardianName);
        deseaed = view.findViewById(R.id.edtDeceased);
        race = view.findViewById(R.id.edtRace);
        deseaedDutTo = view.findViewById(R.id.edtDeceasedDueTo);
        deseaedDate = view.findViewById(R.id.edtDeceasedDate);
        primaryLanguage = view.findViewById(R.id.edtPrimaryLanguage);
        primaryOccupation = view.findViewById(R.id.edtPrimaryOccupation);

        SharedPreferences sh = this.getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String uuid = sh.getString("uuid","");

        db.collection("users").document(uuid).collection("Demographic").document("Doc")
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                consent.setText(documentSnapshot.getString("Consent"));
                reInfected.setText(documentSnapshot.getString("Reinfected"));
                gaurdianName.setText(documentSnapshot.getString("GaurdianName"));
                deseaed.setText(documentSnapshot.getString("Deseaed"));
                race.setText(documentSnapshot.getString("Race"));
                deseaedDutTo.setText(documentSnapshot.getString("Deseaed Due To"));
                deseaedDate.setText(documentSnapshot.getString("Deseaed Date"));
                primaryLanguage.setText(documentSnapshot.getString("Primary Language"));
                primaryOccupation.setText(documentSnapshot.getString("Primary Occupation"));
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> sample = new HashMap<>();
                sample.put("Consent",consent.getText().toString());
                sample.put("Reinfected",reInfected.getText().toString());
                sample.put("GaurdianName", gaurdianName.getText().toString());
                sample.put("Deseaed", deseaed.getText().toString());
                sample.put("Race",race.getText().toString());
                sample.put("Deseaed Due To",deseaedDutTo.getText().toString());
                sample.put("Deseaed Date",deseaedDate.getText().toString());
                sample.put("Primary Language", primaryLanguage.getText().toString());
                sample.put("Primary Occupation",primaryLanguage.getText().toString());

                if(update.getText().equals("Add")) {
                    db.collection("users").document(uuid).collection("Demographic").document("Doc")
                            .set(sample).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                        }
                    });
                }else{
                    db.collection("users").document(uuid).collection("Demographic").document("Doc")
                            .set(sample).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                        }
                    });
                }

            }
        });
        db.collection("users").document(uuid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String firstName = documentSnapshot.getString("First Name");
                String lastName = documentSnapshot.getString("Last Name");
                String id1 = documentSnapshot.getString("ID");
                String str1  = documentSnapshot.getString("Apartment");
                String str2  = documentSnapshot.getString("Street");
                String City  = documentSnapshot.getString("City");
                String state  = documentSnapshot.getString("State");
                String Country  = documentSnapshot.getString("Country");
                String zipCode  = documentSnapshot.getString("Zip Code");
                String number1 = documentSnapshot.getString("Contact Number");
                String dob1 = documentSnapshot.getString("DOB");
                String age1 = documentSnapshot.getString("Age");
                String gender1 = documentSnapshot.getString("Gender");
                String email1 = documentSnapshot.getString("Email");
                String openDate1 = documentSnapshot.getString("Open Date");
                String status1 = documentSnapshot.getString("Investigation Status");
                String priority1 = documentSnapshot.getString("Priority");

                name.setText( firstName + " " + lastName);
                id.setText(id1);
                address.setText(str1 + "," + str2 + "," + City + "," + state +"," + Country + "," + zipCode);
                number.setText(number1);
                dob.setText(dob1);
                age.setText(age1);
                gender.setText(gender1);
                email.setText(email1);
                openDate.setText(openDate1);
                status.setText(status1);
                priority.setText(priority1);


            }
        });


        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        //UI reference of textView
        final AutoCompleteTextView autoConsent = view.findViewById(R.id.edtConsent);
        final AutoCompleteTextView autoReinfected = view.findViewById(R.id.edtReinfected);
        final AutoCompleteTextView autoDeceased = view.findViewById(R.id.edtDeceased);
        final AutoCompleteTextView autoRace = view.findViewById(R.id.edtRace);
        final AutoCompleteTextView autoPrimaryLanguage = view.findViewById(R.id.edtPrimaryLanguage);

        //Create adapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(), R.layout.list_item, getResources().getStringArray(R.array.arrYesNo));
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(), R.layout.list_item, getResources().getStringArray(R.array.arrRace));
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getActivity(), R.layout.list_item, getResources().getStringArray(R.array.arrPrimaryLanguage));

        //Set adapter
        autoConsent.setAdapter(adapter1);
        autoReinfected.setAdapter(adapter1);
        autoDeceased.setAdapter(adapter1);
        autoRace.setAdapter(adapter2);
        autoPrimaryLanguage.setAdapter(adapter3);

        calDeceasedDate = view.findViewById(R.id.calDeceasedDate);

        // On clicking date picker

        calDeceasedDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                DialogFragment newFragment = new SelectDateFragment(deseaedDate);
                newFragment.show(getFragmentManager(), "DeceasedDate");

            }
        });

        // On clicking card view

        CardView card = view.findViewById(R.id.card);

        card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getActivity(), CaseInformationCardActivity.class);
                startActivity(intent);

            }
        });
    }
}