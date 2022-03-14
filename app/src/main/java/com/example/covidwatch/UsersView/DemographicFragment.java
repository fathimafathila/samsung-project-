package com.example.covidwatch.UsersView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.covidwatch.AdminView.CreateUser.AddRecord2Activity;
import com.example.covidwatch.AdminView.CreateUser.AddRecordActivity;
import com.example.covidwatch.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DemographicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DemographicFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DemographicFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DemographicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DemographicFragment newInstance(String param1, String param2) {
        DemographicFragment fragment = new DemographicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_demographic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //UI reference of textView
        final AutoCompleteTextView autoMinor = view.findViewById(R.id.autoMinor);
        final AutoCompleteTextView autoConsent = view.findViewById(R.id.autoConsent);
        final AutoCompleteTextView autoGender = view.findViewById(R.id.autoGender);
        final AutoCompleteTextView autoDeceased = view.findViewById(R.id.autoDeceased);

        //Create lists
        ArrayList<String> List1 = getYesNoList();
        ArrayList<String> List2 = getGendersList();

        //Create adapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, List1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, List2);

        //Set adapter
        autoMinor.setAdapter(adapter1);
        autoConsent.setAdapter(adapter1);
        autoGender.setAdapter(adapter2);
        autoDeceased.setAdapter(adapter1);
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
    private ArrayList<String> getYesNoList()
    {
        ArrayList<String> minor = new ArrayList<>();
        minor.add("Yes");
        minor.add("No");
        return minor;
    }

}