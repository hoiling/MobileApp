package com.example.mobileappapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class startActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button startButton = (Button)findViewById(R.id.startButton);
        Button ruleButton = (Button)findViewById(R.id.ruleButton);
        Button scoreButton = (Button)findViewById(R.id.scoresButton);
        Button settingButton = (Button)findViewById(R.id.settingButton);
    }

    public void startGameMethod(View v) {
        Intent intent = new Intent();
        intent.setClassName(this, getPackageName() + "." + "playActivity");
        startActivity(intent);
    }

    public void ruleMethod(View v) {
        Intent intent = new Intent();
        intent.setClassName(this, getPackageName() + "." + "ruleActivity");
        startActivity(intent);

    }

    public void scoreMethod(View v) {
        Intent intent = new Intent();
        intent.setClassName(this, getPackageName() + "." + "scoreActivity");
        startActivity(intent);

    }

    public void settingMethod(View v) {
        Intent intent = new Intent();
        intent.setClassName(this, getPackageName() + "." + "settingActivity");
        startActivity(intent);
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
