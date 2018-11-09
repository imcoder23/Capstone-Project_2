package com.example.hunter.mybloodcall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import javax.annotation.Nullable;


/**
 * Created by HuNter on 4/2/2018.
 */

public class findDonor extends AppCompatActivity {
    Button find;
    private EditText city;
    private EditText blood;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finddonor);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar_find);
        setSupportActionBar(toolbar);
        setTitle("Find Donor");
    }

    public void find_Donor(View view) {
        city =  findViewById(R.id.spinner_city);
        blood = findViewById(R.id.spinner_blood);
        String City = city.getText().toString();
        String Blood = blood.getText().toString();
        if(!City.isEmpty() || !Blood.isEmpty()){
        Intent intent = new Intent(this,Donorlist.class);
        intent.putExtra("City",City);
        intent.putExtra("Blood",Blood);
        startActivity(intent);
        }
        else{
            Toast.makeText(this,"Enter Proper Data",Toast.LENGTH_SHORT).show();
        }
    }
}






