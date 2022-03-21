package com.example.covidwatch.UsersView.InitialInterview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.covidwatch.R;
import com.example.covidwatch.UsersView.InitialInterview.Demographic.SelectDateFragment;

import java.util.Calendar;

public class InitialHealthAssessmentFragment extends Fragment {
    MultiAutoCompleteTextView initHealthStatus;
    MultiAutoCompleteTextView Symptomscheck;
    AutoCompleteTextView Yesno;
    AutoCompleteTextView Currentsituation;
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
"            Confusion or Change in Mental Status",    "Congestion",
    "Rigors",
    "Cough",
"    Sore Throat",
" ,   Difficulty Breathing/Shortness of Breath","   Runny Nose",
    "Diarrhea",
    "Vomiting",
    "Fatigue",
    "Wheezing",
    "Feverish",
    "Other"
};


    final static String[] item_YN = new String[]{"Yes", "No"};
    EditText edtDatefirstFellsick;
    private Calendar calendar;
    private EditText dateView1, dateView2, dateView3;
    private int year, month, day;
    View v1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate( R.layout.fragment_initial_health_assessment, container, false );
        v1 = v;
        // Health Condition Spinner
        initHealthStatus = v.findViewById( R.id.caseInfo );
        ArrayAdapter<String> adapterHC = new ArrayAdapter( requireContext(), R.layout.list_item, item_Hc );
        initHealthStatus.setTokenizer( new MultiAutoCompleteTextView.CommaTokenizer() );
        initHealthStatus.setAdapter( adapterHC );

        // Yes/ No Spinner
        Yesno = v.findViewById( R.id.edtsymp );
        ArrayAdapter<String> adapterYn = new ArrayAdapter( requireContext(), R.layout.list_item, item_YN );
        //Yesno.setTokenizer( new MultiAutoCompleteTextView.CommaTokenizer() );
        Yesno.setAdapter( adapterYn );

        // Currentsituation Spinner
        Currentsituation = v.findViewById( R.id.edtcurrentsts );
        ArrayAdapter<String> adapterCs = new ArrayAdapter( requireContext(), R.layout.list_item, item_CS );
        //Yesno.setTokenizer( new MultiAutoCompleteTextView.CommaTokenizer() );
        Currentsituation.setAdapter( adapterCs );

        // Symptoms Check  Spinner
        Symptomscheck = v.findViewById( R.id.edtsymptomsInfo );
        ArrayAdapter<String> adapterSC = new ArrayAdapter( requireContext(), R.layout.list_item, item_Sc );
        Symptomscheck.setTokenizer( new MultiAutoCompleteTextView.CommaTokenizer() );
        Symptomscheck.setAdapter( adapterSC );

        dateView3 = (EditText) v.findViewById( R.id.edtDatefirstFellsick );
        calendar = Calendar.getInstance();
        year = calendar.get( Calendar.YEAR );

        month = calendar.get( Calendar.MONTH );
        day = calendar.get( Calendar.DAY_OF_MONTH );
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton calDateFirstFellSick = view.findViewById(R.id.calDateFirstFellSick);

        calDateFirstFellSick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getFragmentManager(), "SickDate");

            }
        });
    }

}