package com.sds.android.sdk.lib.request;

import com.sds.android.sdk.lib.util.EnvironmentUtils.b;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* MethodRequest */
public abstract class k<R extends BaseResult> extends o<R> {
    private static final List<String> c = new ArrayList();
    private static final List<String> d = new ArrayList();
    private String b;

    static {
        c.add("OnlineSongsResult");
        c.add("OnlineMediaItemsResult");
        c.add("OnlineSearchResult");
        c.add("BillboardsResult");
        c.add("AlbumSearchItemsResult");
        c.add("PlaylistResult");
        c.add("MvDataResult");
        c.add("SearchSingerResult");
        d.add("tid");
        d.add("app");
        d.add("s");
        d.add("v");
        d.add("f");
        d.add("alf");
        d.add(ParamKey.UID);
        d.add("imsi");
        d.add("hid");
        d.add("net");
    }

    public k(Class<R> cls, String str, String str2) {
        super(cls, str);
        this.b = str2;
        if (c.contains(cls.getSimpleName())) {
            k();
        }
    }

    private void k() {
        HashMap e = b.e();
        for (String str : d) {
            if (!this.a.containsKey(str)) {
                Object obj = e.get(str);
                if (obj != null) {
                    this.a.put(str, obj);
                }
            }
        }
    }

    public String d() {
        return this.b;
    }
}
