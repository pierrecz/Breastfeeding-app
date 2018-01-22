package com.example.android.breastfeeding;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView lastFeedsTV;
    TextView oneFeedsTV;
    TextView twoFeedsTV;
    TextView threeFeedsTV;

    public static long count;

    //Database ID
    protected static final String DB_DATABASE = "dbFeed";
    protected static final int DB_VERSION = 3;
    protected static final String DB_TABLE = "tblFeed";

    //Table columns
    public static final String ATR_ID = "_id";
    public static final String ATR_TIME = "time";
    public static final String ATR_AMOUNT = "amount";
    public static final String ATR_TYPE = "type";
    public static final String ATR_DATE = "date"; //Prepared for further use
    public static final String ATR_BOOB = "boob"; //Prepared for further use

    public static class MyHelper extends SQLiteOpenHelper {

        public MyHelper (Context context)
        {
        super(context, DB_DATABASE, null, DB_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DB_TABLE + " (" + ATR_ID + " INTEGER PRIMARY KEY," + ATR_TIME + " TEXT," + ATR_AMOUNT + " INTEGER," + ATR_TYPE + " INTEGER," + ATR_DATE + " TEXT," + ATR_BOOB + " TEXT" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String query = "DROP TABLE IF EXISTS " + DB_TABLE;
            db.execSQL(query);
            onCreate(db);
        }

        public long insertRecord (HashMap<String, String> atributes){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues val = new ContentValues();
            val.put(ATR_TIME, atributes.get(ATR_TIME));
            val.put(ATR_AMOUNT, atributes.get(ATR_AMOUNT));
            val.put(ATR_TYPE, atributes.get(ATR_TYPE));
            val.put(ATR_DATE, atributes.get(ATR_DATE));
            long id = db.insert(DB_TABLE, null, val);
            db.close();
            return id;
        }

        public HashMap<String, String> getRecords(){
            SQLiteDatabase db = this.getWritableDatabase();
            count  = numberOfRows();
            String cntText = String.valueOf(count);
            HashMap<String, String> hm = new HashMap<String, String>();
            SQLiteDatabase db2 = this.getReadableDatabase();
            String sSQL = "SELECT * FROM " + DB_TABLE + " WHERE " + ATR_ID +"=" + cntText;
            Cursor cursor = db2.rawQuery(sSQL, null);
            if (cursor.moveToFirst()){
                do {
                    hm.put(ATR_TIME, cursor.getString(1));
                    hm.put(ATR_AMOUNT, cursor.getString(2));
                    hm.put(ATR_TYPE, cursor.getString(3));
                    hm.put(ATR_BOOB, cursor.getString(5));
                } while (cursor.moveToNext());
            }
            return hm;
        }

        public long numberOfRows(){
            SQLiteDatabase db = this.getWritableDatabase();
            long cnt  = DatabaseUtils.queryNumEntries(db, DB_TABLE);
            return cnt;
        }

        public HashMap<String, String> getRecords(long less){
            SQLiteDatabase db = this.getWritableDatabase();
            less = less - 1;
            count = less;
            String cntText = String.valueOf(less);
            HashMap<String, String> hm = new HashMap<String, String>();
            SQLiteDatabase db2 = this.getReadableDatabase();
            String sSQL = "SELECT * FROM " + DB_TABLE + " WHERE " + ATR_ID +"=" + cntText;
            Cursor cursor = db2.rawQuery(sSQL, null);
            if (cursor.moveToFirst()){
                do {
                    hm.put(ATR_TIME, cursor.getString(1));
                    hm.put(ATR_AMOUNT, cursor.getString(2));
                    hm.put(ATR_TYPE, cursor.getString(3));
                    hm.put(ATR_BOOB, cursor.getString(5));
                } while (cursor.moveToNext());
            }
            return hm;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lastFeedsTV = (TextView) findViewById(R.id.lastFeedTV);
        oneFeedsTV = (TextView) findViewById(R.id.oneFeedsTV);
        twoFeedsTV = (TextView) findViewById(R.id.twoFeedsTV);
        threeFeedsTV = (TextView) findViewById(R.id.threeFeedsTV);

        // Creating instance of controller
        MyHelper controller = new MyHelper(this);
        HashMap<String, String> lastFeed = controller.getRecords();
        if (lastFeed.size()!=0)
        {
            lastFeedsTV.setText(lastFeed.get(ATR_TIME)+" │ "+getMeAmount(lastFeed.get(ATR_AMOUNT))+ " │ " +getMeBreastType(lastFeed.get(ATR_TYPE))+ " │ " + lastFeed.get(ATR_BOOB));
        }
        else {
            lastFeedsTV.setText("No records");
        }

        HashMap<String, String> oneFeed = controller.getRecords(count);

        if (oneFeed.size()!=0)
        {
            oneFeedsTV.setText(oneFeed.get(ATR_TIME)+" │ "+getMeAmount(oneFeed.get(ATR_AMOUNT))+ " │ " +getMeBreastType(oneFeed.get(ATR_TYPE))+ " │ " + oneFeed.get(ATR_BOOB));
        }
        else {
            oneFeedsTV.setText("No records");
        }

        HashMap<String, String> twoFeed = controller.getRecords(count);

        if (oneFeed.size()!=0)
        {
            twoFeedsTV.setText(twoFeed.get(ATR_TIME)+" │ "+getMeAmount(twoFeed.get(ATR_AMOUNT))+ " │ " +getMeBreastType(twoFeed.get(ATR_TYPE))+ " │ " + twoFeed.get(ATR_BOOB));
        }
        else {
            twoFeedsTV.setText("No records");
        }

        HashMap<String, String> threeFeed = controller.getRecords(count);

        if (oneFeed.size()!=0)
        {
            threeFeedsTV.setText(threeFeed.get(ATR_TIME)+" │ "+getMeAmount(threeFeed.get(ATR_AMOUNT))+ " │ " +getMeBreastType(threeFeed.get(ATR_TYPE))+ " │ " + threeFeed.get(ATR_BOOB));
        }
        else {
            threeFeedsTV.setText("No records");
        }


    }
    int typeBreastFeeding;

    public void breastClick(View view)  {
        typeBreastFeeding = 1;
        Intent intent = new Intent(this, TrackActivity.class);
        intent.putExtra(TrackActivity.EXTRA, typeBreastFeeding);
        startActivity(intent);
    }
    public void bottleClick(View view)  {
        typeBreastFeeding = 2;
        Intent intent = new Intent(this, TrackActivity.class);
        intent.putExtra(TrackActivity.EXTRA, typeBreastFeeding);
        startActivity(intent);
    }

    public void breastPumpClick (View view)  {
        typeBreastFeeding = 3;
        Intent intent = new Intent(this, TrackActivity.class);
        intent.putExtra(TrackActivity.EXTRA, typeBreastFeeding);
        startActivity(intent);
    }

    public String getMeBreastType(String type)  {
        String breastTypeText = "";
        switch (type) {
            case "1":  breastTypeText = " " + getString(R.string.app_typeBreast);
                break;
            case "2":  breastTypeText = " " + getString(R.string.app_typeBottle);
                break;
            case "3":  breastTypeText = " " + getString(R.string.app_typeBreastPump);
        }
        return breastTypeText;
    }

    public static String getMeAmount(String amount)  {
        String amountText = "";
        if (amount.equals("0")){
            amountText = "-";
        }
        else {
            amountText = amount + " ml";
        }
        return amountText;
    }

}
