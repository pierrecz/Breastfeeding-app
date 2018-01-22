package com.example.android.breastfeeding;

import android.content.ContentValues;
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
    int eAmountActual;
    String eBoobie;
    int eTypeActual;
    private SQLiteDatabase database;

    //MainActivity.MyHelper dm= new MainActivity.MyHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        Bundle extras = getIntent().getExtras();
        Intent i = getIntent();
        eAmountActual = i.getIntExtra("amount",-1);
        eTypeActual = i.getIntExtra("type",-1);
        eBoobie = i.getStringExtra("boobie");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        date = df.format(new Date());
        timeP = (TimePicker) findViewById(R.id.timePicker);


    }

    public void timeClick(View view) {
        timePicker = timeP.getCurrentHour().toString() + ":" + timeP.getCurrentMinute().toString();
        //HashMap<String, String> querryRC = new HashMap<String, String>();
        MainActivity.MyHelper controller = new MainActivity.MyHelper(this);
        SQLiteDatabase db2 = controller.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("time", timePicker);
        initialValues.put("amount", eAmountActual);
        initialValues.put("type", eTypeActual);
        initialValues.put("date", date);
        initialValues.put("boob", eBoobie);
        db2.insert("tblFeed", null, initialValues);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
