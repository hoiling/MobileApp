package com.example.mobileappapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Hoi_Ling on 27/11/2015.
 */
public class scoresActivity extends Activity {

    private  SQLiteDatabase db = null;
    static final String DATABASE_NAME = "GameRank.db";
    static final String TABLE_NAME = "GameRank";
    static final String[] COLUMN = {"name", "scores"};

    public Toast toast;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        TextView data = (TextView)findViewById(R.id.scoresResult);
        Button backButton = (Button)findViewById(R.id.backButton);

        try {
            db = openOrCreateDatabase(DATABASE_NAME, SQLiteDatabase.CREATE_IF_NECESSARY, null);
        }
        catch(Exception e) {
            toast.makeText(this, "Database open fail",toast.LENGTH_SHORT);
            toast.show();
        }

        String sql = "select * from " + TABLE_NAME + "order by " + COLUMN[1];
        Cursor cursor = db.rawQuery(sql, null);

    }

    public void backMethod(View v) {
        Intent intent = new Intent();
        intent.setClassName(this, getPackageName() + "." + "startActivity");

    }




}
