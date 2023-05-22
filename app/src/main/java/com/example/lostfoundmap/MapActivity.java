package com.example.lostfoundmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.lostfoundmap.sqlitehelper.Advert;
import com.example.lostfoundmap.sqlitehelper.DatabaseHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    ArrayList<Advert> advertArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        // DatabaseHelper
        DatabaseHelper databaseHelper = new DatabaseHelper(MapActivity.this);
        advertArrayList = databaseHelper.getAllAdverts();
        for (int  i = 0; i < advertArrayList.size(); i++)
        {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(advertArrayList.get(i).getLatitude()), Double.parseDouble(advertArrayList.get(i).getLongitude())))
                    .title(advertArrayList.get(i).getType() + " in " + advertArrayList.get(i).getName()));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(Double.parseDouble(advertArrayList.get(i).getLatitude()), Double.parseDouble(advertArrayList.get(i).getLongitude()))));
        }
    }
}