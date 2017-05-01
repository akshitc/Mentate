package com.everlong.mentate.util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by akshit on 3/16/17.
 */

public class SharedPreferenceManager implements KeyValueStore {

    private static final String PREF_NAME = "com.everlong.mentate.SharedPrefs";

    private final SharedPreferences sharedPreferences;

    public SharedPreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
    }

    @Override
    public void put(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    @Override
    public void put(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    @Override
    public void put(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    @Override
    public void put(String key, Long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }

    @Override
    public String get(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    @Override
    public void remove(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    @Override
    public void clear() {
        sharedPreferences.edit().clear().apply();
    }
}
