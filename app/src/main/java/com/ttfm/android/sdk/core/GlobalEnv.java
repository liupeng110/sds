package com.ttfm.android.sdk.core;

import android.os.Environment;

public class GlobalEnv {
    public static String CACHE_IMAGE_FOLDER = (ROOT_DIR + "/TTFM/cache/image/");
    public static final int ChannelList_Default_Items_PerPage = 25;
    public static final String ClassifyChannelKeyWord = "classify_keyword";
    public static final String ClassifyChannelType = "classify_type";
    public static final int ClassifyChannelType_Label = 3;
    public static final int ClassifyChannelType_Name = 2;
    public static final int ClassifyChannelType_NameAndLabel = 1;
    public static final int ClassifyList_Default_Items_PerPage = 100;
    public static String FMCenterUrl = FMDNSCenterUrl;
    public static final String FMChannelDetailUrl = "http://fm.ttpod.com/channel/detail.html";
    public static final String FMDNSCenterUrl = "http://center.tiantianfm.com/";
    public static final String FMDNSDSD = "http://dsd.tiantianfm.com/";
    public static final String FMDNSPlayStream = "http://playstream.tiantianfm.com/";
    public static String FMDSDUrl = FMDNSDSD;
    public static String FMPlaySteam = FMDNSPlayStream;
    public static final String FMTestCenterUrl = "http://192.168.10.21/fmcenter/";
    public static final String FMTestDSD = "http://192.168.10.21/fmdsd/";
    public static final String FMTestPlayStream = "http://192.168.10.27:8001/";
    public static String ROOT_DIR = Environment.getExternalStorageDirectory().toString();
    public static String TTFM_FOLDER = (ROOT_DIR + "/TTFM/");
    public static final String TTFM_MAIN_ACTIVITY = "com.ttpodfm.android.activity.SplashActivity";
    public static final String TTFM_PACKAGE_NAME = "com.ttpodfm.android";
    public static final String TTFM_Select_Hot_More = "hot_channel";
    public static final String TTFM_Select_More_Title = "select_more_title";
    public static final String TTFM_Select_More_Type = "select_more_type";
    public static final String TTFM_Select_New_More = "new_channel";
    public static final String TTFM_Select_Talk_More = "talking_channel";
    public static final String TTFM_idAsc = "idAsc";
    public static final String TTFM_idDesc = "idDesc";
    public static final String TTFM_indexDesc = "indexDesc";
    public static final String TTFM_scoreDesc = "scoreDesc";
}
