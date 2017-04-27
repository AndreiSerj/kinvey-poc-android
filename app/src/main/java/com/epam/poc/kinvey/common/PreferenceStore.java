package com.epam.poc.kinvey.common;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceStore {

    private static final String SECURE_STORE = "secure_store";
    public static String ACCESS_TOKEN_KEY = "accessToken";

    private SharedPreferences mSharedPref;

    public PreferenceStore(Context context) {
        mSharedPref = context.getSharedPreferences(SECURE_STORE, Context.MODE_PRIVATE);
    }

    public void storeString(String key, String value) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void storeLong(String key, long value) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public void storeBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public String loadString(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public String loadString(String key) {
        return mSharedPref.getString(key, null);
    }

    public long loadLong(String key) {
        return mSharedPref.getLong(key, 0);
    }

    public boolean loadBoolean(String key, boolean value) {
        return mSharedPref.getBoolean(key, value);
    }

    public void clearStore() {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.clear();
        editor.commit();
    }

    public void delete(String key) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.remove(key);
        editor.commit();
    }

}
