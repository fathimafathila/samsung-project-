package com.example.covidwatch.UsersView.InitialInterview.Location;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidwatch.R;
import com.example.covidwatch.UsersView.InitialInterview.Location.ContactRecycle.ContactModel;
import com.example.covidwatch.UsersView.InitialInterview.Location.LocationRecycle.LocationModel;
import com.example.covidwatch.UsersView.InitialInterview.Location.LocationRecycle.locationAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class AddLocationFragment extends Fragment {


    RecyclerView rcv ;
    locationAdapter adapter;
    ArrayList<LocationModel> locationList ;

    FirebaseAuth fAuth;
    FirebaseFirestore db ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_location, container, false);

        rcv = (RecyclerView) view.findViewById(R.id.recLocation);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        locationList = new ArrayList<LocationModel>();
        adapter = new locationAdapter(locationList,getContext());

        SharedPreferences sh = this.getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String id = sh.getString("uuid","");

        db.collection("users").document(id).collection("Location")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        for(DocumentChange dc : value.getDocumentChanges()){
                            LocationModel location = new LocationModel();
                            location.setLocationName(dc.getDocument().getString("Street"));
                            location.setVisitDate(dc.getDocument().getString("City"));
                            location.setUuid(dc.getDocument().getId());
                            locationList.add(location);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
        rcv.setAdapter(adapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            LocationModel deletedCourse = locationList.get(viewHolder.getAdapterPosition());

            int position = viewHolder.getAdapterPosition();
            locationList.remove(viewHolder.getAdapterPosition());
            adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            db.collection("users").document(id).collection("Location").document(deletedCourse.getUuid()).delete();
//            Snackbar.make(rcv, deletedCourse.getLocationName(), Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    locationList.add(position, deletedCourse);
//                    adapter.notifyItemInserted(position);
//                }
//            }).show();
        }
    }).attachToRecyclerView(rcv);
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //UI reference of textView
        final Spinner spinnerContactType = view.findViewById(R.id.spinnerLocationType);

        //Create adapter

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.arrLocationType, R.layout.list_item);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerContactType.setAdapter(adapter);
        String str = spinnerContactType.getSelectedItem().toString();
       System.out.print("Stringssss" + str);


        Button btnAddContact = view.findViewById(R.id.btnAddLocation);

        btnAddContact.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getActivity(), AddIsolationLocationactivity.class);
                intent.putExtra("uuid","");
                startActivity(intent);

            }
        });
    }



}