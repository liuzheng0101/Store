package com.example.lib_core.utils;

import android.util.Log;

import com.example.lib_core.common.Constants;

/**
 * 封装日志
 */
public class LogUtils {
    private static String TAG=LogUtils.class.getSimpleName();
    public static void d(String msg){
        if (!Constants.IS_RELEASE){
            Log.d(TAG,msg);
        }
    }
    public static void e(String msg){
        if (!Constants.IS_RELEASE){
            Log.e(TAG,msg);
        }
    }
}
