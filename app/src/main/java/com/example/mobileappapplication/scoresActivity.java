package com.example.mobileappapplication;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;

public class scoresActivity extends TabActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        TabHost tabHost = getTabHost();

        // Tab for Easy
        TabHost.TabSpec easySpec = tabHost.newTabSpec("Easy");
        easySpec.setIndicator("", getResources().getDrawable(R.drawable.easy));
        Intent easyIntent = new Intent(this, easyActivity.class);
        easySpec.setContent(easyIntent);

        // Tab for Medium
        TabHost.TabSpec mediumSpec = tabHost.newTabSpec("Medium");
        // setting Title and Icon for the Tab
        mediumSpec.setIndicator("", getResources().getDrawable(R.drawable.medium));
        Intent mediumIntent = new Intent(this, mediumActivity.class);
        mediumSpec.setContent(mediumIntent);

        // Tab for Hard
        TabHost.TabSpec hardSpec = tabHost.newTabSpec("Hard");
        hardSpec.setIndicator("", getResources().getDrawable(R.drawable.hard));
        Intent hardIntent = new Intent(this, hardActivity.class);
        hardSpec.setContent(hardIntent);

        // Adding all TabSpec to TabHost
        tabHost.addTab(easySpec); // Adding photos tab
        tabHost.addTab(mediumSpec); // Adding songs tab
        tabHost.addTab(hardSpec); // Adding videos tab
    }

    public void backMethod(View v) {
        Intent intent = new Intent();
        intent.setClassName(this, getPackageName() + "." + "startActivity");
        startActivity(intent);
    }

}