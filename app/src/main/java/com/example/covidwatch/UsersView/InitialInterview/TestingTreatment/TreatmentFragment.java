package com.example.covidwatch.UsersView.InitialInterview.TestingTreatment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
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

public class TreatmentFragment extends Fragment {

    FirebaseAuth fAuth;
    FirebaseFirestore db;
    EditText medicalCare, receiveTreatment, hospitalized, admissionDate, dischargeDate, hospitalName, treatmentDesc ;
    AutoCompleteTextView edtSoughtMedicalCare;
    AutoCompleteTextView edtHospitalization;
    MultiAutoCompleteTextView edtReceivedMedicalCare;
    Button save ;
    final static String[] item_YN = new String[]{"Yes", "No"};
    final static String[] item_Mc = new String[]{"Monoclonal antibody infusion",
            "Convalescent plasma",
            "Remdesivir","--None--","Other"
    };
    public TreatmentFragment() {
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
        View v = inflater.inflate(R.layout.fragment_treatment, container, false);
        medicalCare = v.findViewById(R.id.edtSoughtMedicalCare);
        receiveTreatment = v.findViewById(R.id.edtReceivedMedicalCare);
        hospitalized = v.findViewById(R.id.edtHospitalization);
        admissionDate = v.findViewById(R.id.edtAdmissionDate);
        dischargeDate = v.findViewById(R.id.edtDischargeDate);
        hospitalName = v.findViewById(R.id.edtHospitalName);
        treatmentDesc = v.findViewById(R.id.edtDescTreatmentReceived);
        save = v.findViewById(R.id.saveButton);
        edtSoughtMedicalCare = v.findViewById( R.id.edtSoughtMedicalCare );
        ArrayAdapter<String> adapterYn = new ArrayAdapter( requireContext(), R.layout.list_item, item_YN );
        //Yesno.setTokenizer( new MultiAutoCompleteTextView.CommaTokenizer() );
        edtSoughtMedicalCare.setAdapter( adapterYn );

        edtReceivedMedicalCare = v.findViewById( R.id.edtReceivedMedicalCare );
        ArrayAdapter<String> adapter_Mc = new ArrayAdapter( requireContext(), R.layout.list_item, item_Mc );
        edtReceivedMedicalCare.setTokenizer( new MultiAutoCompleteTextView.CommaTokenizer() );
        edtReceivedMedicalCare.setAdapter( adapter_Mc );

        edtHospitalization = v.findViewById( R.id.edtHospitalization );
        ArrayAdapter<String> adapter_Yn = new ArrayAdapter( requireContext(), R.layout.list_item, item_YN );
        //Yesno.setTokenizer( new MultiAutoCompleteTextView.CommaTokenizer() );
        edtHospitalization.setAdapter( adapter_Yn );

        SharedPreferences sh = this.getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String uuid = sh.getString("uuid","");

        db.collection("users").document(uuid).collection("Treatment")
                .document(uuid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                medicalCare.setText(documentSnapshot.getString("Medical Care"));
                receiveTreatment.setText(documentSnapshot.getString("Received Treatment"));
                hospitalized.setText(documentSnapshot.getString("Hospitalized"));
                admissionDate.setText(documentSnapshot.getString("Admission Date"));
                dischargeDate.setText(documentSnapshot.getString("Discharge Date"));
                hospitalName.setText(documentSnapshot.getString("Hospital Name"));
                treatmentDesc.setText(documentSnapshot.getString("Treatment Description"));

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<Object, String > treatment = new HashMap<>();
                treatment.put("Medical Care", medicalCare.getText().toString());
                treatment.put("Received Treatment", receiveTreatment.getText().toString());
                treatment.put("Hospitalized", hospitalized.getText().toString());
                treatment.put("Admission Date", admissionDate.getText().toString());
                treatment.put("Discharge Date", dischargeDate.getText().toString());
                treatment.put("Hospital Name", hospitalName.getText().toString());
                treatment.put("Treatment Description", treatmentDesc.getText().toString());

                db.collection("users").document(uuid).collection("Treatment").document(uuid)
                        .set(treatment).addOnSuccessListener(new OnSuccessListener<Void>() {
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

        ImageButton calAdmissionDate = view.findViewById(R.id.calAdmissionDate);
        ImageButton calDischargeDate = view.findViewById(R.id.calDischargeDate);

        calAdmissionDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                DialogFragment newFragment = new SelectDateFragment(admissionDate);
                newFragment.show(getFragmentManager(), "AdmissionDate");

            }
        });

        calDischargeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                DialogFragment newFragment = new SelectDateFragment(dischargeDate);
                newFragment.show(getFragmentManager(), "DischargeDate");

            }
        });
    }
}