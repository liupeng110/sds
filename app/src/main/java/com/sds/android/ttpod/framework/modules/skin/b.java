package com.sds.android.ttpod.framework.modules.skin;

import android.text.TextUtils;
import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.cloudapi.ttpod.result.OnlineBackgroundListResult;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.ttpod.framework.a.v;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.c;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* BackgroundListLoader */
public class b implements Runnable {
    private boolean a = false;

    public b(Boolean bool) {
        this.a = bool.booleanValue();
    }

    public void run() {
        if (this.a) {
            a(a(), a.UPDATE_LOCAL_BACKGROUND_LIST);
        } else {
            a(c(), a.UPDATE_BACKGROUND_LIST);
        }
    }

    private void a(ArrayList<com.sds.android.ttpod.framework.modules.theme.a> arrayList, a aVar) {
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(aVar, arrayList), c.THEME);
    }

    private ArrayList<com.sds.android.ttpod.framework.modules.theme.a> c() {
        ArrayList<com.sds.android.ttpod.framework.modules.theme.a> arrayList = new ArrayList();
        String n = com.sds.android.ttpod.framework.a.n();
        n = c.a(n, v.a(n, "list_"), "bkg_list");
        OnlineBackgroundListResult onlineBackgroundListResult = TextUtils.isEmpty(n) ? null : (OnlineBackgroundListResult) f.a(n, OnlineBackgroundListResult.class);
        if (onlineBackgroundListResult == null || onlineBackgroundListResult.getSkinItems() == null) {
            return arrayList;
        }
        ArrayList skinItems = onlineBackgroundListResult.getSkinItems();
        String mainUrl = onlineBackgroundListResult.getMainUrl();
        Iterator it = skinItems.iterator();
        while (it.hasNext()) {
            OnlineSkinItem onlineSkinItem = (OnlineSkinItem) it.next();
            onlineSkinItem.setPictureUrl(mainUrl + onlineSkinItem.getRecommendPicUrl());
            onlineSkinItem.setSkinUrl(mainUrl + onlineSkinItem.getSkinUrl());
            com.sds.android.ttpod.framework.modules.theme.a aVar = new com.sds.android.ttpod.framework.modules.theme.a(onlineSkinItem);
            if (e.a(aVar.h())) {
                aVar.a(com.sds.android.ttpod.framework.modules.theme.a.a.ADD_BY_USER);
            }
            arrayList.add(aVar);
        }
        return arrayList;
    }

    public static ArrayList<com.sds.android.ttpod.framework.modules.theme.a> a() {
        ArrayList<com.sds.android.ttpod.framework.modules.theme.a> arrayList = new ArrayList();
        File file = new File(com.sds.android.ttpod.framework.a.p());
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles(new FilenameFilter() {
                public boolean accept(File file, String str) {
                    return str.toLowerCase(Locale.US).endsWith(".jpg");
                }
            });
            if (listFiles != null && listFiles.length > 0) {
                for (File name : listFiles) {
                    arrayList.add(new com.sds.android.ttpod.framework.modules.theme.a(name.getName(), com.sds.android.ttpod.framework.modules.theme.a.a.ADD_BY_USER));
                }
            }
        }
        return arrayList;
    }

    public static int b() {
        return a().size();
    }
}
