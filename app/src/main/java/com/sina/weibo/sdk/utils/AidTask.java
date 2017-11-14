package com.sina.weibo.sdk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.support.v4.os.EnvironmentCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.igexin.sdk.PushBuildConfig;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.exception.WeiboHttpException;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.tencent.connect.common.Constants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.concurrent.locks.ReentrantLock;
import javax.crypto.Cipher;
import org.json.JSONException;
import org.json.JSONObject;

public class AidTask {
    private static final String AID_FILE_NAME = "weibo_sdk_aid";
    private static final String TAG = "AidTask";
    private static final int VERSION = 1;
    public static final int WHAT_LOAD_AID_API_ERR = 1002;
    public static final int WHAT_LOAD_AID_IO_ERR = 1003;
    public static final int WHAT_LOAD_AID_SUC = 1001;
    private static AidTask sInstance;
    private String mAppKey;
    private Context mContext;
    private volatile ReentrantLock mTaskLock = new ReentrantLock(true);

    public static final class AidInfo {
        private String mAid;
        private String mSubCookie;

        public String getAid() {
            return this.mAid;
        }

        public String getSubCookie() {
            return this.mSubCookie;
        }

        public static AidInfo parseJson(String str) throws WeiboException {
            AidInfo aidInfo = new AidInfo();
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("error") || jSONObject.has("error_code")) {
                    LogUtil.d(AidTask.TAG, "loadAidFromNet has error !!!");
                    throw new WeiboException("loadAidFromNet has error !!!");
                }
                aidInfo.mAid = jSONObject.optString("aid", "");
                aidInfo.mSubCookie = jSONObject.optString("sub", "");
                return aidInfo;
            } catch (JSONException e) {
                LogUtil.d(AidTask.TAG, "loadAidFromNet JSONException Msg : " + e.getMessage());
                throw new WeiboException("loadAidFromNet has error !!!");
            }
        }
    }

    private AidTask(Context context) {
        this.mContext = context.getApplicationContext();
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 1; i++) {
                    try {
                        AidTask.this.getAidInfoFile(i).delete();
                    } catch (Exception e) {
                    }
                }
            }
        }).start();
    }

    public static synchronized AidTask getInstance(Context context) {
        AidTask aidTask;
        synchronized (AidTask.class) {
            if (sInstance == null) {
                sInstance = new AidTask(context);
            }
            aidTask = sInstance;
        }
        return aidTask;
    }

    public void setAppkey(String str) {
        this.mAppKey = str;
    }

    public void aidTaskInit() {
        aidTaskInit(this.mAppKey);
    }

    public void aidTaskInit(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mAppKey = str;
            new Thread(new Runnable() {
                public void run() {
                    if (!AidTask.this.mTaskLock.tryLock()) {
                        return;
                    }
                    if (TextUtils.isEmpty(AidTask.this.loadAidFromCache())) {
                        int i = 0;
                        while (i < 3) {
                            try {
                                String access$2 = AidTask.this.loadAidFromNet();
                                AidInfo.parseJson(access$2);
                                AidTask.this.cacheAidInfo(access$2);
                                break;
                            } catch (WeiboException e) {
                                LogUtil.e(AidTask.TAG, "AidTaskInit WeiboException Msg : " + e.getMessage());
                                i++;
                            }
                        }
                        AidTask.this.mTaskLock.unlock();
                        return;
                    }
                    AidTask.this.mTaskLock.unlock();
                }
            }).start();
        }
    }

    public ReentrantLock getTaskLock() {
        return this.mTaskLock;
    }

    public AidInfo getAidSync() throws WeiboException {
        if (TextUtils.isEmpty(this.mAppKey)) {
            return null;
        }
        String loadAidFromNet = loadAidFromNet();
        AidInfo parseJson = AidInfo.parseJson(loadAidFromNet);
        cacheAidInfo(loadAidFromNet);
        return parseJson;
    }

    public void getAidAsync(final Handler handler) {
        if (!TextUtils.isEmpty(this.mAppKey)) {
            final Message obtain = Message.obtain();
            new Thread(new Runnable() {
                public void run() {
                    try {
                        String access$2 = AidTask.this.loadAidFromNet();
                        AidInfo parseJson = AidInfo.parseJson(access$2);
                        AidTask.this.cacheAidInfo(access$2);
                        obtain.what = 1001;
                        obtain.obj = parseJson;
                        if (handler != null) {
                            handler.sendMessage(obtain);
                        }
                    } catch (WeiboException e) {
                        if ((e.getCause() instanceof IOException) || (e instanceof WeiboHttpException)) {
                            obtain.what = 1003;
                            if (handler != null) {
                                handler.sendMessage(obtain);
                                return;
                            }
                            return;
                        }
                        obtain.what = 1002;
                        if (handler != null) {
                            handler.sendMessage(obtain);
                        }
                    }
                }
            }).start();
        }
    }

    public synchronized String loadAidFromCache() {
        String aid;
        AidInfo loadAidInfoFromCache = loadAidInfoFromCache();
        if (loadAidInfoFromCache != null) {
            aid = loadAidInfoFromCache.getAid();
        } else {
            aid = "";
        }
        return aid;
    }

    public synchronized String loadSubCookieFromCache() {
        String subCookie;
        AidInfo loadAidInfoFromCache = loadAidInfoFromCache();
        if (loadAidInfoFromCache != null) {
            subCookie = loadAidInfoFromCache.getSubCookie();
        } else {
            subCookie = "";
        }
        return subCookie;
    }

    private synchronized AidInfo loadAidInfoFromCache() {
        FileInputStream fileInputStream;
        Throwable th;
        AidInfo aidInfo = null;
        synchronized (this) {
            try {
                fileInputStream = new FileInputStream(getAidInfoFile(1));
                try {
                    byte[] bArr = new byte[fileInputStream.available()];
                    fileInputStream.read(bArr);
                    aidInfo = AidInfo.parseJson(new String(bArr));
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                        }
                    }
                } catch (Exception e2) {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                        }
                    }
                    return aidInfo;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                fileInputStream = aidInfo;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return aidInfo;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                fileInputStream = aidInfo;
                th = th4;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        }
        return aidInfo;
    }

    private File getAidInfoFile(int i) {
        return new File(this.mContext.getFilesDir(), new StringBuilder(AID_FILE_NAME).append(i).toString());
    }

    private String loadAidFromNet() throws WeiboException {
        String str = "http://api.weibo.com/oauth2/getaid.json";
        str = this.mContext.getPackageName();
        String sign = Utility.getSign(this.mContext, str);
        String mfp = getMfp();
        WeiboParameters weiboParameters = new WeiboParameters(this.mAppKey);
        weiboParameters.put("appkey", this.mAppKey);
        weiboParameters.put("mfp", mfp);
        weiboParameters.put("packagename", str);
        weiboParameters.put("key_hash", sign);
        try {
            str = new AsyncWeiboRunner(this.mContext).request("http://api.weibo.com/oauth2/getaid.json", weiboParameters, Constants.HTTP_GET);
            LogUtil.d(TAG, "loadAidFromNet response : " + str);
            return str;
        } catch (WeiboException e) {
            LogUtil.d(TAG, "loadAidFromNet WeiboException Msg : " + e.getMessage());
            throw e;
        }
    }

    private synchronized void cacheAidInfo(String str) {
        Throwable th;
        if (!TextUtils.isEmpty(str)) {
            FileOutputStream fileOutputStream = null;
            FileOutputStream fileOutputStream2;
            try {
                fileOutputStream2 = new FileOutputStream(getAidInfoFile(1));
                try {
                    fileOutputStream2.write(str.getBytes());
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e) {
                        }
                    }
                } catch (Exception e2) {
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e3) {
                        }
                    }
                    return;
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    fileOutputStream = fileOutputStream2;
                    th = th3;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                fileOutputStream2 = null;
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                return;
            } catch (Throwable th4) {
                th = th4;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        }
        return;
    }

    private String getMfp() {
        String str = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDHHM0Fi2Z6+QYKXqFUX2Cy6AaWq3cPi+GSn9oeAwQbPZR75JB7Netm0HtBVVbtPhzT7UO2p1JhFUKWqrqoYuAjkgMVPmA0sFrQohns5EE44Y86XQopD4ZO+dE5KjUZFE6vrPO3rWW3np2BqlgKpjnYZri6TJApmIpGcQg9/G/3zQIDAQAB";
        String str2 = "";
        try {
            str = new String(genMfpString().getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            str = str2;
        }
        LogUtil.d(TAG, "genMfpString() utf-8 string : " + str);
        try {
            str = encryptRsa(str, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDHHM0Fi2Z6+QYKXqFUX2Cy6AaWq3cPi+GSn9oeAwQbPZR75JB7Netm0HtBVVbtPhzT7UO2p1JhFUKWqrqoYuAjkgMVPmA0sFrQohns5EE44Y86XQopD4ZO+dE5KjUZFE6vrPO3rWW3np2BqlgKpjnYZri6TJApmIpGcQg9/G/3zQIDAQAB");
            LogUtil.d(TAG, "encryptRsa() string : " + str);
            return str;
        } catch (Exception e2) {
            LogUtil.e(TAG, e2.getMessage());
            return "";
        }
    }

    private String genMfpString() {
        JSONObject jSONObject = new JSONObject();
        try {
            CharSequence os = getOS();
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put("1", os);
            }
            os = getImei();
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put(FeedbackItem.STATUS_SOLVED, os);
            }
            os = getMeid();
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put(FeedbackItem.STATUS_RECORDED, os);
            }
            os = getImsi();
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put(FeedbackItem.STATUS_SHELVED, os);
            }
            os = getMac();
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put("5", os);
            }
            os = getIccid();
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put("6", os);
            }
            os = getSerialNo();
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put("7", os);
            }
            os = getAndroidId();
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put("10", os);
            }
            os = getCpu();
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put("13", os);
            }
            os = getModel();
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put("14", os);
            }
            os = getSdSize();
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put("15", os);
            }
            os = getResolution();
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put("16", os);
            }
            os = getSsid();
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put("17", os);
            }
            os = getDeviceName();
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put("18", os);
            }
            os = getConnectType();
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put("19", os);
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            return "";
        }
    }

    private String encryptRsa(String str, String str2) throws Exception {
        Throwable th;
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(1, getPublicKey(str2));
        byte[] bytes = str.getBytes("UTF-8");
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            while (true) {
                int splite = splite(bytes, i, 117);
                if (splite == -1) {
                    break;
                }
                try {
                    byte[] doFinal = instance.doFinal(bytes, i, splite);
                    byteArrayOutputStream.write(doFinal);
                    LogUtil.d(TAG, "encryptRsa offset = " + i + "     len = " + splite + "     enBytes len = " + doFinal.length);
                    i += splite;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            byteArrayOutputStream.flush();
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            LogUtil.d(TAG, "encryptRsa total enBytes len = " + toByteArray.length);
            toByteArray = Base64.encodebyte(toByteArray);
            LogUtil.d(TAG, "encryptRsa total base64byte len = " + toByteArray.length);
            String str3 = "01";
            String str4 = "01" + new String(toByteArray, "UTF-8");
            LogUtil.d(TAG, "encryptRsa total base64string : " + str4);
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                }
            }
            return str4;
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e2) {
                }
            }
            throw th;
        }
    }

    private int splite(byte[] bArr, int i, int i2) {
        if (i >= bArr.length) {
            return -1;
        }
        return Math.min(bArr.length - i, i2);
    }

    private PublicKey getPublicKey(String str) throws Exception {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str.getBytes())));
    }

    private String getOS() {
        try {
            return "Android " + VERSION.RELEASE;
        } catch (Exception e) {
            return "";
        }
    }

    private String getImei() {
        try {
            return ((TelephonyManager) this.mContext.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            return "";
        }
    }

    private String getMeid() {
        try {
            return ((TelephonyManager) this.mContext.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            return "";
        }
    }

    private String getImsi() {
        try {
            return ((TelephonyManager) this.mContext.getSystemService("phone")).getSubscriberId();
        } catch (Exception e) {
            return "";
        }
    }

    private String getMac() {
        try {
            WifiManager wifiManager = (WifiManager) this.mContext.getSystemService("wifi");
            if (wifiManager == null) {
                return "";
            }
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            return connectionInfo != null ? connectionInfo.getMacAddress() : "";
        } catch (Exception e) {
            return "";
        }
    }

    private String getIccid() {
        try {
            return ((TelephonyManager) this.mContext.getSystemService("phone")).getSimSerialNumber();
        } catch (Exception e) {
            return "";
        }
    }

    private String getSerialNo() {
        String str = "";
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{"ro.serialno", EnvironmentCompat.MEDIA_UNKNOWN});
        } catch (Exception e) {
            return str;
        }
    }

    private String getAndroidId() {
        try {
            return Secure.getString(this.mContext.getContentResolver(), "android_id");
        } catch (Exception e) {
            return "";
        }
    }

    private String getCpu() {
        try {
            return Build.CPU_ABI;
        } catch (Exception e) {
            return "";
        }
    }

    private String getModel() {
        try {
            return Build.MODEL;
        } catch (Exception e) {
            return "";
        }
    }

    private String getSdSize() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return Long.toString(((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()));
        } catch (Exception e) {
            return "";
        }
    }

    private String getResolution() {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            return new StringBuilder(String.valueOf(String.valueOf(displayMetrics.widthPixels))).append("*").append(String.valueOf(displayMetrics.heightPixels)).toString();
        } catch (Exception e) {
            return "";
        }
    }

    private String getSsid() {
        try {
            WifiInfo connectionInfo = ((WifiManager) this.mContext.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null) {
                return connectionInfo.getSSID();
            }
        } catch (Exception e) {
        }
        return "";
    }

    private String getDeviceName() {
        try {
            return Build.BRAND;
        } catch (Exception e) {
            return "";
        }
    }

    private String getConnectType() {
        String str = PushBuildConfig.sdk_conf_debug_level;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == 0) {
                    switch (activeNetworkInfo.getSubtype()) {
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                        case 11:
                            return "2G";
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                            return "3G";
                        case 13:
                            return "4G";
                        default:
                            return PushBuildConfig.sdk_conf_debug_level;
                    }
                } else if (activeNetworkInfo.getType() == 1) {
                    return "wifi";
                }
            }
            return str;
        } catch (Exception e) {
            return str;
        }
    }
}
