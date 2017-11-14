package com.sds.android.sdk.lib.util;

import com.sds.android.sdk.lib.d.a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* ZIPUtils */
public class p {
    public static a a(String str) throws IOException {
        OutputStream aVar = new a(str.length());
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(aVar);
        gZIPOutputStream.write(str.getBytes(), 0, str.getBytes().length);
        gZIPOutputStream.close();
        return aVar;
    }

    public static InputStream a(InputStream inputStream) throws IOException {
        return new GZIPInputStream(inputStream);
    }

    public static void a(InputStream inputStream, String str) throws Exception {
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry != null) {
                String name = nextEntry.getName();
                if (nextEntry.isDirectory()) {
                    new File(str + File.separator + name.substring(0, name.length() - 1)).mkdirs();
                } else {
                    File file = new File(str + File.separator + name);
                    if (!(file.getParentFile() == null || file.exists())) {
                        file.getParentFile().mkdirs();
                    }
                    file.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = zipInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                        fileOutputStream.flush();
                    }
                    fileOutputStream.close();
                }
            } else {
                zipInputStream.close();
                return;
            }
        }
    }
}
