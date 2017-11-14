package com.sds.android.ttpod.framework.a;

import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.cloudapi.ttpod.result.OnlineSkinListResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.ttpod.framework.a;
import com.sds.android.ttpod.framework.modules.skin.m;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

/* SkinUtils */
public class v {
    public static String a(String str, int i) {
        String str2;
        switch (i) {
            case 1:
                str2 = "assets://";
                break;
            case 2:
                str2 = "package://";
                break;
            default:
                str2 = "file://";
                break;
        }
        return str2 + str;
    }

    public static String a(String str) {
        if (str.startsWith("assets://")) {
            return str.substring("assets://".length());
        }
        if (str.startsWith("file://")) {
            return str.substring("file://".length());
        }
        if (str.startsWith("package://")) {
            return str.substring("package://".length());
        }
        return str;
    }

    public static String b(String str) {
        String str2 = "assets://";
        if (str.startsWith("assets://")) {
            return "assets://";
        }
        if (str.startsWith("file://")) {
            return "file://";
        }
        return str2;
    }

    public static String a(String str, final String str2) {
        int i = 0;
        String[] list = new File(str).list(new FilenameFilter() {
            public boolean accept(File file, String str) {
                return str.matches(str2 + "[0-9]+\\.json");
            }
        });
        if (list == null || list.length <= 0) {
            return null;
        }
        String str3;
        Arrays.sort(list);
        if (list.length > 1) {
            while (i < list.length - 1) {
                e.h(str + File.separator + list[i]);
                i++;
            }
            str3 = list[list.length - 1];
        } else {
            str3 = list[0];
        }
        return str + File.separator + str3;
    }

    public static File[] a() {
        File file = new File(a.o());
        if (file.exists() && file.isDirectory()) {
            return file.listFiles(new FilenameFilter() {
                public boolean accept(File file, String str) {
                    return str.toLowerCase(Locale.US).endsWith(".tsk");
                }
            });
        }
        return null;
    }

    public static boolean a(OnlineSkinItem onlineSkinItem) {
        String f = EnvironmentUtils.a.f();
        String version = onlineSkinItem.getVersion();
        if (version == null || version.compareTo(f) <= 0) {
            return true;
        }
        return false;
    }

    public static ArrayList<m> a(OnlineSkinListResult onlineSkinListResult) {
        ArrayList<m> arrayList = new ArrayList();
        if (!(onlineSkinListResult == null || onlineSkinListResult.getSkinItems() == null)) {
            Iterator it = onlineSkinListResult.getSkinItems().iterator();
            while (it.hasNext()) {
                OnlineSkinItem onlineSkinItem = (OnlineSkinItem) it.next();
                if (a(onlineSkinItem)) {
                    onlineSkinItem.setPictureUrl(onlineSkinListResult.getMainUrl() + onlineSkinItem.getRecommendPicUrl());
                    onlineSkinItem.setSkinUrl("http://api.skin.ttpod.com/skin/apiSkin/download?id=" + onlineSkinItem.getId());
                    m mVar = new m(onlineSkinItem);
                    if (e.a(mVar.b())) {
                        mVar.a(0);
                    }
                    arrayList.add(mVar);
                }
            }
        }
        return arrayList;
    }
}
