package com.example.hunter.mybloodcall;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DonorlistFragment extends Fragment implements OnMapReadyCallback {


    GoogleMap map;
    String City;
    public DonorlistFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        City = getArguments().getString("City");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_donorlist, container, false);
       SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map1);
       mapFragment.getMapAsync(this);

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
//        LatLng DonorCity = new LatLng(-34, 151);
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(DonorCity).title("City");
//        map.addMarker(markerOptions);
//        map.moveCamera(CameraUpdateFactory.newLatLng(DonorCity));

        List<Address> addressList;

        if(!City.equals(""))
        {
            Geocoder geocoder = new Geocoder(getContext());
            try {

                    addressList = geocoder.getFromLocationName(City, 1000);

                if (addressList != null) {
                    for (int i = 0; i < addressList.size(); i++) {
                        LatLng latLng = new LatLng(addressList.get(i).getLatitude(), addressList.get(i).getLongitude());
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(City);
                        map.addMarker(markerOptions);
                        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        map.animateCamera(CameraUpdateFactory.zoomTo(10));
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    public static DonorlistFragment newInstance(String city) {
        DonorlistFragment fragment = new DonorlistFragment();
        Bundle args = new Bundle();
        args.putString("City",city);
        fragment.setArguments(args);
        return fragment;
    }
}
