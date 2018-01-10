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
    TextView ml;
    private double dAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track2);
        Intent i = getIntent();
        int typeBF = i.getIntExtra(EXTRA, -1);
        zmenaSB = (SeekBar) findViewById(R.id.zmenaSeekBar);
        amountET = (EditText) findViewById(R.id.amountEditText);
        zmenaSB.setOnSeekBarChangeListener(amountSBListener);
        zmenaSB.setProgress(6); //Start position of seek bar
        satView = (CheckBox) findViewById(R.id.zmenaCheckBox);
        satView.setOnClickListener(zmenaSV);
        ml = (TextView) findViewById(R.id.ml_TV);
    }
    private SeekBar.OnSeekBarChangeListener amountSBListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        dAmount = (zmenaSB.getProgress()*10);

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
            }else{
                zmenaSB.setVisibility(View.VISIBLE);
                amountET.setVisibility(View.VISIBLE);
                ml.setVisibility(View.VISIBLE);
            }
        }
    };

    public void amountClick(View view) {
        Intent intent = new Intent(this, TimeActivity.class);
        intent.putExtra(TimeActivity.eType, EXTRA);
        intent.putExtra(TimeActivity.eAmount, dAmount);
        startActivity(intent);
    }
}
