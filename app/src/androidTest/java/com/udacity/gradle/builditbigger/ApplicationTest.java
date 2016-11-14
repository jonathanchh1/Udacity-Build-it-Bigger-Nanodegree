package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.content.Intent;
import android.test.ApplicationTestCase;

import com.udacityproject.jonat.androidlibrary.JokeActivity;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    private final CountDownLatch mSignal = new CountDownLatch(1);

    public void testJokeRetriever(){
                 new EndpointAsync(new TestJokeListener()).execute();
        try{
            boolean success = mSignal.await(5, TimeUnit.SECONDS);
            if(!success){
                fail("Test timed out, make sure the server is actually running");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail();
        }
    }

    private class TestJokeListener implements EndpointAsync.JokeRetrievedListener{

        @Override
        public void onJokeTaskCompleted(String joke) {
            assertTrue(joke != null && joke.length() > 0);
            mSignal.countDown();
        }
    }
    public ApplicationTest() {
        super(Application.class);
    }
}