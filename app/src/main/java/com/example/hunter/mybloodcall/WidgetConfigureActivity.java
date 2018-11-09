package com.example.hunter.mybloodcall;

import android.annotation.TargetApi;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hunter.mybloodcall.Data.DatabaseContract;

import java.util.ArrayList;
import java.util.Objects;

public class WidgetConfigureActivity extends AppCompatActivity {
    String value_City;
    ArrayList<String> city;
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    public static String widgetText, widgetData;
    public static ArrayList<String> data_donors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_configure);
        Intent configIntent = getIntent();
        Bundle extras = configIntent.getExtras();

        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

        }
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        final ListView listView = findViewById(R.id.lv_widget);
        String[] values = getResources().getStringArray(R.array.bloodgrp_arrays);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                widgetText = (String) listView.getItemAtPosition(position);
                loadListItem(getApplicationContext());
                widgetData(getApplicationContext(), position);
                createWidget(getApplicationContext());
            }
        });

    }

    private void widgetData(Context applicationContext, int position) {

        StringBuilder stringBuilder= new StringBuilder();
        int cont = 1;
        for (int i = 0; i< data_donors.size(); i++){
            stringBuilder.append(cont).append(" - ").append(data_donors.get(i)).append("\n");
            cont++;
        }
        WidgetConfigureActivity.widgetData = stringBuilder.toString();
    }

    private void createWidget(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        BloodCallAppWidget.updateAppWidget(context,appWidgetManager, mAppWidgetId);
        Intent result = new Intent();
        result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_OK, result);
        finish();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void loadListItem(Context context) {
        Cursor cursor = context.getContentResolver().query(DatabaseContract.DonorsEntry.CONTENT_URI,
                null,
                null,
                null,
               DatabaseContract.DonorsEntry.COLUMN_BlOODGROUP);


        if(cursor != null){
            while(cursor.moveToNext()){
                data_donors.add(
                        cursor.getString(cursor.getColumnIndex(DatabaseContract.DonorsEntry.COLUMN_NAME)));
                data_donors.add(
                        cursor.getString(cursor.getColumnIndex(DatabaseContract.DonorsEntry.COLUMN_CITY)));
                data_donors.add(
                        cursor.getString(cursor.getColumnIndex(DatabaseContract.DonorsEntry.COLUMN_BlOODGROUP)));
            }
        }
        Objects.requireNonNull(cursor).close();
    }
}
