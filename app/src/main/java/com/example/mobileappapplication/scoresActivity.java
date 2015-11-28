package com.example.mobileappapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class scoresActivity extends Activity {

    private  SQLiteDatabase db = null;
    static final String DATABASE_NAME = "GameRank.db";
    static final String TABLE_NAME = "GameRank";
    static final String[] COLUMN = {"name", "scores"};
    public Toast toast;
    TextView data;
    int count = 1;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        data = (TextView)findViewById(R.id.scoresResult);

        try {
            db = openOrCreateDatabase(DATABASE_NAME, SQLiteDatabase.CREATE_IF_NECESSARY, null);
        }
        catch(Exception e) {
            toast.makeText(this, "Database open fail",toast.LENGTH_SHORT);
            toast.show();
        }

        String TABLE_CREATION = "create table if not exists " + TABLE_NAME + "(" + COLUMN[0] + " text not null, " + COLUMN[1] + " integer not null);";
        db.execSQL(TABLE_CREATION);
        String insert = "insert into " + TABLE_NAME + "(" + COLUMN[0] + ", " + COLUMN[1] +")" +"values('ABC', 10);";
        db.execSQL(insert);

        String sql = "select * from " + TABLE_NAME + " order by " + COLUMN[1];
        Cursor cursor = db.rawQuery(sql, null);
        String result = "";
        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(COLUMN[0]));
            int scores = cursor.getInt(cursor.getColumnIndex(COLUMN[1]));
            result += count + ". " + name + " " + Integer.toString(scores) + "\n\n";
            count++;
        }
        data.setText(result);
        db.close();
    }

    public void backMethod(View v) {
        Intent intent = new Intent();
        intent.setClassName(this, getPackageName() + "." + "startActivity");
        startActivity(intent);
        data.setText("");
    }

}
