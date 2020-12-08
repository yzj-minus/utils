package com.yzjdev.utils;

import android.util.Log;

public class LogUtil {
    public static boolean debug = false;
    public static String tag = "aaa";

    public static void Debug(){
        debug=true;
    }
    public static void d(Object o){
        d(tag, o);
    }
    public static void d(String tag, Object o){
        if (debug) Log.d(tag, String.valueOf(o));
    }
}
