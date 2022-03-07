package com.example.covidwatch.UsersView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.covidwatch.R;
import com.google.android.material.navigation.NavigationView;

public class UserMenuActivity extends AppCompatActivity {


    NavigationView nv;
    ActionBarDrawerToggle toggle;
    DrawerLayout dl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
     //   setSupportActionBar(toolbar);

        nv = (NavigationView) findViewById(R.id.navmenu);
        dl = (DrawerLayout) findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.open, R.string.close);
        dl.addDrawerListener(toggle);
        toggle.syncState();


        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment temp;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_demographic:
                        Toast.makeText(UserMenuActivity.this, "Demographic Fragment", Toast.LENGTH_SHORT).show();
                        temp = new DemographicFragment();
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_vaccination:
                        Toast.makeText(UserMenuActivity.this, "Vaccination Fragment", Toast.LENGTH_SHORT).show();
                        temp = new VaccinationFragment();
                        dl.closeDrawer(GravityCompat.START);
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, temp).commit();
                return true;
            }
        });
    }
}