package com.yzjdev.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtil {
    public static File file(String pathName){
        return new File(pathName);
    }

    public static boolean exists(String pathName){
        return file(pathName).exists();
    }

    public static boolean delete(String pathName){
         return file(pathName).delete();
    }

    public static int delete(String pathName, boolean z){
        return 0;
    }

    public static long size(String pathName){
        return file(pathName).length();
    }


    /**
     * 读取文本
     * @param context 上下文
     * @param pathName 路径
     *                 1. /storage/emulated/0/xxx or %xxx
     *                 2. /data/data/<package_name>/files/xxx or $xxx
     *                 3. Assets文件 @xxx
     * @param charset 字符编码
     * @return 内容
     */
    public static String read(Context context, String pathName, String charset) {
        String content = null;
        try {
            if (pathName.startsWith("@"))
                content = StreamUtil.read(context.getAssets().open(pathName.substring(1)));
            else
                content = StreamUtil.read(new FileInputStream(getPath(context, pathName)));
        }catch (Exception ignored){}
        return content;
    }


    /**
     * 写入文本
     * @param context 上下文
     * @param pathName 路径
     *                 1. /storage/emulated/0/xxx or %xxx
     *                 2. /data/data/<package_name>/files/xxx or $xxx
     * @param content 内容
     */
    public static void write(Context context, String pathName, String content){
        try {
            mkdirs(context, pathName);
            StreamUtil.write(new FileOutputStream(getPath(context, pathName)), content);
        }catch (Exception ignored){ }
    }

    public static boolean mkdirs(String pathName){
        return file(getPath(pathName)).getParentFile().mkdirs();
    }

    public static boolean mkdirs(Context context, String pathName){
        return file(getPath(context, pathName)).getParentFile().mkdirs();
    }

    public static File getExternalStorageDirectory(){
        return Environment.getExternalStorageDirectory();
    }

    public static File getFilesDir(Context context){
        return context.getFilesDir();
    }

    public static String getPath(String pathName){
        if (pathName.startsWith("%"))
            return getExternalStorageDirectory()+"/"+pathName.substring(1);
        else
            return pathName;
    }

    public static String getPath(Context context, String pathName){
        if (pathName.startsWith("$"))
            return getFilesDir(context)+"/"+pathName.substring(1);
        else
            return getPath(pathName);
    }
}
