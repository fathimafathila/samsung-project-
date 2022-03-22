package com.example.covidwatch.UsersView.InitialInterview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.covidwatch.R;


public class EpilinkageCongregateFragment extends Fragment {


    ImageButton calDeceasedDate;
    EditText commentsAuto;

    AutoCompleteTextView KnownCloseCnt;
    AutoCompleteTextView CongrSetting;
    final static String[] item_YN = new String[]{"Yes", "No"};
    final static String[] item_RR = new String[]{
            "--None--",
            "Alcohol/Drug Treatment Facility",
            "Assisted/Supported Living Facility",
            "College - Unk Attendance Type",
            "College - 100% In-Person",
            "College - Partial Remote",
            "College - 100% Remote",
            "Correction Facility or Jail",
            "Day Care Center",
            "Day/Overnight Camp",
            "Developmental Disability Facility",
            "Group Home",
            "Homeless Shelter",
            "Independent/Senior Living Facility",
            "Long-term/Skilled Care Facility",
            "Mental Health Facility",
            "Military Facility",
            "Preschool - Unk Attendance Type",
            "Preschool - 100% In-Person",
            "Preschool - Partial Remote",
            "Preschool - 100% Remote",
            " School K-12 - Unk Attendance Type",
            "School K-12 - 100% In-Person",
            "School K-12 - Partial Remote",
            "School K-12 - 100% Remote",
            " Other",
            " Not Applicable",
            "Unknown"
    };


    View v1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate( R.layout.fragment_epilinkage_congregate, container, false );
        v1 = v;
        // Health Condition Spinner
        KnownCloseCnt = v.findViewById( R.id.congreagetSetting);
        ArrayAdapter<String> adapterHC = new ArrayAdapter( requireContext(), R.layout.list_item, item_RR );
        KnownCloseCnt.setAdapter( adapterHC );
        // Yes/ No Spinner
        CongrSetting = v.findViewById( R.id.resReqTypeAuto );
        ArrayAdapter<String> adapterYn = new ArrayAdapter( requireContext(), R.layout.list_item, item_YN );
        //Yesno.setTokenizer( new MultiAutoCompleteTextView.CommaTokenizer() );
        CongrSetting.setAdapter( adapterYn );
        return v;

    }

}