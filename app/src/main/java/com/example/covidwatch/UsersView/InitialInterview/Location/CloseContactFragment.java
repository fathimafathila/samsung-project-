package com.example.covidwatch.UsersView.InitialInterview.Location;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidwatch.R;
import com.example.covidwatch.UsersView.InitialInterview.Location.ContactRecycle.ContactModel;
import com.example.covidwatch.UsersView.InitialInterview.Location.ContactRecycle.contactAdapter;

import java.util.ArrayList;


public class CloseContactFragment extends Fragment {

    RecyclerView rcv ;
    contactAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_close_contact, container, false);

        rcv = (RecyclerView) view.findViewById(R.id.recCloseContact);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));


        adapter = new contactAdapter(dataQueue(),getContext());
        rcv.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //UI reference of textView
        final Spinner spinnerContactType = view.findViewById(R.id.spinnerContactType);

        //Create adapter

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.arrContactType, R.layout.spinner_item);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerContactType.setAdapter(adapter);

        Button btnAddContact = view.findViewById(R.id.btnAddContact);

        btnAddContact.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getActivity(), CloseContactDetailsActivity.class);
                startActivity(intent);

            }
        });
    }

    public ArrayList<ContactModel> dataQueue() {
        ArrayList<ContactModel> holder = new ArrayList<>();

        ContactModel ob1 = new ContactModel();
        ob1.setHeader("Shrinal Patel");
        ob1.setDesc("03/23/2022");
        holder.add(ob1);

        ContactModel ob2 = new ContactModel();
        ob2.setHeader("Fathima Fathila");
        ob2.setDesc("03/22/2022");
        holder.add(ob2);

        return  holder;
    }
}