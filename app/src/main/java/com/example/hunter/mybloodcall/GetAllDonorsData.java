package com.example.hunter.mybloodcall;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GetAllDonorsData extends AsyncTask<Object, ArrayList<String>, String> {

    private GoogleMap mMap;
    private FirebaseDatabase db;
    private DatabaseReference reference;
    private firebaseDBhelper dBhelper;
    private ArrayList<String> Citylist;
    ArrayList<LatLng>places;
    private String value_City;
    private Context mcontext;


    GetAllDonorsData(Context context) {
        this.mcontext = context;
    }

    @Override
    public String doInBackground(Object... objects) {

        mMap = (GoogleMap) objects[0];
        return "Finding all Donors";

    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(String s) {
        ShowNearbyDonors();
        Toast.makeText(mcontext,s,Toast.LENGTH_LONG).show();

    }

    private void ShowNearbyDonors() {
        dBhelper = new firebaseDBhelper();
        Citylist = new ArrayList<>();
        db = FirebaseDatabase.getInstance();

        reference = db.getReference("donors");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    value_City = ds.getKey();
                    Citylist.add(value_City);
                }

                for (int k = 0; k < Citylist.size(); k++) {
                    List<Address> addressList;
                    places = new ArrayList<>();
                    if (!Citylist.isEmpty()) {
                        Geocoder geocoder = new Geocoder(mcontext);
                        try {
                            addressList = geocoder.getFromLocationName(Citylist.get(k), 1);
                            if (addressList != null) {
                                for (int i = 0; i < addressList.size(); i++) {
                                    LatLng latLng = new LatLng(addressList.get(i).getLatitude(), addressList.get(i).getLongitude());
                                    places.add(latLng);
                                    MarkerOptions markerOptions = new MarkerOptions();
                                    markerOptions.position(latLng);
                                    markerOptions.title(Citylist.get(k));
                                    mMap.addMarker(markerOptions);
                                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                    mMap.animateCamera(CameraUpdateFactory.zoomTo(5));
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
