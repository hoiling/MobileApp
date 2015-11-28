package com.example.mobileappapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.View;

public class startActivity extends Activity {

    private  SQLiteDatabase db = null;
    static final String DATABASE_NAME = "GameRank.db";
    static final String TABLE_NAME = "GameRank";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean sound = prefs.getBoolean(getString(R.string.sound_key), getResources().getBoolean(R.bool.sound_default));
        String level = prefs.getString(getString(R.string.level_key), getString(R.string.level_default));
        boolean deleteData = prefs.getBoolean(getString(R.string.delete_data_key), getResources().getBoolean(R.bool.delete_data_default));

        if(!sound) {

        }
        else {

        }

        if(deleteData) {
            db = openOrCreateDatabase(DATABASE_NAME, SQLiteDatabase.CREATE_IF_NECESSARY, null);
            String DELETE_TABLE = "delete from " + TABLE_NAME +";";
            db.execSQL(DELETE_TABLE);
            db.close();
        }
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
            // can set the checkbox to false but the value cannot change
            // user need to get in setting 2times to eliminate the effect of delete data
            addPreferencesFromResource(R.xml.prefs);CheckBoxPreference delete_data = (CheckBoxPreference)findPreference("delete_data_key");
            delete_data.setChecked(false);
        }
    }

 /*   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    */
}
