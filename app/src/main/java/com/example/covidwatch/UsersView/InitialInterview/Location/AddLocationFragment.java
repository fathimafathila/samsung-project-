package com.example.covidwatch.UsersView.InitialInterview.Location;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covidwatch.R;
import com.example.covidwatch.UsersView.InitialInterview.Location.ContactRecycle.ContactModel;
import com.example.covidwatch.UsersView.InitialInterview.Location.ContactRecycle.contactAdapter;
import com.example.covidwatch.UsersView.InitialInterview.Location.LocationRecycle.LocationModel;
import com.example.covidwatch.UsersView.InitialInterview.Location.LocationRecycle.locationAdapter;

import java.util.ArrayList;


public class AddLocationFragment extends Fragment {

    RecyclerView rcv ;
    locationAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_location, container, false);

        rcv = (RecyclerView) view.findViewById(R.id.recLocation);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));


        adapter = new locationAdapter(dataQueue(),getContext());
        rcv.setAdapter(adapter);

        return view;
    }

    public ArrayList<LocationModel> dataQueue() {
        ArrayList<LocationModel> holder = new ArrayList<>();

        LocationModel ob1 = new LocationModel();
        ob1.setHeader("760 Mohawk road west");
        ob1.setDesc("03/23/2022");
        holder.add(ob1);

        LocationModel ob2 = new LocationModel();
        ob2.setHeader("Toronto Pearson Airport");
        ob2.setDesc("03/22/2022");
        holder.add(ob2);

        return  holder;
    }
}