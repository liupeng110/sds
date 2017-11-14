package com.tencent.a.c;

import java.io.File;

/* ProGuard */
public class a {
    public static boolean a(File file) {
        int i = 0;
        if (file == null) {
            return false;
        }
        if (file.isFile()) {
            if (file.delete()) {
                return true;
            }
            file.deleteOnExit();
            return false;
        } else if (!file.isDirectory()) {
            return false;
        } else {
            File[] listFiles = file.listFiles();
            int length = listFiles.length;
            while (i < length) {
                a(listFiles[i]);
                i++;
            }
            return file.delete();
        }
    }
}
