package com.example.covidwatch.UsersView.InitialInterview;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import com.example.covidwatch.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

//    // Setting date of birth
//    @SuppressWarnings("deprecation")
//    public void setDate(View v1) {
//        showDialog(997);
//    }
//
//    @Override
//    protected Dialog onCreateDialog(int id) {
//        // TODO Auto-generated method stub
//        if (id == 997) {
//            return new DatePickerDialog(this,
//                    myDateListener1, year, month, day);
//        }
//
//        return null;
//    }

//    private DatePickerDialog.OnDateSetListener myDateListener1 = new
//            DatePickerDialog.OnDateSetListener() {
//                @Override
//                public void onDateSet(DatePicker arg0,
//                                      int arg1, int arg2, int arg3) {
//                    // TODO Auto-generated method stub
//                    // arg1 = year
//                    // arg2 = month
//                    // arg3 = day
//                    showDate1( arg1, arg2 + 1, arg3 );
//                }
//
//            };
//
//
//
//    // Showing date of birth
//    private void showDate1(int year, int month, int day) {
//        dateView1.setText( new StringBuilder().append( day ).append( "/" )
//                .append( month ).append( "/" ).append( year ) );
//
//        int number = month * 10000 + day * 100 + year % 100;
//    }

}