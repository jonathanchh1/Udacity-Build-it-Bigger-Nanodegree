package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

import com.example.jonat.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by jonat on 11/4/2016.
 */

public class EndpointAsync extends AsyncTask<Void, Void,
String>{

    private static MyApi myApiService = null;
    private JokeRetrievedListener jokeRetrievedListener;


    public EndpointAsync(JokeRetrievedListener listener){
        jokeRetrievedListener = listener;
    }



    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null){
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }

        try{
            return myApiService.sayJokes().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String results) {
        jokeRetrievedListener.onJokeTaskCompleted(results);
      }


    public interface JokeRetrievedListener {
        void onJokeTaskCompleted(String joke);
    }

}


