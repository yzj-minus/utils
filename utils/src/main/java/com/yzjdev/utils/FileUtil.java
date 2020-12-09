package com.yzjdev.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {
    /**
     * pathname
     * 1. /storage/emulated/0/xxx or %xxx
     * 2. /data/data/<package_name>/files/xxx
     *
     * with context
     * 1. /storage/emulated/0/xxx or %xxx
     * 2. /data/data/<package_name>/files/xxx or $xxx
     *
     * read method supports that pathname like @xxx
     */

    public static final String charSD = "%";        //ExternalStorageDirectory symbol
    public static final String charFiles = "$";     //Files directory symbol
    public static final String charAssets = "@";    //Assets directory symbol


    public static File file(String pathname){
        return new File(pathname);
    }

    public static boolean exists(String pathname){
        return file(pathname).exists();
    }

    public static boolean delete(String pathname){
         return file(pathname).delete();
    }

    public static boolean delete(String pathname, boolean z){
        return delete(file(pathname), z);
    }

    /**
     * delete file
     * @param file file or directory
     * @param z whether to delete subfiles
     * @return bool
     */
    private static boolean delete(File file, boolean z){
        if (!z) return file.delete();
        if (file.isFile()) return file.delete();
        File[] files = file.listFiles();
        for (File f: files){
            delete(f, z);
        }
        return file.delete();
    }

    public static long size(String pathname){
        return file(pathname).length();
    }

    public static boolean copy(Context context, String source, String dest){
        if (file(getPath(context, source)).isDirectory()) return false;
        InputStream inputStream=null;
        OutputStream outputStream=null;
        try {
            mkdirs(context, dest);
            inputStream = new FileInputStream(getPath(context, source));
            outputStream = new FileOutputStream(getPath(context, dest));
            byte[] buffer = new byte[1024];
            int len;
            while ((len=inputStream.read(buffer))!=-1){
                outputStream.write(buffer, 0, len);
            }
            inputStream.close();
            outputStream.close();
            return true;
        } catch (Exception ignored){ }
        return false;
    }

    /**
     * 读取文本
     * @param context 上下文
     * @param pathname 路径 Adjust five type
     *                 1. /storage/emulated/0/xxx or %xxx
     *                 2. /data/data/<package_name>/files/xxx or $xxx
     *                 3. Assets文件 @xxx
     * @param charset 字符编码
     * @return 内容
     */
    public static String read(Context context, String pathname, String charset) {
        String content = null;
        try {
            if (pathname.startsWith(charAssets))
                content = StreamUtil.read(context.getAssets().open(pathname.substring(1)));
            else
                content = StreamUtil.read(new FileInputStream(getPath(context, pathname)));
        }catch (Exception ignored){}
        return content;
    }


    /**
     * 写入文本
     * @param context 上下文
     * @param pathname 路径 Adjust four type
     *                 1. /storage/emulated/0/xxx or %xxx
     *                 2. /data/data/<package_name>/files/xxx or $xxx
     * @param content 内容
     */
    public static void write(Context context, String pathname, String content){
        try {
            mkdirs(context, pathname);
            StreamUtil.write(new FileOutputStream(getPath(context, pathname)), content);
        }catch (Exception ignored){ }
    }

    public static boolean mkdirs(String pathname){
        return file(getPath(pathname)).getParentFile().mkdirs();
    }

    public static boolean mkdirs(Context context, String pathname){
        return file(getPath(context, pathname)).getParentFile().mkdirs();
    }

    public static File getExternalStorageDirectory(){
        return Environment.getExternalStorageDirectory();
    }

    public static File getFilesDir(Context context){
        return context.getFilesDir();
    }

    /**
     * get path
     * @param pathname pathname Adjust three type.
     *                 1. /storage/emulated/0/xxx or %xxx
     *                 2. /data/data/<package_name>/files/xxx
     * @return path
     */
    public static String getPath(String pathname){
        return getPath(null, pathname);
    }


    /**
     * get path
     * @param context context
     * @param pathname pathname Adjust four type.
     *                 1. /storage/emulated/0/xxx or %xxx
     *                 2. /data/data/<package_name>/files/xxx or $xxx
     * @return path
     */
    public static String getPath(Context context, String pathname){
       if (pathname.startsWith(charSD)){
           return getExternalStorageDirectory()+"/"+pathname.substring(1);
       } else if (pathname.startsWith(charFiles)){
           if (context!=null)
               return getFilesDir(context)+"/"+pathname.substring(1);
           else
               return pathname;
       } else{
           return pathname;
       }
    }

}
