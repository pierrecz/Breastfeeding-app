package com.example.android.breastfeeding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TimeActivity extends AppCompatActivity {
    public static final String eAmount = "";
    public static final String eType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
    }

    public void timeClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
