package com.example.hunter.mybloodcall;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hunter.mybloodcall.Data.DatabaseContract;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


/**
 * Created by HuNter on 4/9/2018.
 */

public class Donorlist extends AppCompatActivity {

    private ListView listview;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    private firebaseDBhelper donorlist;
    private FragmentManager fm;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donorlist);

        Bundle extras = getIntent().getExtras();
        assert extras != null;
        String city = Objects.requireNonNull(extras.getString("City")).toUpperCase();
        String bloodGroup = extras.getString("Blood").toUpperCase();

        donorlist = new firebaseDBhelper();
        final ContentValues contentValues = new ContentValues();
        listview = findViewById(R.id.lv_donor);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("donors");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, list);
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

                        contentValues.put(DatabaseContract.DonorsEntry.COLUMN_NAME, donorlist.getName());
                        contentValues.put(DatabaseContract.DonorsEntry.COLUMN_CITY, donorlist.getCity());
                        contentValues.put(DatabaseContract.DonorsEntry.COLUMN_BlOODGROUP, donorlist.getBloodGroup());
                        getContentResolver().insert(DatabaseContract.DonorsEntry.CONTENT_URI, contentValues);
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

