package com.example.covidwatch.UsersView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covidwatch.R;
import com.example.covidwatch.UsersView.DailyHealth.DailyHealthActivity;
import com.example.covidwatch.UsersView.InitialInterview.InitialInterviewActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class UserDashboardActivity extends AppCompatActivity {

    TextView title ;
    FirebaseFirestore db ;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        title = findViewById(R.id.title);

        db.collection("users").document(fAuth.getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String fName = documentSnapshot.getString("First Name");
                String lname = documentSnapshot.getString("Last Name");

                // Greeting Message
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);

                Toast.makeText(UserDashboardActivity.this, String.valueOf(hour), Toast.LENGTH_SHORT).show();
                String greetingMsg = "";
                if (hour >= 0 && hour < 12) {
                    greetingMsg = "Good Morning";
                } else if (hour >= 12 && hour < 16) {
                    greetingMsg = "Good Afternoon";
                } else if (hour >= 16 && hour < 21) {
                    greetingMsg = "Good Evening";
                } else if (hour >= 21 && hour < 24) {
                    greetingMsg = "Good Night";
                }
                title.setText(greetingMsg + " " + fName + " " + lname + "!");
            }
        });


    }


    public void btn_initialInterview(View v){
        Intent intent = new Intent(this, InitialInterviewActivity.class);
        startActivity(intent);
    }

    public void btn_assessment(View v){
        Intent intent = new Intent(this, DailyHealthActivity.class);
        startActivity(intent);
    }
}