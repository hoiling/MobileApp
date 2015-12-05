package com.example.mobileappapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

public class startActivity extends Activity {

    static private  SQLiteDatabase db = null;
    static final String DATABASE_NAME = "GameRank.db";
    static final String TABLE_NAME = "Setting";
    static final String[] COLUMN = {"sound", "level"};
    static Cursor cursor;
    static Toast toast;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void onResume() {
        super.onResume();
        String soundONOff= "";
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean sound = prefs.getBoolean(getString(R.string.sound_key), getResources().getBoolean(R.bool.sound_default));
        String level = prefs.getString(getString(R.string.level_key), getString(R.string.level_default));
        if(!sound)
            soundONOff = "off";
        else
            soundONOff = "on";

        // Open Database
        try {
            db = openOrCreateDatabase(DATABASE_NAME, SQLiteDatabase.CREATE_IF_NECESSARY, null);
        }
        catch (Exception e) {
            toast.makeText(this, "Database open fail",toast.LENGTH_SHORT);
            toast.show();
        }

        String TABLE_CREATION = "create table if not exists " + TABLE_NAME + "(" + COLUMN[0] + " text not null, " + COLUMN[1] + " text not null);";
        db.execSQL(TABLE_CREATION);
        cursor  = db.query(TABLE_NAME, COLUMN, "sound != null and level != null", null, null, null, null);
        if(cursor.moveToNext()) {
            String sql = "update " + TABLE_NAME + " set " + COLUMN[0] + "=" + "'" + soundONOff + "'" + " and " + COLUMN[1] + "=" + "'" + level + "'"
                    +" where " + COLUMN[0] + "=null and " + COLUMN[1] + "= null;";
            db.execSQL(sql);
        }
        else {
            String sql = "insert into " + TABLE_NAME + " (" + COLUMN[0] + ", " + COLUMN[1] + ") values( " + "'" + soundONOff + "'" + ", " + "'" + level + "'" + ");";
            db.execSQL(sql);
        }
        db.close();
    }

    public void startGameMethod(View v) {
        intent = new Intent(startActivity.this, startActivity.class);
        startActivity(intent);
    }

    public void ruleMethod(View v) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.rule_about)
                .setMessage(R.string.rule_msg)
                .setNeutralButton(android.R.string.ok, null)
                .show();
    }

    public void scoresMethod(View v) {
        intent = new Intent(startActivity.this, scoresActivity.class);
        startActivity(intent);
    }

    public void settingMethod(View v) {
        intent = new Intent(startActivity.this, EditorPreferenceActivity.class);
        startActivity(intent);
    }

    public static class EditorPreferenceActivity extends PreferenceActivity {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            FragmentManager mFragmentManager = getFragmentManager();
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
            EditorPreferenceFragment mPrefsFragment = new EditorPreferenceFragment();
            mFragmentTransaction.replace(android.R.id.content, mPrefsFragment);
            mFragmentTransaction.commit();
        }
    }

    public static class EditorPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.prefs);
        }
    }
}
