package com.example.hunter.mybloodcall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    Button findDonor;
    Button beDonor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);
    }



    public void findDonor(View view) {

        startActivity(new Intent(this, findDonor.class));

    }

    public void beDonor(View view) {

        startActivity(new Intent(this, beDonor.class));
    }

    public void findNearby(View view) {

        startActivity(new Intent(this,NearbyActivity.class));

    }

    public void MapsActivity(View view) {
        startActivity(new Intent(this,MapsActivity.class));
    }
}