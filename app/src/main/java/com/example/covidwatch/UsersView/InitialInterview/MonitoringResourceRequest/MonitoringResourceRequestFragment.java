package com.example.covidwatch.UsersView.InitialInterview.MonitoringResourceRequest ;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.covidwatch.R;
import com.example.covidwatch.UsersView.InitialInterview.Demographic.CaseInformationFragment;
import com.example.covidwatch.UsersView.InitialInterview.Demographic.VaccineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MonitoringResourceRequestFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_monitoring_resource_request, container, false);

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation_monitoring);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MonitoringFragment()).commit();
        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedItem = null;

            switch (item.getItemId()){
                case R.id.nav_monitoring:
                    selectedItem = new MonitoringFragment();
                    break;
                case R.id.nav_request:
                    selectedItem = new ResourceRequestFragment();
                    break;
            }
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedItem).commit();
            return true;
        }
    };
}