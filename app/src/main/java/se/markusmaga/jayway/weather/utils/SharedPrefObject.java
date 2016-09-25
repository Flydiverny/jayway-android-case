package se.markusmaga.jayway.weather.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Flydiverny on 25/09/16.
 */
public abstract class SharedPrefObject {

    protected SharedPreferences mPreferences;

    public SharedPrefObject(Context context, String tag) {
        mPreferences = context.getSharedPreferences(tag, Context.MODE_PRIVATE);
    }

    protected void edit(String key, Date value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putLong(key, value.getTime());
        editor.apply();
    }

    protected void edit(String key, String value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    protected void edit(String key, long value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    protected void edit(String key, int value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    protected void edit(String key, boolean value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    protected void nullInt(SharedPreferences.Editor e, String key, Integer value) {
        if(value == null) return;

        e.putInt(key, value);
    }

    protected void nullBoolean(SharedPreferences.Editor e, String key, Boolean value) {
        if(value == null) return;

        e.putBoolean(key, value);
    }

    protected void nullString(SharedPreferences.Editor e, String key, String value) {
        if(value == null) return;

        e.putString(key, value);
    }

    protected String getStringOrEmpty(String key) {
        return mPreferences.getString(key, "");
    }

    protected Set<String> getStringSet(String key) {
        return mPreferences.getStringSet(key, new TreeSet<String>());
    }

    public void clear() {
        mPreferences.edit().clear().apply();
    }
}