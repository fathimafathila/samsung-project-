package com.example.covidwatch.UsersView.InitialInterview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.covidwatch.R;
import com.example.covidwatch.UsersView.InitialInterview.Demographic.DemographicFragment;
import com.example.covidwatch.UsersView.InitialInterview.Location.LocationCloseContactFragment;
import com.example.covidwatch.UsersView.InitialInterview.MonitoringResourceRequest.MonitoringResourceRequestFragment;
import com.example.covidwatch.UsersView.InitialInterview.TestingTreatment.TestingTreatmentFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class InitialInterviewActivity extends AppCompatActivity {


    NavigationView nv;
    ActionBarDrawerToggle toggle;
    DrawerLayout dl;
    FirebaseAuth fAuth ;
    FirebaseFirestore db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_interview);

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
     // setSupportActionBar(toolbar);

        nv = (NavigationView) findViewById(R.id.navmenu);
        dl = (DrawerLayout) findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.open, R.string.close);
        dl.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String uuid = sh.getString("uuid","");

        updateNavheader(uuid);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment temp;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.menu_Home:

                        temp = new HomeFragment();

                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_demographic:

                        temp = new DemographicFragment();

                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_initial_health_assessment:
                        temp = new InitialHealthAssessmentFragment();
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_Testing_Treatment:
                        temp = new TestingTreatmentFragment();
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_Epilinkage_Congregrate:
                        temp = new EpilinkageCongregateFragment();
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_Monitoring_Resource:
                        temp = new MonitoringResourceRequestFragment();
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_Location_Close_Contact:
                        temp = new LocationCloseContactFragment();
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_Additional_Information:
                        temp = new AdditionalInfoFragment();
                        dl.closeDrawer(GravityCompat.START);
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, temp).commit();
                return true;
            }
        });
    }

    private void updateNavheader(String id) {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navmenu);
        View header = navigationView.getHeaderView(0);
        TextView name = header.findViewById(R.id.fullName);

        db.collection("users").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String fName = documentSnapshot.getString("First Name");
                String lname = documentSnapshot.getString("Last Name");
                name.setText(fName +" " + lname);

            }
        });
    }
}