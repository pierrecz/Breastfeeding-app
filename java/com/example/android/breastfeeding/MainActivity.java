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

    //Database ID
    protected static final String DB_DATABASE = "dbFeed";
    protected static final int DB_VERSION = 2;
    protected static final String DB_TABLE = "tblFeed";

    //Table columns
    public static final String ATR_ID = "_id";
    public static final String ATR_TIME = "time";
    public static final String ATR_AMOUNT = "amount";
    public static final String ATR_TYPE = "type";
    public static final String ATR_DATE = "date"; //Prepared for further use

    public static class MyHelper extends SQLiteOpenHelper {

        public MyHelper (Context context)
        {
        super(context, DB_DATABASE, null, DB_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DB_TABLE + " (" + ATR_ID + " INTEGER PRIMARY KEY," + ATR_TIME + " TEXT," + ATR_AMOUNT + " TEXT," + ATR_TYPE + " TEXT," + ATR_DATE + " TEXT" + ");");
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
            long cnt  = DatabaseUtils.queryNumEntries(db, DB_TABLE);
            String cntText = String.valueOf(cnt);
            HashMap<String, String> hm = new HashMap<String, String>();
            SQLiteDatabase db2 = this.getReadableDatabase();
            String sSQL = "SELECT * FROM " + DB_TABLE + " WHERE " + ATR_ID +"=" + cntText;
            Cursor cursor = db2.rawQuery(sSQL, null);
            if (cursor.moveToFirst()){
                do {
                    hm.put(ATR_TIME, cursor.getString(1));
                    hm.put(ATR_AMOUNT, cursor.getString(2));
                    hm.put(ATR_TYPE, cursor.getString(3));
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
        // Creating instance of controller
        MyHelper controller = new MyHelper(this);
        HashMap<String, String> lastFeed = controller.getRecords();

        if (lastFeed.size()!=0)
        {
            lastFeedsTV.setText(lastFeed.get(ATR_TIME)+"   "+lastFeed.get(ATR_AMOUNT)+ " " +lastFeed.get(ATR_TYPE));
        }
        else {
            lastFeedsTV.setText("No records");
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
    public static String getMeBreastType(int type)  {
        String breastTypeText = "";
        switch (type) {
            case 1:  breastTypeText = " breastfeeding from breast";
                break;
            case 2:  breastTypeText = " breastfeeding from bottle";
                break;
            case 3:  breastTypeText = " suction of milk";
        }
        return breastTypeText;
    }

}
