package com.example.covidwatch.UsersView.InitialInterview;

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

import androidx.fragment.app.Fragment;

import com.example.covidwatch.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;


public class EpilinkageCongregateFragment extends Fragment {

    FirebaseAuth fAuth;
    FirebaseFirestore db;
    EditText closeContact, closeContactAddInfo, congregateSetting, schoolName, additionalInformation ;
    Button save;
    ImageButton calDeceasedDate;
    EditText commentsAuto;

    AutoCompleteTextView KnownCloseCnt;
    AutoCompleteTextView CongrSetting;
    final static String[] item_YN = new String[]{"Yes", "No"};
    final static String[] item_RR = new String[]{
            "--None--",
            "Alcohol/Drug Treatment Facility",
            "Assisted/Supported Living Facility",
            "College - Unk Attendance Type",
            "College - 100% In-Person",
            "College - Partial Remote",
            "College - 100% Remote",
            "Correction Facility or Jail",
            "Day Care Center",
            "Day/Overnight Camp",
            "Developmental Disability Facility",
            "Group Home",
            "Homeless Shelter",
            "Independent/Senior Living Facility",
            "Long-term/Skilled Care Facility",
            "Mental Health Facility",
            "Military Facility",
            "Preschool - Unk Attendance Type",
            "Preschool - 100% In-Person",
            "Preschool - Partial Remote",
            "Preschool - 100% Remote",
            " School K-12 - Unk Attendance Type",
            "School K-12 - 100% In-Person",
            "School K-12 - Partial Remote",
            "School K-12 - 100% Remote",
            " Other",
            " Not Applicable",
            "Unknown"
    };


    View v1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        View v = inflater.inflate( R.layout.fragment_epilinkage_congregate, container, false );
        closeContact = v.findViewById(R.id.edtCloseContact);
        closeContactAddInfo = v.findViewById(R.id.edtAdditionalInfoCongregate);
        congregateSetting = v.findViewById(R.id.edtCongregateSetting);
        schoolName = v.findViewById(R.id.edtMonitoringTypeAuto);
        additionalInformation = v.findViewById(R.id.edtAdditionalInfo);
        save = v.findViewById(R.id.saveButton);
        v1 = v;
        // Health Condition Spinner
        KnownCloseCnt = v.findViewById( R.id.edtCongregateSetting);
        ArrayAdapter<String> adapterHC = new ArrayAdapter( requireContext(), R.layout.list_item, item_RR );
        KnownCloseCnt.setAdapter( adapterHC );
        // Yes/ No Spinner
        CongrSetting = v.findViewById( R.id.edtCloseContact );
        ArrayAdapter<String> adapterYn = new ArrayAdapter( requireContext(), R.layout.list_item, item_YN );
        //Yesno.setTokenizer( new MultiAutoCompleteTextView.CommaTokenizer() );
        CongrSetting.setAdapter( adapterYn );

        db.collection("users").document(fAuth.getCurrentUser().getUid()).collection("Epilinkage Page")
                .document(fAuth.getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                closeContact.setText(documentSnapshot.getString("Covid Close Contact"));
                closeContactAddInfo.setText(documentSnapshot.getString("Close Contact Additional Info"));
                congregateSetting.setText(documentSnapshot.getString("Congregate Setting"));
                schoolName.setText(documentSnapshot.getString("Congregate Name"));
                additionalInformation.setText(documentSnapshot.getString("Additional Info"));
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<Object,String> epilinkage = new HashMap<>();
                epilinkage.put("Covid Close Contact", closeContact.getText().toString());
                epilinkage.put("Close Contact Additional Info",closeContactAddInfo.getText().toString());
                epilinkage.put("Congregate Setting",congregateSetting.getText().toString());
                epilinkage.put("Congregate Name",schoolName.getText().toString());
                epilinkage.put("Additional Info",additionalInformation.getText().toString());

                db.collection("users").document(fAuth.getCurrentUser().getUid()).collection("Epilinkage Page").document(fAuth.getCurrentUser().getUid())
                        .set(epilinkage).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "working", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        return v;

    }

}