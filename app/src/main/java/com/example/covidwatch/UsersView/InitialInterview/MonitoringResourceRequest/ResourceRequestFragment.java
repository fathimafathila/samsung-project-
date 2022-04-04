package com.example.covidwatch.UsersView.InitialInterview.MonitoringResourceRequest ;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.covidwatch.R;
import com.example.covidwatch.UsersView.InitialInterview.Demographic.SelectDateFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;


public class ResourceRequestFragment extends Fragment {


    FirebaseAuth fAuth;
    FirebaseFirestore db;
    ImageButton calDeceasedDate;
    //MultiAutoCompleteTextView requestType;

    EditText  requestDate, comments, monitoringType ;
    CheckBox urgent ;
    Button save;
    AutoCompleteTextView Agreeconsent;
    final static String[] item_YN = new String[]{"Yes", "No"};

    MultiAutoCompleteTextView requestType;
    final static String[] item_RR = new String[]{
            "-- None --",
            "Activities of daily living",
            "Child care/elder care",
            "Vaccine Related",
            "Medical",
            "Income Assistance",
            "Food",
            "Personal Safety Concern",
            "Emotional/Mental Health",
            "Household item",
            "Other"};




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate( R.layout.fragment_resource_request, container, false );
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        Agreeconsent =  v.findViewById( R.id.edtagreeconsent );
        ArrayAdapter<String> adapterYN = new ArrayAdapter( requireContext(), R.layout.list_item, item_RR );
        Agreeconsent.setAdapter( adapterYN );
try {
    // Health Condition Spinner
    requestType = (MultiAutoCompleteTextView) v.findViewById( R.id.edtRequestType );
    ArrayAdapter<String> adapterHC = new ArrayAdapter( requireContext(), R.layout.list_item, item_RR );
    requestType.setAdapter( adapterHC );
} catch (Exception e){
    e.printStackTrace();
}

        requestDate = v.findViewById(R.id.edtRequestDate);
        comments = v.findViewById(R.id.edtComments);
        monitoringType = v.findViewById(R.id.edtMonitoringType);
        urgent = v.findViewById(R.id.checkBoxUrgent);
        save = v.findViewById(R.id.saveButton);

        SharedPreferences sh = this.getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String uuid = sh.getString("uuid","");

        db.collection("users").document(uuid).collection("Resource Request")
                .document(uuid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                requestType.setText(documentSnapshot.getString("Resource Type"));
                requestDate.setText(documentSnapshot.getString("Request Date"));
                comments.setText(documentSnapshot.getString("Comments"));
                monitoringType.setText(documentSnapshot.getString("Monitoring Type"));
                if(documentSnapshot.getString("Urgent").equals("True")){
                    urgent.setChecked(true);
                }else{
                    urgent.setChecked(false);
                }
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<Object,String> resource = new HashMap<>();
                resource.put("Resource Type",requestType.getText().toString());
                resource.put("Request Date",requestDate.getText().toString());
                resource.put("Comments",comments.getText().toString());
                resource.put("Monitoring Type",monitoringType.getText().toString());

                if(urgent.isChecked()){
                    resource.put("Urgent","True");
                }else{
                    resource.put("Urgent","False");
                }

                db.collection("users").document(uuid).collection("Resource Request").document(uuid)
                        .set(resource).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "working", Toast.LENGTH_SHORT).show();
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