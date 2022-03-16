package com.example.covidwatch.UsersView.InitialInterview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.covidwatch.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestingInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestingInformationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TestingInformationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestingInformation.
     */
    // TODO: Rename and change types and number of parameters
    public static TestingInformationFragment newInstance(String param1, String param2) {
        TestingInformationFragment fragment = new TestingInformationFragment();
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
        return inflater.inflate(R.layout.fragment_testing_information, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //UI reference of textView
        final AutoCompleteTextView autoTestingSite = view.findViewById(R.id.autoTestingSite);

        //Create lists
        ArrayList<String> List1 = getTestingSites();

        //Create adapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, List1);

        //Set adapter
        autoTestingSite.setAdapter(adapter1);
    }

    private ArrayList<String> getTestingSites()
    {
        ArrayList<String> sites = new ArrayList<>();
        sites.add("Toronto");
        sites.add("Mississauga");
        sites.add("Hamilton");
        sites.add("Milton");
        return sites;
    }
}