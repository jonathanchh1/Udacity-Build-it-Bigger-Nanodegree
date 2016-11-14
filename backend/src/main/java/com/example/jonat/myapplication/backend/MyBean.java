package com.example.jonat.myapplication.backend;


/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private String myData;

    public String getData() {
        return myData;
    }

    public void setMyJoke(String data) {
        myData = data;
    }
}