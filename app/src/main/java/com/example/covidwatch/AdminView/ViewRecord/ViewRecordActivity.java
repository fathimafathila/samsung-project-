package com.example.covidwatch.AdminView.ViewRecord;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.covidwatch.AdminView.ViewRecord.RecyclerView.viewModel;
import com.example.covidwatch.AdminView.ViewRecord.RecyclerView.myAdapter;
import com.example.covidwatch.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ViewRecordActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseFirestore db;
    RecyclerView rcv;
    myAdapter adapter;
    ArrayList<viewModel> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);

        getSupportActionBar().hide();
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        rcv = (RecyclerView) findViewById(R.id.rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        userArrayList = new ArrayList<viewModel>();
        adapter = new myAdapter(userArrayList, ViewRecordActivity.this);

        rcv.setAdapter(adapter);
        EditText caseNumber = findViewById(R.id.edtCase);


        caseNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                EditText caseNumber = findViewById(R.id.edtCase);
                db.collection("users")
                        .orderBy("ID")
                        .startAt(caseNumber.getText().toString()).endAt(caseNumber.getText().toString() + "\uf8ff")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                for (DocumentChange dc : value.getDocumentChanges()) {
                                    viewModel ob1 = new viewModel();
                                    ob1.setfName(dc.getDocument().getString("First Name"));
                                    ob1.setLname(dc.getDocument().getString("Last Name"));
                                    ob1.setId(dc.getDocument().getString("ID"));
                                    ob1.setNumber(dc.getDocument().getString("Phone Number"));
                                    ob1.setUuid(dc.getDocument().getString("uuid"));
                                    userArrayList.add(ob1);
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });
            }

            @Override
            public void afterTextChanged(Editable editable) {
                userArrayList.clear();
            }
        });

    }
}