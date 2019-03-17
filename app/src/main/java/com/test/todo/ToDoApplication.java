package com.test.todo;

import android.app.Application;
import android.content.Context;

public class ToDoApplication extends Application {
    public static Context context;

    public static Context getAppContext() {
        return ToDoApplication.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ToDoApplication.context = getApplicationContext();
    }
}
