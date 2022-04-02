package com.example.covidwatch.UsersView.InitialInterview.TestingTreatment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import com.example.covidwatch.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class TestingFragment extends Fragment {


    EditText edtSpecimendate ,edtReportdate, site, siteName ;
    Button save;
    AutoCompleteTextView testingSite;

    FirebaseAuth fAuth ;
    FirebaseFirestore db ;
    final static String[] item_Ts = new String[]{"--None--",

            "Can't remember",
            "Clinic or Outpatient Center",
            "Community Drive-thru Testing Site",
            "Community Walk-up Testing Site",
            "Doctor's office",
            "Emergency Department",
           " Hospital",
            "Pharmacy",
            "Prisons",
            "Processing Plants",
            "School",
            "Urgent Care Center",
            "Work",
            "Other"};



    public TestingFragment() {
        // Required empty public constructor
    }



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
        View v =  inflater.inflate(R.layout.fragment_testing, container, false);
        edtSpecimendate = v.findViewById( R.id.edtSpecimendate );
        edtSpecimendate.setFocusableInTouchMode(false);
        edtReportdate = v.findViewById( R.id.edtReportdate );
        site = v.findViewById(R.id.siteInfo);
        siteName = v.findViewById(R.id.edtName1);
        edtReportdate.setFocusableInTouchMode(false);
        save = v.findViewById(R.id.saveBtn);

        SharedPreferences sh = this.getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String uuid = sh.getString("uuid","");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<>();
                map.put("Site",site.getText().toString());
                map.put("siteName", siteName.getText().toString());

                db.collection("users").document(uuid).collection("Testing").document("test")
                        .set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                });
            }
        });
        // Covid Testing Site Spinner
        testingSite = v.findViewById( R.id.siteInfo );
        ArrayAdapter<String> adapterTs = new ArrayAdapter( requireContext(), R.layout.list_item, item_Ts );
        testingSite.setAdapter( adapterTs );
        db.collection("users").document(uuid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String specimen = documentSnapshot.getString("Specimen Date");
                String result = documentSnapshot.getString("Test Report Date");

                edtSpecimendate.setText(specimen);
                edtReportdate.setText(result);
            }
        });
        db.collection("users").document(uuid).collection("Testing").document("test")
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                site.setText(documentSnapshot.getString("Site"));
                siteName.setText(documentSnapshot.getString("siteName"));
            }
        });

        return  v;
    }
}