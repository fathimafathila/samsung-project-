package com.example.covidwatch.AdminView ;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.covidwatch.AdminView.ResourceRequest.ResourceModel;
import com.example.covidwatch.AdminView.ResourceRequest.resourceAdapter;
import com.example.covidwatch.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class EscalatedCaseActivity extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseFirestore db;
    RecyclerView rcv ;
    resourceAdapter adapter;
    ArrayList<ResourceModel> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escalated_case);

        getSupportActionBar().hide();
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        rcv = (RecyclerView) findViewById(R.id.rcvResource);
        rcv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        userArrayList=new ArrayList<ResourceModel>();
        adapter = new resourceAdapter(userArrayList,getApplicationContext());

        db.collection("users")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            ResourceModel ob1 = new ResourceModel();
                            ob1.setIdResource(dc.getDocument().getString("ID"));
                            ob1.setNameResource(dc.getDocument().getString("First Name"));
                            ob1.setEmailResource(dc.getDocument().getString("Email"));
                            ob1.setUuid(dc.getDocument().getString("uuid"));
                            userArrayList.add(ob1);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

        rcv.setAdapter(adapter);
    }
}