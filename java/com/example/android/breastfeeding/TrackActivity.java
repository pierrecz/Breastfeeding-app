package com.example.android.breastfeeding;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class TrackActivity extends AppCompatActivity {

    public static final String EXTRA = "";
    SeekBar zmenaSB;
    EditText amountET;
    CheckBox satView;
    CheckBox rView;
    CheckBox lView;
    TextView ml;
    private double dAmount;
    int typeBF;
    String amountText;
    int intentAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track2);
        Intent i = getIntent();
        typeBF = i.getIntExtra(EXTRA, -2);
        zmenaSB = (SeekBar) findViewById(R.id.zmenaSeekBar);
        amountET = (EditText) findViewById(R.id.amountEditText);
        zmenaSB.setOnSeekBarChangeListener(amountSBListener);
        zmenaSB.setProgress(6); //Start position of seek bar
        satView = (CheckBox) findViewById(R.id.zmenaCheckBox);
        satView.setOnClickListener(zmenaSV);

        rView = (CheckBox) findViewById(R.id.rightBoob);
        lView = (CheckBox) findViewById(R.id.leftBoob);

        ml = (TextView) findViewById(R.id.ml_TV);
    }
    private SeekBar.OnSeekBarChangeListener amountSBListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        dAmount = (zmenaSB.getProgress()*10);
        amountText = String.valueOf(dAmount) + " ml";
        amountET.setText(String.format("%.0f",dAmount));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    private CheckBox.OnClickListener zmenaSV = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if(satView.isChecked()){
                zmenaSB.setVisibility(View.GONE);
                amountET.setVisibility(View.GONE);
                ml.setVisibility(View.GONE);
                amountET.setText("0");
            }else{
                zmenaSB.setVisibility(View.VISIBLE);
                amountET.setVisibility(View.VISIBLE);
                ml.setVisibility(View.VISIBLE);
                amountET.setText("60");
            }
        }
    };

    public void amountClick(View view) {
        Intent intent = new Intent(this, TimeActivity.class);
        intent.putExtra("type", typeBF);
        try {
            intentAmount = Integer.parseInt(amountET.getText().toString());
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        intent.putExtra("amount", intentAmount);
        intent.putExtra("boobie", boobieString());
        startActivity(intent);
    }

    public void rOnClick (View view) {
        if (rView.isChecked()) {
            rView.setChecked(false);
        }
        else{
            rView.setChecked(true);
        }
    }

    public void lOnClick (View view) {
        if (lView.isChecked()) {
            lView.setChecked(false);
        }
        else{
            lView.setChecked(true);
        }
    }

    public String boobieString() {
        String output = "";
        if (rView.isChecked() && lView.isChecked()) {
            output = getString(R.string.track_L) + "+" + getString(R.string.track_R);
        }
        else if (!rView.isChecked() && lView.isChecked()){
            output = getString(R.string.track_L);
        }
        else if (rView.isChecked() && !lView.isChecked()){
            output = getString(R.string.track_R);
        }
        else {
            output = "-";
        }
        return output;
    }
}
