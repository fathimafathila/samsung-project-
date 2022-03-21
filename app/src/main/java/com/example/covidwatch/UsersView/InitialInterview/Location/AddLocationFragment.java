package com.example.covidwatch.UsersView.InitialInterview.Location;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //UI reference of textView
        final Spinner spinnerContactType = view.findViewById(R.id.spinnerLocationType);

        //Create adapter

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.arrContactType, R.layout.list_item);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerContactType.setAdapter(adapter);
        String str = spinnerContactType.getSelectedItem().toString();
       System.out.print("Stringssss" + str);


        Button btnAddContact = view.findViewById(R.id.btnAddLocation);

        btnAddContact.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getActivity(), AddIsolationLocationactivity.class);
                startActivity(intent);

            }
        });
    }
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
//    }
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) { }

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