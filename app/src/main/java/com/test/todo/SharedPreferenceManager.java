package com.test.todo;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {
    private SharedPreferenceManager() {
    }

    public static SharedPreferenceManager getInstance() {
        return new SharedPreferenceManager();
    }

    public void saveValue(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void clearValue(String key) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.remove(key);
        editor.apply();
    }

    public void clearUserData() {
        getSharedPreferences().getAll().clear();
    }

    public String getValue(String key) {
        return getSharedPreferences().getString(key, "");
    }


    private SharedPreferences getSharedPreferences() {
        String PREF_NAME = BuildConfig.APPLICATION_ID + ".Prefs";
        return ToDoApplication.context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }
}
