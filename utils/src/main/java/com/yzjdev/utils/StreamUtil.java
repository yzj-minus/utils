package com.yzjdev.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class StreamUtil {
    public static String read(InputStream inputStream)throws Exception{
        String content = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        while ((line=reader.readLine())!=null){
            stringBuilder.append(line);
        }
        content = stringBuilder.toString();
        reader.close();
        return content;
    }

    public static void write(OutputStream outputStream, String content)throws Exception{
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write(content);
        writer.close();
    }
}
