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
import com.example.covidwatch.UsersView.InitialInterview.Demographic.VaccineRecyclerView.VaccineModel;
import com.example.covidwatch.UsersView.InitialInterview.Location.ContactRecycle.ContactModel;
import com.example.covidwatch.UsersView.InitialInterview.Location.ContactRecycle.contactAdapter;
import com.example.covidwatch.UsersView.InitialInterview.Location.LocationRecycle.LocationModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class CloseContactFragment extends Fragment {

    RecyclerView rcv ;
    contactAdapter adapter;
    ArrayList<ContactModel> contactList ;

    FirebaseAuth fAuth;
    FirebaseFirestore db;
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
        View view = inflater.inflate(R.layout.fragment_close_contact, container, false);

        rcv = (RecyclerView) view.findViewById(R.id.recCloseContact);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        contactList =  new ArrayList<ContactModel>();
        adapter = new contactAdapter(contactList,getContext());
        SharedPreferences sh = this.getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String id = sh.getString("uuid","");

        db.collection("users").document(id).collection("Contact")
          .addSnapshotListener(new EventListener<QuerySnapshot>() {
              @Override
              public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                     for(DocumentChange dc : value.getDocumentChanges()){
                         ContactModel contact = new ContactModel();
                         contact.setContactName(dc.getDocument().getString("First Name"));
                         contact.setContactDate(dc.getDocument().getString("Email"));
                         contact.setUuid(dc.getDocument().getId());
                         contactList.add(contact);
                     }
                     adapter.notifyDataSetChanged();
              }
          });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                ContactModel deletedCourse = contactList.get(viewHolder.getAdapterPosition());

                int position = viewHolder.getAdapterPosition();
                contactList.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                db.collection("users").document(id).collection("Contact").document(deletedCourse.getUuid()).delete();
            }
        }).attachToRecyclerView(rcv);
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
                R.array.arrContactType, R.layout.list_item);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerContactType.setAdapter(adapter);

        Button btnAddContact = view.findViewById(R.id.btnAddContact);
        btnAddContact.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getActivity(), CloseContactDetailsActivity.class);
                intent.putExtra("uuid","");
                startActivity(intent);

            }
        });
    }

//    public ArrayList<ContactModel> dataQueue() {
//        ArrayList<ContactModel> holder = new ArrayList<>();
//
//        ContactModel ob1 = new ContactModel();
//        ob1.setContactName("Shrinal Patel");
//        ob1.setContactDate("03/23/2022");
//        holder.add(ob1);
//
//        ContactModel ob2 = new ContactModel();
//        ob2.setContactName("Fathima Fathila");
//        ob2.setContactDate("03/22/2022");
//        holder.add(ob2);
//
//        return  holder;
//    }
}