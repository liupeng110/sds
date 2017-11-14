package com.sds.android.ttpod.framework.modules.skin.a;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.result.OnlineSkinListResult.OnlineThemeListExtra;
import com.sds.android.sdk.lib.request.BaseResult;
import java.io.Serializable;
import java.util.ArrayList;

/* OnlineCategoryListResult */
public class b extends BaseResult implements Serializable {
    @c(a = "data")
    private ArrayList<a> a;
    @c(a = "ctime")
    private long b;
    @c(a = "extra")
    private OnlineThemeListExtra c;

    public ArrayList<a> a() {
        return this.a;
    }

    public long b() {
        return this.b;
    }

    public String c() {
        return this.c.getPicUrl();
    }
}
