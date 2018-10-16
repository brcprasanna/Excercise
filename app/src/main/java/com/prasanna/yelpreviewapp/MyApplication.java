package com.prasanna.yelpreviewapp;

import android.app.Application;

import com.prasanna.yelpreviewapp.utils.DataManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataManager.getInstance().init();
    }
}
