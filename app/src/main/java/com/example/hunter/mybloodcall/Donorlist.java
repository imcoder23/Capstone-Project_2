package com.example.hunter.mybloodcall;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * Created by HuNter on 4/9/2018.
 */

public class Donorlist extends AppCompatActivity {

    ListView listview;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    firebaseDBhelper donorlist;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donorlist);

        Bundle extras = getIntent().getExtras();
        String city = extras.getString("City").toUpperCase();
        String bloodGroup = extras.getString("Blood").toUpperCase();

        Log.d("NAME",city);
        Log.d("Blood",bloodGroup);

        donorlist = new firebaseDBhelper();

        listview =(ListView) findViewById(R.id.lv_donor);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("donors");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,list);
        ref.child(city).child(bloodGroup).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){

                    donorlist = ds.getValue(firebaseDBhelper.class);

                    if( null != donorlist ) {
                        list.add(donorlist.getName());
                        list.add(donorlist.getSex());
                        list.add(donorlist.getCity());
                        list.add(donorlist.getBloodGroup());
                        list.add(donorlist.getContactNumber());
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Data not Found",Toast.LENGTH_SHORT).show();
                    }
                }
                listview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

         fm = getSupportFragmentManager();
         DonorlistFragment dl = DonorlistFragment.newInstance(city);
         fm.beginTransaction().replace(R.id.map2,dl).commit();

    }
    }

