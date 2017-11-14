package com.tencent.utils;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* ProGuard */
public class OpenConfig {
    private static HashMap<String, OpenConfig> a = null;
    private static String b = null;
    private Context c = null;
    private String d = null;
    private JSONObject e = null;
    private long f = 0;
    private int g = 0;
    private boolean h = true;

    public static OpenConfig getInstance(Context context, String str) {
        if (a == null) {
            a = new HashMap();
        }
        if (str != null) {
            b = str;
        }
        if (str == null) {
            if (b != null) {
                str = b;
            } else {
                str = FeedbackItem.STATUS_WAITING;
            }
        }
        OpenConfig openConfig = (OpenConfig) a.get(str);
        if (openConfig != null) {
            return openConfig;
        }
        openConfig = new OpenConfig(context, str);
        a.put(str, openConfig);
        return openConfig;
    }

    private OpenConfig(Context context, String str) {
        this.c = context;
        this.d = str;
        a();
        b();
    }

    private void a() {
        try {
            this.e = new JSONObject(a("com.tencent.open.config.json"));
        } catch (JSONException e) {
            this.e = new JSONObject();
        }
    }

    /* JADX err: Inconsistent code. */
    private java.lang.String a(java.lang.String r6) {
        /*
        r5 = this;
        r1 = "";
        r0 = r5.d;	 Catch:{ FileNotFoundException -> 0x004c }
        if (r0 == 0) goto L_0x004a;
    L_0x0006:
        r0 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x004c }
        r0.<init>();	 Catch:{ FileNotFoundException -> 0x004c }
        r0 = r0.append(r6);	 Catch:{ FileNotFoundException -> 0x004c }
        r2 = ".";
        r0 = r0.append(r2);	 Catch:{ FileNotFoundException -> 0x004c }
        r2 = r5.d;	 Catch:{ FileNotFoundException -> 0x004c }
        r0 = r0.append(r2);	 Catch:{ FileNotFoundException -> 0x004c }
        r0 = r0.toString();	 Catch:{ FileNotFoundException -> 0x004c }
    L_0x001f:
        r2 = r5.c;	 Catch:{ FileNotFoundException -> 0x004c }
        r0 = r2.openFileInput(r0);	 Catch:{ FileNotFoundException -> 0x004c }
    L_0x0025:
        r3 = new java.io.BufferedReader;
        r2 = new java.io.InputStreamReader;
        r2.<init>(r0);
        r3.<init>(r2);
        r2 = new java.lang.StringBuffer;
        r2.<init>();
    L_0x0034:
        r4 = r3.readLine();	 Catch:{ IOException -> 0x003e }
        if (r4 == 0) goto L_0x005e;
    L_0x003a:
        r2.append(r4);	 Catch:{ IOException -> 0x003e }
        goto L_0x0034;
    L_0x003e:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ all -> 0x0076 }
        r0.close();	 Catch:{ IOException -> 0x0070 }
        r3.close();	 Catch:{ IOException -> 0x0070 }
        r0 = r1;
    L_0x0049:
        return r0;
    L_0x004a:
        r0 = r6;
        goto L_0x001f;
    L_0x004c:
        r0 = move-exception;
        r0 = r5.c;	 Catch:{ IOException -> 0x0058 }
        r0 = r0.getAssets();	 Catch:{ IOException -> 0x0058 }
        r0 = r0.open(r6);	 Catch:{ IOException -> 0x0058 }
        goto L_0x0025;
    L_0x0058:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r1;
        goto L_0x0049;
    L_0x005e:
        r1 = r2.toString();	 Catch:{ IOException -> 0x003e }
        r0.close();	 Catch:{ IOException -> 0x006a }
        r3.close();	 Catch:{ IOException -> 0x006a }
        r0 = r1;
        goto L_0x0049;
    L_0x006a:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r1;
        goto L_0x0049;
    L_0x0070:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r1;
        goto L_0x0049;
    L_0x0076:
        r1 = move-exception;
        r0.close();	 Catch:{ IOException -> 0x007e }
        r3.close();	 Catch:{ IOException -> 0x007e }
    L_0x007d:
        throw r1;
    L_0x007e:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x007d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.utils.OpenConfig.a(java.lang.String):java.lang.String");
    }

    private void a(String str, String str2) {
        try {
            if (this.d != null) {
                str = str + "." + this.d;
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.c.openFileOutput(str, 0));
            outputStreamWriter.write(str2);
            outputStreamWriter.flush();
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void b() {
        if (this.g != 0) {
            b("update thread is running, return");
            return;
        }
        this.g = 1;
        final Bundle bundle = new Bundle();
        bundle.putString("appid", this.d);
        bundle.putString("appid_for_getting_config", this.d);
        bundle.putString("status_os", VERSION.RELEASE);
        bundle.putString("status_machine", Build.MODEL);
        bundle.putString("status_version", VERSION.SDK);
        bundle.putString("sdkv", Constants.SDK_VERSION);
        bundle.putString("sdkp", "a");
        new Thread(this) {
            final /* synthetic */ OpenConfig b;

            public void run() {
                try {
                    this.b.a(Util.parseJson(HttpUtils.openUrl2(this.b.c, "http://cgi.connect.qq.com/qqconnectopen/openapi/policy_conf", Constants.HTTP_GET, bundle).response));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.b.g = 0;
            }
        }.start();
    }

    private void a(JSONObject jSONObject) {
        b("cgi back, do update");
        this.e = jSONObject;
        a("com.tencent.open.config.json", jSONObject.toString());
        this.f = SystemClock.elapsedRealtime();
    }

    private void c() {
        int optInt = this.e.optInt("Common_frequency");
        if (optInt == 0) {
            optInt = 1;
        }
        if (SystemClock.elapsedRealtime() - this.f >= ((long) (optInt * 3600000))) {
            b();
        }
    }

    public String getString(String str) {
        b("get " + str);
        c();
        return this.e.optString(str);
    }

    public int getInt(String str) {
        b("get " + str);
        c();
        return this.e.optInt(str);
    }

    public long getLong(String str) {
        b("get " + str);
        c();
        return this.e.optLong(str);
    }

    public double getDouble(String str) {
        b("get " + str);
        c();
        return this.e.optDouble(str, 0.0d);
    }

    public boolean getBoolean(String str) {
        b("get " + str);
        c();
        Object opt = this.e.opt(str);
        if (opt == null) {
            return false;
        }
        if (opt instanceof Integer) {
            return !opt.equals(Integer.valueOf(0));
        } else if (opt instanceof Boolean) {
            return ((Boolean) opt).booleanValue();
        } else {
            return false;
        }
    }

    public Object get(String str) {
        b("get " + str);
        c();
        return this.e.opt(str);
    }

    private void b(String str) {
        if (this.h) {
            Log.i("OpenConfig", str + "; appid: " + this.d);
        }
    }
}
