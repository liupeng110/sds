package com.taobao.dp.service;

import android.os.Environment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public final class g {
    public static String a(String str) {
        try {
            String b = b(str);
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(b))));
            b = bufferedReader.readLine();
            if (b == null) {
                b = "";
            }
            stringBuilder.append(b);
            bufferedReader.close();
            return stringBuilder.toString();
        } catch (IOException e) {
            new StringBuilder("ExternalFileManager RUMID").append(e);
            return "";
        }
    }

    public static void a(String str, String str2) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(b(str)), false);
            fileOutputStream.write(str2.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            new StringBuilder("ExternalFileManager WUMID").append(e);
        }
    }

    private static String b(String str) {
        String absolutePath;
        File file;
        if (Environment.getExternalStorageState().equals("mounted")) {
            File file2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/.com.taobao.dp");
            if (file2.exists() || file2.mkdir()) {
                absolutePath = file2.getAbsolutePath();
                if (absolutePath != null) {
                    file = new File(absolutePath, str);
                    if (file.exists() || file.createNewFile()) {
                        return file.getAbsolutePath();
                    }
                }
                return "";
            }
        }
        absolutePath = null;
        if (absolutePath != null) {
            file = new File(absolutePath, str);
            return file.getAbsolutePath();
        }
        return "";
    }
}
