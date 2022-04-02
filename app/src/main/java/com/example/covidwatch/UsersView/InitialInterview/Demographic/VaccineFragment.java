package com.example.covidwatch.UsersView.InitialInterview.Demographic;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidwatch.R;
import com.example.covidwatch.UsersView.InitialInterview.Demographic.VaccineRecyclerView.VaccineModel;
import com.example.covidwatch.UsersView.InitialInterview.Demographic.VaccineRecyclerView.vaccineAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class VaccineFragment extends Fragment {


    FirebaseAuth fAuth;
    FirebaseFirestore db;
    RecyclerView rcv ;
    vaccineAdapter adapter;
    ArrayList<VaccineModel> userArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        View view = inflater.inflate(R.layout.fragment_vaccine, container, false);

        rcv = (RecyclerView) view.findViewById(R.id.recVaccine);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        // rcv.setAdapter(adapter);
        userArrayList=new ArrayList<VaccineModel>();
        adapter = new vaccineAdapter(userArrayList,getContext());

        SharedPreferences sh = this.getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String uuid = sh.getString("uuid","");

        db.collection("users").document(uuid).collection("Vaccine")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            VaccineModel ob1 = new VaccineModel();
                            ob1.setDose(dc.getDocument().getId());
                            ob1.setName(dc.getDocument().getString("Name"));
                            ob1.setDate(dc.getDocument().getString("Date"));
                            ob1.setLot(dc.getDocument().getString("Lot No"));
                            ob1.setProvided(dc.getDocument().getString("Provided"));
                            userArrayList.add(ob1);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

        rcv.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnUpload = view.findViewById(R.id.btnUpload);

        // On clicking date picker
        btnUpload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                TextView textFileName = view.findViewById(R.id.txtFileName);
                textFileName.setText("COVID-19 Vaccine Dose-2.pdf");
            }
        });
    }

}