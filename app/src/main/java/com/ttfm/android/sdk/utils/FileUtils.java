package com.ttfm.android.sdk.utils;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;

public class FileUtils {
    public static final int MODE_COVER = 1;
    public static final int MODE_UNCOVER = 0;

    public static void saveFile(String str, String str2) {
        ObjectOutputStream objectOutputStream;
        Exception e;
        Throwable th;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(str));
            try {
                objectOutputStream.writeUTF(str2);
                objectOutputStream.flush();
                objectOutputStream.close();
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e5) {
            e = e5;
            objectOutputStream = null;
            e.printStackTrace();
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        } catch (Throwable th3) {
            th = th3;
            objectOutputStream = null;
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            throw th;
        }
    }

    public static String readFile(String str) {
        ObjectInputStream objectInputStream;
        String readUTF;
        IOException e;
        FileNotFoundException e2;
        Throwable th;
        StreamCorruptedException e3;
        FileNotFoundException fileNotFoundException;
        StreamCorruptedException streamCorruptedException;
        IOException iOException;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(str));
            try {
                readUTF = objectInputStream.readUTF();
                try {
                    objectInputStream.close();
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                } catch (FileNotFoundException e5) {
                    e2 = e5;
                    try {
                        e2.printStackTrace();
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e42) {
                                e42.printStackTrace();
                            }
                        }
                        return readUTF;
                    } catch (Throwable th2) {
                        th = th2;
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e422) {
                                e422.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (StreamCorruptedException e6) {
                    e3 = e6;
                    e3.printStackTrace();
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e4222) {
                            e4222.printStackTrace();
                        }
                    }
                    return readUTF;
                } catch (IOException e7) {
                    e4222 = e7;
                    e4222.printStackTrace();
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e42222) {
                            e42222.printStackTrace();
                        }
                    }
                    return readUTF;
                }
            } catch (FileNotFoundException e8) {
                fileNotFoundException = e8;
                readUTF = null;
                e2 = fileNotFoundException;
                e2.printStackTrace();
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                return readUTF;
            } catch (StreamCorruptedException e9) {
                streamCorruptedException = e9;
                readUTF = null;
                e3 = streamCorruptedException;
                e3.printStackTrace();
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                return readUTF;
            } catch (IOException e10) {
                iOException = e10;
                readUTF = null;
                e42222 = iOException;
                e42222.printStackTrace();
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                return readUTF;
            }
        } catch (FileNotFoundException e82) {
            objectInputStream = null;
            fileNotFoundException = e82;
            readUTF = null;
            e2 = fileNotFoundException;
            e2.printStackTrace();
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            return readUTF;
        } catch (StreamCorruptedException e92) {
            objectInputStream = null;
            streamCorruptedException = e92;
            readUTF = null;
            e3 = streamCorruptedException;
            e3.printStackTrace();
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            return readUTF;
        } catch (IOException e102) {
            objectInputStream = null;
            iOException = e102;
            readUTF = null;
            e42222 = iOException;
            e42222.printStackTrace();
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            return readUTF;
        } catch (Throwable th3) {
            th = th3;
            objectInputStream = null;
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            throw th;
        }
        return readUTF;
    }

    public static FileInputStream getFileInputStream(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return new FileInputStream(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static OutputStream getFileOutputStream(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return new FileOutputStream(file);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] getFileData(String str) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                return null;
            }
            byte[] bArr = new byte[((int) file.length())];
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bArr);
            fileInputStream.close();
            return bArr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getFileSize(String str) {
        return (int) new File(str).length();
    }

    public static void rewriteData(String str, byte[] bArr) {
        try {
            File file = new File(str);
            if (file.exists()) {
                FileOutputStream fileOutputStream = new FileOutputStream(file, false);
                fileOutputStream.write(bArr);
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void rewriteData(String str, InputStream inputStream) {
        try {
            File file = new File(str);
            if (file.exists()) {
                FileOutputStream fileOutputStream = new FileOutputStream(file, false);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                        fileOutputStream.flush();
                    } else {
                        fileOutputStream.close();
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean appendData(String str, byte[] bArr) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write(bArr);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void appendData(String str, InputStream inputStream) {
        try {
            File file = new File(str);
            if (file.exists()) {
                FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                        fileOutputStream.flush();
                    } else {
                        fileOutputStream.close();
                        return;
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public static void deleteFile(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                File file = new File(str);
                if (file.exists() && file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        for (File absolutePath : listFiles) {
                            deleteFile(absolutePath.getAbsolutePath());
                        }
                    }
                }
                file.delete();
            } catch (Exception e) {
            }
        }
    }

    public static void deleteFile(String str, boolean z) {
        if (str != null) {
            try {
                File file = new File(str);
                if (file.exists() && file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        for (File absolutePath : listFiles) {
                            deleteFile(absolutePath.getAbsolutePath(), z);
                        }
                    }
                }
                if (z) {
                    file.delete();
                } else if (file.isFile()) {
                    file.delete();
                }
            } catch (Exception e) {
            }
        }
    }

    public static boolean createFile(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            File file = new File(str);
            if (!file.exists()) {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                file.createNewFile();
            } else if (i == 1) {
                file.delete();
                file.createNewFile();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void createFolder(String str, int i) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            } else if (i == 1) {
                file.delete();
                file.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long getSize(String str) {
        long j = 0;
        if (!TextUtils.isEmpty(str)) {
            try {
                File file = new File(str);
                if (file.exists()) {
                    j = file.length();
                }
            } catch (Exception e) {
            }
        }
        return j;
    }

    public static boolean isExist(String str) {
        boolean z = false;
        if (!TextUtils.isEmpty(str)) {
            try {
                z = new File(str).exists();
            } catch (Exception e) {
            }
        }
        return z;
    }

    public static boolean rename(String str, String str2) {
        boolean z = false;
        if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
            try {
                File file = new File(str);
                if (file.exists()) {
                    z = file.renameTo(new File(str2));
                }
            } catch (Exception e) {
            }
        }
        return z;
    }

    public static File[] listFiles(String str) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            return file.listFiles();
        }
        return null;
    }

    public static void copyFile(String str, String str2) {
        int i = 0;
        try {
            if (!str.equals(str2)) {
                deleteFile(str2);
                if (new File(str).exists()) {
                    createFile(str2, 0);
                    InputStream fileInputStream = new FileInputStream(str);
                    FileOutputStream fileOutputStream = new FileOutputStream(str2);
                    byte[] bArr = new byte[1444];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read != -1) {
                            i += read;
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            fileInputStream.close();
                            fileOutputStream.close();
                            return;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyFolder(String str, String str2) {
        try {
            new File(str2).mkdirs();
            String[] list = new File(str).list();
            for (int i = 0; i < list.length; i++) {
                File file;
                if (str.endsWith(File.separator)) {
                    file = new File(str + list[i]);
                } else {
                    file = new File(str + File.separator + list[i]);
                }
                if (file.isFile()) {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    FileOutputStream fileOutputStream = new FileOutputStream(str2 + "/" + file.getName().toString());
                    byte[] bArr = new byte[5120];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    fileInputStream.close();
                }
                if (file.isDirectory()) {
                    copyFolder(str + "/" + list[i], str2 + "/" + list[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String ExtractRawResourse(Context context, String str, int i) {
        Object obj = 1;
        File file = new File(str);
        InputStream openRawResource = context.getResources().openRawResource(i);
        if (file.exists()) {
            long j = 0;
            try {
                j = (long) openRawResource.available();
            } catch (IOException e) {
            }
            if (file.length() == j) {
                obj = null;
            }
        }
        if (obj != null) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(str);
                byte[] bArr = new byte[10240];
                while (true) {
                    int read = openRawResource.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.close();
                openRawResource.close();
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }
}
