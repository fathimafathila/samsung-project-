package com.example.covidwatch.UsersView.InitialInterview.TestingTreatment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import com.example.covidwatch.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText edtSpecimendate ,edtReportdate;
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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TestingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TestingFragment newInstance(String param1, String param2) {
        TestingFragment fragment = new TestingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        edtReportdate.setFocusableInTouchMode(false);

        // Covid Testing Site Spinner
        testingSite = v.findViewById( R.id.siteInfo );
        ArrayAdapter<String> adapterTs = new ArrayAdapter( requireContext(), R.layout.list_item, item_Ts );
        testingSite.setAdapter( adapterTs );
        db.collection("users").document(fAuth.getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String specimen = documentSnapshot.getString("Specimen Date");
                String result = documentSnapshot.getString("Test Report Date");

                edtSpecimendate.setText(specimen);
                edtReportdate.setText(result);
            }
        });

        return  v;
    }
}