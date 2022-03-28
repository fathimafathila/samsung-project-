package com.example.covidwatch.UsersView.InitialInterview.Location;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.covidwatch.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class LocationCloseContactFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_location_close_contact, container, false);

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation_location);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AddLocationFragment()).commit();
        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedItem = null;

            switch (item.getItemId()){
                case R.id.nav_location:
                    selectedItem = new AddLocationFragment();
                    break;
                case R.id.nav_Contact:
                    selectedItem = new CloseContactFragment();
                    break;
            }
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedItem).commit();
            return true;
        }
    };

}