package com.sp.spmultipleapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by 123 on 2017/11/13.
 */

public class SpUtils
{
    private static final String TAG = "SpUtils";
    private static final String SHAREDPREFERENCES_NAME = "test";
    static public int getInt(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(key, 0);
    }
    /*synchronized*/ static public void putInt(Context context, String key, int value) {
        Log.d(TAG,">>putInt>>key:" + key  + ",value:" + value);
        SharedPreferences prefs = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        prefs.edit().putInt(key, value).commit();
    }
}
