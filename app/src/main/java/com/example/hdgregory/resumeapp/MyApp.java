package com.example.hdgregory.resumeapp;

import android.app.Application;
import com.example.hdgregory.resumeapp.Data.ScheduleManager;

public class MyApp extends Application {

    private ScheduleManager scheduleManager;

    @Override
    public void onCreate() {
        super.onCreate();
        scheduleManager = new ScheduleManager(getApplicationContext());
    }

    public ScheduleManager getScheduleManager() {
        return scheduleManager;
    }
}