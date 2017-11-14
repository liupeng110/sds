package com.taobao.wireless.security.adapter.dynamicupdatelib;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Proxy;
import android.os.Build.VERSION;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class DynamicUpdateLibAdapter {
    private static Context a = null;

    private static HttpClient a() {
        String property;
        int i = 0;
        if (VERSION.SDK_INT > 14) {
            property = System.getProperty("http.proxyHost", null);
            if (property == null || property.length() <= 0) {
                property = null;
            } else {
                String property2 = System.getProperty("http.proxyPort", null);
                if (property2 != null && property2.length() > 0) {
                    try {
                        i = Integer.valueOf(property2).intValue();
                    } catch (NumberFormatException e) {
                    }
                }
                property = null;
            }
        } else {
            property = Proxy.getDefaultHost();
            if (property == null || property.length() <= 0) {
                property = null;
            } else {
                i = Proxy.getDefaultPort();
            }
        }
        if (property == null || i == -1) {
            return new DefaultHttpClient();
        }
        HttpHost httpHost = new HttpHost(property, i);
        HttpParams basicHttpParams = new BasicHttpParams();
        basicHttpParams.setParameter("http.route.default-proxy", httpHost);
        return new DefaultHttpClient(basicHttpParams);
    }

    public static void cleanUnusedSoFile(String str) {
        int i = 0;
        String downloadSoFolderDir = getDownloadSoFolderDir();
        if (downloadSoFolderDir != null) {
            try {
                File file = new File(downloadSoFolderDir);
                int i2 = str == null ? 1 : 0;
                if (file.exists()) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null && listFiles.length > 0) {
                        int length = listFiles.length;
                        while (i < length) {
                            File file2 = listFiles[i];
                            if (i2 != 0) {
                                file2.delete();
                            } else if (!str.equals(file2.getAbsolutePath())) {
                                file2.delete();
                            }
                            i++;
                        }
                    }
                }
            } catch (Throwable th) {
            }
        }
    }

    public static String getDownloadSoFolderDir() {
        File file;
        try {
            file = new File(a.getFilesDir().getAbsolutePath() + File.separator + "/sofile");
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!file.exists()) {
                file = null;
            }
        } catch (Throwable th) {
            file = null;
        }
        return file != null ? file.getAbsolutePath() : null;
    }

    public static long getLastSoDynamicUpdateTime() {
        int i = 0;
        if (VERSION.SDK_INT >= 11) {
            i = 4;
        }
        SharedPreferences sharedPreferences = a.getSharedPreferences("DynamicUpdateDataPreferences", i);
        return sharedPreferences != null ? sharedPreferences.getLong("dynamicSoUpdateTimeStamp", 0) : 0;
    }

    public static String getMiniVersion() {
        int i = 0;
        if (VERSION.SDK_INT >= 11) {
            i = 4;
        }
        SharedPreferences sharedPreferences = a.getSharedPreferences("DynamicUpdateDataPreferences", i);
        return sharedPreferences != null ? sharedPreferences.getString("miniVersion", null) : null;
    }

    public static byte[] querySoUpdateInfo(String str, String str2, byte[] bArr) {
        byte[] bArr2 = null;
        if (!(str == null || str.length() == 0 || str2 == null || str2.length() == 0 || bArr == null || bArr.length == 0)) {
            HttpClient a = a();
            HttpUriRequest httpPost = new HttpPost(str);
            httpPost.addHeader("keyindex", str2);
            httpPost.setEntity(new ByteArrayEntity(bArr));
            try {
                HttpResponse execute = a.execute(httpPost);
                if (execute.getStatusLine().getStatusCode() / 200 == 1) {
                    bArr2 = EntityUtils.toByteArray(execute.getEntity());
                    try {
                        a.getConnectionManager().shutdown();
                    } catch (Throwable th) {
                    }
                } else {
                    try {
                        a.getConnectionManager().shutdown();
                    } catch (Throwable th2) {
                    }
                }
            } catch (ClientProtocolException e) {
                a.getConnectionManager().shutdown();
            } catch (IOException e2) {
                a.getConnectionManager().shutdown();
            } catch (Exception e3) {
                a.getConnectionManager().shutdown();
            } catch (Throwable th3) {
            }
        }
        return bArr2;
    }

    public static void setApplicationContext(Context context) {
        a = context;
    }

    public static String startSoDownload(String str) {
        InputStream inputStream;
        HttpURLConnection httpURLConnection;
        Throwable th;
        Throwable th2;
        Object obj;
        Object obj2;
        OutputStream outputStream = null;
        if (str == null || str.length() <= 0) {
            return outputStream;
        }
        InputStream inputStream2;
        try {
            String substring;
            HttpURLConnection httpURLConnection2;
            File cacheDir;
            File file;
            OutputStream fileOutputStream;
            byte[] bArr;
            int read;
            String absolutePath;
            URL url = new URL(str);
            String path = url.getPath();
            if (path != null && path.length() > 0) {
                int lastIndexOf = path.lastIndexOf("/");
                if (lastIndexOf > 0 && lastIndexOf + 1 < path.length()) {
                    substring = path.substring(lastIndexOf + 1);
                    if (substring == null) {
                        return outputStream;
                    }
                    httpURLConnection2 = (HttpURLConnection) url.openConnection();
                    try {
                        inputStream2 = httpURLConnection2.getInputStream();
                        if (inputStream2 != null) {
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (Exception e) {
                                }
                            }
                            if (httpURLConnection2 != null) {
                                try {
                                    httpURLConnection2.disconnect();
                                } catch (Exception e2) {
                                }
                            }
                            return outputStream;
                        }
                        try {
                            cacheDir = a.getCacheDir();
                            if (!cacheDir.exists()) {
                                cacheDir.mkdirs();
                            }
                            if (cacheDir.exists()) {
                                if (inputStream2 != null) {
                                    try {
                                        inputStream2.close();
                                    } catch (Exception e3) {
                                    }
                                }
                                if (httpURLConnection2 != null) {
                                    try {
                                        httpURLConnection2.disconnect();
                                    } catch (Exception e4) {
                                    }
                                }
                                return outputStream;
                            }
                            file = new File(cacheDir.getAbsoluteFile() + File.separator + substring);
                            if (file.exists()) {
                                file.delete();
                            }
                            file.createNewFile();
                            fileOutputStream = new FileOutputStream(file);
                            try {
                                bArr = new byte[1024];
                                while (true) {
                                    read = inputStream2.read(bArr);
                                    if (read != -1) {
                                        break;
                                    }
                                    fileOutputStream.write(bArr, 0, read);
                                }
                                absolutePath = file.getAbsolutePath();
                                if (inputStream2 != null) {
                                    try {
                                        inputStream2.close();
                                    } catch (Exception e5) {
                                    }
                                }
                                try {
                                    fileOutputStream.close();
                                } catch (Exception e6) {
                                }
                                if (httpURLConnection2 != null) {
                                    try {
                                        httpURLConnection2.disconnect();
                                    } catch (Exception e7) {
                                    }
                                }
                                return absolutePath;
                            } catch (Throwable th3) {
                                th = th3;
                                outputStream = fileOutputStream;
                                r2 = httpURLConnection2;
                                th2 = th;
                                if (inputStream2 != null) {
                                    try {
                                        inputStream2.close();
                                    } catch (Exception e8) {
                                    }
                                }
                                if (outputStream != null) {
                                    try {
                                        outputStream.close();
                                    } catch (Exception e9) {
                                    }
                                }
                                if (r2 != null) {
                                    try {
                                        r2.disconnect();
                                    } catch (Exception e10) {
                                    }
                                }
                                throw th2;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            r2 = httpURLConnection2;
                            th2 = th;
                            if (inputStream2 != null) {
                                inputStream2.close();
                            }
                            if (outputStream != null) {
                                outputStream.close();
                            }
                            if (r2 != null) {
                                r2.disconnect();
                            }
                            throw th2;
                        }
                    } catch (Throwable th42) {
                        obj2 = outputStream;
                        th = th42;
                        r2 = httpURLConnection2;
                        th2 = th;
                        if (inputStream2 != null) {
                            inputStream2.close();
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (r2 != null) {
                            r2.disconnect();
                        }
                        throw th2;
                    }
                }
            }
            obj = outputStream;
            if (substring == null) {
                return outputStream;
            }
            httpURLConnection2 = (HttpURLConnection) url.openConnection();
            inputStream2 = httpURLConnection2.getInputStream();
            if (inputStream2 != null) {
                cacheDir = a.getCacheDir();
                if (cacheDir.exists()) {
                    cacheDir.mkdirs();
                }
                if (cacheDir.exists()) {
                    file = new File(cacheDir.getAbsoluteFile() + File.separator + substring);
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    fileOutputStream = new FileOutputStream(file);
                    bArr = new byte[1024];
                    while (true) {
                        read = inputStream2.read(bArr);
                        if (read != -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    absolutePath = file.getAbsolutePath();
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    fileOutputStream.close();
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    return absolutePath;
                }
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                return outputStream;
            }
            if (inputStream2 != null) {
                inputStream2.close();
            }
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            return outputStream;
        } catch (Throwable th5) {
            th2 = th5;
            inputStream2 = outputStream;
            r2 = outputStream;
            if (inputStream2 != null) {
                inputStream2.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            HttpURLConnection httpURLConnection3;
            if (httpURLConnection3 != null) {
                httpURLConnection3.disconnect();
            }
            throw th2;
        }
    }

    public static void updateMiniVersion(String str) {
        int i = 0;
        if (VERSION.SDK_INT >= 11) {
            i = 4;
        }
        SharedPreferences sharedPreferences = a.getSharedPreferences("DynamicUpdateDataPreferences", i);
        if (sharedPreferences != null) {
            Editor edit = sharedPreferences.edit();
            edit.putString("miniVersion", str);
            edit.commit();
        }
    }

    public static void updateTimeStamp() {
        int i = 0;
        if (VERSION.SDK_INT >= 11) {
            i = 4;
        }
        SharedPreferences sharedPreferences = a.getSharedPreferences("DynamicUpdateDataPreferences", i);
        if (sharedPreferences != null) {
            Editor edit = sharedPreferences.edit();
            edit.putLong("dynamicSoUpdateTimeStamp", System.currentTimeMillis() / 1000);
            edit.commit();
        }
    }
}
