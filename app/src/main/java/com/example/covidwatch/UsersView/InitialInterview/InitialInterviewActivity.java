package com.example.covidwatch.UsersView.InitialInterview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covidwatch.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.chrono.HijrahChronology;

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

        updateNavheader(fAuth.getCurrentUser().getUid());

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment temp;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_Home:
                        Toast.makeText(InitialInterviewActivity.this, "Home Fragment", Toast.LENGTH_SHORT).show();
                        temp = new HomeFragment();
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_demographic:
                        Toast.makeText(InitialInterviewActivity.this, "Demographic Fragment", Toast.LENGTH_SHORT).show();
                        temp = new DemographicFragment();
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_vaccination:
                        Toast.makeText(InitialInterviewActivity.this, "Vaccination Fragment", Toast.LENGTH_SHORT).show();
                        temp = new VaccinationFragment();
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_case_information:
                        Toast.makeText(InitialInterviewActivity.this, "Case Information Fragment", Toast.LENGTH_SHORT).show();
                        temp = new CaseInformationFragment();
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_initial_health_assessment:
                        Toast.makeText(InitialInterviewActivity.this, "Initial Health  Fragment", Toast.LENGTH_SHORT).show();
                        temp = new InitialHealthAssessmentFragment();
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_testing_information:
                        Toast.makeText(InitialInterviewActivity.this, "Testing Fragment", Toast.LENGTH_SHORT).show();
                        temp = new TestingInformationFragment();
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