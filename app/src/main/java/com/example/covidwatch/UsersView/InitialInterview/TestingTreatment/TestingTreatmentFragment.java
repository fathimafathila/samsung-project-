package com.example.covidwatch.UsersView.InitialInterview.TestingTreatment ;

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
import com.example.covidwatch.UsersView.InitialInterview.MonitoringResourceRequest.MonitoringFragment ;
import com.example.covidwatch.UsersView.InitialInterview.MonitoringResourceRequest.ResourceRequestFragment ;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class TestingTreatmentFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_testing_treatment, container, false);

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation_testing);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new TestingFragment()).commit();
        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedItem = null;

            switch (item.getItemId()){
                case R.id.nav_testing:
                    selectedItem = new TestingFragment();
                    break;
                case R.id.nav_treatment:
                    selectedItem = new TreatmentFragment();
                    break;
            }
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedItem).commit();
            return true;
        }
    };




}