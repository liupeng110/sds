package com.ttfm.android.sdk.storage;

import android.content.Context;
import com.ttfm.android.sdk.utils.FileUtils;
import com.ttfm.android.sdk.utils.TTFMEnvironmentUtils;
import java.io.File;

public class ChannelListDB {
    public static final String CLASSIFYS_FILE = "list_classifys";
    public static final String FEATURED_FILE = "list_featured";

    public static synchronized void saveFeaturedClassifysList(Context context, String str) {
        synchronized (ChannelListDB.class) {
            FileUtils.saveFile(TTFMEnvironmentUtils.getmCachepath() + FEATURED_FILE, str);
        }
    }

    public static String getFeaturedClassifysList(Context context) {
        return FileUtils.readFile(TTFMEnvironmentUtils.getmCachepath() + FEATURED_FILE);
    }

    public static void deleteFeaturedChannelList(Context context) {
        new File(TTFMEnvironmentUtils.getmCachepath() + FEATURED_FILE).delete();
    }

    public static synchronized void saveChannelClassifysList(Context context, String str) {
        synchronized (ChannelListDB.class) {
            FileUtils.saveFile(TTFMEnvironmentUtils.getmCachepath() + CLASSIFYS_FILE, str);
        }
    }

    public static String getChannelClassifysList(Context context) {
        return FileUtils.readFile(TTFMEnvironmentUtils.getmCachepath() + CLASSIFYS_FILE);
    }

    public static void deleteChannelClassifysList(Context context) {
        new File(TTFMEnvironmentUtils.getmCachepath() + CLASSIFYS_FILE).delete();
    }
}
