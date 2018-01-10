package com.example.android.breastfeeding;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Database ID
    protected static final String DB_DATABASE = "dbFeed";
    protected static final int DB_VERSION = 1;
    protected static final String DB_TABLE = "tblFeed";

    //Table columns
    public static final String ATR_ID = "_id";
    public static final String ATR_TIME = "time";
    public static final String ATR_AMOUNT = "amount";
    public static final String ATR_TYPE = "type";
    public static final String ATR_DATE = "date"; //Prepared for further use

    public class MyHelper extends SQLiteOpenHelper {

        public MyHelper (Context context){
        super(context, DB_DATABASE, null, DB_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DB_TABLE + " (" + ATR_ID + "INTEGER PRIMARY KEY," + ATR_TIME + " TEXT," + ATR_AMOUNT + " TEXT," + ATR_TYPE + " TEXT," + ATR_DATE + " TEXT" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
