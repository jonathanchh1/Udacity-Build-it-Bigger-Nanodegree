package com.udacityproject.jonat.androidlibrary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String Jokes = "Jokes";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            TextView mtextview = (TextView) findViewById(R.id.Jokers);
            mtextview.setText(extras.getString(Jokes));
        }
    }

}
