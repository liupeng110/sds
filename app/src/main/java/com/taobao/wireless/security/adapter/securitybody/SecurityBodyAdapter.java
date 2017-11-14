package com.taobao.wireless.security.adapter.securitybody;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import com.taobao.wireless.security.adapter.JNICLibrary;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@TargetApi(9)
public class SecurityBodyAdapter implements a {
    private static final String NO_VALUE = "noSuchValue";
    private static final String SHARED_PREFERENCES_NAME = "SafeGuard";
    private static Context staticContext;
    private JNICLibrary jniInstance = JNICLibrary.getInstance();

    public SecurityBodyAdapter(Context context) {
        staticContext = context;
    }

    public static String readFromSharedPreference(Context context, String str) {
        if (context == null || str == null || str.length() <= 0) {
            return null;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0);
        if (sharedPreferences == null) {
            return null;
        }
        String string = sharedPreferences.getString(str, NO_VALUE);
        return string.equals(NO_VALUE) ? null : string;
    }

    public static String sendSyncHttpRequest(String str) {
        InputStreamReader inputStreamReader;
        HttpURLConnection httpURLConnection;
        InputStreamReader inputStreamReader2;
        Throwable th;
        Throwable th2;
        Object obj;
        HttpURLConnection httpURLConnection2 = null;
        try {
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(str).openConnection();
            try {
                inputStreamReader = new InputStreamReader(httpURLConnection3.getInputStream());
                try {
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuffer stringBuffer = new StringBuffer();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuffer.append(readLine);
                    }
                    String stringBuffer2 = stringBuffer.toString();
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                    try {
                        inputStreamReader.close();
                        return stringBuffer2;
                    } catch (IOException e) {
                        return stringBuffer2;
                    }
                } catch (Exception e2) {
                    InputStreamReader inputStreamReader3 = inputStreamReader;
                    httpURLConnection = httpURLConnection3;
                    inputStreamReader2 = inputStreamReader3;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (inputStreamReader2 != null) {
                        return httpURLConnection2;
                    }
                    try {
                        inputStreamReader2.close();
                        return httpURLConnection2;
                    } catch (IOException e3) {
                        return httpURLConnection2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    httpURLConnection2 = httpURLConnection3;
                    th2 = th;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th2;
                }
            } catch (Exception e5) {
                httpURLConnection = httpURLConnection3;
                obj = httpURLConnection2;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (inputStreamReader2 != null) {
                    return httpURLConnection2;
                }
                inputStreamReader2.close();
                return httpURLConnection2;
            } catch (Throwable th4) {
                th = th4;
                Object obj2 = httpURLConnection2;
                httpURLConnection2 = httpURLConnection3;
                th2 = th;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                throw th2;
            }
        } catch (Exception e6) {
            obj = httpURLConnection2;
            httpURLConnection = httpURLConnection2;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStreamReader2 != null) {
                return httpURLConnection2;
            }
            inputStreamReader2.close();
            return httpURLConnection2;
        } catch (Throwable th5) {
            th2 = th5;
            inputStreamReader = httpURLConnection2;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            throw th2;
        }
    }

    public static void writeToSharedPreference(Context context, String str, String str2) {
        if (context != null && str != null && str.length() > 0 && str2 != null && str2.length() > 0) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0);
            if (sharedPreferences != null) {
                Editor edit = sharedPreferences.edit();
                edit.putString(str, str2);
                if (VERSION.SDK_INT >= 9) {
                    edit.apply();
                } else {
                    edit.commit();
                }
            }
        }
    }

    public String getSecurityBodyData(String str) {
        return (str == null || this.jniInstance == null) ? null : this.jniInstance.getSecurityBodyData(str, null);
    }

    public String getSecurityBodyData(String str, String str2) {
        return (str == null || str2 == null || this.jniInstance == null) ? null : this.jniInstance.getSecurityBodyData(str, str2);
    }

    public boolean initSecurityBody(String str) {
        return (str == null || this.jniInstance == null) ? false : this.jniInstance.initSecurityBody(str, staticContext.getPackageName());
    }

    public boolean putUserActionRecord(String str, String str2, float f, float f2) {
        return (str == null || str2 == null || this.jniInstance == null) ? false : this.jniInstance.putUserActionRecord(str, str2, f, f2);
    }

    public boolean putUserTrackRecord(String str) {
        return (str == null || this.jniInstance == null) ? false : this.jniInstance.putUserTrackRecord(str);
    }
}
