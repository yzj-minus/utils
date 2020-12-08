package com.yzjdev.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void show(Context context, Object o){
        show(context, o, Toast.LENGTH_SHORT);
    }
    public static void show(Context context, Object o, int time){
        Toast.makeText(context, String.valueOf(o), time).show();
    }
}
