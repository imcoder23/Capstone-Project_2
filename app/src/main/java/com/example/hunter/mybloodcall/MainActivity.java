package com.example.hunter.mybloodcall;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.facebook.stetho.Stetho;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    private AdView adView;
    Button findDonor;
    Button beDonor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, String.valueOf(R.string.app_id));

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adView = findViewById(R.id.adview);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("AC5B8CB4388B512355ED1F4C8CED364D").build();
        adView.loadAd(adRequest);

    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void findDonor(View view) {

        Intent intent = new Intent(this,findDonor.class);
        startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
//        startActivity(new Intent(this, findDonor.class));

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void beDonor(View view) {
        Intent intent = new Intent(this,beDonor.class);
        startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
//        startActivity(new Intent(this, beDonor.class));
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void MapsActivity(View view) {
//        Intent intent = new Intent(this,MapsActivity.class);
//        startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        startActivity(new Intent(this,MapsActivity.class));
    }
}