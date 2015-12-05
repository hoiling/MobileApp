package com.example.mobileappapplication;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Hoi_Ling on 5/12/2015.
 */
public class easyActivity extends Activity {

    private SQLiteDatabase db = null;
    static final String DATABASE_NAME = "GameRank.db";
    static final String TABLE_NAME = "easyLevel";
    static final String[] COLUMN = {"name", "scores"};
    public Toast toast;
    TextView one, two, three, four, five, six, seven, eight, nine, ten;
    int count = 1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rankview);

        one = (TextView)findViewById(R.id.rankOne);
        two = (TextView)findViewById(R.id.rankTwo);
        three = (TextView)findViewById(R.id.rankThree);
        four = (TextView)findViewById(R.id.rankFour);
        five = (TextView)findViewById(R.id.rankFive);
        six = (TextView)findViewById(R.id.rankSix);
        seven = (TextView)findViewById(R.id.rankSeven);
        eight = (TextView)findViewById(R.id.rankEight);
        nine = (TextView)findViewById(R.id.rankNine);
        ten = (TextView)findViewById(R.id.rankTen);

        try {
            db = openOrCreateDatabase(DATABASE_NAME, SQLiteDatabase.CREATE_IF_NECESSARY, null);
        }
        catch (Exception e) {
            toast.makeText(this, "Database open fail",toast.LENGTH_SHORT);
            toast.show();
        }

        String TABLE_CREATION = "create table if not exists " + TABLE_NAME + "(" + COLUMN[0] + " text not null, " + COLUMN[1] + " integer not null);";
        db.execSQL(TABLE_CREATION);

        String sql = "select * from " + TABLE_NAME + " order by " + COLUMN[1];
        Cursor cursor = db.rawQuery(sql, null);
        String result = "";
        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(COLUMN[0]));
            int scores = cursor.getInt(cursor.getColumnIndex(COLUMN[1]));
            result = "          " + name + "        " + Integer.toString(scores) + "\n";

            if(count == 1)
                one.setText(result);
            if(count == 2)
                two.setText(result);
            if(count == 3)
                three.setText(result);
            if(count == 4)
                four.setText(result);
            if(count == 5)
                five.setText(result);
            if(count == 6)
                six.setText(result);
            if(count == 7)
                seven.setText(result);
            if(count == 8)
                eight.setText(result);
            if(count == 9)
                nine.setText(result);
            if(count == 10)
                ten.setText(result);
            if(count > 10)
                break;

            count++;
        }
        db.close();
    }
}
