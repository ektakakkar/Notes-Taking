package com.example.ekta.notes_taking;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by EKTA on 20/03/16.
 */
public class MyApplication extends com.activeandroid.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();                            //as per the documnetation it is mandatory to initialize the library
        ActiveAndroid.initialize(this);
    }
}
