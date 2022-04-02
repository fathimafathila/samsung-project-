package com.example.covidwatch.UsersView.InitialInterview.MonitoringResourceRequest ;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.covidwatch.DateCalculation;
import com.example.covidwatch.R;
import com.example.covidwatch.UsersView.InitialInterview.Demographic.SelectDateFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;


public class MonitoringFragment extends Fragment {

    FirebaseAuth fAuth;
    FirebaseFirestore db;
    EditText startDate, endDate, monitoringDay, monitoringType, contactNumber, email;
    Button save;
    ImageButton calDeceasedDate;

    AutoCompleteTextView monitorType;
    final static String[] item_RR = new String[]{
            "Phone Call",
            "Email",
            "Text Message",
            "No Monitoring"};


    public MonitoringFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    View v1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate( R.layout.fragment_monitoring, container, false );
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        startDate = v.findViewById(R.id.edtStartDate);
        endDate = v.findViewById(R.id.edtEndDate);
        monitoringDay = v.findViewById(R.id.edtMonitoringDay);
        monitoringType = v.findViewById(R.id.edtMonitoringType);
        contactNumber = v.findViewById(R.id.edtContactNumber);
        email = v.findViewById(R.id.edtEmail);
        save = v.findViewById(R.id.saveButton);
        v1 = v;

        // Health Condition Spinner
        monitorType = v.findViewById( R.id.edtMonitoringType);
        ArrayAdapter<String> adapterHC = new ArrayAdapter( requireContext(), R.layout.list_item, item_RR );
        monitorType.setAdapter( adapterHC );

        SharedPreferences sh = this.getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String uuid = sh.getString("uuid","");

        db.collection("users").document(uuid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String firstDate = documentSnapshot.getString("Open Date");
                DateCalculation dateCalculation = new DateCalculation();
                endDate.setText(dateCalculation.findEndDate(firstDate));
                monitoringDay.setText(String.valueOf(dateCalculation.findDifference(dateCalculation.findEndDate(firstDate))));
                startDate.setText(firstDate);
            }
        });
        db.collection("users").document(uuid).collection("Monitoring").document(uuid)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                monitoringType.setText(documentSnapshot.getString("Monitoring Type"));
                contactNumber.setText(documentSnapshot.getString("Contact Number"));
                email.setText(documentSnapshot.getString("Email"));
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<Object, String> monitor = new HashMap<>();
//                monitor.put("Start Date", startDate.getText().toString());
//                monitor.put("End Date",endDate.getText().toString());
//                monitor.put("Remaining Days", monitoringDay.getText().toString());
                monitor.put("Monitoring Type", monitoringType.getText().toString());
                monitor.put("Contact Number", contactNumber.getText().toString());
                monitor.put("Email",email.getText().toString());

                db.collection("users").document(uuid).collection("Monitoring").document(uuid)
                        .set(monitor).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "Working", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        calDeceasedDate = view.findViewById(R.id.calDeceasedDate);

        calDeceasedDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getFragmentManager(), "DatePicker");

            }
        });
    }

}