package com.udacity.gradle.builditbigger.paid.debug;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.udacity.gradle.builditbigger.*;
import com.udacityproject.jonat.androidlibrary.JokeActivity;

import butterknife.Bind;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.mButton)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       findViewById(R.id.mButton).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               tellJoke();
           }
       });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void tellJoke(){
        new EndpointAsync(new JokeRetrievedHandler()).execute();
    }

    private class JokeRetrievedHandler implements EndpointAsync.JokeRetrievedListener {

        @Override
        public void onJokeTaskCompleted(String joke) {
            Intent intent = new Intent(MainActivity.this, JokeActivity.class);
            intent.putExtra(JokeActivity.Jokes, joke);
            startActivity(intent);
        }
    }


}
