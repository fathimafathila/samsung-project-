package com.example.covidwatch.UsersView ;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covidwatch.DateCalculation;
import com.example.covidwatch.R;
import com.example.covidwatch.UsersView.DailyHealth.DailyHealthActivity;
import com.example.covidwatch.UsersView.InitialInterview.InitialInterviewActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class UserDashboardActivity extends AppCompatActivity {

    TextView title,title2, day, mon ;
    FirebaseFirestore db ;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        getSupportActionBar().hide();

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        title = findViewById(R.id.title);
        title2 = findViewById(R.id.titl2);
        day = findViewById(R.id.txtDays);
        mon = findViewById(R.id.txtMon);
        String id;
       if(getIntent().getStringExtra("uuid").isEmpty()){
           id = fAuth.getCurrentUser().getUid();
       }else{
           id = getIntent().getStringExtra("uuid");
       }

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("uuid", id);
        myEdit.commit();

        db.collection("users").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String fName = documentSnapshot.getString("First Name");
                String lname = documentSnapshot.getString("Last Name");

                String firstDate = documentSnapshot.getString("Specimen Date");
                DateCalculation dateCalculation = new DateCalculation();
                int remainingDay = dateCalculation.findDifference(dateCalculation.findEndDate(firstDate));

                if(remainingDay <= 0){
                    day.setText("Congratulations");
                    mon.setText("Monitoring Period Completed");
                }else{
                    day.setText(String.valueOf(remainingDay) + " DAYS"  );
                }

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
                title.setText(greetingMsg + " ");
                title2.setText(fName + " " + lname );
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
    public void btn_vaccine(View v){
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    public static class MapActivity extends AppCompatActivity {
        private WebView webView;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate( savedInstanceState );
            setContentView( R.layout.activity_map );



                webView = (WebView) findViewById(R.id.webView1);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("http://maps.google.com/maps?~~");
            }
        }
}