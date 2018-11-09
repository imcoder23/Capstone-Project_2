package com.example.hunter.mybloodcall;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        search();
   }

    private void search() {
            Object datatransfer[] = new Object[1];
            GetAllDonorsData getAllDonorsData = new GetAllDonorsData(getApplicationContext());
            datatransfer[0] = mMap;
            getAllDonorsData.execute(datatransfer);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
       String clicked = marker.getTitle();
       Log.d("clicked",clicked);
       return true;
    }
}
