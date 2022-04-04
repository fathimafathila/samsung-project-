package com.example.covidwatch.UsersView.InitialInterview;

import android.content.Context;
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
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.covidwatch.R;
import com.example.covidwatch.UsersView.InitialInterview.Demographic.SelectDateFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;

public class InitialHealthAssessmentFragment extends Fragment {

    FirebaseAuth fAuth;
    FirebaseFirestore db;
    EditText heathCondition, otherHealthCondition, everSymptoms, firstFeelSick, currentHealthCondition,currentSymptoms , descOtherSymptoms ;
    Button save;
    MultiAutoCompleteTextView initHealthStatus, Symptomscheck;
    AutoCompleteTextView Yesno, Currentsituation;
    final static String[] item_Hc = new String[]{
            "Asthma",
            "Cardiac Disease",
            " Chronic Kidney Disease",
            "Chronic liver Disease",
            "Chronic Pulmonary Disease",
            "Congenital heart disease",
            " Current Smoker",
            "Current Vaper",
            "Diabetes",
            "Former Smoker",
            "Hypertension",
            "Immunocompromised",
            "Pregnancy",
            "Sickle cell disease",
            "Other"};

    final static String[] item_CS = new String[]{"--None--",
            "Still symptomatic", "not currently hospitalized Still symptomatic"," currently hospitalized",
            "No longer have any symptoms",
            "Never had symptom"};

    final static String[] item_Sc = new String[]{

            "Inability to Wake or Stay Awake",
           " Abdominal Discomfort",
           " Loss of Taste and/or Smell",
            "Change in Skin", "Lips or Nail Beds",
            "Muscle Ache",
            "Chest Pain or Pressure in the Chest",
            "Rash",
            "Chills",
            "Confusion or Change in Mental Status",
            "Congestion",
            "Rigors",
            "Cough",
            "Sore Throat",
            ",Difficulty Breathing/Shortness of Breath","   Runny Nose",
            "Diarrhea",
            "Vomiting",
            "Fatigue",
            "Wheezing",
            "Feverish",
            "Other"
};


    final static String[] item_YN = new String[]{"Yes", "No"};

    private Calendar calendar;
    private EditText dateView3;
    private int year, month, day;
    View v1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        SharedPreferences sh = this.getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String uuid = sh.getString("uuid","");

        View v = inflater.inflate( R.layout.fragment_initial_health_assessment, container, false );
        heathCondition = v.findViewById(R.id.edtHealthCondition);
        otherHealthCondition = v.findViewById(R.id.edtOtherHealthCondition);
        everSymptoms = v.findViewById(R.id.edtSymptoms);
        firstFeelSick = v.findViewById(R.id.edtDateFirstFellSick);
        currentHealthCondition = v.findViewById(R.id.edtCurrentSymptoms);
        currentSymptoms = v.findViewById(R.id.edtSymptomsInfo);
        descOtherSymptoms = v.findViewById(R.id.edtOtherSymptoms);
        save = v.findViewById(R.id.saveButton);

        db.collection("users").document(uuid).collection("Initial Interview")
                .document(fAuth.getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                   heathCondition.setText(documentSnapshot.getString("Health Condition"));
                   otherHealthCondition.setText(documentSnapshot.getString("Other Health Condition"));
                   everSymptoms.setText(documentSnapshot.getString("Past Symptoms"));
                   firstFeelSick.setText(documentSnapshot.getString("First Sick Date"));
                   currentHealthCondition.setText(documentSnapshot.getString("Current Health Condition"));
                   currentSymptoms.setText(documentSnapshot.getString("Current Symptoms"));
                   descOtherSymptoms.setText(documentSnapshot.getString("Other Symptom"));

            }
        });
        v1 = v;
        // Health Condition Spinner
        initHealthStatus = v.findViewById( R.id.edtHealthCondition );
        ArrayAdapter<String> adapterHC = new ArrayAdapter( requireContext(), R.layout.list_item, item_Hc );
        initHealthStatus.setTokenizer( new MultiAutoCompleteTextView.CommaTokenizer() );
        initHealthStatus.setAdapter( adapterHC );

        // Yes/ No Spinner
        Yesno = v.findViewById( R.id.edtSymptoms );
        ArrayAdapter<String> adapterYn = new ArrayAdapter( requireContext(), R.layout.list_item, item_YN );
        //Yesno.setTokenizer( new MultiAutoCompleteTextView.CommaTokenizer() );
        Yesno.setAdapter( adapterYn );

        // Currentsituation Spinner
        Currentsituation = v.findViewById( R.id.edtCurrentSymptoms );
        ArrayAdapter<String> adapterCs = new ArrayAdapter( requireContext(), R.layout.list_item, item_CS );
        //Yesno.setTokenizer( new MultiAutoCompleteTextView.CommaTokenizer() );
        Currentsituation.setAdapter( adapterCs );

        // Symptoms Check  Spinner
        Symptomscheck = v.findViewById( R.id.edtSymptomsInfo );
        ArrayAdapter<String> adapterSC = new ArrayAdapter( requireContext(), R.layout.list_item, item_Sc );
        Symptomscheck.setTokenizer( new MultiAutoCompleteTextView.CommaTokenizer() );
        Symptomscheck.setAdapter( adapterSC );

        dateView3 = (EditText) v.findViewById( R.id.edtDateFirstFellSick );
        calendar = Calendar.getInstance();
        year = calendar.get( Calendar.YEAR );

        month = calendar.get( Calendar.MONTH );
        day = calendar.get( Calendar.DAY_OF_MONTH );

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<Object,String> initialInterview = new HashMap<>();
                initialInterview.put("Health Condition", heathCondition.getText().toString());
                initialInterview.put("Other Health Condition", otherHealthCondition.getText().toString());
                initialInterview.put("Past Symptoms",everSymptoms.getText().toString());
                initialInterview.put("First Sick Date",firstFeelSick.getText().toString());
                initialInterview.put("Current Health Condition",currentHealthCondition.getText().toString());
                initialInterview.put("Current Symptoms",currentSymptoms.getText().toString());
                initialInterview.put("Other Symptom",descOtherSymptoms.getText().toString());

                db.collection("users").document(uuid).collection("Initial Interview")
                        .document(fAuth.getCurrentUser().getUid()).set(initialInterview).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "Working", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton calDateFirstFellSick = view.findViewById(R.id.calDateFirstFellSick);

        calDateFirstFellSick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                DialogFragment newFragment = new SelectDateFragment(firstFeelSick);
                newFragment.show(getFragmentManager(), "SickDate");

            }
        });
    }

}