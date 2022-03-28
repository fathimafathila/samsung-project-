package com.example.covidwatch.UsersView.InitialInterview.Demographic ;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.covidwatch.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class DemographicFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_demographic, container, false);

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation_demographic);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CaseInformationFragment()).commit();
        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedItem = null;

            switch (item.getItemId()){
                case R.id.nav_case_information:
                    selectedItem = new CaseInformationFragment();
                    break;
                case R.id.nav_Vaccine:
                    selectedItem = new VaccineFragment();
                    break;
            }
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedItem).commit();
            return true;
        }
    };

}