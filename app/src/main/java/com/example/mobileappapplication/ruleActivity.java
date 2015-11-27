package com.example.mobileappapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Hoi_Ling on 27/11/2015.
 */
public class ruleActivity extends Activity {

    Button backButton = (Button)findViewById(R.id.ruleBackButton);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);
    }

    public void ruleBackMethod(View v) {
        Intent intent = new Intent();
        intent.setClassName(this, getPackageName() + "." + "startActivity");
        startActivity(intent);

    }


}
