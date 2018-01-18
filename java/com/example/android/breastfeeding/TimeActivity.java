package com.example.android.breastfeeding;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static com.example.android.breastfeeding.TrackActivity.EXTRA;


public class TimeActivity extends AppCompatActivity {
    String date;
    TimePicker timeP;
    String timePicker;
    String eAmountActual;
    String eTypeText;
    int eTypeActual;

    MainActivity.MyHelper dm= new MainActivity.MyHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        Bundle extras = getIntent().getExtras();
        Intent i = getIntent();
        eAmountActual = i.getStringExtra("amount");
        eTypeActual = i.getIntExtra("type",-1);

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        date = df.format(new Date());
        timeP = (TimePicker) findViewById(R.id.timePicker);


    }

    public void timeClick(View view) {
        timePicker = timeP.getCurrentHour().toString() + ":" + timeP.getCurrentMinute().toString();
        HashMap<String, String> querryRC = new HashMap<String, String>();

        querryRC.put("time", timePicker);
        querryRC.put("amount", eAmountActual);
        querryRC.put("type", eTypeText);
        querryRC.put("date", date);
        dm.insertRecord(querryRC);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
