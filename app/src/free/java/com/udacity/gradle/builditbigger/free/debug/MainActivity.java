package com.udacity.gradle.builditbigger.free.debug;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.udacity.gradle.builditbigger.EndpointAsync;
import com.udacity.gradle.builditbigger.R;
import com.udacityproject.jonat.androidlibrary.JokeActivity;

public class MainActivity extends AppCompatActivity {
    InterstitialAd mInterstitial;
    ProgressBar mprogressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mprogressbar = (ProgressBar)findViewById(R.id.mprogressbar);
        findViewById(R.id.mButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitial.isLoaded()) {
                    mInterstitial.show();
                    requestNewInterstitial();
                    mprogressbar.setVisibility(View.GONE);
                } else {
                    mprogressbar.setVisibility(View.VISIBLE);
                    tellJoke();
                }
            }
        });

        MobileAds.initialize(this, getResources().getString(R.string.mobileAd));

        mInterstitial = new InterstitialAd(this);
        mInterstitial.setAdUnitId(getResources().getString(R.string.interstitial_code));

        mInterstitial.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                tellJoke();;
            }
        });

        AdView adview = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adview.loadAd(adRequest);

    }

    private void requestNewInterstitial(){
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitial.loadAd(adRequest);
    }

    private void tellJoke() {
        new EndpointAsync(new JokeTaskRetriever()).execute();
    }

    private class JokeTaskRetriever implements EndpointAsync.JokeRetrievedListener{

        @Override
        public void onJokeTaskCompleted(String joke) {
            Intent intent = new Intent(MainActivity.this, JokeActivity.class);
            intent.putExtra(JokeActivity.Jokes, joke);
            startActivity(intent);
        }
    }


}
