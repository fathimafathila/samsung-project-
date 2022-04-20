package com.example.covidwatch.UsersView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.covidwatch.UsersView.InitialInterview.informationActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.libraries.maps.GoogleMap.OnMarkerClickListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.covidwatch.R;

public class GMapActivity extends AppCompatActivity implements OnMapReadyCallback,OnMarkerClickListener  {
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmap);

        SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap)  {
        map = googleMap;

        LatLng Location = new LatLng(43.23,-79.81);
        MarkerOptions markerOptions = new MarkerOptions().position(Location).title("Hamilton").snippet( "Ths is a pCR test part" ).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        map.addMarker(markerOptions);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Location,16.0f));

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
                System.out.print(":ian here");
                Log.d("imhere", "imhere");
                Intent intent = new Intent(GMapActivity.this, informationActivity.class);
                startActivity(intent);
                return true;
            }
        });
//        myMarker = googleMap.addMarker(new MarkerOptions()
//                .position(latLng)
//                .title("My Spot")
//
//                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
//        map.setOnMapClickListener( new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(@NonNull LatLng latLng) {
//                //creating marker
//                MarkerOptions marker = new MarkerOptions();
//                //setting marker
//                marker.position( latLng );
//                //set lat and lonh
//                marker.title( latLng.latitude+ " : " + latLng.longitude );
//                //clear
//                map.clear();
//                //zoom
//                map.animateCamera( CameraUpdateFactory.newLatLngZoom( latLng, 10 ) );
//                //sadd marker
//                map.addMarker( marker );
//
//            }
//        } );
    }
//    @Override
//    public boolean onMarkerClick(final Marker marker)
//    {
//
//        String name= marker.getTitle();
//
//        if (name.equalsIgnoreCase("Hamilton"))
//        {
//            Intent intent = new Intent(GMapActivity.this, informationActivity.class);
//            startActivity(intent);
//        }
//        return true;
//    }

    @Override
    public boolean onMarkerClick(com.google.android.libraries.maps.model.Marker marker) {
        String name= marker.getTitle();

        if (name.equalsIgnoreCase("Hamilton"))
        {
            Intent intent = new Intent(GMapActivity.this, informationActivity.class);
            startActivity(intent);
        }
        return false;
    }
}