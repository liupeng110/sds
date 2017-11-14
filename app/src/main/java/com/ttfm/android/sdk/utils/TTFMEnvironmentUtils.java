package com.ttfm.android.sdk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.taobao.dp.client.b;
import com.ttfm.android.sdk.cache.ImageCacheHelper;
import java.util.HashMap;

public class TTFMEnvironmentUtils {
    private static int[] Appscreen = new int[]{0, 0};
    private static final int Cachepath_Ver = 0;
    public static final String FOLDER_VER = ".ver";
    private static final String mApp = "ttpod";
    private static int mAppVer = 0;
    private static String mCachepath = "";
    private static Context mContext;
    private static String mDeviceMIEI = "";
    private static String mDeviceName = "";
    private static ImageCacheHelper mImageCache = null;
    private static int mNet = 0;
    private static String mOSName = b.OS;
    private static String mOSVer = "";
    private static HashMap<String, String> mParameters = new HashMap();
    private static int mScreenH = 0;
    private static int mScreenW = 0;

    public static class NetworkType {
        public static final int Net_2G = 2;
        public static final int Net_3G = 3;
        public static final int Net_4G = 4;
        public static final int Net_UNKNOWN = 1;
        public static final int Net_WIFI = 0;
    }

    public static void init(Context context, String str) {
        mContext = context;
        mCachepath = str;
        try {
            initcacheFolder(context, str);
            mDeviceName = Build.MODEL;
            mOSVer = VERSION.RELEASE;
            mDeviceMIEI = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            if (mDeviceMIEI == null) {
                mDeviceMIEI = "";
            }
            Appscreen = getScreenSize(context);
            mScreenW = Appscreen[0];
            mScreenH = Appscreen[1];
            mAppVer = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            setParameters();
            mImageCache = ImageCacheHelper.getInstance();
        } catch (Exception e) {
        }
    }

    public static void finalization() {
        if (mImageCache != null) {
            mImageCache.close();
            mImageCache = null;
        }
    }

    public static void setParameters() {
        mParameters.put("dN", mDeviceName);
        mParameters.put("dId", mDeviceMIEI);
        mParameters.put("osN", mOSName);
        mParameters.put("osV", mOSVer);
        mParameters.put("sW", "" + mScreenW);
        mParameters.put("sH", "" + mScreenH);
        mParameters.put("appV", "" + mAppVer);
        mParameters.put("app", "ttpod");
    }

    public static ImageCacheHelper getImageCacheHelper() {
        if (mImageCache == null) {
            mImageCache = ImageCacheHelper.getInstance();
        }
        return mImageCache;
    }

    public static int[] getScreenSize(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        displayMetrics = context.getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        return new int[]{i, i2};
    }

    public static int getNetworkTypeInteger(Context context) {
        if (context == null) {
            return 1;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return 1;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return 1;
        }
        if (!activeNetworkInfo.isAvailable()) {
            return 1;
        }
        if (activeNetworkInfo.getType() == 1) {
            return 0;
        }
        int networkType = ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
        if (networkType == 8 || networkType == 10 || networkType == 9 || networkType == 5 || networkType == 6 || networkType == 3) {
            return 3;
        }
        return 2;
    }

    public static String getmDeviceName() {
        return mDeviceName;
    }

    public static void setmDeviceName(String str) {
        mDeviceName = str;
    }

    public static String getmDeviceMIEI() {
        return mDeviceMIEI;
    }

    public static void setmDeviceMIEI(String str) {
        mDeviceMIEI = str;
    }

    public static String getmOSName() {
        return mOSName;
    }

    public static void setmOSName(String str) {
        mOSName = str;
    }

    public static String getmOSVer() {
        return mOSVer;
    }

    public static void setmOSVer(String str) {
        mOSVer = str;
    }

    public static int getmScreenW() {
        return mScreenW;
    }

    public static void setmScreenW(int i) {
        mScreenW = i;
    }

    public static int getmScreenH() {
        return mScreenH;
    }

    public static void setmScreenH(int i) {
        mScreenH = i;
    }

    public static int getmNet() {
        return mNet;
    }

    public static void setmNet(int i) {
        mNet = i;
    }

    public static String getmApp() {
        return "ttpod";
    }

    public static int getmAppVer() {
        return mAppVer;
    }

    public static void setmAppVer(int i) {
        mAppVer = i;
    }

    public static HashMap<String, String> getmParameters() {
        if (mParameters != null) {
            mParameters.put("net", String.valueOf(getNetworkTypeInteger(mContext)));
        }
        return mParameters;
    }

    public static void setmParameters(HashMap<String, String> hashMap) {
        mParameters = hashMap;
    }

    public static String getmCachepath() {
        return mCachepath;
    }

    public static void setmCachepath(String str) {
        mCachepath = str;
    }

    public static void initcacheFolder(Context context, String str) {
        checkFolder(context, str, 0);
    }

    private static synchronized void checkFolder(Context context, String str, int i) {
        synchronized (TTFMEnvironmentUtils.class) {
            if (!FileUtils.isExist(str)) {
                initFolder(context, str, i);
            } else if (i != readFolderVer(str + FOLDER_VER)) {
                FileUtils.deleteFile(str, false);
                initFolder(context, str, i);
            }
        }
    }

    private static void initFolder(Context context, String str, int i) {
        FileUtils.createFolder(str, 0);
        createVerFile(str + FOLDER_VER, String.valueOf(i).getBytes(), 1);
    }

    private static int readFolderVer(String str) {
        if (!FileUtils.isExist(str)) {
            return 0;
        }
        byte[] fileData = FileUtils.getFileData(str);
        if (fileData == null || fileData.length <= 0) {
            return 0;
        }
        return Integer.parseInt(new String(fileData));
    }

    public static void createVerFile(String str, byte[] bArr, int i) {
        FileUtils.createFile(str, i);
        FileUtils.rewriteData(str, bArr);
    }
}
