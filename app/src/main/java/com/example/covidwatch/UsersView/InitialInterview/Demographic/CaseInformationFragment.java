package com.example.covidwatch.UsersView.InitialInterview.Demographic;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.covidwatch.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.zip.ZipEntry;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CaseInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class CaseInformationFragment extends Fragment {

    FirebaseAuth fAuth ;
    FirebaseFirestore db ;
    TextView name,id ,  address ,  number , dob , age, gender, email, openDate,  status , priority ;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    String mParam1, mParam2;
    ImageButton calDeceasedDate;

    final Calendar calendar = Calendar.getInstance();
    int yy = calendar.get(Calendar.YEAR);
    int mm = calendar.get(Calendar.MONTH);
    int dd = calendar.get(Calendar.DAY_OF_MONTH);

    public CaseInformationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CaseInformationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CaseInformationFragment newInstance(String param1, String param2) {
        CaseInformationFragment fragment = new CaseInformationFragment();
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
        // Inflate the layout for this fragment
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        View view = inflater.inflate(R.layout.fragment_case_information, container, false);

        name = view.findViewById(R.id.txtFullName);
        id = view.findViewById(R.id.txtUserId);
        address = view.findViewById(R.id.txtAddress);
        number =view.findViewById(R.id.txtContact);
        dob = view.findViewById(R.id.txtDob);
        age = view.findViewById(R.id.txtAge);
        gender = view.findViewById(R.id.txtGender);
        email = view.findViewById(R.id.txtEmail);
        openDate = view.findViewById(R.id.txtStartDate);
        status = view.findViewById(R.id.txtInvestigationStatusV);
        priority = view.findViewById(R.id.txtPriorityV);

        db.collection("users").document(fAuth.getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String firstName = documentSnapshot.getString("First Name");
                String lastName = documentSnapshot.getString("Last Name");
                String id1 = documentSnapshot.getString("ID");
                String str1  = documentSnapshot.getString("Street1");
                String str2  = documentSnapshot.getString("Street2");
                String City  = documentSnapshot.getString("City");
                String state  = documentSnapshot.getString("State");
                String Country  = documentSnapshot.getString("Country");
                String zipCode  = documentSnapshot.getString("Zip Code");
                String number1 = documentSnapshot.getString("Contact Number");
                String dob1 = documentSnapshot.getString("DOB");
                String age1 = documentSnapshot.getString("Age");
                String gender1 = documentSnapshot.getString("Gender");
                String email1 = documentSnapshot.getString("email");
                String openDate1 = documentSnapshot.getString("Open Date");
                String status1 = documentSnapshot.getString("Investigation Status");
                String priority1 = documentSnapshot.getString("Priority");

                name.setText( firstName + " " + lastName);
                id.setText(id1);
                address.setText(str1 + "," + str2 + "," + City + "," + state +"," + Country + "," + zipCode);
                number.setText(number1);
                dob.setText(dob1);
                age.setText(age1);
                gender.setText(gender1);
                email.setText(email1);
                openDate.setText(openDate1);
                status.setText(status1);
                priority.setText(priority1);


            }
        });


        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        //UI reference of textView
        final AutoCompleteTextView autoConsent = view.findViewById(R.id.autoConsent);
        final AutoCompleteTextView autoReinfected = view.findViewById(R.id.autoReinfected);
        final AutoCompleteTextView autoDeceased = view.findViewById(R.id.autoDeceased);
        final AutoCompleteTextView autoRace = view.findViewById(R.id.autoRace);
        final AutoCompleteTextView autoPrimaryLanguage = view.findViewById(R.id.autoPrimaryLanguage);

        //Create adapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(), R.layout.list_item, getResources().getStringArray(R.array.arrYesNo));
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(), R.layout.list_item, getResources().getStringArray(R.array.arrRace));
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getActivity(), R.layout.list_item, getResources().getStringArray(R.array.arrPrimaryLanguage));

        //Set adapter
        autoConsent.setAdapter(adapter1);
        autoReinfected.setAdapter(adapter1);
        autoDeceased.setAdapter(adapter1);
        autoRace.setAdapter(adapter2);
        autoPrimaryLanguage.setAdapter(adapter3);

        calDeceasedDate = view.findViewById(R.id.calDeceasedDate);

        // On clicking date picker

        calDeceasedDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getFragmentManager(), "DeceasedDate");

            }
        });

        // On clicking card view

        CardView card = view.findViewById(R.id.card);

        card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getActivity(), CaseInformationCardActivity.class);
                startActivity(intent);

            }
        });
    }
}