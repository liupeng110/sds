package com.igexin.push.core.a;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Message;
import android.os.PowerManager;
import android.os.Process;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import com.igexin.a.b.a;
import com.igexin.download.Downloads;
import com.igexin.push.core.a.a.b;
import com.igexin.push.core.a.a.c;
import com.igexin.push.core.a.a.d;
import com.igexin.push.core.a.a.e;
import com.igexin.push.core.a.a.g;
import com.igexin.push.core.a.a.h;
import com.igexin.push.core.a.a.i;
import com.igexin.push.core.a.a.j;
import com.igexin.push.core.bean.BaseAction;
import com.igexin.push.core.bean.PushTaskBean;
import com.igexin.push.core.bean.l;
import com.igexin.push.core.c.r;
import com.igexin.push.d.k;
import com.igexin.push.extension.stub.IPushExtension;
import com.igexin.sdk.PushBuildConfig;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushService;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.taobao.wireless.security.sdk.indiekit.IndieKitDefine;
import com.tencent.open.SocialConstants;
import com.ttfm.android.sdk.http.HttpChannelSongListGetV2;
import com.ttfm.android.sdk.http.TTPodFMHttpClient;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.UnresolvedAddressException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class f extends a implements k {
    private static Map a;
    private static Map b;
    private static f c;

    private f() {
        a = new HashMap();
        a.put(Integer.valueOf(0), new i());
        a.put(Integer.valueOf(5), new j());
        a.put(Integer.valueOf(37), new l());
        a.put(Integer.valueOf(9), new p());
        a.put(Integer.valueOf(26), new h());
        a.put(Integer.valueOf(28), new e());
        b = new HashMap();
        b.put("goto", new g());
        b.put("notification", new h());
        b.put("startapp", new j());
        b.put("null", new com.igexin.push.core.a.a.f());
        b.put("wakeupsdk", new com.igexin.push.core.a.a.k());
        b.put("startweb", new i());
        b.put("checkapp", new b());
        b.put("cleanext", new c());
        b.put("enablelog", new e());
        b.put("disablelog", new d());
    }

    private int F() {
        if (com.igexin.push.core.g.al.isEmpty() && com.igexin.push.core.g.q) {
            Cursor a = com.igexin.push.core.f.a().i().a("message", new String[]{Downloads.COLUMN_STATUS}, new String[]{FeedbackItem.STATUS_WAITING}, null, null);
            if (a != null) {
                while (a.moveToNext()) {
                    try {
                        JSONObject jSONObject = new JSONObject(new String(a.c(a.getBlob(a.getColumnIndex("info")))));
                        String string = jSONObject.getString(StarCategory.KEY_STAR_CATEGORY_ID);
                        String string2 = jSONObject.getString("appid");
                        String string3 = jSONObject.getString("messageid");
                        String string4 = jSONObject.getString("taskid");
                        String string5 = jSONObject.getString("appkey");
                        String a2 = a().a(string4, string3);
                        PushTaskBean pushTaskBean = new PushTaskBean();
                        pushTaskBean.setAppid(string2);
                        pushTaskBean.setMessageId(string3);
                        pushTaskBean.setTaskId(string4);
                        pushTaskBean.setId(string);
                        pushTaskBean.setAppKey(string5);
                        pushTaskBean.setCurrentActionid(1);
                        pushTaskBean.setStatus(a.getInt(a.getColumnIndex(Downloads.COLUMN_STATUS)));
                        if (jSONObject.has("cdnType")) {
                            pushTaskBean.setCDNType(jSONObject.getBoolean("cdnType"));
                        }
                        if (jSONObject.has("condition")) {
                            b(jSONObject, pushTaskBean);
                        }
                        com.igexin.push.core.g.al.put(a2, pushTaskBean);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                a.close();
            }
            com.igexin.push.core.g.q = false;
        }
        return com.igexin.push.core.g.al.size();
    }

    public static f a() {
        if (c == null) {
            c = new f();
        }
        return c;
    }

    private void a(int i, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Downloads.COLUMN_STATUS, Integer.valueOf(i));
        com.igexin.push.core.f.a().i().a("message", contentValues, new String[]{"taskid"}, new String[]{str});
    }

    private void a(com.igexin.push.c.c.c cVar, PushTaskBean pushTaskBean, String str, String str2) {
        cVar.a(new com.igexin.push.e.b.c(pushTaskBean, str, k()));
        com.igexin.push.core.g.ao.put(str2, cVar);
    }

    private void a(com.igexin.push.core.bean.e eVar) {
        File file = new File(com.igexin.push.core.g.ac + "/" + eVar.c());
        File file2 = new File(com.igexin.push.core.g.ad + "/" + eVar.c());
        if (file2.exists()) {
            if (a.a(com.igexin.push.core.g.i, file2.getAbsolutePath()).equals(eVar.f())) {
                Intent intent = new Intent(com.igexin.push.core.g.i, PushService.class);
                intent.putExtra(PushConsts.CMD_ACTION, "com.igexin.sdk.action.extdownloadsuccess");
                intent.putExtra(StarCategory.KEY_STAR_CATEGORY_ID, eVar.a());
                intent.putExtra("result", true);
                com.igexin.push.core.g.i.startService(intent);
                return;
            }
            file2.delete();
        }
        if (file.exists() && a.a(com.igexin.push.core.g.i, file.getAbsolutePath()).equals(eVar.f()) && a(file, file2, eVar.f())) {
            intent = new Intent(com.igexin.push.core.g.i, PushService.class);
            intent.putExtra(PushConsts.CMD_ACTION, "com.igexin.sdk.action.extdownloadsuccess");
            intent.putExtra(StarCategory.KEY_STAR_CATEGORY_ID, eVar.a());
            intent.putExtra("result", true);
            com.igexin.push.core.g.i.startService(intent);
            return;
        }
        new Thread(new com.igexin.push.core.d.f(com.igexin.push.core.g.i, eVar)).start();
    }

    private void a(List list) {
        Comparator gVar = new g(this);
        PackageManager packageManager = com.igexin.push.core.g.i.getPackageManager();
        List installedPackages = packageManager.getInstalledPackages(0);
        for (int i = 0; i < installedPackages.size(); i++) {
            try {
                PackageInfo packageInfo = (PackageInfo) installedPackages.get(i);
                ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                if ((applicationInfo.flags & 1) <= 0) {
                    l lVar = new l();
                    lVar.a(applicationInfo.loadLabel(packageManager).toString());
                    lVar.c(applicationInfo.packageName);
                    lVar.b(String.valueOf(packageInfo.versionCode));
                    list.add(lVar);
                }
            } catch (Exception e) {
            }
        }
        Collections.sort(list, gVar);
    }

    private boolean a(Class cls, Method method, String str) {
        try {
            return method.invoke(cls, new Object[]{str}) != null;
        } catch (Exception e) {
            return true;
        }
    }

    private void b(List list) {
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                String str = (String) list.get(i);
                PushTaskBean pushTaskBean = (PushTaskBean) com.igexin.push.core.g.al.get(str);
                pushTaskBean.setStatus(com.igexin.push.core.a.l);
                com.igexin.push.core.g.al.put(str, pushTaskBean);
            }
        }
    }

    private void b(JSONObject jSONObject, PushTaskBean pushTaskBean) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("condition");
            Map hashMap = new HashMap();
            if (jSONObject2.has("wifi")) {
                hashMap.put("wifi", jSONObject2.getString("wifi"));
            }
            if (jSONObject2.has("screenOn")) {
                hashMap.put("screenOn", jSONObject2.getString("screenOn"));
            }
            if (jSONObject2.has("ssid")) {
                hashMap.put("ssid", jSONObject2.getString("ssid"));
                if (jSONObject2.has("bssid")) {
                    hashMap.put("bssid", jSONObject2.getString("bssid"));
                }
            }
            if (jSONObject2.has("duration")) {
                String string = jSONObject2.getString("duration");
                if (string.contains("-")) {
                    int indexOf = string.indexOf("-");
                    String substring = string.substring(0, indexOf);
                    string = string.substring(indexOf + 1, string.length());
                    hashMap.put("startTime", substring);
                    hashMap.put("endTime", string);
                }
            }
            pushTaskBean.setConditionMap(hashMap);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void h(String str) {
        Throwable th;
        FileOutputStream fileOutputStream = null;
        FileOutputStream fileOutputStream2;
        try {
            File file = new File(com.igexin.push.core.g.aa);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileOutputStream2 = new FileOutputStream(com.igexin.push.core.g.aa);
            try {
                fileOutputStream2.write(a.a(str).getBytes());
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e3) {
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Exception e4) {
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            fileOutputStream2 = null;
            th = th4;
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
    }

    private boolean i(String str) {
        try {
            com.igexin.push.core.g.i.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void A() {
        int i = com.igexin.push.core.g.aq - 100;
        if (i < 0) {
            com.igexin.push.core.g.aq = 0;
        } else {
            com.igexin.push.core.g.aq = i;
        }
        List<String> arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        for (Entry entry : com.igexin.push.core.g.ap.entrySet()) {
            if (currentTimeMillis - ((Long) entry.getValue()).longValue() > 3600000) {
                arrayList.add((String) entry.getKey());
            }
        }
        for (String remove : arrayList) {
            com.igexin.push.core.g.ap.remove(remove);
        }
    }

    public void B() {
        if (com.igexin.push.core.g.R < System.currentTimeMillis()) {
            com.igexin.push.core.c.f.a().a(false);
        }
    }

    public void C() {
        if (!com.igexin.push.core.g.af) {
            com.igexin.push.core.g.af = com.igexin.a.a.b.d.c().a(com.igexin.push.e.b.d.g(), false, true);
        }
        if (!com.igexin.push.core.g.ag) {
            com.igexin.push.core.g.ag = com.igexin.a.a.b.d.c().a(com.igexin.push.e.b.f.g(), true, true);
        }
        if (!com.igexin.push.core.g.ah) {
            com.igexin.push.core.g.ah = com.igexin.a.a.b.d.c().a(com.igexin.push.e.b.e.g(), false, true);
        }
        if (!com.igexin.push.core.g.ai) {
            com.igexin.push.core.g.ai = com.igexin.a.a.b.d.c().a(com.igexin.push.e.b.g.g(), false, true);
        }
        if (!com.igexin.push.core.g.aj) {
            com.igexin.push.core.g.aj = com.igexin.a.a.b.d.c().a(com.igexin.push.e.b.a.g(), false, true);
        }
        if (!com.igexin.push.core.g.ak) {
            com.igexin.push.core.g.ak = com.igexin.a.a.b.d.c().a(com.igexin.push.e.b.b.g(), false, true);
        }
    }

    public void D() {
        FileOutputStream fileOutputStream;
        Throwable th;
        FileOutputStream fileOutputStream2 = null;
        String str = "/data/data/" + com.igexin.push.core.g.g + "/files/" + "init.pid";
        try {
            if (new File(str).exists()) {
                byte[] bytes = com.igexin.push.core.g.u.getBytes();
                byte[] bArr = new byte[bytes.length];
                for (int i = 0; i < bytes.length; i++) {
                    bArr[i] = (byte) (bytes[i] ^ com.igexin.push.core.g.ae[i]);
                }
                fileOutputStream = new FileOutputStream(str);
                try {
                    fileOutputStream.write(bArr);
                } catch (Exception e) {
                    fileOutputStream2 = fileOutputStream;
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (Exception e2) {
                            return;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e3) {
                        }
                    }
                    throw th;
                }
            }
            fileOutputStream = null;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e4) {
                }
            }
        } catch (Exception e5) {
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            fileOutputStream = null;
            th = th4;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    public boolean E() {
        try {
            if (PushBuildConfig.sdk_conf_debug_level.equals(com.igexin.push.a.k.y)) {
                return false;
            }
            String[] split = com.igexin.push.a.k.y.split(SelectCountryActivity.SPLITTER);
            for (String i : split) {
                if (i(i)) {
                    return false;
                }
            }
            if (PushBuildConfig.sdk_conf_debug_level.equals(com.igexin.push.a.k.z)) {
                return false;
            }
            split = com.igexin.push.a.k.z.split(SelectCountryActivity.SPLITTER);
            Class cls = Class.forName("android.os.ServiceManager");
            Method method = cls.getMethod("getService", new Class[]{String.class});
            method.setAccessible(true);
            for (String a : split) {
                if (a(cls, method, a)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public com.igexin.push.core.bean.f a(JSONObject jSONObject) {
        com.igexin.push.core.bean.f fVar = new com.igexin.push.core.bean.f();
        fVar.a(jSONObject.getString("version"));
        JSONArray jSONArray = jSONObject.getJSONArray("extensions");
        if (jSONArray == null || jSONArray.length() <= 0) {
            fVar.a(new HashMap());
        } else {
            Map hashMap = new HashMap();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                com.igexin.push.core.bean.e eVar = new com.igexin.push.core.bean.e();
                eVar.a(jSONObject2.getInt(StarCategory.KEY_STAR_CATEGORY_ID));
                eVar.a(jSONObject2.getString("version"));
                eVar.b(jSONObject2.getString("name"));
                eVar.c(jSONObject2.getString("cls_name"));
                eVar.d(jSONObject2.getString("url"));
                eVar.e(jSONObject2.getString("checksum"));
                eVar.f(jSONObject2.getString("key"));
                if (jSONObject2.has("isdestroy")) {
                    eVar.a(jSONObject2.getBoolean("isdestroy"));
                }
                if (jSONObject2.has("effective")) {
                    String string = jSONObject2.getString("effective");
                    long j = 0;
                    if (string != null && string.length() <= 13) {
                        j = Long.parseLong(string);
                    }
                    eVar.a(j);
                }
                if (jSONObject2.has("loadTime")) {
                    eVar.b(jSONObject2.getLong("loadTime"));
                }
                hashMap.put(Integer.valueOf(eVar.a()), eVar);
            }
            fVar.a(hashMap);
        }
        return fVar;
    }

    public String a(String str, String str2) {
        return str + ":" + str2;
    }

    public String a(boolean z, int i) {
        String str;
        Throwable th;
        Cursor cursor = null;
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Cursor cursor2;
        if (i == -1) {
            try {
                str = format + "|" + com.igexin.push.core.g.C + "|" + "register" + "|" + com.igexin.push.core.g.v;
            } catch (Exception e) {
                str = null;
                if (cursor != null) {
                    cursor.close();
                }
                return str;
            } catch (Throwable th2) {
                th = th2;
                cursor2 = null;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } else if (i == 0) {
            cursor2 = z ? com.igexin.push.core.f.a().i().a("bi", new String[]{SocialConstants.PARAM_TYPE}, new String[]{"1", FeedbackItem.STATUS_SOLVED}, null, null) : com.igexin.push.core.f.a().i().a("bi", new String[]{SocialConstants.PARAM_TYPE}, new String[]{FeedbackItem.STATUS_SOLVED}, null, null);
            if (cursor2 != null) {
                str = null;
                while (cursor2.moveToNext()) {
                    try {
                        int i2 = cursor2.getInt(cursor2.getColumnIndexOrThrow("start_service_count"));
                        int i3 = cursor2.getInt(cursor2.getColumnIndexOrThrow("login_count"));
                        int i4 = cursor2.getInt(cursor2.getColumnIndexOrThrow("loginerror_nonetwork_count"));
                        int i5 = cursor2.getInt(cursor2.getColumnIndexOrThrow("loginerror_connecterror_count"));
                        int i6 = cursor2.getInt(cursor2.getColumnIndexOrThrow("online_time"));
                        int i7 = cursor2.getInt(cursor2.getColumnIndexOrThrow("network_time"));
                        int i8 = cursor2.getInt(cursor2.getColumnIndexOrThrow("running_time"));
                        String str2 = cursor2.getString(cursor2.getColumnIndexOrThrow("create_time")) + " 00:00:00";
                        str = str == null ? str2 + "|" + com.igexin.push.core.g.C + "|" + "startservice" + "|" + i2 + "\n" + str2 + "|" + com.igexin.push.core.g.C + "|" + "login" + "|" + i3 + "\n" + str2 + "|" + com.igexin.push.core.g.C + "|" + "loginerror-nonetwork" + "|" + i4 + "\n" + str2 + "|" + com.igexin.push.core.g.C + "|" + "loginerror-connecterror" + "|" + i5 + "\n" + str2 + "|" + com.igexin.push.core.g.C + "|" + "online" + "|" + i6 + "\n" + str2 + "|" + com.igexin.push.core.g.C + "|" + "network" + "|" + i7 + "\n" + str2 + "|" + com.igexin.push.core.g.C + "|" + "running" + "|" + i8 : str + "\n" + str2 + "|" + com.igexin.push.core.g.C + "|" + "startservice" + "|" + i2 + "\n" + str2 + "|" + com.igexin.push.core.g.C + "|" + "login" + "|" + i3 + "\n" + str2 + "|" + com.igexin.push.core.g.C + "|" + "loginerror-nonetwork" + "|" + i4 + "\n" + str2 + "|" + com.igexin.push.core.g.C + "|" + "loginerror-connecterror" + "|" + i5 + "\n" + str2 + "|" + com.igexin.push.core.g.C + "|" + "online" + "|" + i6 + "\n" + str2 + "|" + com.igexin.push.core.g.C + "|" + "network" + "|" + i7 + "\n" + str2 + "|" + com.igexin.push.core.g.C + "|" + "running" + "|" + i8;
                    } catch (Exception e2) {
                        cursor = cursor2;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
            } else {
                str = null;
            }
            cursor = cursor2;
        } else if (i == 1) {
            long j = com.igexin.push.core.i.a().a;
            if (com.igexin.push.a.k.h > 0) {
                j = (long) (com.igexin.push.a.k.h * 1000);
            }
            str = format + "|" + com.igexin.push.core.g.u + "|" + com.igexin.push.core.g.c + "|" + com.igexin.push.core.g.l + "|" + (com.igexin.push.a.k.e + SelectCountryActivity.SPLITTER + com.igexin.push.a.k.f) + "|" + j + "|";
        } else {
            str = i == 4 ? format + "|" + com.igexin.push.core.g.u + "|" + com.igexin.push.core.g.c + "|" : i == 5 ? format + "|" + com.igexin.push.core.g.u + "|" + com.igexin.push.core.g.c : null;
        }
        if (cursor != null) {
            cursor.close();
        }
        return str;
    }

    public void a(int i) {
        Intent intent = new Intent();
        intent.setAction("com.igexin.sdk.action." + com.igexin.push.core.g.c);
        Bundle bundle = new Bundle();
        bundle.putInt(PushConsts.CMD_ACTION, PushConsts.GET_SDKSERVICEPID);
        bundle.putInt("pid", i);
        intent.putExtras(bundle);
        com.igexin.push.core.f.a().a(intent);
    }

    public void a(int i, int i2, String str) {
        com.igexin.push.a.k.e = i;
        com.igexin.push.a.k.f = i2;
        com.igexin.push.a.a.a().b();
        com.igexin.push.e.b.g.g().h();
    }

    public void a(int i, String str) {
        com.igexin.push.a.k.h = i;
        com.igexin.push.a.a.a().c();
        if (com.igexin.push.core.g.o) {
            com.igexin.a.a.c.a.a("setHeartbeatInterval heartbeatReq");
            if (System.currentTimeMillis() - com.igexin.push.core.g.U > 5000) {
                com.igexin.push.core.g.U = System.currentTimeMillis();
                f();
            }
        }
    }

    public void a(Intent intent) {
        if (intent != null) {
            com.igexin.push.core.f.a().a(false);
            if (intent.hasExtra("op_app")) {
                com.igexin.push.core.g.E = intent.getStringExtra("op_app");
            } else {
                com.igexin.push.core.g.E = "";
            }
            if (com.igexin.push.core.g.o) {
                l();
            }
        }
    }

    public void a(Bundle bundle) {
        String string = bundle.getString(PushConsts.CMD_ACTION);
        if (string.equals("setTag")) {
            if (com.igexin.push.a.k.n) {
                c(bundle.getString("tags"));
            }
        } else if (string.equals("setSilentTime")) {
            if (com.igexin.push.a.k.o) {
                a(bundle.getInt("beginHour", 0), bundle.getInt("duration", 0), com.igexin.push.core.g.i.getPackageName());
            }
        } else if (string.equals("sendMessage")) {
            if (com.igexin.push.a.k.m) {
                a(bundle.getString("taskid"), bundle.getByteArray("extraData"));
            }
        } else if (string.equals("stopService")) {
            com.igexin.push.core.f.a().a(com.igexin.push.core.g.i.getPackageName());
        } else if (string.equals("setHeartbeatInterval")) {
            if (com.igexin.push.a.k.p) {
                a(bundle.getInt("interval", 0), com.igexin.push.core.g.i.getPackageName());
            }
        } else if (string.equals("setSocketTimeout")) {
            if (com.igexin.push.a.k.q) {
                b(bundle.getInt("timeout", 0), com.igexin.push.core.g.i.getPackageName());
            }
        } else if (string.equals("sendFeedbackMessage")) {
            if (com.igexin.push.a.k.w && com.igexin.push.core.g.aq <= 200) {
                string = bundle.getString("taskid");
                String string2 = bundle.getString("messageid");
                String string3 = bundle.getString("actionid");
                String str = string + ":" + string2 + ":" + string3;
                if (com.igexin.push.core.g.ap.get(str) == null) {
                    long currentTimeMillis = System.currentTimeMillis();
                    PushTaskBean pushTaskBean = new PushTaskBean();
                    pushTaskBean.setTaskId(string);
                    pushTaskBean.setMessageId(string2);
                    pushTaskBean.setAppid(com.igexin.push.core.g.c);
                    pushTaskBean.setAppKey(com.igexin.push.core.g.d);
                    a(pushTaskBean, string3);
                    com.igexin.push.core.g.aq++;
                    com.igexin.push.core.g.ap.put(str, Long.valueOf(currentTimeMillis));
                }
            }
        } else if (string.equals("turnOffPush")) {
            com.igexin.push.core.f.a().b(com.igexin.push.core.g.i.getPackageName());
        }
    }

    public void a(PushTaskBean pushTaskBean) {
        com.igexin.push.c.c.e cVar = new com.igexin.push.c.c.c();
        cVar.a();
        cVar.c = "RCV" + pushTaskBean.getMessageId();
        cVar.d = com.igexin.push.core.g.u;
        cVar.a = (int) System.currentTimeMillis();
        com.igexin.push.core.f.a().e().a("C-" + com.igexin.push.core.g.u, cVar);
        com.igexin.a.a.c.a.a("cdnreceive|" + pushTaskBean.getTaskId() + "|" + pushTaskBean.getMessageId());
    }

    public void a(PushTaskBean pushTaskBean, String str) {
        if (pushTaskBean.isCDNType()) {
            b(pushTaskBean, str);
        } else {
            a(pushTaskBean, str, "ok");
        }
    }

    public void a(PushTaskBean pushTaskBean, String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis();
        String str3 = "{\"action\":\"pushmessage_feedback\",\"appid\":\"" + pushTaskBean.getAppid() + "\", \"id\":\"" + currentTimeMillis + "\", \"appkey\":\"" + pushTaskBean.getAppKey() + "\", \"messageid\":\"" + pushTaskBean.getMessageId() + "\",\"taskid\":\"" + pushTaskBean.getTaskId() + "\",\"actionid\": \"" + str + "\",\"result\":\"" + str2 + "\",\"timestamp\":\"" + System.currentTimeMillis() + "\"}";
        com.igexin.push.c.c.e dVar = new com.igexin.push.c.c.d();
        dVar.a();
        dVar.a = (int) currentTimeMillis;
        dVar.d = "17258000";
        dVar.e = str3;
        dVar.g = com.igexin.push.core.g.u;
        com.igexin.push.core.f.a().e().a("C-" + com.igexin.push.core.g.u, dVar);
        com.igexin.push.core.c.c a = com.igexin.push.core.c.c.a();
        if (a != null) {
            a.a(new com.igexin.push.core.bean.i(currentTimeMillis, str3, (byte) 3, currentTimeMillis));
        }
        com.igexin.a.a.c.a.a("feedback|" + pushTaskBean.getTaskId() + "|" + pushTaskBean.getMessageId() + "|" + str);
    }

    public void a(com.igexin.push.core.bean.f fVar) {
        com.igexin.push.core.g.as = 0;
        com.igexin.push.core.g.at = 0;
        com.igexin.push.core.g.av = fVar;
        Map b = fVar.b();
        com.igexin.push.core.bean.e eVar;
        if (com.igexin.push.a.k.x != null) {
            int intValue;
            Map b2 = com.igexin.push.a.k.x.b();
            List<Integer> arrayList = new ArrayList();
            for (Entry entry : b2.entrySet()) {
                intValue = ((Integer) entry.getKey()).intValue();
                eVar = (com.igexin.push.core.bean.e) entry.getValue();
                if (!b.containsKey(Integer.valueOf(intValue))) {
                    com.igexin.push.core.g.aw = true;
                    File file = new File(com.igexin.push.core.g.ad + "/" + eVar.c());
                    if (file.exists()) {
                        file.delete();
                    }
                    arrayList.add(Integer.valueOf(intValue));
                }
            }
            if (arrayList != null && arrayList.size() > 0) {
                for (Integer intValue2 : arrayList) {
                    b2.remove(Integer.valueOf(intValue2.intValue()));
                }
                com.igexin.push.a.a.a().g();
            }
            boolean z = true;
            for (Entry entry2 : b.entrySet()) {
                boolean z2;
                intValue = ((Integer) entry2.getKey()).intValue();
                eVar = (com.igexin.push.core.bean.e) entry2.getValue();
                if (b2.containsKey(Integer.valueOf(intValue))) {
                    if (!((com.igexin.push.core.bean.e) b2.get(Integer.valueOf(intValue))).b().equals(eVar.b())) {
                        com.igexin.push.core.g.aw = true;
                        com.igexin.push.core.g.as++;
                        a(eVar);
                        z = false;
                    }
                    z2 = z;
                } else {
                    com.igexin.push.core.g.as++;
                    a(eVar);
                    z2 = false;
                }
                z = z2;
            }
            if (z) {
                com.igexin.push.a.k.x.a(fVar.a());
                com.igexin.push.a.a.a().g();
                Process.killProcess(Process.myPid());
                return;
            }
            return;
        }
        for (Entry entry22 : b.entrySet()) {
            eVar = (com.igexin.push.core.bean.e) entry22.getValue();
            com.igexin.push.core.g.as++;
            a(eVar);
        }
    }

    public void a(String str) {
        String str2 = "{\"action\":\"received\",\"id\":\"" + str + "\"}";
        com.igexin.push.c.c.e dVar = new com.igexin.push.c.c.d();
        dVar.a();
        dVar.a = (int) System.currentTimeMillis();
        dVar.d = "17258000";
        dVar.e = str2;
        dVar.g = com.igexin.push.core.g.u;
        com.igexin.push.core.f.a().e().a("C-" + com.igexin.push.core.g.u, dVar);
    }

    public void a(String str, com.igexin.push.c.c.a aVar, PushTaskBean pushTaskBean) {
        com.igexin.a.a.b.d.c().a(new com.igexin.push.e.a.a(new com.igexin.push.core.d.b(str, aVar, pushTaskBean)), false, true);
    }

    public void a(String str, String str2, String str3, String str4) {
        Intent intent = new Intent("com.igexin.sdk.action.execute");
        intent.putExtra("taskid", str);
        intent.putExtra("messageid", str2);
        intent.putExtra("appid", com.igexin.push.core.g.c);
        intent.putExtra("pkgname", com.igexin.push.core.g.g);
        com.igexin.push.core.f.a().a(intent);
    }

    public void a(String str, String str2, String str3, String str4, long j) {
        Intent intent = new Intent();
        intent.setAction("com.igexin.sdk.action." + com.igexin.push.core.g.c);
        Bundle bundle = new Bundle();
        bundle.putInt(PushConsts.CMD_ACTION, PushConsts.THIRDPART_FEEDBACK);
        bundle.putString("appid", str);
        bundle.putString("taskid", str2);
        bundle.putString("actionid", str3);
        bundle.putString("result", str4);
        bundle.putLong(IndieKitDefine.SG_KEY_INDIE_KIT_TIMESTAMP, j);
        intent.putExtras(bundle);
        com.igexin.push.core.f.a().a(intent);
    }

    public void a(String str, byte[] bArr) {
        if (com.igexin.push.core.g.u != null) {
            JSONObject jSONObject = new JSONObject();
            long currentTimeMillis = System.currentTimeMillis();
            try {
                jSONObject.put(PushConsts.CMD_ACTION, "sendmessage");
                jSONObject.put(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(currentTimeMillis));
                jSONObject.put("cid", com.igexin.push.core.g.u);
                jSONObject.put("appid", com.igexin.push.core.g.c);
                jSONObject.put("taskid", str);
                String jSONObject2 = jSONObject.toString();
                com.igexin.push.core.c.c.a().a(new com.igexin.push.core.bean.i(currentTimeMillis, jSONObject2, (byte) 6, currentTimeMillis));
                com.igexin.push.c.c.e dVar = new com.igexin.push.c.c.d();
                dVar.a();
                dVar.a = (int) currentTimeMillis;
                dVar.d = com.igexin.push.core.g.u;
                dVar.e = jSONObject2;
                dVar.f = bArr;
                dVar.g = com.igexin.push.core.g.u;
                com.igexin.push.core.f.a().e().a("C-" + com.igexin.push.core.g.u, dVar);
            } catch (JSONException e) {
            }
        }
    }

    public void a(boolean z) {
    }

    /* JADX err: Inconsistent code. */
    public void a(byte[] r7) {
        /*
        r6 = this;
        r0 = 0;
        r1 = 0;
        r1 = android.util.Base64.decode(r7, r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1 = com.igexin.a.b.a.c(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r2 = new java.lang.String;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r2.<init>(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1 = new org.json.JSONObject;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1.<init>(r2);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r2 = "result";
        r2 = r1.has(r2);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r2 == 0) goto L_0x031a;
    L_0x001c:
        r2 = "result";
        r2 = r1.getString(r2);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r3 = "ok";
        r2 = r3.equals(r2);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r2 == 0) goto L_0x031a;
    L_0x002a:
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r4 = com.igexin.push.core.c.f.a();	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r4.f(r2);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r2 = "config";
        r2 = r1.has(r2);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r2 == 0) goto L_0x031a;
    L_0x003d:
        r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r3 = "config";
        r1 = r1.getString(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r2.<init>(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1 = "sdk.uploadapplist.enable";
        r1 = r2.has(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r1 == 0) goto L_0x0070;
    L_0x0050:
        r1 = "sdk.uploadapplist.enable";
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r3 = "true";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 != 0) goto L_0x0066;
    L_0x005e:
        r3 = "false";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 == 0) goto L_0x0070;
    L_0x0066:
        r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1 = r1.booleanValue();	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        com.igexin.push.a.k.l = r1;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
    L_0x0070:
        r1 = "sdk.feature.sendmessage.enable";
        r1 = r2.has(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r1 == 0) goto L_0x0098;
    L_0x0078:
        r1 = "sdk.feature.sendmessage.enable";
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r3 = "true";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 != 0) goto L_0x008e;
    L_0x0086:
        r3 = "false";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 == 0) goto L_0x0098;
    L_0x008e:
        r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1 = r1.booleanValue();	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        com.igexin.push.a.k.m = r1;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
    L_0x0098:
        r1 = "sdk.readlocalcell.enable";
        r1 = r2.has(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r1 == 0) goto L_0x00c0;
    L_0x00a0:
        r1 = "sdk.readlocalcell.enable";
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r3 = "true";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 != 0) goto L_0x00b6;
    L_0x00ae:
        r3 = "false";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 == 0) goto L_0x00c0;
    L_0x00b6:
        r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1 = r1.booleanValue();	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        com.igexin.push.a.k.k = r1;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
    L_0x00c0:
        r1 = "sdk.ca.enable";
        r1 = r2.has(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r1 == 0) goto L_0x00e8;
    L_0x00c8:
        r1 = "sdk.ca.enable";
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r3 = "true";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 != 0) goto L_0x00de;
    L_0x00d6:
        r3 = "false";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 == 0) goto L_0x00e8;
    L_0x00de:
        r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1 = r1.booleanValue();	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        com.igexin.push.a.k.r = r1;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
    L_0x00e8:
        r1 = "sdk.snl.enable";
        r1 = r2.has(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r1 == 0) goto L_0x0110;
    L_0x00f0:
        r1 = "sdk.snl.enable";
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r3 = "true";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 != 0) goto L_0x0106;
    L_0x00fe:
        r3 = "false";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 == 0) goto L_0x0110;
    L_0x0106:
        r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1 = r1.booleanValue();	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        com.igexin.push.a.k.s = r1;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
    L_0x0110:
        r1 = "sdk.domainbackup.enable";
        r1 = r2.has(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r1 == 0) goto L_0x0138;
    L_0x0118:
        r1 = "sdk.domainbackup.enable";
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r3 = "true";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 != 0) goto L_0x012e;
    L_0x0126:
        r3 = "false";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 == 0) goto L_0x0138;
    L_0x012e:
        r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1 = r1.booleanValue();	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        com.igexin.push.a.k.j = r1;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
    L_0x0138:
        r1 = "sdk.feature.setsilenttime.enable";
        r1 = r2.has(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r1 == 0) goto L_0x0170;
    L_0x0140:
        r1 = "sdk.feature.setsilenttime.enable";
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r3 = "true";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 != 0) goto L_0x0156;
    L_0x014e:
        r3 = "false";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 == 0) goto L_0x0170;
    L_0x0156:
        r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1 = r1.booleanValue();	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        com.igexin.push.a.k.o = r1;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1 = com.igexin.push.a.k.o;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r1 != 0) goto L_0x0170;
    L_0x0164:
        r1 = com.igexin.push.a.k.f;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r1 == 0) goto L_0x0170;
    L_0x0168:
        r1 = 12;
        r3 = 0;
        r4 = "server";
        r6.a(r1, r3, r4);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
    L_0x0170:
        r1 = "sdk.snl.maxactiveflow";
        r1 = r2.has(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r1 == 0) goto L_0x0185;
    L_0x0178:
        r1 = "sdk.snl.maxactiveflow";
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1 = java.lang.Integer.parseInt(r1);	 Catch:{ Exception -> 0x0326, Throwable -> 0x0324 }
        r4 = (long) r1;	 Catch:{ Exception -> 0x0326, Throwable -> 0x0324 }
        com.igexin.push.a.k.t = r4;	 Catch:{ Exception -> 0x0326, Throwable -> 0x0324 }
    L_0x0185:
        r1 = "sdk.feature.settag.enable";
        r1 = r2.has(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r1 == 0) goto L_0x01ad;
    L_0x018d:
        r1 = "sdk.feature.settag.enable";
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r3 = "true";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 != 0) goto L_0x01a3;
    L_0x019b:
        r3 = "false";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 == 0) goto L_0x01ad;
    L_0x01a3:
        r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1 = r1.booleanValue();	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        com.igexin.push.a.k.n = r1;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
    L_0x01ad:
        r1 = "sdk.feature.setheartbeatinterval.enable";
        r1 = r2.has(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r1 == 0) goto L_0x01d5;
    L_0x01b5:
        r1 = "sdk.feature.setheartbeatinterval.enable";
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r3 = "true";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 != 0) goto L_0x01cb;
    L_0x01c3:
        r3 = "false";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 == 0) goto L_0x01d5;
    L_0x01cb:
        r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1 = r1.booleanValue();	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        com.igexin.push.a.k.p = r1;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
    L_0x01d5:
        r1 = "sdk.feature.setsockettimeout.enable";
        r1 = r2.has(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r1 == 0) goto L_0x01fd;
    L_0x01dd:
        r1 = "sdk.feature.setsockettimeout.enable";
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r3 = "true";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 != 0) goto L_0x01f3;
    L_0x01eb:
        r3 = "false";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 == 0) goto L_0x01fd;
    L_0x01f3:
        r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1 = r1.booleanValue();	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        com.igexin.push.a.k.q = r1;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
    L_0x01fd:
        r1 = "sdk.guard.enable";
        r1 = r2.has(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r1 == 0) goto L_0x0225;
    L_0x0205:
        r1 = "sdk.guard.enable";
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r3 = "true";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 != 0) goto L_0x021b;
    L_0x0213:
        r3 = "false";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 == 0) goto L_0x0225;
    L_0x021b:
        r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1 = r1.booleanValue();	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        com.igexin.push.a.k.u = r1;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
    L_0x0225:
        r1 = "sdk.wakeupsdk.enable";
        r1 = r2.has(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r1 == 0) goto L_0x024d;
    L_0x022d:
        r1 = "sdk.wakeupsdk.enable";
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r3 = "true";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 != 0) goto L_0x0243;
    L_0x023b:
        r3 = "false";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 == 0) goto L_0x024d;
    L_0x0243:
        r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1 = r1.booleanValue();	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        com.igexin.push.a.k.v = r1;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
    L_0x024d:
        r1 = "sdk.feature.feedback.enable";
        r1 = r2.has(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r1 == 0) goto L_0x0275;
    L_0x0255:
        r1 = "sdk.feature.feedback.enable";
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r3 = "true";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 != 0) goto L_0x026b;
    L_0x0263:
        r3 = "false";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 == 0) goto L_0x0275;
    L_0x026b:
        r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1 = r1.booleanValue();	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        com.igexin.push.a.k.w = r1;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
    L_0x0275:
        r1 = "sdk.watchout.app";
        r1 = r2.has(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r1 == 0) goto L_0x0285;
    L_0x027d:
        r1 = "sdk.watchout.app";
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        com.igexin.push.a.k.y = r1;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
    L_0x0285:
        r1 = "sdk.watchout.service";
        r1 = r2.has(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r1 == 0) goto L_0x0295;
    L_0x028d:
        r1 = "sdk.watchout.service";
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        com.igexin.push.a.k.z = r1;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
    L_0x0295:
        r1 = "sdk.daemon.enable";
        r1 = r2.has(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r1 == 0) goto L_0x02bd;
    L_0x029d:
        r1 = "sdk.daemon.enable";
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r3 = "true";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 != 0) goto L_0x02b3;
    L_0x02ab:
        r3 = "false";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 == 0) goto L_0x02bd;
    L_0x02b3:
        r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1 = r1.booleanValue();	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        com.igexin.push.a.k.A = r1;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
    L_0x02bd:
        r1 = "ext_infos";
        r1 = r2.has(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r1 == 0) goto L_0x0313;
    L_0x02c5:
        r1 = "ext_infos";
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r1 == 0) goto L_0x0313;
    L_0x02cd:
        r2 = "";
        r2 = r2.equals(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r2 != 0) goto L_0x0313;
    L_0x02d5:
        r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r2.<init>(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1 = "version";
        r1 = r2.has(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r1 == 0) goto L_0x0313;
    L_0x02e2:
        r1 = "version";
        r3 = r2.getString(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1 = 1;
        r4 = com.igexin.push.a.k.x;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r4 == 0) goto L_0x0329;
    L_0x02ed:
        r4 = com.igexin.push.a.k.x;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r4 = r4.a();	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r3 = r3.equals(r4);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r3 == 0) goto L_0x0329;
    L_0x02f9:
        if (r0 == 0) goto L_0x0313;
    L_0x02fb:
        r0 = r6.a(r2);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        if (r0 == 0) goto L_0x0313;
    L_0x0301:
        r1 = new android.os.Message;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1.<init>();	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r2 = com.igexin.push.core.a.i;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1.what = r2;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r1.obj = r0;	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r0 = com.igexin.push.core.f.a();	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r0.a(r1);	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
    L_0x0313:
        r0 = com.igexin.push.a.a.a();	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
        r0.f();	 Catch:{ Exception -> 0x031b, Throwable -> 0x0324 }
    L_0x031a:
        return;
    L_0x031b:
        r0 = move-exception;
        r0 = r0.toString();
        r6.f(r0);
        goto L_0x031a;
    L_0x0324:
        r0 = move-exception;
        goto L_0x031a;
    L_0x0326:
        r1 = move-exception;
        goto L_0x0185;
    L_0x0329:
        r0 = r1;
        goto L_0x02f9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.a.f.a(byte[]):void");
    }

    public boolean a(long j) {
        Date date = new Date(j);
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int i = instance.get(11);
        int i2 = com.igexin.push.a.k.e + com.igexin.push.a.k.f;
        if (i2 >= 24) {
            i2 -= 24;
        }
        if (com.igexin.push.a.k.f == 0) {
            return false;
        }
        if (com.igexin.push.a.k.e < i2) {
            if (i >= com.igexin.push.a.k.e && i < i2) {
                return true;
            }
        } else if (com.igexin.push.a.k.e > i2) {
            if (i >= 0 && i < i2) {
                return true;
            }
            if (i >= com.igexin.push.a.k.e && i < 24) {
                return true;
            }
        }
        return false;
    }

    public boolean a(com.igexin.a.a.d.d dVar) {
        switch (dVar.b()) {
            case -2047:
                com.igexin.a.a.c.a.a("disconnected|network");
                com.igexin.push.core.i.a().a(com.igexin.push.core.k.NETWORK_ERROR);
                r.d();
                if ((dVar.N instanceof ClosedChannelException) || (dVar.N instanceof SocketTimeoutException) || (dVar.N instanceof UnknownHostException) || (dVar.N instanceof UnresolvedAddressException) || (dVar.N instanceof UnknownServiceException)) {
                    r.a();
                }
                if (!com.igexin.push.core.g.l || !com.igexin.push.core.g.m) {
                    if (com.igexin.push.core.g.o) {
                        com.igexin.push.core.g.o = false;
                        m();
                        break;
                    }
                }
                if (com.igexin.push.core.g.o) {
                    com.igexin.push.core.g.o = false;
                    m();
                }
                com.igexin.push.core.f.a().e().c(false);
                break;
                break;
            case -2045:
                com.igexin.a.a.c.a.a("disconnected|user");
                r.d();
                if (com.igexin.push.core.g.o) {
                    com.igexin.push.core.g.o = false;
                    m();
                    break;
                }
                break;
            default:
                return true;
        }
        return false;
    }

    public boolean a(com.igexin.push.c.c.e eVar) {
        if (eVar == null) {
            return false;
        }
        a aVar = (a) a.get(Integer.valueOf(eVar.i));
        if (aVar != null) {
            aVar.a((Object) eVar);
        }
        com.igexin.push.e.b.d.g().h();
        return true;
    }

    public boolean a(File file, File file2, String str) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream2;
        Throwable th;
        FileOutputStream fileOutputStream2 = null;
        boolean z = false;
        BufferedOutputStream bufferedOutputStream;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                } catch (Exception e) {
                    bufferedOutputStream = null;
                    fileOutputStream2 = fileOutputStream;
                    fileInputStream2 = fileInputStream;
                    try {
                        if (file2.exists()) {
                            file2.delete();
                        }
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (Exception e2) {
                            }
                        }
                        if (bufferedOutputStream != null) {
                            bufferedOutputStream.close();
                        }
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        return z;
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = fileInputStream2;
                        fileOutputStream = fileOutputStream2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e3) {
                                throw th;
                            }
                        }
                        if (bufferedOutputStream != null) {
                            bufferedOutputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedOutputStream = null;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e4) {
                bufferedOutputStream = null;
                fileInputStream2 = fileInputStream;
                if (file2.exists()) {
                    file2.delete();
                }
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                return z;
            } catch (Throwable th4) {
                th = th4;
                bufferedOutputStream = null;
                fileOutputStream = null;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
            try {
                Object obj = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(obj);
                    if (read == -1) {
                        break;
                    }
                    Object obj2 = new byte[read];
                    System.arraycopy(obj, 0, obj2, 0, read);
                    bufferedOutputStream.write(obj2);
                }
                fileInputStream.close();
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                fileOutputStream.close();
                if (a.a(com.igexin.push.core.g.i, file2.getAbsolutePath()).equals(str)) {
                    z = true;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e5) {
                        }
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } else {
                    file2.delete();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e6) {
                        }
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                }
            } catch (Exception e7) {
                fileOutputStream2 = fileOutputStream;
                fileInputStream2 = fileInputStream;
                if (file2.exists()) {
                    file2.delete();
                }
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                return z;
            } catch (Throwable th5) {
                th = th5;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (Exception e8) {
            bufferedOutputStream = null;
            fileInputStream2 = null;
            if (file2.exists()) {
                file2.delete();
            }
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            return z;
        } catch (Throwable th6) {
            th = th6;
            bufferedOutputStream = null;
            fileOutputStream = null;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
        return z;
    }

    public boolean a(Object obj) {
        com.igexin.push.d.j e = com.igexin.push.core.f.a().e();
        if ((obj instanceof com.igexin.push.c.c.e) && e != null) {
            e.a((com.igexin.push.c.c.e) obj);
        } else if (obj instanceof com.igexin.a.a.b.a.a.b) {
            if (com.igexin.push.core.g.o) {
                com.igexin.push.core.g.o = false;
                m();
            }
        } else if (!(obj instanceof com.igexin.a.a.b.a.a.a)) {
            if (obj instanceof com.igexin.push.c.b.a) {
                e.c(false);
            } else if (obj instanceof com.igexin.push.c.b.b) {
                e.c(true);
            }
        }
        return true;
    }

    public boolean a(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("taskid", str);
        bundle.putString("messageid", str2);
        bundle.putString("actionid", str3);
        Message message = new Message();
        message.what = com.igexin.push.core.a.h;
        message.obj = bundle;
        return com.igexin.push.core.f.a().a(message);
    }

    public boolean a(JSONObject jSONObject, PushTaskBean pushTaskBean) {
        List arrayList = new ArrayList();
        try {
            Object obj;
            JSONArray jSONArray = jSONObject.getJSONArray("action_chains");
            for (int i = 0; i < jSONArray.length(); i++) {
                String string = ((JSONObject) jSONArray.get(i)).getString(SocialConstants.PARAM_TYPE);
                if (string != null) {
                    for (IPushExtension isActionSupported : com.igexin.push.extension.a.a().c()) {
                        if (isActionSupported.isActionSupported(string)) {
                            obj = 1;
                            break;
                        }
                    }
                    obj = null;
                    if (obj == null && b.get(string) == null) {
                        return false;
                    }
                }
            }
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject2 = (JSONObject) jSONArray.get(i2);
                String string2 = jSONObject2.getString(SocialConstants.PARAM_TYPE);
                if (string2 != null) {
                    BaseAction baseAction = null;
                    for (IPushExtension parseAction : com.igexin.push.extension.a.a().c()) {
                        baseAction = parseAction.parseAction(jSONObject2);
                        if (baseAction != null) {
                            break;
                        }
                    }
                    if (baseAction == null) {
                        com.igexin.push.core.a.a.a aVar = (com.igexin.push.core.a.a.a) b.get(string2);
                        if (aVar != null) {
                            obj = aVar.a(jSONObject2);
                            if (obj != null) {
                                obj.setSupportExt(false);
                            }
                            if (obj == null) {
                                return false;
                            }
                            arrayList.add(obj);
                        }
                    }
                    BaseAction baseAction2 = baseAction;
                    if (obj == null) {
                        return false;
                    }
                    arrayList.add(obj);
                }
            }
        } catch (JSONException e) {
        }
        pushTaskBean.setActionChains(arrayList);
        return true;
    }

    public boolean a(JSONObject jSONObject, byte[] bArr, boolean z) {
        Cursor a;
        Cursor cursor;
        Throwable th;
        try {
            if (jSONObject.has(PushConsts.CMD_ACTION) && jSONObject.getString(PushConsts.CMD_ACTION).equals("pushmessage")) {
                String string = jSONObject.getString(StarCategory.KEY_STAR_CATEGORY_ID);
                String string2 = jSONObject.getString("appid");
                String string3 = jSONObject.getString("messageid");
                String string4 = jSONObject.getString("taskid");
                String string5 = jSONObject.getString("appkey");
                JSONArray jSONArray = jSONObject.getJSONArray("action_chains");
                com.igexin.a.a.c.a.a("pushmessage|" + string4 + "|" + string3 + "|" + string2 + "|" + z);
                if (!(string2 == null || string == null || string3 == null || string4 == null || jSONArray == null || !string2.equals(com.igexin.push.core.g.c))) {
                    PushTaskBean pushTaskBean = new PushTaskBean();
                    pushTaskBean.setAppid(string2);
                    pushTaskBean.setMessageId(string3);
                    pushTaskBean.setTaskId(string4);
                    pushTaskBean.setId(string);
                    pushTaskBean.setAppKey(string5);
                    pushTaskBean.setCurrentActionid(1);
                    if (jSONObject.has("cdnType")) {
                        pushTaskBean.setCDNType(jSONObject.getBoolean("cdnType"));
                    }
                    String a2 = a().a(string4, string3);
                    if (z) {
                        a().a(pushTaskBean, FeedbackItem.STATUS_WAITING);
                        if (a().a(System.currentTimeMillis())) {
                            return true;
                        }
                    }
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("messageid", string3);
                    contentValues.put("taskid", string4);
                    contentValues.put("appid", string2);
                    contentValues.put("key", "CACHE_" + a2);
                    contentValues.put("info", a.b(jSONObject.toString().getBytes()));
                    contentValues.put("createtime", Long.valueOf(System.currentTimeMillis()));
                    if (bArr != null) {
                        contentValues.put("msgextra", bArr);
                        pushTaskBean.setMsgExtra(bArr);
                    }
                    if (jSONArray.length() > 0 && !a().a(jSONObject, pushTaskBean)) {
                        return true;
                    }
                    if (z) {
                        try {
                            a = com.igexin.push.core.f.a().i().a("message", new String[]{"taskid"}, new String[]{string4}, null, null);
                            if (a != null) {
                                try {
                                    if (a.getCount() == 0) {
                                        if (jSONObject.has("condition")) {
                                            b(jSONObject, pushTaskBean);
                                            pushTaskBean.setStatus(com.igexin.push.core.a.k);
                                            contentValues.put(Downloads.COLUMN_STATUS, Integer.valueOf(com.igexin.push.core.a.k));
                                        } else {
                                            pushTaskBean.setStatus(com.igexin.push.core.a.l);
                                            contentValues.put(Downloads.COLUMN_STATUS, Integer.valueOf(com.igexin.push.core.a.l));
                                        }
                                        com.igexin.push.core.f.a().i().a("message", contentValues);
                                        com.igexin.push.core.g.al.put(a2, pushTaskBean);
                                        if (jSONObject.has("condition")) {
                                            u();
                                        } else {
                                            a().a(string4, string3, com.igexin.push.core.g.c, com.igexin.push.core.g.g);
                                        }
                                    } else if (a == null) {
                                        return true;
                                    } else {
                                        a.close();
                                        return true;
                                    }
                                } catch (Exception e) {
                                    cursor = a;
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    return true;
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (a != null) {
                                        a.close();
                                    }
                                    throw th;
                                }
                            }
                            if (a != null) {
                                a.close();
                            }
                        } catch (Exception e2) {
                            cursor = null;
                            if (cursor != null) {
                                cursor.close();
                            }
                            return true;
                        } catch (Throwable th3) {
                            th = th3;
                            a = null;
                            if (a != null) {
                                a.close();
                            }
                            throw th;
                        }
                    }
                    if (jSONObject.has("condition")) {
                        b(jSONObject, pushTaskBean);
                    }
                    pushTaskBean.setStatus(com.igexin.push.core.a.l);
                    com.igexin.push.core.g.al.put(a2, pushTaskBean);
                }
            }
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        return true;
    }

    public com.igexin.push.core.b b(String str, String str2) {
        com.igexin.push.core.b bVar = com.igexin.push.core.b.success;
        PushTaskBean pushTaskBean = (PushTaskBean) com.igexin.push.core.g.al.get(str + ":" + str2);
        if (pushTaskBean == null) {
            return com.igexin.push.core.b.stop;
        }
        int i = 0;
        com.igexin.push.core.b bVar2 = bVar;
        for (BaseAction baseAction : pushTaskBean.getActionChains()) {
            bVar = com.igexin.push.core.b.stop;
            if (baseAction == null) {
                return bVar;
            }
            com.igexin.push.core.b bVar3;
            for (IPushExtension prepareExecuteAction : com.igexin.push.extension.a.a().c()) {
                bVar = prepareExecuteAction.prepareExecuteAction(pushTaskBean, baseAction);
                if (bVar != com.igexin.push.core.b.stop) {
                    bVar3 = bVar;
                    break;
                }
            }
            bVar3 = bVar;
            if (bVar3 == com.igexin.push.core.b.stop) {
                com.igexin.push.core.a.a.a aVar = (com.igexin.push.core.a.a.a) b.get(baseAction.getType());
                if (aVar == null) {
                    return bVar3;
                }
                bVar3 = aVar.a(pushTaskBean, baseAction);
                if (bVar3 == com.igexin.push.core.b.stop) {
                    return bVar3;
                }
            }
            i = bVar3 == com.igexin.push.core.b.wait ? i + 1 : i;
            bVar2 = bVar2 == com.igexin.push.core.b.success ? bVar3 : bVar2;
        }
        if (!(i == 0 || com.igexin.push.core.g.a(str, Integer.valueOf(i), true))) {
            bVar2 = com.igexin.push.core.b.success;
        }
        return bVar2;
    }

    public String b(com.igexin.push.core.bean.f fVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            String a = fVar.a();
            Map b = fVar.b();
            String str = "[]";
            if (a != null) {
                jSONObject.put("version", a);
            }
            if (b != null && b.size() > 0) {
                a = "[";
                for (Entry value : b.entrySet()) {
                    com.igexin.push.core.bean.e eVar = (com.igexin.push.core.bean.e) value.getValue();
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(StarCategory.KEY_STAR_CATEGORY_ID, eVar.a());
                    jSONObject2.put("version", eVar.b());
                    jSONObject2.put("name", eVar.c());
                    jSONObject2.put("cls_name", eVar.d());
                    jSONObject2.put("url", eVar.e());
                    jSONObject2.put("checksum", eVar.f());
                    jSONObject2.put("isdestroy", eVar.g());
                    jSONObject2.put("effective", eVar.h());
                    jSONObject2.put("loadTime", eVar.i());
                    jSONObject2.put("key", eVar.j());
                    a = (a + jSONObject2.toString()) + SelectCountryActivity.SPLITTER;
                }
                str = (a.endsWith(SelectCountryActivity.SPLITTER) ? a.substring(0, a.length() - 1) : a) + "]";
            }
            jSONObject.put("extensions", new JSONArray(str));
            return jSONObject.toString();
        } catch (JSONException e) {
            return null;
        }
    }

    public void b() {
        d();
    }

    public void b(int i, String str) {
        com.igexin.push.a.k.i = i;
        com.igexin.push.a.a.a().d();
    }

    public void b(Intent intent) {
        if (intent != null && intent.hasExtra("isSlave") && intent.getBooleanExtra("isSlave", false)) {
            com.igexin.push.core.f.a().a(true);
            if (intent.hasExtra("op_app")) {
                com.igexin.push.core.g.E = intent.getStringExtra("op_app");
            } else {
                com.igexin.push.core.g.E = "";
            }
            if (com.igexin.push.core.g.o) {
                l();
            }
        }
    }

    public void b(PushTaskBean pushTaskBean, String str) {
        String str2 = pushTaskBean.getMessageId() + "|" + str;
        com.igexin.push.c.c.c cVar;
        if (com.igexin.push.core.g.ao.containsKey(str2)) {
            cVar = (com.igexin.push.c.c.c) com.igexin.push.core.g.ao.get(str2);
            if (cVar.c() < 2) {
                com.igexin.push.core.f.a().e().a("C-" + com.igexin.push.core.g.u, cVar);
                cVar.a(cVar.c() + 1);
                a(cVar, pushTaskBean, str, str2);
            }
        } else {
            cVar = new com.igexin.push.c.c.c();
            long currentTimeMillis = System.currentTimeMillis();
            cVar.a();
            cVar.c = "FDB" + pushTaskBean.getMessageId() + "|" + pushTaskBean.getTaskId() + "|" + str + "|" + "ok" + "|" + currentTimeMillis;
            cVar.d = com.igexin.push.core.g.u;
            cVar.a = (int) currentTimeMillis;
            com.igexin.push.core.f.a().e().a("C-" + com.igexin.push.core.g.u, cVar);
            a(cVar, pushTaskBean, str, str2);
        }
        com.igexin.a.a.c.a.a("cdnfeedback|" + pushTaskBean.getTaskId() + "|" + pushTaskBean.getMessageId() + "|" + str);
    }

    public void b(String str) {
        if (str != null) {
            long currentTimeMillis = System.currentTimeMillis();
            String str2 = "{\"action\":\"bindapp\",\"appid\":\"" + str + "\",\"cid\":\"" + com.igexin.push.core.g.u + "\",\"id\":\"" + currentTimeMillis + "\",\"type\":\"bind\"}";
            com.igexin.push.core.c.c a = com.igexin.push.core.c.c.a();
            if (a != null) {
                a.a(new com.igexin.push.core.bean.i(currentTimeMillis, str2, (byte) 1, currentTimeMillis));
            }
            if (str2 != null) {
                com.igexin.push.c.c.e dVar = new com.igexin.push.c.c.d();
                dVar.a();
                dVar.a = (int) currentTimeMillis;
                dVar.d = "17258000";
                dVar.e = str2;
                dVar.g = com.igexin.push.core.g.u;
                com.igexin.push.core.f.a().e().a("C-" + com.igexin.push.core.g.u, dVar);
            }
        }
    }

    public void b(String str, String str2, String str3, String str4) {
        byte[] bytes;
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt(PushConsts.CMD_ACTION, PushConsts.GET_MSG_DATA);
        bundle.putString("taskid", str);
        bundle.putString("messageid", str2);
        bundle.putString("appid", str3);
        bundle.putString("packagename", com.igexin.push.core.g.g);
        intent.setAction("com.igexin.sdk.action." + str3);
        if (str4 != null) {
            bytes = str4.getBytes();
        } else {
            PushTaskBean pushTaskBean = (PushTaskBean) com.igexin.push.core.g.al.get(a(str, str2));
            bytes = pushTaskBean != null ? pushTaskBean.getMsgExtra() : null;
        }
        if (bytes != null) {
            bundle.putByteArray("payload", bytes);
            intent.putExtras(bundle);
        } else {
            bundle.putByteArray("payload", bytes);
            intent.putExtras(bundle);
        }
        try {
            com.igexin.a.a.c.a.a("startapp|broadcast|" + str3 + "|" + new String(bytes, TTPodFMHttpClient.AppEncode));
        } catch (Exception e) {
        }
        com.igexin.push.core.g.i.sendBroadcast(intent);
    }

    public boolean b(String str, String str2, String str3) {
        Cursor cursor;
        BaseAction baseAction;
        com.igexin.push.core.a.a.a aVar;
        Throwable th;
        PushTaskBean pushTaskBean = (PushTaskBean) com.igexin.push.core.g.al.get(str + ":" + str2);
        if (pushTaskBean == null) {
            Cursor a;
            try {
                a = com.igexin.push.core.f.a().i().a("message", new String[]{"taskid", "messageid"}, new String[]{str, str2}, null, null);
                if (a != null) {
                    try {
                        if (a.getCount() > 0) {
                            while (a.moveToNext()) {
                                a(new JSONObject(new String(a.c(a.getBlob(a.getColumnIndexOrThrow("info"))))), a.getBlob(a.getColumnIndexOrThrow("msgextra")), false);
                                PushTaskBean pushTaskBean2 = (PushTaskBean) com.igexin.push.core.g.al.get(str + ":" + str2);
                                if (pushTaskBean2 == null) {
                                    if (a != null) {
                                        a.close();
                                    }
                                    return false;
                                }
                                pushTaskBean = pushTaskBean2;
                            }
                            if (a != null) {
                                a.close();
                            }
                        } else {
                            if (a != null) {
                                a.close();
                            }
                            return false;
                        }
                    } catch (Exception e) {
                        cursor = a;
                        if (cursor != null) {
                            cursor.close();
                        }
                        a().a(pushTaskBean, str3);
                        baseAction = pushTaskBean.getBaseAction(str3);
                        if (baseAction != null) {
                            return false;
                        }
                        if (baseAction.isSupportExt()) {
                            for (IPushExtension executeAction : com.igexin.push.extension.a.a().c()) {
                                if (executeAction.executeAction(pushTaskBean, baseAction)) {
                                    return true;
                                }
                            }
                        }
                        aVar = (com.igexin.push.core.a.a.a) b.get(baseAction.getType());
                        if (aVar != null) {
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (a != null) {
                            a.close();
                        }
                        throw th;
                    }
                }
                if (a != null) {
                    a.close();
                }
                return false;
            } catch (Exception e2) {
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
                a().a(pushTaskBean, str3);
                baseAction = pushTaskBean.getBaseAction(str3);
                if (baseAction != null) {
                    return false;
                }
                if (baseAction.isSupportExt()) {
                    while (r2.hasNext()) {
                        if (executeAction.executeAction(pushTaskBean, baseAction)) {
                            return true;
                        }
                    }
                }
                aVar = (com.igexin.push.core.a.a.a) b.get(baseAction.getType());
                if (aVar != null) {
                }
            } catch (Throwable th3) {
                th = th3;
                a = null;
                if (a != null) {
                    a.close();
                }
                throw th;
            }
        }
        a().a(pushTaskBean, str3);
        baseAction = pushTaskBean.getBaseAction(str3);
        if (baseAction != null) {
            return false;
        }
        if (baseAction.isSupportExt()) {
            while (r2.hasNext()) {
                if (executeAction.executeAction(pushTaskBean, baseAction)) {
                    return true;
                }
            }
        }
        aVar = (com.igexin.push.core.a.a.a) b.get(baseAction.getType());
        return (aVar != null || pushTaskBean.isStop()) ? false : aVar.b(pushTaskBean, baseAction);
    }

    public com.igexin.push.c.c.i c() {
        com.igexin.push.c.c.i iVar = new com.igexin.push.c.c.i();
        iVar.a = com.igexin.push.core.g.t;
        iVar.b = (byte) 0;
        iVar.c = MotionEventCompat.ACTION_POINTER_INDEX_MASK;
        try {
            if (E()) {
                List arrayList = new ArrayList();
                WifiManager wifiManager = (WifiManager) com.igexin.push.core.g.i.getSystemService("wifi");
                if (wifiManager != null && wifiManager.isWifiEnabled()) {
                    WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                    if (connectionInfo != null) {
                        String ssid = connectionInfo.getSSID();
                        String bssid = connectionInfo.getBSSID();
                        if (ssid != null) {
                            com.igexin.push.c.c.j jVar = new com.igexin.push.c.c.j();
                            jVar.a = (byte) 1;
                            jVar.b = ssid;
                            arrayList.add(jVar);
                        }
                        if (bssid != null) {
                            com.igexin.push.c.c.j jVar2 = new com.igexin.push.c.c.j();
                            jVar2.a = (byte) 4;
                            jVar2.b = bssid;
                            arrayList.add(jVar2);
                        }
                    }
                }
                if (arrayList.size() > 0) {
                    iVar.d = arrayList;
                }
            }
        } catch (Exception e) {
        }
        return iVar;
    }

    public void c(Intent intent) {
        Throwable th;
        Cursor cursor = null;
        if (intent != null) {
            try {
                if (PushConsts.ACTION_BROADCAST_NETWORK_CHANGE.equals(intent.getAction())) {
                    if (com.igexin.a.a.b.d.c() != null) {
                        com.igexin.push.core.i.a().a(com.igexin.push.core.k.NETWORK_SWITCH);
                        if (com.igexin.push.core.f.a().h().getActiveNetworkInfo() == null || !com.igexin.push.core.f.a().h().getActiveNetworkInfo().isAvailable()) {
                            com.igexin.push.core.g.k = false;
                        } else {
                            com.igexin.push.core.g.k = true;
                        }
                        if (!com.igexin.push.core.g.o) {
                            com.igexin.push.core.f.a().e().c(true);
                        } else if (System.currentTimeMillis() - com.igexin.push.core.g.U > 5000) {
                            com.igexin.push.core.g.U = System.currentTimeMillis();
                            if (f() == -2) {
                                com.igexin.push.core.g.o = false;
                                m();
                            }
                        }
                        if (v()) {
                            u();
                        }
                    }
                } else if ("com.igexin.sdk.action.snlrefresh".equals(intent.getAction()) || com.igexin.push.core.g.W.equals(intent.getAction()) || "com.igexin.sdk.action.snlretire".equals(intent.getAction())) {
                    com.igexin.push.core.f.a().f().a(intent);
                } else if ("com.igexin.sdk.action.execute".equals(intent.getAction())) {
                    String stringExtra = intent.getStringExtra("taskid");
                    String stringExtra2 = intent.getStringExtra("messageid");
                    r0 = intent.getStringExtra("appid");
                    String stringExtra3 = intent.getStringExtra("pkgname");
                    ContentValues contentValues = new ContentValues();
                    r4 = "EXEC_" + stringExtra;
                    contentValues.put("taskid", stringExtra);
                    contentValues.put("appid", r0);
                    contentValues.put("key", r4);
                    contentValues.put("createtime", Long.valueOf(System.currentTimeMillis()));
                    Cursor a;
                    try {
                        a = com.igexin.push.core.f.a().i().a("message", new String[]{"key"}, new String[]{r4}, null, null);
                        if (a != null) {
                            try {
                                if (a.getCount() == 0) {
                                    com.igexin.push.core.f.a().i().a("message", contentValues);
                                    if (stringExtra3.equals(com.igexin.push.core.g.g)) {
                                        if (stringExtra2 == null || stringExtra == null) {
                                            if (a != null) {
                                                a.close();
                                                return;
                                            }
                                            return;
                                        } else if (com.igexin.push.core.f.a() != null && b(stringExtra, stringExtra2) == com.igexin.push.core.b.success) {
                                            a(stringExtra, stringExtra2, "1");
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                if (a != null) {
                                    a.close();
                                }
                            } catch (Throwable th2) {
                                cursor = a;
                                th = th2;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                throw th;
                            }
                        }
                        if (a != null) {
                            a.close();
                        }
                    } catch (Exception e2) {
                        a = null;
                        if (a != null) {
                            a.close();
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                } else if ("com.igexin.sdk.action.doaction".equals(intent.getAction())) {
                    String stringExtra4 = intent.getStringExtra("taskid");
                    String stringExtra5 = intent.getStringExtra("messageid");
                    r4 = intent.getStringExtra("actionid");
                    String stringExtra6 = intent.getStringExtra("accesstoken");
                    int intExtra = intent.getIntExtra("notifID", 0);
                    NotificationManager notificationManager = (NotificationManager) com.igexin.push.core.g.i.getSystemService("notification");
                    if (intExtra != 0) {
                        notificationManager.cancel(intExtra);
                    } else if (com.igexin.push.core.g.am.get(stringExtra4) != null) {
                        notificationManager.cancel(((Integer) com.igexin.push.core.g.am.get(stringExtra4)).intValue());
                    }
                    if (stringExtra6.equals(com.igexin.push.core.g.ax)) {
                        b(stringExtra4, stringExtra5, r4);
                    }
                } else if ("android.intent.action.TIME_SET".equals(intent.getAction())) {
                    if (com.igexin.push.a.k.f != 0) {
                        com.igexin.push.e.b.g.g().h();
                    }
                } else if ("android.intent.action.SCREEN_ON".equals(intent.getAction())) {
                    com.igexin.push.core.g.s = 1;
                    if (v()) {
                        u();
                    }
                } else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                    com.igexin.push.core.g.s = 0;
                } else if ("android.intent.action.PACKAGE_ADDED".equals(intent.getAction())) {
                    String dataString = intent.getDataString();
                    if (dataString != null && dataString.startsWith("package:")) {
                        dataString = dataString.substring(8);
                        try {
                            PackageInfo packageInfo = com.igexin.push.core.g.i.getPackageManager().getPackageInfo(dataString, 4);
                            if (packageInfo != null) {
                                ServiceInfo[] serviceInfoArr = packageInfo.services;
                                if (serviceInfoArr != null) {
                                    for (ServiceInfo serviceInfo : serviceInfoArr) {
                                        if (com.igexin.push.core.a.o.equals(serviceInfo.name) || com.igexin.push.core.a.n.equals(serviceInfo.name) || com.igexin.push.core.a.p.equals(serviceInfo.name)) {
                                            com.igexin.push.core.c.f.a().c().put(dataString, serviceInfo.name);
                                            return;
                                        }
                                    }
                                }
                            }
                        } catch (NameNotFoundException e3) {
                        }
                    }
                } else if ("android.intent.action.PACKAGE_REMOVED".equals(intent.getAction())) {
                    r0 = intent.getDataString();
                    if (r0 != null && r0.startsWith("package:")) {
                        r0 = r0.substring(8);
                        if (com.igexin.push.core.c.f.a().c().containsKey(r0)) {
                            com.igexin.push.core.c.f.a().c().remove(r0);
                        }
                    }
                } else if ("com.igexin.sdk.action.core.clearmsg".equals(intent.getAction())) {
                    com.igexin.push.core.f.a().i().a("message", null);
                }
            } catch (Exception e4) {
            }
        }
    }

    public void c(String str) {
        if (com.igexin.push.core.g.u != null) {
            long currentTimeMillis = System.currentTimeMillis();
            String str2 = "{\"action\":\"set_tag\",\"id\":\"" + currentTimeMillis + "\", \"cid\":\"" + com.igexin.push.core.g.u + "\", \"appid\":\"" + com.igexin.push.core.g.c + "\", \"tags\":\"" + str + "\"}";
            com.igexin.push.core.c.c a = com.igexin.push.core.c.c.a();
            if (a != null) {
                a.a(new com.igexin.push.core.bean.i(currentTimeMillis, str2, (byte) 2, currentTimeMillis));
            }
            com.igexin.push.c.c.d dVar = new com.igexin.push.c.c.d();
            dVar.a();
            dVar.d = "17258000";
            dVar.e = str2;
            com.igexin.a.a.b.d.c().a(com.igexin.push.core.g.a, 3, com.igexin.push.core.f.a().d(), dVar, false);
            com.igexin.a.a.c.a.a("settag");
        }
    }

    public int d() {
        Cursor cursor;
        Throwable th;
        if (!com.igexin.push.core.g.l || !com.igexin.push.core.g.m || a(System.currentTimeMillis()) || !n()) {
            return -1;
        }
        int i;
        if (com.igexin.push.core.g.n) {
            com.igexin.push.core.g.n = !com.igexin.push.core.g.n;
            com.igexin.push.core.g.O = (((long) Math.abs(new Random().nextInt() % 24)) * 3600000) + System.currentTimeMillis();
        }
        r.b();
        if (com.igexin.push.core.g.t == 0) {
            com.igexin.a.a.c.a.a("registerReq");
            i = com.igexin.push.core.f.a().e().a(new StringBuilder().append("R-").append(com.igexin.push.core.g.C).toString(), new com.igexin.push.c.c.f(com.igexin.push.core.g.w, com.igexin.push.core.g.x, com.igexin.push.core.g.C, com.igexin.push.core.g.c)) < 0 ? 0 : 1;
        } else {
            com.igexin.push.c.c.e c = c();
            com.igexin.a.a.c.a.a("loginReqBefore|" + c.a);
            i = com.igexin.push.core.f.a().e().a(new StringBuilder().append("S-").append(String.valueOf(com.igexin.push.core.g.t)).toString(), c) < 0 ? 0 : 1;
            if (i != 0) {
                com.igexin.a.a.c.a.a("loginReq|" + com.igexin.push.core.g.u);
            }
        }
        if (i != 0) {
            return 1;
        }
        Cursor a;
        try {
            String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            a = com.igexin.push.core.f.a().i().a("bi", new String[]{SocialConstants.PARAM_TYPE}, new String[]{"1"}, null, null);
            if (a != null) {
                try {
                    if (a.getCount() == 0) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("loginerror_nonetwork_count", Integer.valueOf(1));
                        contentValues.put("create_time", format);
                        contentValues.put(SocialConstants.PARAM_TYPE, "1");
                        com.igexin.push.core.f.a().i().a("bi", contentValues);
                    } else {
                        i = 0;
                        while (a.moveToNext()) {
                            String string = a.getString(a.getColumnIndexOrThrow("create_time"));
                            String string2 = a.getString(a.getColumnIndexOrThrow(StarCategory.KEY_STAR_CATEGORY_ID));
                            ContentValues contentValues2;
                            if (format.equals(string)) {
                                i = a.getInt(a.getColumnIndexOrThrow("loginerror_nonetwork_count"));
                                contentValues2 = new ContentValues();
                                contentValues2.put("loginerror_nonetwork_count", Integer.valueOf(i + 1));
                                com.igexin.push.core.f.a().i().a("bi", contentValues2, new String[]{StarCategory.KEY_STAR_CATEGORY_ID}, new String[]{string2});
                            } else {
                                contentValues2 = new ContentValues();
                                contentValues2.put(SocialConstants.PARAM_TYPE, FeedbackItem.STATUS_SOLVED);
                                com.igexin.push.core.f.a().i().a("bi", contentValues2, new String[]{StarCategory.KEY_STAR_CATEGORY_ID}, new String[]{string2});
                                contentValues2 = new ContentValues();
                                contentValues2.put("loginerror_nonetwork_count", Integer.valueOf(i + 1));
                                contentValues2.put("create_time", format);
                                contentValues2.put(SocialConstants.PARAM_TYPE, "1");
                                com.igexin.push.core.f.a().i().a("bi", contentValues2);
                            }
                        }
                    }
                } catch (Exception e) {
                    cursor = a;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (a != null) {
                a.close();
            }
        } catch (Exception e2) {
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            return 0;
        } catch (Throwable th3) {
            th = th3;
            a = null;
            if (a != null) {
                a.close();
            }
            throw th;
        }
        return 0;
    }

    public void d(Intent intent) {
        if (intent != null) {
            int intExtra = intent.getIntExtra(StarCategory.KEY_STAR_CATEGORY_ID, -1);
            boolean booleanExtra = intent.getBooleanExtra("result", false);
            if (intExtra != -1) {
                com.igexin.push.core.g.au++;
                if (booleanExtra) {
                    com.igexin.push.core.g.at++;
                    Map b = com.igexin.push.core.g.av != null ? com.igexin.push.core.g.av.b() : null;
                    if (b != null) {
                        Map map;
                        if (com.igexin.push.a.k.x != null) {
                            Map b2 = com.igexin.push.a.k.x.b();
                            if (b2 == null) {
                                return;
                            }
                            if (b2.containsKey(Integer.valueOf(intExtra))) {
                                booleanExtra = true;
                                com.igexin.push.core.bean.e eVar = (com.igexin.push.core.bean.e) b2.get(Integer.valueOf(intExtra));
                                if (eVar != null) {
                                    File file = new File(com.igexin.push.core.g.ad + "/" + eVar.c());
                                    if (file.exists()) {
                                        file.delete();
                                    }
                                }
                                b2.remove(Integer.valueOf(intExtra));
                                map = b2;
                            } else {
                                booleanExtra = false;
                                map = b2;
                            }
                        } else {
                            Map hashMap = new HashMap();
                            com.igexin.push.core.bean.f fVar = new com.igexin.push.core.bean.f();
                            fVar.a(FeedbackItem.STATUS_WAITING);
                            fVar.a(hashMap);
                            com.igexin.push.a.k.x = fVar;
                            map = hashMap;
                            booleanExtra = false;
                        }
                        com.igexin.push.core.bean.e eVar2 = (com.igexin.push.core.bean.e) b.get(Integer.valueOf(intExtra));
                        if (eVar2 != null) {
                            String str = com.igexin.push.core.g.ad + "/" + eVar2.c();
                            File file2 = new File(str);
                            if (file2.exists()) {
                                map.put(Integer.valueOf(intExtra), eVar2);
                                if (com.igexin.push.core.g.at == com.igexin.push.core.g.as) {
                                    com.igexin.push.a.k.x.a(com.igexin.push.core.g.av.a());
                                }
                                if (!booleanExtra && com.igexin.push.extension.a.a().a(com.igexin.push.core.g.i, str, eVar2.d(), eVar2.j(), eVar2.c())) {
                                    eVar2.b(System.currentTimeMillis());
                                    if (eVar2.g()) {
                                        file2.delete();
                                        map.remove(Integer.valueOf(intExtra));
                                    }
                                }
                                com.igexin.push.a.a.a().g();
                            }
                        } else {
                            return;
                        }
                    }
                    return;
                }
                if (com.igexin.push.core.g.au == com.igexin.push.core.g.as && com.igexin.push.core.g.aw) {
                    Process.killProcess(Process.myPid());
                }
            }
        }
    }

    public boolean d(String str) {
        PackageManager packageManager = com.igexin.push.core.g.i.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN", null);
        intent.addCategory("android.intent.category.LAUNCHER");
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 0)) {
            if (resolveInfo.activityInfo.packageName.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void e() {
        com.igexin.a.a.b.d.c().a(com.igexin.push.core.g.a.replaceFirst("socket", "disConnect"), 0, null);
    }

    public boolean e(String str) {
        for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) com.igexin.push.core.g.i.getSystemService("activity")).getRunningAppProcesses()) {
            if (str.equals(runningAppProcessInfo.processName)) {
                return true;
            }
        }
        return false;
    }

    public int f() {
        return com.igexin.push.core.f.a().e().a("H-" + com.igexin.push.core.g.u, new com.igexin.push.c.c.h());
    }

    public void f(String str) {
        com.igexin.a.a.b.d.c().a(new com.igexin.push.e.a.c(new com.igexin.push.core.d.g(com.igexin.push.core.g.a(), ((a(true, 4) + "2.3.6.0|sdkconfig-error|") + str).getBytes(), 0, true)), false, true);
    }

    public String g(String str) {
        return com.igexin.push.core.g.c() == null ? null : (String) com.igexin.push.core.g.c().get(str);
    }

    public void g() {
        for (com.igexin.push.core.bean.i iVar : com.igexin.push.core.c.c.a().b()) {
            if (iVar.d() + 10000 <= System.currentTimeMillis()) {
                long currentTimeMillis = System.currentTimeMillis();
                com.igexin.push.c.c.e dVar = new com.igexin.push.c.c.d();
                dVar.a();
                dVar.a = (int) currentTimeMillis;
                dVar.d = "17258000";
                dVar.e = iVar.b();
                dVar.g = com.igexin.push.core.g.u;
                com.igexin.push.core.f.a().e().a("C-" + com.igexin.push.core.g.u, dVar);
                com.igexin.a.a.c.a.a("freshral|" + iVar.b());
                return;
            }
        }
    }

    public void h() {
        long currentTimeMillis = System.currentTimeMillis();
        String str = "{\"action\":\"request_deviceid\",\"id\":\"" + currentTimeMillis + "\"}";
        com.igexin.push.c.c.e dVar = new com.igexin.push.c.c.d();
        dVar.a();
        dVar.a = (int) currentTimeMillis;
        dVar.d = "17258000";
        dVar.e = str;
        dVar.g = com.igexin.push.core.g.u;
        com.igexin.push.core.f.a().e().a("C-" + com.igexin.push.core.g.u, dVar);
        com.igexin.a.a.c.a.a("deviceidReq");
    }

    public void i() {
        long j;
        String str = null;
        long j2 = -1;
        try {
            com.igexin.push.core.bean.a aVar = new com.igexin.push.core.bean.a();
            j2 = aVar.l;
            str = com.igexin.push.core.bean.a.a(aVar);
            j = j2;
        } catch (JSONException e) {
            j = j2;
        }
        if (str != null) {
            com.igexin.a.a.c.a.a("addphoneinfo");
            com.igexin.push.core.c.c a = com.igexin.push.core.c.c.a();
            if (a != null) {
                a.a(new com.igexin.push.core.bean.i(j, str, (byte) 5, j));
            }
            com.igexin.push.c.c.e dVar = new com.igexin.push.c.c.d();
            dVar.a();
            dVar.a = (int) j;
            dVar.d = "17258000";
            dVar.e = str;
            dVar.g = com.igexin.push.core.g.u;
            com.igexin.push.core.f.a().e().a("C-" + com.igexin.push.core.g.u, dVar);
        }
    }

    public void j() {
        long currentTimeMillis = System.currentTimeMillis();
        String str = "{\"action\":\"request_ca_list\",\"id\":\"" + currentTimeMillis + "\", \"appid\":\"" + com.igexin.push.core.g.c + "\", \"cid\":\"" + com.igexin.push.core.g.u + "\"}";
        com.igexin.push.c.c.e dVar = new com.igexin.push.c.c.d();
        dVar.a();
        dVar.a = (int) currentTimeMillis;
        dVar.d = "17258000";
        dVar.e = str;
        dVar.g = com.igexin.push.core.g.u;
        com.igexin.push.core.f.a().e().a("C-" + com.igexin.push.core.g.u, dVar);
    }

    public long k() {
        return ((long) (new Random().nextInt(6) + 2)) * 60000;
    }

    public void l() {
        Intent intent = new Intent();
        intent.setAction("com.igexin.sdk.action." + com.igexin.push.core.g.c);
        Bundle bundle = new Bundle();
        bundle.putInt(PushConsts.CMD_ACTION, PushConsts.GET_CLIENTID);
        bundle.putString("clientid", com.igexin.push.core.g.u);
        intent.putExtras(bundle);
        com.igexin.push.core.f.a().a(intent);
        Log.d("PushService", "clientid is " + com.igexin.push.core.g.u);
    }

    public void m() {
        Intent intent = new Intent();
        intent.setAction("com.igexin.sdk.action." + com.igexin.push.core.g.c);
        Bundle bundle = new Bundle();
        bundle.putInt(PushConsts.CMD_ACTION, PushConsts.GET_SDKONLINESTATE);
        bundle.putBoolean("onlineState", com.igexin.push.core.g.o);
        intent.putExtras(bundle);
        com.igexin.push.core.f.a().a(intent);
    }

    public boolean n() {
        return System.currentTimeMillis() > com.igexin.push.a.k.g;
    }

    public String o() {
        FileInputStream fileInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        FileInputStream fileInputStream2;
        Throwable th;
        if (new File(com.igexin.push.core.g.aa).exists()) {
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream2;
            try {
                fileInputStream = new FileInputStream(com.igexin.push.core.g.aa);
                try {
                    byteArrayOutputStream2 = new ByteArrayOutputStream();
                    while (true) {
                        try {
                            int read = fileInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream2.write(bArr, 0, read);
                        } catch (Exception e) {
                            byteArrayOutputStream = byteArrayOutputStream2;
                            fileInputStream2 = fileInputStream;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    String str = new String(byteArrayOutputStream2.toByteArray());
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e2) {
                        }
                    }
                    if (byteArrayOutputStream2 == null) {
                        return str;
                    }
                    try {
                        byteArrayOutputStream2.close();
                        return str;
                    } catch (Exception e3) {
                        return str;
                    }
                } catch (Exception e4) {
                    byteArrayOutputStream = null;
                    fileInputStream2 = fileInputStream;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (Exception e5) {
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                            return null;
                        } catch (Exception e6) {
                            return null;
                        }
                    }
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    byteArrayOutputStream2 = null;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e7) {
                        }
                    }
                    if (byteArrayOutputStream2 != null) {
                        try {
                            byteArrayOutputStream2.close();
                        } catch (Exception e8) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e9) {
                byteArrayOutputStream = null;
                fileInputStream2 = null;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                    return null;
                }
                return null;
            } catch (Throwable th4) {
                th = th4;
                byteArrayOutputStream2 = null;
                fileInputStream = null;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                }
                throw th;
            }
        }
        return null;
    }

    public void p() {
        List arrayList = new ArrayList();
        a(arrayList);
        int size = arrayList.size();
        if (size > 0) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(PushConsts.CMD_ACTION, "reportapplist");
                jSONObject.put("session_last", com.igexin.push.core.g.t);
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < size; i++) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("appid", ((l) arrayList.get(i)).c());
                    jSONObject2.put("name", ((l) arrayList.get(i)).a());
                    jSONObject2.put("version", ((l) arrayList.get(i)).b());
                    jSONArray.put(jSONObject2);
                }
                jSONObject.put("applist", jSONArray);
            } catch (JSONException e) {
            }
            byte[] b = a.b(jSONObject.toString().getBytes());
            if (b != null) {
                com.igexin.a.a.b.d.c().a(new com.igexin.push.e.a.c(new com.igexin.push.core.d.a(com.igexin.push.core.g.a(), b)), false, true);
                h(q());
                com.igexin.a.a.c.a.a("reportapplist");
            }
        }
    }

    public String q() {
        ArrayList arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        a(arrayList2);
        int size = arrayList2.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                arrayList.add(((l) arrayList2.get(i)).c());
            }
        }
        return arrayList.toString();
    }

    public boolean r() {
        String packageName;
        boolean z;
        boolean z2;
        boolean z3;
        if (com.igexin.push.core.g.i == null) {
            packageName = com.igexin.push.core.g.i.getApplicationContext().getPackageName();
        } else {
            packageName = com.igexin.push.core.g.i.getApplicationContext().getPackageName();
        }
        try {
            int i;
            ServiceInfo[] serviceInfoArr = com.igexin.push.core.g.i.getPackageManager().getPackageInfo(packageName, 4).services;
            if (serviceInfoArr != null) {
                int length = serviceInfoArr.length;
                i = 0;
                z = false;
                while (i < length) {
                    try {
                        if (serviceInfoArr[i].name.indexOf("DownloadService") != -1) {
                            z = true;
                        }
                        i++;
                    } catch (NameNotFoundException e) {
                        z2 = false;
                        z3 = z;
                        z = false;
                    }
                }
                z3 = z;
            } else {
                z3 = false;
            }
            try {
                int length2;
                ProviderInfo[] providerInfoArr = com.igexin.push.core.g.i.getPackageManager().getPackageInfo(packageName, 8).providers;
                if (providerInfoArr != null) {
                    length2 = providerInfoArr.length;
                    i = 0;
                    z = false;
                    while (i < length2) {
                        try {
                            if (providerInfoArr[i].name.indexOf("DownloadProvider") != -1) {
                                z = true;
                            }
                            i++;
                        } catch (NameNotFoundException e2) {
                            z2 = z;
                            z = false;
                        }
                    }
                    z2 = z;
                } else {
                    z2 = false;
                }
                try {
                    ActivityInfo[] activityInfoArr = com.igexin.push.core.g.i.getPackageManager().getPackageInfo(packageName, 2).receivers;
                    if (activityInfoArr != null) {
                        length2 = activityInfoArr.length;
                        int i2 = 0;
                        z = false;
                        while (i2 < length2) {
                            try {
                                if (activityInfoArr[i2].name.indexOf("DownloadReceiver") != -1) {
                                    z = true;
                                }
                                i2++;
                            } catch (NameNotFoundException e3) {
                            }
                        }
                    } else {
                        z = false;
                    }
                } catch (NameNotFoundException e4) {
                    z = false;
                }
            } catch (NameNotFoundException e5) {
                z = false;
                z2 = false;
            }
        } catch (NameNotFoundException e6) {
            z = false;
            z2 = false;
            z3 = false;
        }
        return z3 && z2 && z;
    }

    public void s() {
        com.igexin.push.core.f.a().i().a("message", "createtime <= " + (System.currentTimeMillis() - 604800000));
    }

    public void t() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        String str = "/sdcard/libs/";
        File file = new File(str);
        String str2 = com.igexin.push.core.g.g;
        if (str2 == null) {
            str2 = "unknowPacageName";
        }
        if (file.exists()) {
            String[] list = file.list();
            int length = list.length;
            int i = 0;
            while (i < length) {
                int length2 = list[i].length();
                if (list[i].startsWith(str2) && list[i].endsWith(".log") && length2 > str2.length() + 14 && str2.equals(list[i].substring(0, length2 - 15))) {
                    try {
                        if (Math.abs((simpleDateFormat.parse(format).getTime() - simpleDateFormat.parse(list[i].substring(str2.length() + 1, length2 - 4)).getTime()) / HttpChannelSongListGetV2.CACHE_TIME) > 6) {
                            File file2 = new File(str + list[i]);
                            if (file2.exists()) {
                                file2.delete();
                            }
                        }
                    } catch (Exception e) {
                    }
                }
                i++;
            }
        }
    }

    public void u() {
        if (F() > 0) {
            List arrayList = new ArrayList();
            for (Entry key : com.igexin.push.core.g.al.entrySet()) {
                String str = (String) key.getKey();
                PushTaskBean pushTaskBean = (PushTaskBean) com.igexin.push.core.g.al.get(str);
                Object obj = "";
                if (pushTaskBean != null && pushTaskBean.getStatus() == com.igexin.push.core.a.k) {
                    String taskId = pushTaskBean.getTaskId();
                    Map conditionMap = pushTaskBean.getConditionMap();
                    if (conditionMap != null) {
                        if (!conditionMap.containsKey("endTime") || Long.valueOf((String) conditionMap.get("endTime")).longValue() >= System.currentTimeMillis()) {
                            int intValue;
                            String str2;
                            if (conditionMap.containsKey("wifi")) {
                                intValue = Integer.valueOf((String) conditionMap.get("wifi")).intValue();
                                x();
                                if (intValue != com.igexin.push.core.g.r) {
                                }
                            }
                            if (conditionMap.containsKey("screenOn")) {
                                intValue = Integer.valueOf((String) conditionMap.get("screenOn")).intValue();
                                w();
                                if (intValue != com.igexin.push.core.g.s) {
                                }
                            }
                            if (conditionMap.containsKey("ssid")) {
                                str2 = (String) conditionMap.get("ssid");
                                y();
                                if (com.igexin.push.core.g.ar.containsValue(str2)) {
                                    obj = str2;
                                }
                            }
                            if (conditionMap.containsKey("bssid")) {
                                str2 = (String) conditionMap.get("bssid");
                                if (com.igexin.push.core.g.ar.containsKey(str2)) {
                                    if (!((String) com.igexin.push.core.g.ar.get(str2)).equals(obj)) {
                                    }
                                }
                            }
                            if (!conditionMap.containsKey("startTime") || Long.valueOf((String) conditionMap.get("startTime")).longValue() <= System.currentTimeMillis()) {
                                a().a(taskId, pushTaskBean.getMessageId(), com.igexin.push.core.g.c, com.igexin.push.core.g.g);
                                a(com.igexin.push.core.a.l, taskId, str);
                                arrayList.add(str);
                            }
                        } else {
                            a(com.igexin.push.core.a.m, taskId, str);
                            arrayList.add(str);
                        }
                    } else {
                        return;
                    }
                }
            }
            b(arrayList);
        }
    }

    public boolean v() {
        long currentTimeMillis = System.currentTimeMillis();
        if (com.igexin.push.core.g.L <= 0) {
            com.igexin.push.core.g.L = currentTimeMillis - 60000;
            return true;
        } else if (currentTimeMillis - com.igexin.push.core.g.L <= 60000) {
            return false;
        } else {
            com.igexin.push.core.g.L = currentTimeMillis;
            return true;
        }
    }

    public void w() {
        if (((PowerManager) com.igexin.push.core.g.i.getSystemService("power")).isScreenOn()) {
            com.igexin.push.core.g.s = 1;
        } else {
            com.igexin.push.core.g.s = 0;
        }
    }

    public void x() {
        State state = ((ConnectivityManager) com.igexin.push.core.g.i.getSystemService("connectivity")).getNetworkInfo(1).getState();
        if (state == State.CONNECTED || state == State.CONNECTING) {
            com.igexin.push.core.g.r = 1;
        } else {
            com.igexin.push.core.g.r = 0;
        }
    }

    public void y() {
        List scanResults = ((WifiManager) com.igexin.push.core.g.i.getSystemService("wifi")).getScanResults();
        com.igexin.push.core.g.ar.clear();
        if (scanResults != null) {
            for (int i = 0; i < scanResults.size(); i++) {
                com.igexin.push.core.g.ar.put(((ScanResult) scanResults.get(i)).BSSID, ((ScanResult) scanResults.get(i)).SSID);
            }
        }
    }

    public void z() {
        if (com.igexin.push.a.k.u) {
            Map c = com.igexin.push.core.c.f.a().c();
            if (c != null && c.size() > 0) {
                for (String str : c.keySet()) {
                    String str2 = (String) c.get(str);
                    try {
                        Intent intent = new Intent();
                        intent.setClassName(str, str2);
                        com.igexin.push.core.g.i.startService(intent);
                    } catch (Exception e) {
                    }
                }
            }
        }
    }
}
