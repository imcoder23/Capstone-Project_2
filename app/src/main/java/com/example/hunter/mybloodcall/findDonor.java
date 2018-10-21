package com.example.hunter.mybloodcall;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by HuNter on 4/2/2018.
 */

public class findDonor extends AppCompatActivity {
    Button find;
    EditText city,blood;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finddonor);
    }

    public void findDonor(View view) {
        city = (EditText) findViewById(R.id.spinner_city);
        blood = (EditText)findViewById(R.id.spinner_blood);
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






