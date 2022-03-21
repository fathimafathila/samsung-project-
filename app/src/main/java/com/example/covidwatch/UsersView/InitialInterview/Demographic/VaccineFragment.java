package com.example.covidwatch.UsersView.InitialInterview.Demographic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covidwatch.R;
import com.example.covidwatch.UsersView.InitialInterview.Demographic.VaccineRecyclerView.VaccineModel;
import com.example.covidwatch.UsersView.InitialInterview.Demographic.VaccineRecyclerView.vaccineAdapter;
import com.example.covidwatch.UsersView.InitialInterview.Location.LocationRecycle.LocationModel;
import com.example.covidwatch.UsersView.InitialInterview.Location.LocationRecycle.locationAdapter;

import java.util.ArrayList;


public class VaccineFragment extends Fragment {


    RecyclerView rcv ;
    vaccineAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vaccine, container, false);

        rcv = (RecyclerView) view.findViewById(R.id.recVaccine);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));


        adapter = new vaccineAdapter(dataQueue(),getContext());
        rcv.setAdapter(adapter);

        return view;
    }

    public ArrayList<VaccineModel> dataQueue() {
        ArrayList<VaccineModel> holder = new ArrayList<>();

        VaccineModel ob1 = new VaccineModel();
        ob1.setHeader("Moderna covid-19 mRNA -1273 ");
        ob1.setDesc("01/10/2022");
        ob1.setLot("0946737TF");
        ob1.setDose("Dose 1");
        holder.add(ob1);

        VaccineModel ob2 = new VaccineModel();
        ob2.setHeader("Moderna covid-19 mRNA -1273 ");
        ob2.setDesc("05/30/2022");
        ob2.setLot("0946737TF");
        ob2.setDose("Dose 2");
        holder.add(ob2);

        return  holder;
    }
}