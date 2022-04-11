package com.example.covidwatch.UsersView.InitialInterview.MonitoringResourceRequest ;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
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
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.example.covidwatch.AdminView.CreateUser.JavaMailAPI;
import com.example.covidwatch.DateCalculation;
import com.example.covidwatch.R;
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
    ImageButton calEndDate;
    String fName;
    String mail;
    String specimenDate;

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


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel =new NotificationChannel("My Notification","My Notification",NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getContext().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
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
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                specimenDate = documentSnapshot.getString("Specimen Date");
                fName = documentSnapshot.getString("First Name");
                mail = documentSnapshot.getString("Email");
            }
        });


        db.collection("users").document(uuid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String firstDate = documentSnapshot.getString("Specimen Date");
                DateCalculation dateCalculation = new DateCalculation();
                endDate.setText(dateCalculation.findEndDate(firstDate));
                int remainingDay = dateCalculation.findDifference(dateCalculation.findEndDate(firstDate));

                startDate.setText(firstDate);



                if(remainingDay == 0 ){
                    JavaMailAPI javaMailAPI = new JavaMailAPI(getContext(), mail, specimenDate, fName, 1);
                    javaMailAPI.execute();
                    monitoringDay.setText(String.valueOf(remainingDay));
                }
                if(remainingDay > 0) {
                    monitoringDay.setText(String.valueOf(remainingDay));
//                    if(Calendar.getInstance().getTime().getHours() == 8 && Calendar.getInstance().getTime().getMinutes() == 0 )  {
                        Notification.Builder builder = new Notification.Builder(getContext(), "My Notification");
                        builder.setContentTitle("Covid Watch");


                        builder.setContentText("Greetings, Ridham! Click the Link to begin a new Health Assessment.Survey Link : https://forms.gle/uCBgZBnDdpxZUEqq6");

                        builder.setSmallIcon(R.drawable.appicon);
                        builder.setAutoCancel(true);
                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getContext());
                        managerCompat.notify(1, builder.build());
                   // }
                }else{
                    monitoringDay.setText("Days Completed");
                }


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
    }

}