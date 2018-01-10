package com.example.android.breastfeeding;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RecordActivity extends AppCompatActivity {

    public static final String EXTRA2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_activitz);
        Intent i = getIntent();
        int typeBF = i.getIntExtra(EXTRA2, -1);
        String message = Integer.toString(typeBF);
        TextView textView = findViewById(R.id.test);
        textView.setText (message);


    }
}
